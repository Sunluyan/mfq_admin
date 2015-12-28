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


angular.module('answerApp', []).directive('sessionItem', function(){
    return {
        restrict: 'A',
        controller: function($scope, $element){

        }
    }
});

function answerList($scope){

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

        return item;
    }

    $scope.List = [];
    $scope.List = sessionQuestions.questions;
    var bindUser = $('#bind-user');
    pushstream.onmessage = function(message){
        var resp = $.parseJSON(message),
            item = resp.detail;
        var results = Enumerable.from($scope.List).where("$.qid == '"+item.qid+"'").toArray(); 

        if(item.tsid != SESSION_ID) return;

        var current;
        if(resp.systemType == 'AdvisorOneSession'){
            if(results.length > 0){
                current = results[0];
                var key;
                for(key in current){
                    current[key] = item[key]?item[key]:current[key];
                }

            }else{
                $scope.List.push(item);
            }

            blink();
        }

        if(resp.systemType == 'BandUser'){
            bindUser.html('用户：uid'+item.uid+', '+'手机号： '+item.mobile+' 邮件：'+item.email+'');
            blink();
        }


        $scope.$apply();

    }

    _connect('system');
}
