<#assign _jss=['/static/js/plugin/jquery-ui.js?1.10.2','/static/js/underscore-min.js?1.5.1']>
<#assign _title="用户相关分析">
<#include "commons/header.ftl" />
<#assign toolbar="config" />
<#include "commons/toolbar.ftl" />
<script type="text/javascript" src="/static/js/analyse/Chart.js" charset="UTF-8"></script>



<div class="container">
    <div>
        <div>男<span></span></div>
    </div>
    <canvas id="myChart" width="600" height="400"></canvas>


</div>

<script>
    var data = [
        {
            value: 20,
            color:"#F38630"
        },
        {
            value : 30,
            color : "#E0E4CC"
        },
        {
            value : 50,
            color : "#69D2E7"
        }
    ]

    var options =  {
        //Boolean - Whether we should show a stroke on each segment
        segmentShowStroke : true,

        //String - The colour of each segment stroke
        segmentStrokeColor : "#fff",

        //Number - The width of each segment stroke
        segmentStrokeWidth : 2,

        //Boolean - Whether we should animate the chart
        animation : true,

        //Number - Amount of animation steps
        animationSteps : 50,

        //String - Animation easing effect
        animationEasing : "easing",

        //Boolean - Whether we animate the rotation of the Pie
        animateRotate : true,

        //Boolean - Whether we animate scaling the Pie from the centre
        animateScale : false,

        //Function - Will fire on animation completion.
        onAnimationComplete : null
    }
    //Get context with jQuery - using jQuery's .get() method.
    var ctx = $("#myChart").get(0).getContext("2d");
    //This will get the first returned node in the jQuery collection.
    var myNewChart = new Chart(ctx).Pie(data,options);


</script>

<#include "commons/footer.ftl" />