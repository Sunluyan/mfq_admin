;(function($, window, document, undefined) {
    var defaults = {
        startMonth: new Date().getMonth(),
        startYear: new Date().getFullYear(),
        firstDayOfWeek: "Monday",
        events: [],
        color: "red",

        showdays: true,
        tracking: true,
        template: 	'<div class="c-month-view">'+
            '<div class="c-month-arrow-left" style="display: none"><i class="fcon-arrow-l"></i> 前一个月</div>'+
            '<p class="f14 fb"></p>'+
            '<div class="c-month-arrow-right" style="display: none">后一个月 <i class="fcon-arrow-r"></i></div>'+
            '</div>'+
            '<div class="c-holder">'+
            '<div class="c-grid"></div>'+
            '<div class="c-specific">'+
            '<div class="specific-day">'+
            '<div class="specific-day-info" i="date"></div>'+
            '<div class="specific-day-info" i="day"></div>'+
            '</div>'+
            '<div class="s-scheme"></div>'+
            '</div>'+
            '</div>',
        calendar_elements: {
            monthShow: '.c-month-view p',
            prevMonth: '.c-month-arrow-left',
            nextMonth: '.c-month-arrow-right',
            grid: '.c-grid',
            specday_trigger: ".specific-day",
            specday_day: ".specific-day-info[i=day]",
            specday_date: ".specific-day-info[i=date]",
            specday_scheme: ".s-scheme"
        },
        monthHuman: [["JAN","January"],["FEB","February"],["MAR","March"],["APR","April"],["MAY","May"],["JUN","June"],["JUL","July"],["AUG","August"],["SEP","September"],["OCT","October"],["NOV","November"],["DEC","December"]],
        dayHuman: [["S","Sunday"],["M","Monday"],["T","Thursday"],["W","Wednesday"],["T","Thursday"],["F","Friday"],["S","Saturday"]],
        dayMachine: function(s) {
            var a = [];a["Sunday"] = 0;a["Monday"] = 1;a["Tuesday"] = 2;a["Wednesday"] = 3;a["Thursday"] = 4;a["Friday"] = 5;a["Saturday"] = 6;
            return a[s];
        },
        urlText: "View on Web",
        onInitiated: function() { },
        onGoogleParsed: function() { },
        onMonthChanged: function() {},
        onDayShow: function() { },
        onGridShow: function() { },
        onDayClick: function(e) {}
    }
    function kalendar(element, options) {
        this.options = $.extend(true, {}, defaults, options);
        this.element = element;

        this.currentMonth = this.options.startMonth;
        this.currentYear = this.options.startYear;
        this.currentTimeSet = new Date(this.options.startYear, this.options.startMonth);

        this.firstDayOfWeek = [this.options.dayMachine(this.options.firstDayOfWeek), this.options.firstDayOfWeek];

        this.currentTime = new Date();
        this.currentTime.setHours(0,0,0,0);
        this.init();
    }

    kalendar.prototype.setMaxAndMin = function(min, max){

        this.max = max;
        this.min = min;


    }


    kalendar.prototype.init = function() {
        this.element.html(this.options.template);
        this.element.attr('ewcalendar','');
        this.element.attr('color', this.options.color);
        this.elements = {};
        for(var ele in this.options.calendar_elements) {
            this.elements[ele] = this.element.find(this.options.calendar_elements[ele]);
        }
        this.setMonth();
        var self = this
        if(this.options.hidePrev)  this.elements.prevMonth.hide();
        if(this.options.hideNext)   this.elements.nextMonth.hide();
        this.elements.prevMonth.on('click', {"self": this, "dir": "prev"}, function(e){
            if(self.options.onprev){
                self.options.onprev(e)
            }

            self.changeMonth(e);
        });
        this.elements.nextMonth.on('click', {"self": this, "dir": "next"}, function(e){
            if(self.options.onnext){
                self.options.onnext(e)
            }

            self.changeMonth(e);
        });
        this.options.onInitiated();
    }

    kalendar.prototype.checkMonth = function(){


        if(this.min && !this.options.hidePrev){
            var min = new Date(this.min);
            if(this.currentMonth == min.getMonth()){
                this.elements.prevMonth.hide();
            }else{
                this.elements.prevMonth.show();
            }
        }

        if(this.max && !this.options.hideNext){
            var max = new Date(this.max);
            if(this.currentMonth == max.getMonth()){
                this.elements.nextMonth.hide();
            }else{
                this.elements.nextMonth.show();
            }
        }
    }

    kalendar.prototype.changeMonth = function(e) {
        var self = e.data.self;
        var dir = e.data.dir;
        self.currentMonth += dir == 'prev' ? -1 : 1;
        self.currentTimeSet = new Date(self.currentYear, self.currentMonth);
        self.currentMonth = self.currentTimeSet.getMonth();
        self.currentYear = self.currentTimeSet.getFullYear();
        self.setMonth();
    }
    kalendar.prototype.handleChange = function(time){
        var temp = new Date(time)
        this.currentTimeSet = new Date(temp.getFullYear(), temp.getMonth());
        this.currentMonth = this.currentTimeSet.getMonth();
        this.currentYear = this.currentTimeSet.getFullYear();
        this.setMonth();
    },
        kalendar.prototype.setMonth = function() {
            this.checkMonth();
            var $grid = this.elements.grid;
            $grid.html('');
            this.elements.monthShow.html(this.currentTimeSet.getFullYear()+'年 '+this.options.monthHuman[this.currentTimeSet.getMonth()][1]);

            if(this.options.showdays) {
                $dayView = $('<div class="c-row"></div>');
                for(var i=0;i<7;i++) {
                    var id = this.firstDayOfWeek[0] + i;
                    id -= id > 6 ? 7 : 0;
                    $dayView.append('<div class="c-day c-l"><div class="date-holder">'+this.options.dayHuman[id][0]+'</div></div>');
                }
                $grid.append($dayView);
            }

            var tempDate = new Date(this.currentTimeSet),
                diffdays = tempDate.getDay() - this.firstDayOfWeek[0];
            diffdays += diffdays < 1 ? 7 : 0;
            tempDate.setDate(tempDate.getDate() - diffdays);
            for(var i=0;i<42;i++) {
                if(i==0 || i%7==0) {
                    $row = $('<div class="c-row"></div>');
                    $grid.append($row);
                }
                $day = $('<div class="c-day"><div class="date-holder">'+tempDate.getDate()+'</div></div>');
                var strtime = dToFormat(tempDate, "YYYYMMDD");
                var myday = dToFormat(tempDate, "YYYY-MM-DD");
                if(tempDate.getMonth() !== this.currentTimeSet.getMonth()) {
                    $day.addClass('other-month');
                    $day.on('click', { "info": "other-month", 'date': myday, ele: $day}, this.options.onDayClick);
                } else if(tempDate.getTime() == this.currentTime.getTime()) {
                    $day.addClass('this-day');
                    $day.on('click', { "info": "this-day", 'date': myday, ele: $day}, this.options.onDayClick);
                } else {
                    $day.on('click', { "info": "this-month", 'date': myday, ele: $day}, this.options.onDayClick);
                }

                $day.addClass('have-events');
                $eventholder = $('<div class="event-n-holder"></div>');
                if(this.options.fillEventHolder){
                    this.options.fillEventHolder($day, $eventholder, myday, tempDate)
                }

                //$day.on('click', { "day": this.options.eventsParsed[strtime], "self": this, "date": tempDate.getTime(), "strtime": strtime}, this.showDay);
                $day.append($eventholder);
                $row.append($day);
                tempDate.setDate(tempDate.getDate() + 1);
            }
            this.options.onMonthChanged();
        }


    $.fn.kalendar = function(options) {
        return this.each(function() {
            options.source = "JS";
            var kalendar_instance = new kalendar($(this), options);
            $(this).data('kalendar-instance', kalendar_instance);
        });
    }
    function dToFormat(d,f) {
        var ff = function(d) {
            return d<10?0+''+d:d;
        }
        if(f == "YYYYMMDD") {
            var year = d.getFullYear(),
                month = ff(d.getMonth()+1),
                date = ff(d.getDate());
            return year+''+month+''+date;
        } else if(f == "YYYY-MM-DD"){
            var year = d.getFullYear(),
                month = ff(d.getMonth()+1),
                date = ff(d.getDate());
            return year+'-'+month+'-'+date;

        }else if(f == "HH.MM") {
            var hr = ff(d.getHours()+1),
                min = ff(d.getMinutes()+1)
            return hr+'.'+min;
        }
    }

    function formatToD(s,f) {
        if(f == "YYYYMMDD") {
            s = s.toString();
            d = new Date();
            d.setYear(s.substring(0,4));
            d.setMonth(s.substring(4,6)-1);
            d.setDate(s.substring(6,8));
        } else if(f == "YYYYMMDDHHMM") {
            d = new Date();
            st = s[0].toString();
            d.setYear(st.substring(0,4));
            d.setMonth(st.substring(4,6)-1);
            d.setDate(st.substring(6,8));
            st = s[1].toString();
            st = st.split(".")[0].length < 2 ? "0"+st : st;
            d.setHours(st.substring(0,2));
            d.setMinutes(st.substring(3,5));
            d.setSeconds(00);
        }
        return d;
    }
})(jQuery, window, document);