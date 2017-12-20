<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello World!</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/reSources/js/common/jquery-1.12.3.js"></script>
    <script type="text/javascript">var contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
Hello World!
<p>
    <%=new java.util.Date().toLocaleString() %>
</p>
<button onclick="aa();">注册用户</button>
<button onclick="aaa();">注册内部用户</button>
<a href="page/manager/register.jsp">注册用户</a>
<a href="page/room/roomlist.jsp">房源列表</a>
<a href="page/WebSocket/index.jsp">聊天</a>
<a href="page/im/demo.jsp">聊天im</a>

<script type="text/javascript">
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[1];
    var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
    function aa() {
        $.ajax({
            type: "POST",
            url: basePath+"/index.do",
            contentType: "application/Json",
            success: function(data){
                if(data.success == true){
                    alert(data.msg);
                }else{
                    window.location.href=basePath+"/index1.do";
                }
            },
            error:function (data) {
                console.log(data.responseText);
            }
        });
    }
    function aaa() {
        window.location.href=basePath+"/index1.do";
    }
</script>
</body>
</html>