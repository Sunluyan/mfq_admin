angular.module('poacomponents', [])
    .directive('jsTokenInput', ['$parse', '$timeout', function($parse, $timeout){

        return {
            restrict: 'A',
            require: '?ngModel',
            replace: true,
            link: function (scope, element, attrs) {
                var options = {};

                var options = {
                    theme: "facebook",
                    hintText: "输入关键字搜索",
                    noResultsText: "无匹配的结果",
                    searchingText: "检索中",
                    onResult: function(results){
                        var data = [];
                        $.each(results, function(index, value){
                            data[index] = {
                                'name': value.value,
                                'id': value.poiid
                            }
                        })
                        return data
                    }
                };

                if (attrs.ngModel) {
                    var apply = function (results) {
                        scope.$apply(function applyTokenInputToModels() {
                            var tokenVal = element.tokenInput("get");
                            $parse(attrs.ngModel).assign(scope, tokenVal);
                        });
                    };
                    options.onAdd = apply;
                    options.onDelete = apply;

                    var clear = function(newValue, oldValue) {

                        if (!newValue || newValue.length < 1){
                            $timeout(function(){
                                $(element).tokenInput("clear");
                            });
                        }
                    };

                    scope.$watch(attrs.ngModel, clear);

                    var model = $parse(attrs.ngModel);
                    options.prePopulate = model(scope)
                }

                $(element).tokenInput(function(){
                    return "/trip/poi/suggest/?datatype=0&" + 'r=' + Math.random();
                }, options);

            }
        }

    }])
    .directive('jsDirtTimespace', function(){

        var link = function(scope, element, attrs){

            element.find('input').dateRangePicker({
                language:'cn',
                separator : '-',
                format: 'YYYY/MM/DD',
                getValue: function()
                {
                    //console.log(1+''+this.innerHTML);
                    return this.innerHTML;
                },
                setValue: function(s)
                {
                    //console.log(2+s);
                    //this.innerHTML = s;
                    scope.period = s;
                    scope.$apply();
                }
            });

        }
        return {
            link: link,
            scope: {
                period:'=',
            },
            template: '<div><input type="text" ng-model="period"></div>',
            restrict: 'A'
        }
    })
    .directive('jsDirtDuration', function(){

        var link = function(scope, element, attrs){

            element.find('input').dateRangePicker({
                language:'cn',
                separator : '-',
                format: 'YYYY/MM/DD',
                getValue: function()
                {
                    //console.log(1+''+this.innerHTML);
                    return this.innerHTML;
                },
                setValue: function(s)
                {
                    //console.log(2+s);
                    //this.innerHTML = s;
                    scope.extday.duration = s;
                    scope.$apply();
                }
            });

            scope.dodelete = function(){
                scope.extarr.splice(scope.extarr.indexOf(scope.extday), 1);

            }

            //console.log(scope.markattr);

        }
        return {
            link: link,
            scope: { extday: '=',
                markattr: '@',
                extarr: '=' },
            template: '<div>时间区间：<input type="text" ng-model="extday.duration"> <span style="cursor: pointer;" ng-click="dodelete()">X</span></div>',
            restrict: 'A'
        }
    })

var poaextend = angular.module('poaextend', ['ngRoute', 'poacomponents'])
    .config(function($routeProvider){
        $routeProvider.when('/', {controller: 'MainCtrl', templateUrl: 'main.html'})
    })

    .controller('MainCtrl', function($scope){
        //$scope.sellType = 'period';
        $scope.sell = {poasell: 'year'}
        $scope.sellYear = {
            type: 'year',
            duration: '',
            inMon: true,
            inTue: true,
            inWes: true,
            inThu: true,
            inFri: true,
            inSar: true,
            inSun: true,
            exDate: [
            ]
        }

        $scope.sellPeriod = [
        ]
        
        if(SER_DATA['work_date']){
            var work_date = $.parseJSON(SER_DATA['work_date']);
            if(work_date.length > 0){
                var type = work_date[0]['type'];
                $scope.sell = {poasell: type}
                if(type == 'year'){
                    $scope.sellYear = work_date[0];
                }else{
                    $scope.sellPeriod = work_date;
                }
            }
        } else {
        	$scope.sell = {poasell: 'period'}
        }


        $scope.addDuration = function(arr){

            arr.push({duration: ''})
        }

        $scope.addPreiod = function(){

            if($scope.sell.poasell == 'year'){
                return;
            }

            $scope.sellPeriod.push({
                type: 'period',
                duration: '',
                inMon: true,
                inTue: true,
                inWes: true,
                inThu: true,
                inFri: true,
                inSar: true,
                inSun: true,
                exDate: [],
                //
                specialDate:[],
                duty:'morning'
            });
        }
        
        //
        $scope.addSpecialDate = function(period){
        	if (!period.specialDate) {
        		period.specialDate = []
        	}
        	period.specialDate.push({duration: ''})
        }

        $scope.$watch('sell', function(){

            if($scope.sell.poasell == 'period'){
                $scope.sellYear = {
                    type: 'year',
                    inMon: true,
                    inTue: true,
                    inWes: true,
                    inThu: true,
                    inFri: true,
                    inSar: true,
                    inSun: true,
                    exDate: [
                    ]
                }
            }else{
                $scope.sellPeriod = [];
            }

        }, true)

        $scope.saveOpenDate = function(){

            var arr = [];
            if($scope.sell.poasell == 'period'){
                arr = $scope.sellPeriod;
            }else{
                arr.push($scope.sellYear);
            }

            for(var i=0; i<arr.length; i++){
                var weekArr = [];
                if(arr[i].inMon) weekArr.push(1);
                if(arr[i].inTue) weekArr.push(2);
                if(arr[i].inWes) weekArr.push(3);
                if(arr[i].inThu) weekArr.push(4);
                if(arr[i].inFri) weekArr.push(5);
                if(arr[i].inSar) weekArr.push(6);
                if(arr[i].inSun) weekArr.push(7);

                arr[i]['frequency'] = weekArr.join(',');

                for(var j=0; j<arr[i]['exDate'].length;j++){
                    var temp = arr[i]['exDate'][j]['duration'].split('-');
                    arr[i]['exDate'][j]['periodStart'] = temp[0];
                    arr[i]['exDate'][j]['periodEnd'] = temp[1];
                }
                if(!arr[i]['duration']) arr[i]['duration'] = ''
                var temp = arr[i]['duration'].split('-');
                arr[i]['periodStart'] = !arr[i]['duration']?'':temp[0]
                arr[i]['periodEnd'] = !arr[i]['duration']?'':temp[1]
                //
                arr[i]['duty'] = arr[i].duty
                if (!arr[i]['specialDate']) {
                	arr[i]['specialDate'] = [];
                }
                for(var j=0; j<arr[i]['specialDate'].length;j++){
                	var temp = arr[i]['specialDate'][j]['duration'].split('-');
                    arr[i]['specialDate'][j]['periodStart'] = temp[0];
                    arr[i]['specialDate'][j]['periodEnd'] = temp[1];
                }
            }

            $.ajax({
                url: '/outcall/admin/calendar/',
                type: 'post',
                data: {
                    work_date: angular.toJson(arr),
                    id: SER_DATA['id']
                },
                success: function(resp){
                    alert('保存成功');
                    $scope.ajaxCalData(null, function(){
                    });
                }
            })


        }

        $scope.delList = function(list, item){
        	alert(list.indexOf(item));
            list.splice(list.indexOf(item), 1);
        }

        $scope.delItem = function(list, index){
            list.splice(index, 1);
        }

        var changeCalendar = function(start, end){

            var oStart = new Date(start);
            var oEnd = new Date(end);
            $scope.calendar1Obj.handleChange(oStart.getTime());
            $scope.calendar2Obj.handleChange(oEnd.getTime());
        }


        $scope.ajaxCalData = function(type, callback){

            var start, end;
            if(!$scope.calendar1Obj || !$scope.calendar2Obj){
                var date = new Date();
                var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
                var nextMonthLastDay = new Date(date.getFullYear(), date.getMonth() + 2, 0);

                var mfirst = moment(firstDay);
                var mlast = moment(nextMonthLastDay);

            }else{

                var current = $scope.calendar1Obj.currentTimeSet;
                var mCurrent = moment(current);

                if(type == 'prev'){
                    var firstDay = new Date(current.getFullYear(), current.getMonth() - 2, 1);
                    var nextMonthLastDay = new Date(current.getFullYear(), current.getMonth(), 0);

                    var mfirst = moment(firstDay);
                    var mlast = moment(nextMonthLastDay);
                }else if(type == 'next'){
                    var firstDay = new Date(current.getFullYear(), current.getMonth() + 2, 1);
                    var nextMonthLastDay = new Date(current.getFullYear(), current.getMonth()+4, 0);

                    var mfirst = moment(firstDay);
                    var mlast = moment(nextMonthLastDay);
                }else{
                    var firstDay = new Date(current.getFullYear(), current.getMonth(), 1);
                    var nextMonthLastDay = new Date(current.getFullYear(), current.getMonth()+2, 0);

                    var mfirst = moment(firstDay);
                    var mlast = moment(nextMonthLastDay);

                }

            }

            start = mfirst.format('YYYY/MM/DD');
            end = mlast.format('YYYY/MM/DD');

            $.ajax({
                url: '/outcall/admin/calendar/show/',
                type: 'get',
                data: {
                    userName: SER_DATA['userName'],
                    start: start,
                    end: end,
                    r: Math.random()
                },
                success: function(resp){
                    resp = $.parseJSON(resp)

                    window.calendarData = resp.data;

                    changeCalendar(start, end);
                }
            })
        }


        $scope.renderCalendar = function(){
            var calOpts = {
                fillEventHolder: function($dayholder, $eventholder, day, dayobj){


                    if(!$dayholder.hasClass('other-month')){
                        var data = window.calendarData;
                        if(!data) return;
                        if(data[day]){
                            var arr = data[day];
                            if(arr['work']){
                                $dayholder.addClass('has-sale');

                                if(arr['openTime']){
                                    var str = arr['openTime'].join('<br/>');
                                    if(arr['openTime'].length < 1){
                                        str='未配置时间'
                                    }
                                    $dayholder.append('<div class="sale-tip">'+str+'</div>');
                                }

                            }else{
                                $dayholder.addClass('no-sale');
                            }

                            $dayholder.append();
                        }
                    }else{
                        $dayholder.find('.date-holder').html('&nbsp;');
                    }



                },
                onDayClick: function(e){
                },
                monthHuman: [['一月','一月'],['二月','二月'],['三月','三月'],['四月','四月'],['五月','五月'],['六月','六月'],['七月','七月'],['八月','八月'],['九月','九月'],['十月','十月'],['十一月','十一月'],['十二月','十二月']],
                dayHuman: [['日','星期日'],['一','星期一'],['二','星期二'],['三','星期三'],['四','星期四'],['五','星期五'],['六','星期六']],
                color: "red",
                firstDayOfWeek: "Sunday"
            }
            var calendar1 = $('.price-calendar1'), calendar2 = $('.price-calendar2');
            calendar1.kalendar(calOpts);
            calendar2.kalendar(calOpts);

            calendar1Obj = calendar1.data('kalendar-instance');
            calendar2Obj = calendar2.data('kalendar-instance');

            var tempday = new Date();
            calendar1Obj.handleChange(tempday.getTime());
            tempday.setDate(1);
            tempday.setMonth(calendar1Obj.currentMonth+1);
            calendar2Obj.handleChange(tempday.getTime());

            $scope.calendar1Obj = calendar1Obj;
            $scope.calendar2Obj = calendar2Obj;

        }


        $scope.prevDates = function(){

            if(!$scope.calendar1Obj || !$scope.calendar2Obj) return;

            $scope.ajaxCalData('prev', function(){
            })

        }

        $scope.nextDates = function(){
            if(!$scope.calendar1Obj || !$scope.calendar2Obj) return;
            $scope.ajaxCalData('next', function(){
            })

        }

        $(function(){
            $scope.renderCalendar();
            $scope.ajaxCalData(null, function(){
            });
        })


    })

$(function(){
    angular.bootstrap(document, [poaextend.name]);
})









