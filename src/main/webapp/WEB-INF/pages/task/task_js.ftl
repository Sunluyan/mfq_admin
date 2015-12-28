<script type="text/javascript">

    function taskDetail(doyenId,taskSessionId){
        if(!confirm("亲，您正在以达人视角查看任务需求，您能保证只是看看不做任何修改和不黏贴打开的url给外部达人吗？")){
            return;
        }
        var url="http://www.shijiebang.com/u"+doyenId+"/tasksession-"+taskSessionId+"/?sale=true";
        window.open(url);

    }


    function delayTime(taskSessionId) {

        if(!confirm("确定要延期吗")){
            return;
        }
        var hours=$('#hours_'+taskSessionId).val()  ;

        if(hours==0){
            alert("起码要一小时呀，亲");
            return;
        }
        $.post(
                "/a/advisor/task/",
                {task_s_id: taskSessionId, op: "delay",hours:hours},
                function (result) {
                    window.location.href="/advisor/task/?sign=${sign}&num=${num}&status=${status}";
                }
        );
    }


    function dis(taskSessionId,taskId,tsid,uid) {

        var doyenUid=$('#doyen_'+taskSessionId).val()  ;

        if(doyenUid==0){
            alert("请填写达人的uid");
            return;
        }
        $.post(
                "/a/advisor/task/",
                {taskId: taskId,tsid:tsid,uid:uid ,doyenUid:doyenUid,op: "dis"},
                function (result) {
                    if(result=='true'){
                        alert('已分配');
                        window.location.href="/advisor/task/?sign=${sign}&num=${num}&status=${status}";

                    }else{
                        alert(result);
                    }
                }
        );
    }


    $('a[data-poload]').hover(function() {
        var e=$(this);
        e.unbind('hover');
        $.get(e.data('poload'),function(d) {
            e.popover({content: d,placement:"left",trigger:"hover",html:"html"}).popover('show');
        });
    });


    function terminal(taskSessionId) {

        if(!confirm("确定要关闭此达人的任务吗")){
            return;
        }
        $.post(
                "/advisor/task/session/",
                {taskSId: taskSessionId},
                function (result) {
                    window.location.href="/advisor/task/?sign=${sign}&num=${num}&status=${status}";
                }
        );
    }

</script>