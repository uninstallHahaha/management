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


    $('#pushNoticeBtn').click(function () {
        var title = $('#noticeTitle').val()
        var content = $('#noticeContent').val()
        if(title == "" || content == ""){
            layer.msg('标题或内容不能为空')
            return
        }
        $.ajax({
            url: '/addNotice',
            data: {title: title, content: content},
            success: function (res) {
                if (res == 1) {
                    layer.msg('发布成功')
                    location.href = "/getAllNoticePage"
                } else {
                    layer.msg('发布失败')
                }
            }
        })
    })


})

