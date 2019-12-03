<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="kindeditor-all-min.js"></script>
    <script src="lang/zh-CN.js"></script>

    <script>
        KindEditor.ready(function (K) {
            window.editor = K.create('#editor_id', {
                //图片的上传路径
                uploadJson: "${pageContext.request.contextPath}/kindeditor/upload",
                //上传图片的名字 name
                filePostName: "img",
                //判断上传的内容是否为图片
                allowFileManager: true,
                //图片空间 内容的获取路径
                fileManagerJson: "${pageContext.request.contextPath}/kindeditor/allImages"
            });
        });
    </script>

</head>
<body>
<%--文本域标签--%>
<textarea id="editor_id" name="content" style="width:700px;height:300px;">
        &lt;strong&gt;HTML内容&lt;/strong&gt;
    </textarea>
</body>
</html>