<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/10/31 11:03
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String serverPath = request.getServerName() + ":" + request.getServerPort() + path;
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<%--
<link href="<%=basePath%>/reSources/images/favicon.ico" rel="shortcut icon">
<link href="<%=basePath%>/reSources/images/favicon.ico" rel="bookmark">
--%>
<%--css--%>
<link href="<%=basePath%>/reSources/common/bootstrap/bootstrap.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>/reSources/common/bootstrap/bootstrap-theme.css" rel="stylesheet" type="text/css">
<%--js--%>
<script type="text/javascript" src="<%=basePath%>/reSources/common/jquery-1.12.3.js"></script>
<script type="text/javascript" src="<%=basePath%>/reSources/common/bootstrap/bootstrap.js"></script>
<script type="text/javascript">var contextPath = "<%=basePath%>";</script>