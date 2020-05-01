function tosave(){
    var ts = new Array()
    var rs = layui.transfer.getData('demo1')
    rs.forEach(item=>{
        ts.push(item.value)
    })
debugger
    $.ajax({
        url: "/databasebackup",
        type: "post",
        data: {pass: '123123',ts:ts},
        datatype: "json",
        success: function(result){
            if(result=="success"){
                layer.msg('数据备份成功')
            }
            else{
                layer.msg('数据备份失败')
            }
        }
    })
}



$(function () {
    layui.use('transfer', function () {
        var transfer = layui.transfer;
        //渲染
        transfer.render({
            elem: '#test1'  //绑定元素
            , data: [
                {"value": "achi", "title": "achi: 学术成果表", "disabled": "", "checked": ""}
                , {"value": "log", "title": "log: 日志表", "disabled": "", "checked": ""}
                , {"value": "message", "title": "message: 留言表", "disabled": "", "checked": ""}
                , {"value": "notice", "title": "notice: 通知表", "disabled": "", "checked": ""}
                , {"value": "proj", "title": "proj: 项目表", "disabled": "", "checked": ""}
                , {"value": "user", "title": "user: 用户表", "disabled": "", "checked": ""}
            ]
            , id: 'demo1' //定义索引
        });
    });
})