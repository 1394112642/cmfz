<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>

<script>

    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/album/findAll",
            editurl: "${pageContext.request.contextPath}/album/edit",//操作表时调用的方法
            datatype: "json",
            multiselect: true,
            colNames: ["id", "标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "上传时间", "插图"],
            colModel: [
                {name: "id"},
                {
                    name: "title",//标题
                    editable: true //单元格是否为可编辑
                },
                {
                    name: "score",//评分
                    editable: true, //单元格是否为可编辑
                    edittype: "select",
                    editoptions: {value: '1:1;2:2;3:3;4:4;5:5;6:6;7:7;8:8;9:9;10:10'}
                },
                {
                    name: "author",//作者
                    editable: true //单元格是否为可编辑
                },
                {
                    name: "beam",//播音员
                    editable: true //单元格是否为可编辑
                },
                {
                    name: "count",//章节数
                },
                {
                    name: "content",//简介
                    editable: true //单元格是否为可编辑
                },

                {
                    name: "status",//状态
                    editable: true, //单元格是否为可编辑
                    edittype: "select",
                    editoptions: {value: '上架:上架;下架:下架'}
                },
                {
                    name: "publish_date"//上传时间
                },
                {
                    name: "cover",
                    editable: true, //单元格是否为可编辑
                    edittype: "file",
                    //对列进行格式化时设置的函数名或者类型
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='width:100px;height:50px' src='${pageContext.request.contextPath}/img/" + cellvalue + "'/>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#albumPager",
            page: 1,
            rowNum: 2,
            rowList: [2, 4, 6],
            viewrecords: true,
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, albumId) {
                addSubGrid(subgrid_id, albumId);
            }
        }).jqGrid("navGrid", "#albumPager",
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (o) {
                    o.find("#id").attr("readonly", true),
                        o.find("#count").attr("readonly", true),
                        o.find("#cover").attr("disabled", true)
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

                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "cover",
                        data: {"bannerId": bannerId},
                        success: function (data) {
                            $("#albumList").trigger("reloadGrid")

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
    });

    function addSubGrid(subgrid_id, albumId) {
        var tableId = subgrid_id + "table";
        var divId = subgrid_id + "div";
        $("#" + subgrid_id).html(
            "<table id='" + tableId + "' ></table>" + "<div id='" + divId + "' ></div>"
        );
        $("#" + tableId).jqGrid({
            url: "${pageContext.request.contextPath}/chapter/findAll?albumid=" + albumId,
            editurl: "${pageContext.request.contextPath}/chapter/edit?albumid=" + albumId,
            datatype: "json",
            colNames: ["id", "标题", "大小", "时长", "上传时间", "音频", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "size"},
                {name: "long_time"},
                {name: "upload_date"},
                {
                    name: "filepath",
                    editable: true,
                    edittype: "file"

                },
                {
                    name: "",
                    formatter: function (cellValue, options, rowObject) {

                        return "<a onclick=\"playAudio('" + rowObject.filepath + "')\" href='#' ><span class='glyphicon glyphicon-play-circle'></span></a>" + "                       " +
                            "<a href='${pageContext.request.contextPath}/chapter/download?fileName=" + rowObject.filepath + "'><span class='glyphicon glyphicon-download'></span></a>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#" + divId,
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [2, 4, 6],
            viewrecords: true
        }).jqGrid("navGrid", "#" + divId,
            {//处理页面几个按钮的样式
                search: false
            },
            {//在编辑之前或者之后进行额外的操作
                closeAfterEdit: true,
                beforeShowForm: function (o) {
                    o.find("#id").attr("readonly", true),
                        o.find("#filepath").attr("disabled", true)
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

                        url: "${pageContext.request.contextPath}/chapter/upload",
                        fileElementId: "filepath",
                        data: {
                            "bannerId": bannerId,
                            "albumId": albumId
                        },
                        success: function (data) {
                            $("#" + tableId).trigger("reloadGrid")
                            $("#albumList").trigger("reloadGrid")
                        }
                    });
                    return response
                }

            },
            {//在删除数据之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("3")
                }*/
                afterSubmit: function () {
                    $("#" + tableId).trigger("reloadGrid")
                    $("#albumList").trigger("reloadGrid")
                    return "mm";
                }

            }
        )

    }


    function playAudio(d) {
        $("#dialogId").modal("show");
        $("#audioId").attr("src", "${pageContext.request.contextPath}/music/" + d);
    }

</script>


<div id="dialogId" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" controls src=""></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->


<table id="albumList"></table>
<div id="albumPager"></div>

