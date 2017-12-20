<html>
<head>
    <jsp:include page="/page/common/includeCommon.jsp"></jsp:include>
</head>
<body>
<h2>Hello World!</h2>
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
</body>
</html>
