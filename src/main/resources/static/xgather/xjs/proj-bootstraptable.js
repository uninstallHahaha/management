var $table = $('#table')
var $remove = $('#remove')
var selections = []

function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id
    })
}

function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1
    })
    return res
}

function detailFormatter(index, row) {
    var html = []
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':</b> ' + value + '</p>')
    })
    return html.join('')
}

function operateFormatter(value, row, index) {
    return [
        '<a class="info" href="javascript:void(0)" title="详情">',
        '<i class="icon wb-info" aria-hidden="true"></i>',
        '</a>  ',
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="icon wb-wrench" aria-hidden="true"></i>',
        '</a>  ',
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="icon wb-close" aria-hidden="true"></i>',
        '</a>'
    ].join('')
}

window.operateEvents = {
    //详情
    'click .info': function (e, value, row, index) {
        // alert('You click like action, row: ' + JSON.stringify(row))
        location.href = '/getProjInfoPage?id='+row.id
    },
    //修改
    'click .like': function (e, value, row, index) {
        // alert('You click like action, row: ' + JSON.stringify(row))
        location.href = '/getProjFormPage?id='+row.id
    },
    //删除
    'click .remove': function (e, value, row, index) {
        $.ajax({
            url: '/delProj',
            data: {'id': row.id},
            async: false,
            success: function (res) {
                if (res == 1) {
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    })
                    layer.msg('删除成功')
                }else{
                    layer.msg('删除失败')
                }
            }
        })

    }
}


function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        height: 550,
        locale: 'zh-CN',
        columns:
            [
                {
                    field: '#',
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                title: '项目发起者',
                field: 'userName',
                align: 'center',
                valign: 'middle',
                sortable: true,
            }, {
                field: 'name',
                title: '项目名称',
                sortable: true,
                align: 'center'
            }, {
                field: 'state',
                title: '项目状态',
                sortable: true,
                align: 'center',
            }, {
                field: 'count',
                title: '参与人数',
                align: 'center',
                valign: 'middle',
                sortable: true,
            }, {
                field: 'ctime',
                title: '创建时间',
                sortable: true,
                align: 'center'
            },
                {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    clickToSelect: false,
                    events: window.operateEvents,
                    formatter: operateFormatter
                }
            ]
    })
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table',
        function () {
            $remove.prop('disabled', !$table.bootstrapTable('getSelections').length)

            // save your data, here just save the current page
            selections = getIdSelections()
            // push or splice the selections if you want to save all data selections
        })
    $table.on('all.bs.table', function (e, name, args) {
        console.log(name, args)
    })
    //del list
    $remove.click(function () {
        var ids = getIdSelections()
        $.ajax({
            url: '/delProjList',
            data: {'ids': ids},
            async: false,
            success: function (res) {
                if (res == 1) {
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: ids
                    })
                    layer.msg('删除成功')
                }else{
                    layer.msg('删除失败')
                }
            }
        })
        $remove.prop('disabled', true)
    })
}

$(function () {
    initTable()
})