function set_status() {
    var status = $("#user-status").val();
    var txt = status;
    var delete_data = "15";
    var delete_txt = "删除用户";
    var active_data = "0";
    var active_txt = "激活用户";
    if("0" == status) {
        txt = "已激活";
        active_data = "1";
        active_txt = "取消激活";
    } else if("1" == status) {
        txt = "未激活";
        active_data = "0";
        active_txt = "激活用户";

    } else if("15" == status) {
        txt = "已删除";
        delete_data = "0";
        delete_txt = "恢复用户";
    }
    $("#user-status-msg").text(txt);
    $("#btn-delete-user").text(delete_txt).attr("data-status", delete_data);
    $("#btn-active-user").text(active_txt).attr("data-status", active_data);
}

function update_status(event) {
    var target_status = $(event.target).attr("data-status");
    var isdelete = "btn-delete-user" == $(event.target).attr("id");

    $.post("/search/user/", {
        _method: "DELETE",
        uid: $("#uid").val(),
        status: target_status
    }, function(data) {
        if("true" == data) {
            $("#user-status").val(target_status);
            set_status();
        } else {
            alert("操作失败。刷新重新操作吧。");
        }
    });

}
$("#btn-delete-user").click(update_status);
$("#btn-active-user").click(update_status);
$("#btn-password").click(function() {
    var uid = $("#uid").val();
    var password = $("#password").val();
    if(password.length < 6) {
        alert("密码至少6位");
        return;
    }
    $.post("/search/user/password/", {
        uid: uid,
        password: password
    }, function(data) {
        var msg = "true" == data ? "修改密码成功" : "修改密码失败";
        $("#msg").text(msg);
        $("#well").removeClass("hidden");
        alert(msg);
    });
});

$("#btn-delete-pic").click(function() {
    $.post("/search/user/avatar/", {
        _method: "DELETE",
        uid: $("#uid").val()
    }, function(data) {
        if("true" == data) {
            $("#user-pic").attr("src", "http://s2.sjbly.cn/img/avt_180.png");
        } else {
            alert("操作失败");
        }
    });
});

set_status();