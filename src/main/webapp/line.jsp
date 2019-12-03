<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 1000px;height:500px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例'
        },
        tooltip: {},
        legend: {
            data: ['销量']
        },
        xAxis: {
            data: ["1", "2", "3", "4", "5", "6", "7"]
        },
        yAxis: {},
        series: [{
            name: '销量',
            type: 'line',

            /* data: [1,1,1,1,1]*/
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    $.ajax({
        url: "${pageContext.request.contextPath}/user/date",
        datatype: "json",
        type: "POST",
        success: function (da) {

            myChart.setOption({
                xAxis: [{data: da.y}],
                series: [{data: da.x}]
            });
        }
    });


</script>

</body>
</html>