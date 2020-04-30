$(function () {

    var newuser;

    $('#newuserbtn').click(function () {
        newuser = layer.open({
                title: false,
                type: 1,
                closeBtn: 1,
                anim: 2,
                area: ['500px', '300px'],
                shadeClose: true,
                content: $('#newUser')
            });
        }
    )

    $('#subnewuser').click(
        function () {
            var newusername = $('#newusername').val()
            var newuserpass = $('#newuserpass').val()
            var newuserrepass = $('#newuserrepass').val()
            if(newusername == "" || newuserpass==""||newuserrepass==""){
                layer.msg('请完善信息')
                return
            }
            if (newuserpass != newuserrepass) {
                layer.msg('两次输入密码不一致')
                return
            }
            if (newuserpass.length < 8) {
                layer.msg('密码长度不能少于8位')
                return
            }
            $.ajax({
                url: '/signup',
                method: 'post',
                data: {name: newusername, pass: newuserpass, role: 2},
                success: function (res) {
                    if (res.stat == 1) {
                        layer.msg('注册成功')
                        location.href = "/getUserManagePage"
                    } else {
                        layer.msg(res.data)
                    }
                }
            })
        }
    )


    $('#cancelnewuser').click(function () {
        layer.close(newuser)
    })
})