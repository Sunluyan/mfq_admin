$(function () {

    var Agreeflag = true;
    $('.agree').click(function (event) {
        if (Agreeflag == true) {
            $('.shenpi-information').css({
                display: 'block',

            });
            Agreeflag = false;

        } else {
            $('.shenpi-information').css({
                display: 'none',

            });
            Agreeflag = true;
        }
    });

    var refuseFlag = true;
    $('.refuse').click(function (event) {
        if (refuseFlag == true) {
            $('.refuse-information').css({
                display: 'block',

            });
            refuseFlag = false;

        } else {
            $('.refuse-information').css({
                display: 'none',

            });
            refuseFlag = true;
        }
    });

    $('.tijiao').click(function (event) {
        $('.shenpi-information').css({
            display: 'none',

        });
    });
    $('.tijiao-2').click(function (event) {
        $('.refuse-information').css({
            display: 'none',

        });
    });
    $('.quxiao').click(function (event) {
        $('.refuse-information').css({
            display: 'none',

        });
        $('.shenpi-information').css({
            display: 'none',
        });

        Agreeflag = true;
        refuseFlag = true;
    });

    var uploadIsClicked = false;
    $(".upload").click(function () {
        uploadIsClicked = true;

        var content = $(".group-span-filestyle").next().val()
        if (content == null || content == "") {
            return;
        }
        var formData = new FormData($(".uploadForm")[0]);
        var $this = $(this);
        $.ajax({
            url: '/user/interview/uploadimg/',
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            success: function (img) {
                $this.parent().parent().parent().find(".img").attr("src", img);

            },
            error: function (returndata) {
                alert("失败啦")
            }
        });
        return false;
    })

    function updateInterview(img, desc, remark, uid, id) {

        $.ajax({
            url: "/ajax",
            data: {
                method: "addInterviewRemark",
                id: id,
                uid: uid,
                desc: desc,
                remark: remark,
                img: img
            },
            type: "post",
            success: function (data) {
                console.log(data)
            }
        })
    }


    $(".loading").ajaxStart(function () {
        $(this).show();
    })
    $(".loading").ajaxStop(function () {
        $(this).hide();
    })


    $(".img").click(function () {
        if (this.className.indexOf("layui-layer-wrap") != -1) {
            window.location.href = $(this).attr("src")
        }
        var img = $(this).css("width", "auto");
        layer.open({
            type: 1,
            title: false,
            closeBtn: 0,
            area: ['516px', '516px'],
            skin: 'layui-layer-nobg', //没有背景色
            shadeClose: true,
            content: img
        });
        return false;
    })
    $(document).click(function () {
        $(".img").show().css("width", 150);
    })

    $(".add-tr").click(function () {
        var $this = $(this)
        var desc = $this.parent().parent().find(".desc").val().trim()
        var uid = $(".uid").html();
        var remark = $this.parent().parent().find(".remark").val().trim();
        var img = $this.parent().parent().find(".img").attr("src").trim()
        addInterview(img, desc, remark, uid, 0);

    })
    function addInterview(img, desc, remark, uid, id) {
        if (uploadIsClicked == false) {
            alert("请上传图片!")
            return false;
        }
        $.ajax({
            url: "/ajax",
            data: {
                method: "addInterviewRemark",
                id: id,
                uid: uid,
                desc: desc,
                remark: remark,
                img: img
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data.code == 0) {
                    var $tr = $(".tr").last().clone(true);
                    console.log($tr)
                    if($tr.length == 0){
                        window.location.reload()
                    }
                    $tr.find(".desc").val(desc)
                    $tr.find(".remark").val(remark)
                    $tr.find(".img").attr("src", img)
                    $(".tr").last().after($tr)
                    uploadIsClicked = false;
                }
            }
        })
    }

    $(".delete").click(function () {
        var id = $(this).attr("data");
        $.ajax({
            url: "/ajax",
            data: {
                method: "delInterview",
                id: id
            },
            dataType: "json",
            type: "post",
            success: function (json) {
                if (json.code != 0) {
                    alert("删除失败")
                } else {
                    $(".delete[data='" + id + "']").parent().parent().remove()
                }
            }
        })
    })


    $(".tijiao").click(function(){
        
        fm = $("#frm-shenpi").serialize()
        $.ajax({
            url:"/ajax",
            data:fm,
            dataType:"json",
            type:"post",
            success:function(data){
                if(data.code == 0){
                    alert("成功....");
                    location.reload();
                }else{
                    alert("系统错误啦. code "+data.code+", msg "+data.msg)
                }
            }
        })

    });

    $(".tijiao-2").click(function(){
        fm = $("#frm-refuse").serialize();
        $.ajax({
            url:"/ajax",
            data:fm,
            dataType:"json",
            type:"post",
            success:function(data){
                if(data.code == 0){
                    alert("成功...")
                    location.reload();
                }else{
                    alert("系统错误啦. code "+data.code+", msg "+data.msg)
                }
            }
        })

    })



});