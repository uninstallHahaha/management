$(function () {

    $('.notice-close').unbind('click')
    $('.notice-close').click(function (e) {
        var cuserid = e.target.getAttribute('data-cuserid')
        $.ajax({
            url: '/notice/delNotice/' + cuserid,
            success: function (res) {
                if (res == 1) {
                    layer.msg('删除成功')
                }
            }
        })
    })

})