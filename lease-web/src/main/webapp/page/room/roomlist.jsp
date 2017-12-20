<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/11/2 11:12
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房源列表</title>
    <jsp:include page="/page/common/includeCommon.jsp"></jsp:include>
    <script type="text/javascript">
        var localObj = window.location;
        var contextPath = localObj.pathname.split("/")[1];
        var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;
        $(document).ready(function() {
            getRoomList();
        });
        function getRoomList() {
            $.ajax({
                url:basePath+"/room/getRoomList.do",
                type:"post",
                dataType:"json",
                async:false,
                success:function(data){
                    console.log(data.data);
                    document.write(JSON.stringify(data));
                },
                error:function(error){

                }
            });
        }
    </script>
</head>
<body>
</body>
</html>
