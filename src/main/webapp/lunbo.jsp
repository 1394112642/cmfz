<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<script>
    $(function () {
        $("#bannerList").jqGrid({
            styleUI: "Bootstrap",
            url: "${path}/banner/findAll", //获取数据的地址
            datatype: "json",//从服务器端返回的数据类型，默认xml。可选类型：xml，local，json，jsonnp，script，xmlstring，jsonstring，clientside
            colNames: ["编号", "标题", "状态", "描述", "创建时间", "图片"],//列显示名称，是一个数组对象
            autowidth: true,//根据父元素比例重新调整表格宽度
            //caption:"轮播图管理",//表格名称
            pager: "#page",//
            page: 1,
            rowNum: 2,//默认一页展示几条数据
            viewrecords: true,//是否显示总记录(条)数
            rowList: [2, 4, 6],//一个数组用来调整表格显示的记录数，此参数值会替代rowNum参数值传给服务器端。
            editurl: "${path}/banner/edit",//操作表时调用的方法
            multiselect: true,
            cellEdit: false,//启用或者禁用单元格的标记功能
            //toolbar:[true,"top"],//开启表格的工具栏，以及放置的位置
            colModel: [//常用到的属性：name 列显示的名称；index 传到服务器端用来排序用的列名称；width 列宽度；align 对齐方式；sortable 是否可以排序
                {
                    name: "id"
                },
                {
                    name: "title",
                    editable: true //单元格是否为可编辑
                },
                {
                    name: "status",
                    editable: true,
                    edittype: "select",
                    editoptions: {value: '显示:显示;隐藏:隐藏'},

                },
                {
                    name: "des", editable: true
                },
                {
                    name: "create_date", editable: false

                },
                {
                    name: "img_path",
                    editable: true,
                    edittype: "file",
                    //对列进行格式化时设置的函数名或者类型
                    formatter: function (cellvalues, options, rowObject) {
                        return "<img style='width:100px;height:50px' src='${path}/img/" + cellvalues + "' />"
                    }
                }
            ],

        }).jqGrid('navGrid', '#page',
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (o) {
                    o.find("#id").attr("readonly", true),
                        o.find("#img_path").attr("disabled", true)
                }
            },
            {//在添加数据 之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("2")
                }*/
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({

                        url: "${path}/banner/upload",
                        fileElementId: "img_path",
                        data: {"bannerId": bannerId},
                        success: function (data) {
                            $("#bannerList").trigger("reloadGrid")
                        }
                    });
                    return response
                }

            },
            {//在删除数据之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("3")
                }*/
            }
        )
    })

    function showModal() {
        location.href = "${pageContext.request.contextPath}/banner/download"
    }
</script>

<h1>
    轮播图管理
</h1>
<ul class="nav nav-tabs">
    <li class="active">
    <li role="presentation" class="active"><a href="#home" data-toggle="tab">轮播图信息</a></li>
    <li><a href="#" onclick="showModal()">导出信息</a></li>
    </li>
</ul>

<div class="tab-pane active fade in" id="home">
    <table id="bannerList"></table>
    <div id="page"></div>
</div>
