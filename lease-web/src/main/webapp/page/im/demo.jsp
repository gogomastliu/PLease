<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/11/24 9:31
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String serverPath = request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title>测试前段聊天页面</title>
    <jsp:include page="/page/common/includeCommon.jsp"></jsp:include>
    <link href="<%=basePath%>/reSources/common/layui/css/layui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>/reSources/common/layui/layui.js"></script>
    <script type="text/javascript" src="<%=basePath%>/reSources/js/imWebSocket.js"></script>
</head>
<body>
<input id="userid" style="width: 300px" placeholder="输入用户ID"/>
<input id="username" style="width: 300px" placeholder="输入用户名称"/>
<button id="connect" onclick="connectws();">连接</button>
<script>

    function connectws() {
        var userId = $("#userid").val();
        var userName = $("#username").val();
        var url = "localhost:8080/wsim/imServer.do?id=" + userId + "&name=" + userName;
        connect(url);
    }
</script>
</body>
</html>
