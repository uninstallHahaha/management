$(function () {
    $('#signupbtn').click(function () {
        var name = $('#name').val()
        var pass = $('#pass').val()
        var repass = $('#repass').val()
        if (name == "" || pass == "" || repass == "") {
            layer.msg('账号或密码不能为空')
            return
        }
        if (name.length < 4 || name.length > 20) {
            layer.msg('账号长度应当为4~20')
            return;
        }
        if (pass.length < 8 || pass.length > 20) {
            layer.msg('密码长度应当为8~20')
            return
        }
        if (pass != repass) {
            layer.msg('两次输入的密码不一致')
            return
        }
        $.ajax({
            url: '/signup',
            method: 'post',
            data: {name: name, pass: pass, role: 1},
            success: function (res) {
                if (res.stat == 1) {
                    layer.msg('注册成功,已为您自动登录')
                    location.href = "/index"
                } else {
                    layer.msg(res.data)
                }
            }
        })
    })
})