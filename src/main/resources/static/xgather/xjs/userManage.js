function delUser(id) {
    layer.confirm('确定删除?', function () {
        $.ajax({
            url: '/delUser',
            data: {id: id},
            method:'post',
            success: function (res) {
                if (res == 1) {
                    layer.msg('删除成功')
                    location.href="/getUserManagePage"
                } else {
                    layer.msg('删除失败')
                }
            }
        })
    })
}