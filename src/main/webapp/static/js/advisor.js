'use strict';
var isOldTitle = true;
var oldTitle = "旅行小邦手";
var newTitle = "";
var interval = null;
function changeTitle() {
    document.title = isOldTitle ? oldTitle : newTitle;
    isOldTitle = !isOldTitle;
}


$(window).focus(function () {
    if(interval) clearInterval(interval);
    $("title").text(oldTitle);
});

var blink = function(){

    newTitle = "...有新的动态";
    interval = setInterval(changeTitle, 500);    
}

angular.module('advisor', []).directive('topicItem', function(){
    
    return {
        restrict: 'A',
        controller: function($scope, $element){
            if(!$scope.item.time) $scope.item.time = 0;
            $scope.countDown = countdown($scope.item.time, countdown.SECONDS).toString();

            $scope.$on('hideItem', function(event, track){
               
                if($scope.item.hide){
                    $element.fadeOut('fast', function(){
                        $element.hide();
                    })
                }
            })

            var chooseColor = function($scope){
                var color = '';                
                if($scope.item.status == '等待用户回应'){
                    color = '#40f551';
                }else if($scope.item.time > 10*1000){
                    color = 'red';
                }else{
                    color = 'yellow';
                }
                return color;
            }
            $scope.color = chooseColor($scope);

            if($scope.item.time < 30*60*1000){
           
                var timer = window.setInterval(function(){
                    $scope.item.time +=1000;
                    $scope.countDown = countdown($scope.item.time, countdown.SECONDS).toString();
                    $scope.color = chooseColor($scope);

                    $scope.$apply();

                    if($scope.item.time > 30*60*1000){
                        clearInterval(timer);
                        //$scope.countDown = countdown($scope.item.time, countdown.SECONDS).toString();
                    }
                }, 1000)
            }
        }
    }
});

function advisorList($scope){

    //PushStream.LOG_LEVEL = 'debug'; 
    var pushstream = new PushStream({
        host: 'comet.shijiebang.com',
        post: 80,
        modes: "websocket|eventsource|stream"
    });

    var  _connect = function(channel){
        pushstream.removeAllChannels(); 
        try {
            pushstream.addChannel(channel);
            pushstream.connect();
        }catch(e){
            alert(e);
        }
    }

    var parseItem = function(item){
       
        if(item.status == 'WaitUser'){
            item.status = '等待用户回应';
        }else{
            item.status = '该小邦回应';
        }
        if(item.selfIp){
            item.ip='<span style="color: #808080">内部ip'+item.ip+'</span>'
        }else{
            item.ip='<a href="http://ip138.com/ips138.asp?ip='+item.ip+'&action=2" target="_blank">'+item.ip+'</a>'
        }

        return item;
    }

    var items = sessions.sessions;
    for(var i=0;i<items.length;i++){
        items[i] = parseItem(items[i]);
        items[i]['hide'] = false;
        items[i]['nick'] = items[i]['nick']?items[i]['nick']:'';
    }

    $scope.List = items;

    pushstream.onmessage = function(message){
        var resp = $.parseJSON(message),
            item = parseItem(resp.detail);
        var results = Enumerable.from($scope.List).where("$.tsid == '"+item.tsid+"'").toArray(); 

        var current = null;
        if(results.length > 0){
            current = results[0];
            var key;
            for(key in current){
                current[key] = item[key]?item[key]:current[key];
            }

            if(resp.systemType == 'SessionRemove'){
                current['hide'] = true;

                $scope.$broadcast('hideItem')
            }

        }else if(resp.systemType == 'SessionAdd'){
            $scope.List.unshift(item);
            blink();
        }
        $scope.$apply();

    }

    _connect('system');
}
