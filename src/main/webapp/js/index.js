
function updatePassword() {
    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: "修改密码",
        area: ['550px', '270px'],
        shadeClose: false,
        content: jQuery("#passwordLayer"),
        btn: ['修改','取消'],
        btn1: function (index) {
            var data = "password="+ $("#password").val()+"&newPassword="+$("#newPassword").val();
            console.log("kk:"+data);
            $.ajax({
                type: "POST",
                url: "member/password",
                data: data,
                dataType: "json",
                success: function(result){
                    if(result.code == 0){
                        layer.close(index);
                        layer.alert('修改成功', function(index){
                            location.reload();
                        });
                    }else{
                        layer.alert(result.msg);
                    }
                }
            });
        }
    });
}