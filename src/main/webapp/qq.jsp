<%@page contentType="text/html; utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="boot/js/jquery-2.2.1.min.js"></script>
    <script src="echarts/echarts.min.js"></script>
    <script src="echarts/china.js"></script>
    <script src=" http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        var goEasy = new GoEasy({
            appkey: "BC-033e2140859e4eb4a5882dcf4cfa0898"
        });
        goEasy.subscribe({
            channel: "qq",
            onMessage: function (message) {
                $("#sh").append("<h1>" + message.content + "</h1>")
                $("#aa").val("")
            }
        });

        function fa() {
            var val = $("#aa").val();
            $.ajax({
                url: "${pageContext.request.contextPath}/user/qq?aa=" + val,
                datatype: "json",
                type: "POST",
                success: function (da) {

                }
            })
        }
    </script>
    <style>
        #sh {

            border: green 2px solid;
        }

        #qq {
            height: 500px;
            border: red 2px solid;
        }

        #wrter {
            height: 30px;
            border: blue 2px solid;
        }
    </style>
</head>
<body>
<div id="wrter">
    <center>
        请输入你要说的话
        <input type="text" id="aa" name="bb">
        <input type="button" id="btn" value="发送" onclick="fa()">
    </center>
</div>
<div id="qq">
    <center>聊天内容</center>
    <div id="sh"></div>
</div>
</body>
</html>