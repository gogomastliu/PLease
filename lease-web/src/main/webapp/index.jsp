<%--
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/12/19 10:33
  Comment: 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Properties" %>
<html>
<head>
    <title>租房首页</title>
    <%
         InputStream in = this.getClass().getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        properties.load(in);
        String s=properties.getProperty("project.build.timestamp");
        System.out.println(s);
    %>
    <jsp:include page="/page/common/includeCommon.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath}/reSources/css/index<%=s%>.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/reSources/js/index<%=s%>.min.js"></script>
</head>
<body>
<!--悬浮框-->
<div class="homePage-control">
    <a class="fa fa-chevron-up" href="#top"></a>
    <a class="fa fa-share-alt"></a>
    <a class="fa fa-link"></a>
</div>
<!--导航-->
<div class="container-fluid">
    <div class="nav homePage-nav">
        <div class="homePage-logo col-lg-1 col-xs-3">Logo</div>
        <div class="col-lg-2 col-xs-5">
            <div class="dropdown homePage-area">
                <a class="dropdown-toggle fa fa-map-marker " data-toggle="dropdown" href="#">&nbsp;&nbsp;石家庄</a>
                <ul class="dropdown-menu dropdown-menu-area" role="menu" aria-labelledby="dLabel">
                    <li>北京</li>
                    <li>上海</li>
                    <li>深圳</li>
                </ul>
            </div>
        </div>
        <div class="col-lg-9 homePage-nav-box hidden-xs">
            <a class="nav-active">首页</a>
            <a>我要找房</a>
            <a>租房</a>
            <a>月租房源</a>
            <a>业主加盟</a>
            <a>关于我们</a>
        </div>
        <div class="col-xs-4 visible-xs">
            <a class="dropdown-toggle fa fa-bars navs-menu" data-toggle="dropdown" href="#"></a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                <li><a>首页</a></li>
                <li><a>我要找房</a></li>
                <li><a>月租房源</a></li>
                <li><a>业主加盟</a></li>
                <li><a>关于我们</a></li>
            </ul>
        </div>
    </div>
</div>
<!--banner-->
<div>
    <div class="col-sm-12 container-fluid homePage-banner">
        <div id="divBanner" class="carousel slide" data-ride="carousel">
            <!--<ul class="carousel-indicators">
                <li data-target="#divBanner" data-slide-to="0" class="active"></li>
                <li data-target="#divBanner" data-slide-to="1"></li>
                <li data-target="#divBanner" data-slide-to="2"></li>
            </ul>-->
            <div class="carousel-inner">
                <div class="item active">
                    <img src="reSources/images/index/banner.jpg" class="img-responsive">
                </div>
                <div class="item">
                    <img src="reSources/images/index/banner.jpg" class="img-responsive">
                </div>
                <div class="item">
                    <img src="reSources/images/index/banner.jpg" class="img-responsive">
                </div>
            </div>
            <a class="left carousel-control" data-slide="prev" href="#divBanner">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" data-slide="next" href="#divBanner">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
    </div>
</div>


<div class="clearfix"></div>

<!--栏目-->
<div class="container">
    <h1 class="homePage-h1">精选房源</h1>
    <h2 class="homePage-h2">公寓致力于为都市白领创造高品质租住生活</h2>

    <div class="homePage-tabbox">



        <div class="col-lg-12">
            <ul class="homePage-tabnav homePage-tabnav-1">
                <li class="active"><a data-toggle="tab" href="#one">特惠房源</a></li>
                <li><a data-toggle="tab" href="#two">租户推荐</a></li>
                <li><a data-toggle="tab" href="#three">特大空间</a></li>
            </ul>
        </div>
        <div class="col-lg-12">
            <div class="tab-content">
                <div class="tab-pane fade in active" id="one">

                    <div class="col-lg-4 col-sm-6">
                        <div class="homePage-imgbox"><img src="reSources/images/index/shop.jpg"><a class="like fa fa-heart"></a></div>
                        <div class="columns-title"><b>大望路 东区国际公寓 主卧 朝西南 A室</b></div>
                        <div class="homePage-columns row">
                            <div class="col-lg-4"><em>￥</em><strong>3940</strong><abbr>起></abbr></div>
                            <div class="col-lg-8 esp-title">地铁 1号线,八通线 / 约20平米</div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="homePage-imgbox"><img src="reSources/images/index/shop.jpg"><a class="like fa fa-heart"></a></div>
                        <div class="columns-title"><b>大望路 东区国际公寓 主卧 朝西南 A室</b></div>
                        <div class="homePage-columns row">
                            <div class="col-lg-4"><em>￥</em><strong>3940</strong><abbr>起></abbr></div>
                            <div class="col-lg-8 esp-title">地铁 1号线,八通线 / 约20平米</div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="homePage-imgbox"><img src="reSources/images/index/shop.jpg"><a class="like fa fa-heart"></a></div>
                        <div class="columns-title"><b>大望路 东区国际公寓 主卧 朝西南 A室</b></div>
                        <div class="homePage-columns row">
                            <div class="col-lg-4"><em>￥</em><strong>3940</strong><abbr>起></abbr></div>
                            <div class="col-lg-8 esp-title">地铁 1号线,八通线 / 约20平米</div>
                        </div>
                    </div>

                </div>
                <div class="tab-pane fade" id="two">

                </div>
                <div class="tab-pane fade" id="three">

                </div>
            </div>
        </div>
        <div class="clearfix"></div>
        <div>
            <a class="btn homePage-more">更多></a>
        </div>

    </div>
</div>

<!--隔断-->
<div class="container-fluid homePage-banner homePage-banner-2">
    <img src="reSources/images/index/x_banner.jpg">
</div>

<!--栏目-->

<div class="container">
    <h1 class="homePage-h1">样板间展示</h1>
    <h2 class="homePage-h2">80后设计师倾力打造的温馨环境</h2>

    <div class="homePage-tabbox">



        <div class="col-lg-12">
            <ul class="homePage-tabnav homePage-tabnav-1">
                <li class="active"><a data-toggle="tab" href="#f1">房间</a></li>
                <li><a data-toggle="tab" href="#f2">客厅</a></li>
                <li><a data-toggle="tab" href="#f3">厨房</a></li>
                <li><a data-toggle="tab" href="#f4">卫生间</a></li>
            </ul>
        </div>
        <div class="col-lg-12">
            <div class="tab-content">
                <div class="tab-pane fade in active" id="f1">
                    <div class="col-lg-4 col-sm-6">
                        <div class="homePage-imgbox homePage-imgboxs"><img src="reSources/images/index/shop.jpg"></div>
                        <div class="columns-title"><b>大望路 东区国际公寓 主卧 朝西南 A室</b></div>
                        <div class="homePage-columns row">
                            <div class="col-lg-8 esp-title-s">地铁 1号线,八通线 / 约20平米</div>
                            <div class="col-lg-4 new-money"><strong>3940</strong><abbr>/月</abbr></div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="homePage-imgbox homePage-imgboxs"><img src="reSources/images/index/shop.jpg"></div>
                        <div class="columns-title"><b>大望路 东区国际公寓 主卧 朝西南 A室</b></div>
                        <div class="homePage-columns row">
                            <div class="col-lg-8 esp-title-s">地铁 1号线,八通线 / 约20平米</div>
                            <div class="col-lg-4 new-money"><strong>3940</strong><abbr>/月</abbr></div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-sm-6">
                        <div class="homePage-imgbox homePage-imgboxs"><img src="reSources/images/index/shop.jpg"></div>
                        <div class="columns-title"><b>大望路 东区国际公寓 主卧 朝西南 A室</b></div>
                        <div class="homePage-columns row">
                            <div class="col-lg-8 esp-title-s">地铁 1号线,八通线 / 约20平米</div>
                            <div class="col-lg-4 new-money"><strong>3940</strong><abbr>/月</abbr></div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="f2">

                </div>
                <div class="tab-pane fade" id="f3">

                </div>

                <div class="tab-pane fade" id="f4">

                </div>




            </div>
        </div>
        <div class="clearfix"></div>
        <div>
            <a class="btn homePage-more">更多></a>
        </div>

    </div>
</div>

<!--底部-->

<div class="footer">
    <div class="container">
        <div class="col-lg-3" style="font-size:30px;">LOGO</div>
        <div class="col-lg-9" style="overflow:hidden;">
            <dl>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
            </dl>
            <dl>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
            </dl>
            <dl>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
                <dd>产品功能</dd>
            </dl>
            <div class="codeImg hidden-xs"><img src="reSources/images/index/codeImg.jpg"></div>
        </div>
    </div>
</div>
</body>
</html>
