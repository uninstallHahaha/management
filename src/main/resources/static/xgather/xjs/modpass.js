$(function () {

    var modpass;

    $('#modpassbtn').click(function () {
            modpass = layer.open({
                title: false,
                type: 1,
                closeBtn: 1,
                anim: 2,
                area: ['500px', '300px'],
                shadeClose: true,
                content: $('#modpasslayer')
            });
        }
    )

    $('#submodpass').click(
        function () {
            var oldpass = $('#oldpass').val()
            var pass = $('#pass').val()
            var repass = $('#repass').val()
            if(oldpass == "" || pass==""||repass==""){
                layer.msg('请完善信息')
                return
            }
            if (pass != repass) {
                layer.msg('两次输入密码不一致')
                return
            }
            if (pass.length < 8) {
                layer.msg('新密码长度不能少于8位')
                return
            }
            $.ajax({
                url: '/modPass',
                data: {oldpass: oldpass, pass: pass},
                method: 'post',
                success: function (res) {
                    if (res.stat == 0) {
                        layer.msg(res.data)
                    } else {
                        layer.msg(res.data)
                        layer.close(modpass)
                    }
                }

            })
        }
    )


    $('#cancelmodpass').click(function () {
        layer.close(modpass)
    })
})