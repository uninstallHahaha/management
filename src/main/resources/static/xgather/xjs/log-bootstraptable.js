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
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="icon wb-close" aria-hidden="true"></i>',
        '</a>'
    ].join('')
}

window.operateEvents = {
    //删除
    'click .remove': function (e, value, row, index) {
        $.ajax({
            url: '/delLog',
            data: {'id': row.id},
            method: 'post',
            async: false,
            success: function (res) {
                if (res == 1) {
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    })
                    layer.msg('删除成功')
                } else {
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
            [{
                field: '#',
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: '日志ID',
                field: 'id',
                align: 'center',
                valign: 'middle',
                sortable: true,
            }, {
                field: 'userid',
                title: '操作者ID',
                sortable: true,
                align: 'center'
            }, {
                field: 'username',
                title: '操作者账户名',
                sortable: true,
                align: 'center',
            }, {
                field: 'userrole',
                title: '操作者角色',
                align: 'center',
                valign: 'middle',
                sortable: true,
            },{
                field: 'opobj',
                title: '操作接口',
                align: 'center',
                valign: 'middle',
                sortable: true,
            }, {
                field: 'ctime',
                title: '操作时间',
                sortable: true,
                align: 'center'
            }, {
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
                } else {
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