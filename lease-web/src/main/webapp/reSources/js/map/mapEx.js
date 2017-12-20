var map;
var curBoundary;
var centerControl;
var polys;//行政区划数组
var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
var basePath = localObj.protocol + "//" + localObj.host + "/" + contextPath;
$(document).ready(function () {

    var myCity = new BMap.LocalCity();
    myCity.get(function (result) {
        loadMap(result.name, result.center.lng, result.center.lat, 13);
        addMapController();
    });


});

/**
 * 加载地图
 * 精度，维度，显示级别，DIV的ID
 */

function loadMap(cityName, lon, lat, lvl) {
    _mapId = "containerMap";
    //初始化地图
    map = new BMap.Map(_mapId, {enableMapClick: false}); // 创建Map实例
    //设置中心点和缩放级别
    map.centerAndZoom(new BMap.Point(lon, lat), lvl);
    map.disableInertialDragging();
    map.enableScrollWheelZoom(); //启用滚轮放大
    //map.setDefaultCursor("auto");   //设置鼠标指针
    //地图放大缩小事件
    map.addEventListener("zoomend", function (type) {
        refreshMap(type);
    });
    getdistrict(cityName);
}
function getdistrict(cityName){
    $.ajax({
        type: "POST",
        url: basePath + "/map/getDistrict.do",
        data: {'cityName': cityName},
        success: function (data) {
            if (data.success == true) {
                console.log(data.data);
                addCircleByDiv(114.545391, 38.043311, "石家庄市", "长安区", parseInt(Math.random() * 1000, 10));//添加区划
            } else {
                console.log(data.msg);
            }

        }
    });
}
//刷新地图
function refreshMap(type) {
    //根据当前级别显示不同的东西
    if (type.target.Oa > 13) {
        //清除所有覆盖物
        map.clearOverlays();
        //区以下增加点
        addPlotByDiv(114.547834, 38.055249, "天涯小区0", Math.round(Math.random() * 999));
        // addPlotByDiv(114.532168,38.056723,"天涯小区1",Math.round(Math.random()*999));
        // addPlotByDiv(114.53763,38.060814,"天涯小区2",Math.round(Math.random()*999));
        // addPlotByDiv(114.558327,38.047403,"天涯小区3",Math.round(Math.random()*999));
        //addPoint(114.574328, 38.119426, '../../reSources/images/map/mapTest.png', 32, 32, '123123');
    } else {
        //区级
        map.clearOverlays();
        getdistrict();//获取各个区的列表
    }

}

/**
 * 是否在地图左上角显示地图类型切换控件*/
function setMapTypeCtrl() {
    //加载地图类型切换控件
    var mapType1 = new BMap.MapTypeControl({
        mapTypes: [BMAP_NORMAL_MAP, BMAP_HYBRID_MAP],
        anchor: BMAP_ANCHOR_TOP_LEFT
    });
    map.addControl(mapType1);           //添加地图类型控件

    var top_left_navigation = new BMap.NavigationControl(
        {anchor: BMAP_ANCHOR_BOTTOM_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});  //左
    map.addControl(top_left_navigation);
}

/**添加点对象
 * 参数 ：地图
 *      经度
 *      纬度
 *      图例的路径
 *      图例宽度
 *      图例高度
 *      标注名称  传空字符串时 不添加标注
 *
 *      return  marker
 * */
function addPoint(lon, lat, iconStr, iconWidth, iconHeight, name) {
    var point = new BMap.Point(lon, lat);
    var icon = new BMap.Icon(iconStr, new BMap.Size(iconWidth, iconHeight));
    var marker = new BMap.Marker(point, {
        icon: icon
    });
    map.addOverlay(marker);
    if (name == null || name == "") {
        name = '';
    }

    var labelLen = getLabelOffset(name);
    //标注水平偏移量
    var offsetX = (labelLen - marker.getIcon().size.width) / -2;
    //标注垂直偏移量
    var offsetY = marker.getIcon().size.height;
    var label = new BMap.Label(name, {offset: new BMap.Size(offsetX, offsetY)});
    label.setStyle({
        color: "black",
        fontSize: "10px",
        width: labelLen + "px",
        display: "inline-block",
        overflow: "hidden",
        border: "0px",
        backgroundColor: "0.05",
    });
    marker.setLabel(label);
    //点击事件
    var sContent =
        "<h4 style='margin:0 0 5px 0;padding:0.2em 0'>这是一个弹出层</h4>" +
        "<p style='margin:0;line-height:1.5;font-size:13px;text-indent:2em'>具体详细信息 哦哦哦哦 </p>" +
        "</div>";
    var infoWindow = new BMap.InfoWindow(sContent);
    //图元点击事件
    addEvent(marker, "click", function () {
        //替换图标--能否记录tag属性-在移动图标时判断该tag属性
        marker.setIcon(new BMap.Icon('../../reSources/images/map/mapTest2.png', new BMap.Size(iconWidth, iconHeight)));
        this.openInfoWindow(infoWindow);
        //显示加载控件
        if (centerControl.isVisible()) {
            centerControl.hide();
        } else {
            centerControl.show();
        }
    });
    //图元移入事件
    addEvent(marker, "mouseover", function () {
        // if(){//如果图元为已点选将不在
        //
        // }
        marker.setIcon(new BMap.Icon('../../reSources/images/map/mapTest1.png', new BMap.Size(iconWidth, iconHeight)));
    });
    //图元移出事件
    addEvent(marker, "mouseout", function () {
        marker.setIcon(new BMap.Icon(iconStr, new BMap.Size(iconWidth, iconHeight)));
    });

    return marker;
}

//获取行政区域边界
function getBoundary(district) {
    var bdary = new BMap.Boundary();
    bdary.get(district, function (rs) {       //获取行政区域
        //map.clearOverlays();        //清除地图覆盖物
        var count = rs.boundaries.length; //行政区域的点有多少个
        if (count === 0) {
            console.log('未能获取当前输入行政区域');
            return;
        }
        var pointArray = [];
        if (polys != undefined && polys.length > 0) {
            for (var i = 0; i < polys.length; i++) {
                map.removeOverlay(polys[i]);
            }
        }
        polys = new Array();
        if (count > 0) {
            var poly = new BMap.Polygon(rs.boundaries[0], {
                strokeWeight: 4,
                strokeColor: "#ff0000",
                fillColor: "none"
            }); //建立多边形覆盖物
            //console.log("添加：" + poly);
            polys[0] = poly;
            map.addOverlay(poly);  //添加覆盖物
            //pointArray = pointArray.concat(poly.getPath());
        }
        //map.setViewport(pointArray);    //调整视野

    });
}

//添加一个类似圆的DIV
function addCircleByDiv(lng, lat, city, district, disNum) {
    var cpoint = new BMap.Point(lng, lat);

//复杂的自定义覆盖物
    function ComplexCustomOverlay(point, text, countnumText) {
        this._point = point;
        this._text = text;
        this._countnum = countnumText;
    }

    ComplexCustomOverlay.prototype = new BMap.Overlay();
    ComplexCustomOverlay.prototype.initialize = function (map) {
        this._map = map;
        var div = this._div = document.createElement("div");
        div.className = "marker marker-region";
        var h6 = this._h6 = document.createElement("h6");
        div.appendChild(h6);
        h6.appendChild(document.createTextNode(this._text));
        var countnum = this.countnum = document.createElement("p");
        div.appendChild(countnum);
        countnum.appendChild(document.createTextNode(this._countnum));
        var unit = this._unit = document.createElement("small");
        countnum.appendChild(unit);
        unit.appendChild(document.createTextNode("套"));
        var ndiv = this._ndiv = document.createElement("div");
        ndiv.className = "marker-meng";
        div.appendChild(ndiv);

        var that = this;
        ndiv.onmouseover = function () {
            getBoundary(city + district);
        }

        ndiv.onmouseout = function () {
            //this.getElementsByTagName("span")[0].innerHTML = that._text;
            console.log("删除" + curBoundary + "    :" + polys.length);
            if (polys != undefined && polys.length > 0) {
                for (var i = 0; i < polys.length; i++) {
                    map.removeOverlay(polys[i]);
                }
            }
            polys = new Array();
        }
        ndiv.onclick = function () {
            map.centerAndZoom(cpoint, 14);
            //查询该区点-小区的汇总数
        }


        map.getPanes().labelPane.appendChild(div);

        return div;
    }
    ComplexCustomOverlay.prototype.draw = function () {
        var map = this._map;
        var pixel = map.pointToOverlayPixel(this._point);
        this._div.style.left = pixel.x - 10 + "px";
        this._div.style.top = pixel.y;
    }
    var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(lng, lat), district, disNum);
    map.addOverlay(myCompOverlay);
}

//添加一个小区的div
function addPlotByDiv(lng, lat, plotName, plotNum) {
    //复杂的自定义覆盖物
    function ComplexCustomOverlay(point, text, mouseoverText) {
        this._point = point;
        this._text = text;
        this._overText = mouseoverText;
    }

    ComplexCustomOverlay.prototype = new BMap.Overlay();
    ComplexCustomOverlay.prototype.initialize = function (map) {
        this._map = map;
        var div = this._div = document.createElement("div");
        div.style.position = "absolute";
        div.style.zIndex = BMap.Overlay.getZIndex(this._point.lat);
        div.style.backgroundColor = "#EE5D5B";
        div.style.border = "1px solid #BC3B3A";
        div.style.color = "white";
        // div.style.height = "18px";
        div.style.padding = "2px";
        div.style.lineHeight = "18px";
        div.style.whiteSpace = "nowrap";
        div.style.MozUserSelect = "none";
        div.style.fontSize = "12px";
        div.style.borderRadius = "18px 18px 18px 0px";
        var span = this._span = document.createElement("span");
        span.style.fontFamily = "微软雅黑";
        div.appendChild(span);
        span.appendChild(document.createTextNode(this._text));
        var that = this;

        var arrow = this._arrow = document.createElement("div");
        arrow.style.background = "url(http://map.baidu.com/fwmap/upload/r/map/fwmap/static/house/images/label.png) no-repeat";
        arrow.style.position = "absolute";
        arrow.style.width = "11px";
        arrow.style.height = "10px";
        arrow.style.top = "22px";
        arrow.style.left = "10px";
        arrow.style.overflow = "hidden";
        div.appendChild(arrow);

        div.onmouseover = function () {
            this.style.backgroundColor = "#6BADCA";
            this.style.borderColor = "#0000ff";
            this.getElementsByTagName("span")[0].innerHTML = that._overText;
            arrow.style.backgroundPosition = "0px -20px";
        }

        div.onmouseout = function () {
            this.style.backgroundColor = "#EE5D5B";
            this.style.borderColor = "#BC3B3A";
            this.getElementsByTagName("span")[0].innerHTML = that._text;
            arrow.style.backgroundPosition = "0px 0px";
        }
        map.getPanes().labelPane.appendChild(div);
        return div;
    }
    ComplexCustomOverlay.prototype.draw = function () {
        var map = this._map;
        var pixel = map.pointToOverlayPixel(this._point);
        this._div.style.left = pixel.x - parseInt(this._arrow.style.left) + "px";
        this._div.style.top = pixel.y - 33 + "px";
    }
    var mouseoverTxt = plotName + " " + plotNum + "套";
    var myCompOverlay = new ComplexCustomOverlay(new BMap.Point(lng, lat), plotName, mouseoverTxt);
    map.addOverlay(myCompOverlay);
}

//增加圆形覆盖物
function addCircle(lng, lat, name) {
    var point = new BMap.Point(lng, lat);
    //设置位置，半径，边框宽度，透明度，填充颜色
    var circle = new BMap.Circle(point, 800, {
        strokeColor: "blue",
        strokeWeight: 2,
        strokeOpacity: 0.5,
        fillColor: 'red'
    }); //创建圆
    map.addOverlay(circle);

    addEvent(circle, "mouseover", function () {
        getBoundary();
    });
    addEvent(circle, "mouseout", function () {
        map.removeOverlay(curBoundary);
    });
    addEvent(circle, 'click', function () {
        map.centerAndZoom(point, 14);
    })
    //设置文字标注
    var opts = {
        position: point,    // 指定文本标注所在的地理位置
        offset: new BMap.Size(-30, -10)    //设置文本偏移量
    };
    var label = new BMap.Label("长安区10001套", opts);  // 创建文本标注对象
    //文字标注类型
    label.setStyle({
        color: "yellow",
        fontSize: "12px",
        height: "20px",
        lineHeight: "20px",
        fontFamily: "微软雅黑"
    });
    map.addOverlay(label);
}

//增加地图控件
function addMapController() {
    // 定义一个控件类,即function
    function ZoomControl() {
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;
        this.defaultOffset = new BMap.Size(10, 10);
    }

    // 通过JavaScript的prototype属性继承于BMap.Control
    ZoomControl.prototype = new BMap.Control();

    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
    ZoomControl.prototype.initialize = function (map) {
        // 创建一个DOM元素
        var div = $("<div style='width:200px;height:60px;'><input type='text' style='width:100%;'></div>");
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div[0]);
        // 将DOM元素返回
        return div[0];
    }
    // 创建控件
    var myZoomCtrl = new ZoomControl();
    // 添加到地图当中
    map.addControl(myZoomCtrl);
    //
    // 定义一个控件类,即function
    function ZoomControlCenter() {
        // 默认停靠位置和偏移量
        this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;
        this.defaultOffset = new BMap.Size(600, 10);
    }

    // 通过JavaScript的prototype属性继承于BMap.Control
    ZoomControlCenter.prototype = new BMap.Control();

    // 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
    // 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
    ZoomControlCenter.prototype.initialize = function (map) {
        // 创建一个DOM元素
        var div = $("<div style='width:300px;height:260px;background-color: blue'><input type='text' style='width:100%;'></div>");
        // 添加DOM元素到地图中
        map.getContainer().appendChild(div[0]);
        // 将DOM元素返回
        return div[0];
    }
    // 创建控件
    var ZoomControlCenter = new ZoomControlCenter();
    centerControl = ZoomControlCenter;
    // 添加到地图当中
    map.addControl(ZoomControlCenter);
}


/**添加监听*/
function addEvent(object, eventStr, eventFunc) {
    object.addEventListener(eventStr, eventFunc);
}

/**取消监听*/
function cancelEvent(object, eventStr, eventFunc) {
    object.removeEventListener(eventStr, eventFunc);
}

/**计算标注的长度 每个汉字长度为11  每个应为字符长度为6*/
function getLabelOffset(name) {
    var re = /[\u4E00-\u9FA5]/g;
    var totalLen = name.length;
    var chineseLen = 0;
    if (name.match(re) != null) {
        chineseLen = name.match(re).length;
    }
    var englishLen = name.length - chineseLen;
    return (chineseLen * 11 + englishLen * 6);
}