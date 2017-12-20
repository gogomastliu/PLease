<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/10/31 15:04
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户中心</title>
    <jsp:include page="/page/common/includeCommon.jsp"></jsp:include>
</head>
<body>
<input id="userId" type="hidden" value="1">
账户信息：
修改手机号：
<input type="text" id="phone">
登录密码：
<input type="text" id="password">
身份认证：
<input type="text" id="password1">
<br>
<button id="btn1" onclick="phone()">修改手机</button>
<button id="btn2" onclick="password()">修改密码</button>
<script type="text/javascript">
    var localObj = window.location;
    var contextPath = localObj.pathname.split("/")[2];
    var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
    function phone() {
        var id=$("#userId").val();
        var phone=$("#phone").val();
        $.ajax({
            type: "POST",
            url: basePath+"/updateUser.do",
            data: {'phone':phone,'id':id},
            success: function(data){
                if(data.success == true){
                    window.location.href=basePath+"/index1.jsp";
                    console.log("修改成功！");
                }else{
                    console.error("修改失败！"+data.msg);
                }

            }
        });
    }
    function password() {
        var id=$("#userId").val();
        var password=$("#password").val();
        $.ajax({
            type: "POST",
            url: basePath+"/User/updateUser.do",
            data: {'userpsw':password,'id':id},
            success: function(data){
                if(data.success == true){
                    window.location.href=basePath+"/index1.jsp";
                    console.log("修改成功！");
                }else{
                    console.error("修改失败！"+data.msg);
                }

            }
        });
    }
</script>
</body>
</html>
