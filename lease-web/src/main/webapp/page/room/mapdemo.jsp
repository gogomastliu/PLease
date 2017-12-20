<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/12/5 14:05
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello, World</title>
    <style type="text/css">
        html {
            height: 100%;
        }

        body {
            height: 100%;
            margin: 0px;
            padding: 0px;

        }

        #containerMap {
            height: 100%
        }
        .marker {
             position: relative;
             z-index: 0;
             white-space: nowrap;
         }

        .marker-region {
            border-radius: 50%;
            opacity: 0.9;
            background-color: rgba(246, 80, 0, 0.9);
            background-image: linear-gradient(0deg, #f65000, #ff7800);
            box-shadow: 0 4px 4px rgba(0, 0, 0, 0.2);
            width: 76px;
            height: 76px;
            text-align: center;
            padding: 8px;
            color: white;
            margin-left: -27px;
            margin-top: -42px;
        }
        .marker-region:hover{
            background-image: none;
            background-color: #7459dc;
            box-shadow: 0 2px 2px rgba(0, 0, 0, 0.2);
        }
        .marker-region h6 {
            font-size: 13px;
            font-weight: normal;
            margin-top: 13px;
            margin-bottom: 0;
            white-space: nowrap;
        }
        .marker-region p {
            font-size: 18px;
            margin: 0;
            font-family: microsoft yahei;
            color: inherit;
            white-space: nowrap;
        }
        .marker-region small {
            color: inherit;
            padding-left: 2px;
            font-size: .7em;
        }
        .marker-meng{
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
        }
    </style>
    <jsp:include page="/page/common/includeCommon.jsp"></jsp:include>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=EvQXrq1tUMLIu2d1tGpQIeNZSTe6LG6y"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/reSources/js/map/mapEx.js"></script>
</head>

<body>
<div id="containerMap">

</div>
</body>
</html>
