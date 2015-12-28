var topic = (function () {
    var section = {};
    //多标签图文混排区域

//    section['img_text'] = img_text;
    //

    return section;
})();

$(function(){
    $(".imgtooltip").hover(
        function(e){

            var imgurl = $.trim($(this).prev().val());

            $("body").append("<div id='imgtooltip'><img src="+ imgurl +"></div>")
            $("#imgtooltip").css({
                left: e.pageX+20+"px",
                top: e.pageY+20+"px"
            }).show("fast")
        },
        function(){
            $("#imgtooltip").remove()
        }
    ).mousemove(function(e){$("#imgtooltip").css({
            left: e.pageX+20+"px",
            top: e.pageY+20+"px"
        })})
});

