<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/10/31 10:51
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>租房-注册页</title>
    <jsp:include page="/page/common/includeCommon.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath}/reSources/css/registerUser.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/reSources/js/registerUser.js"></script>
</head>
<body>
    <%--Web容器--%>
    <div class="bg-sign">
        <div class="main-sign">
            <div class="aside-sign">
                <a href="${pageContext.request.contextPath}/index1.jsp" class="logo">
                    <img src="${pageContext.request.contextPath}/reSources/images/logo-white.png" alt="租房">
                </a>
                <div class="box-sign free-hight">
                    <form class="form-sign-up form-addon">
                        <h1>欢迎<em>注册</em></h1>
                        <div class="form-group">
                            <i class="glyphicon glyphicon-star"></i>
                            <input type="tel" id="userPhone" class="form-control" autocomplete="off" placeholder="请输入手机号码">
                            <div class="tooltip bottom" role="tooltip">
                                <div class="tooltip-arrow"></div>
                                <div class="tooltip-inner"></div>
                            </div>
                        </div>
                        <input type="hidden" name="pwd">
                        <div class="form-group">
                            <i class="glyphicon glyphicon-lock"></i>
                            <input type="password" id="passWord" class="form-control" autocomplete="off" placeholder="请输入密码">
                            <div class="tooltip bottom" role="tooltip">
                                <div class="tooltip-arrow"></div>
                                <div class="tooltip-inner"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <i class="glyphicon glyphicon-lock"></i>
                            <input type="password" name="password1" class="form-control" autocomplete="off" placeholder="请确认密码">
                            <div class="tooltip bottom" role="tooltip">
                                <div class="tooltip-arrow"></div>
                                <div class="tooltip-inner"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" autocomplete="off" id="verifyCode" placeholder="请输入验证码">
                            <div class="tooltip bottom" role="tooltip">
                                <div class="tooltip-arrow"></div>
                                <div class="tooltip-inner"></div>
                            </div>
                            <button type="button" class="btn-link fetch-cmd">获取验证码</button>
                        </div>
                        <div class="form-group geetest-wrapper hidden">
                            <div id="geetest">
                                <div id="register_validate_code" class="validate-item">
                                    <div class="validate-code">
                                        <input type="text" class="form-control" placeholder="请输入验证码" name="veriCode" id="register_validate_txt" value="" autocomplete="off">

                                    </div>
                                    <div class="validate-con">
                                        <img src="./注册_files/getPicVeriCode" class="verifyCode-input">
                                        <span class="switch-code">看不清换一张</span>
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>

                        <input type="hidden" name="geetest_ShowFlag" value="NOSHOW">
                        <div class="form-group">
                            <label class="text-ellipsis">
                                <input type="checkbox" name="agree" value="1">
                                <div class="tooltip bottom" role="tooltip">
                                    <div class="tooltip-arrow"></div>
                                    <div class="tooltip-inner"></div>
                                </div>
                                我已阅读并同意<a class="link-term" target="_blank" href="privacy.jsp">《用户使用条款与隐私声明》</a>
                            </label>
                        </div>
                        <div class="placeholder-info"></div>
                        <div class="form-group">
                            <button class="btn btn-orange-lg btn-block" onclick="regUser();">创建账号</button>
                        </div>
                    </form>
                </div>
                <div class="aside-sign-footer">
                    <a href="${pageContext.request.contextPath}/index1.jsp"><i class="fa fa-home"></i>返回首页</a>
                    &nbsp;&nbsp;<a href="login.jsp" class="active pull-right">账号登录</a>
                    &nbsp;&nbsp;<a href="userCenter.jsp" class="active pull-right">用户中心|</a>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
