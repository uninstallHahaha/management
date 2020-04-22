$(function () {
    $('#signinbtn').click(function () {
        var name = $('#name').val()
        var pass = $('#pass').val()
        if (name == "" || pass == "") {
            layer.msg('账号或密码不能为空')
            return
        }
        $.ajax({
            url: '/signin',
            method:'post',
            data: {name: name, pass: pass},
            success: function (res) {
                if (res.stat == 1) {
                    layer.msg('登录成功')
                    location.href = "/index"
                } else {
                    layer.msg(res.data)
                }
            }
        })
    })
})