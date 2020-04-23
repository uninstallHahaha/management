$(function () {

    $('#joinBtn').click(function (e) {
        var projid = e.target.getAttribute("data-projid")
        $.ajax({
            url: '/modProjCount',
            data: {projid: projid},
            method: 'post',
            async: false,
            success: function (res) {
                if (res == 1) {
                    layer.msg('参与成功')
                    $('#joinCount').text(parseInt($('#joinCount').text()) + 1)
                    $('#joinCount1').text(parseInt($('#joinCount1').text()) + 1)
                    $(e.target).addClass('disabled')
                    $(e.target).text('已参与')
                    $(e.target).unbind('click')
                } else {
                    layer.msg('参与失败')
                }
            }
        })
    })


    $('#determineBtn').click(function (e) {
        layer.confirm('确定终止项目?', function () {
            var projid = e.target.getAttribute("data-projid")
            $.ajax({
                url: '/stopProj',
                data: {projid: projid},
                method: 'post',
                async: false,
                success: function (res) {
                    if (res == 1) {
                        layer.msg('终止成功')
                        $(e.target).addClass('disabled')
                        $(e.target).text('已终止')
                        $(e.target).unbind('click')
                        $('#projStateSpan').text('已经结束')
                        $('#projStateBar').attr('valuenow', 100)
                        $('#projStateBar').css('width', '100%')
                    } else {
                        layer.msg('终止失败')
                    }
                }
            })
        })
    })


    $('.delComBtn').click(function (e) {
        layer.confirm('确定删除留言?', function () {
            var mesid = e.target.getAttribute("date-commentid")
            $.ajax({
                url: '/delMessage',
                data: {mesid: mesid},
                method: 'post',
                async: false,
                success: function (res) {
                    if (res == 1) {
                        layer.msg('删除成功')
                        e.target.parentElement.parentElement.parentElement.remove()
                    } else {
                        layer.msg('删除失败')
                    }
                }
            })
        })
    })


})