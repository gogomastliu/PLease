<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String serverPath = request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Websocket</title>

    <script src="<%=basePath%>/reSources/common/jquery-1.12.3.js"></script>
    <!--引入sockjs-->
    <script src="<%=basePath%>/reSources/common/sockjs-0.3.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script type="text/javascript">
        var socket = null;
        var osType = "windowsOs";

        function connect() {
            var userId = $("#userid").val();
            var userName = $("#username").val();
            var url = "localhost:8080/wsim/imServer.do?id=" + userId + "&name=" + userName + "&os=" + osType;
            var urlpath = "ws://" + url;
            var surlpath = "http://" + url;
            if ('WebSocket' in window) {
                var urlpath = "ws://" + url;
                socket = new WebSocket(urlpath);
            } else if ('MozWebSocket' in window) {
                socket = new MozWebSocket(urlpath);
            } else {
                socket = new SockJS(surlpath);

            }
            socket.onopen = function () {
                log('建立连接');
            };

            socket.onmessage = function (event) {
                log('接收消息: ' + event.data);
                var json = JSON.parse(event.data);
                switch (json.msgType) {
                    case "通知":
                        //更新所有在线用户
                        addUserList(json);
                        break;
                    case "上线通知":
                        //用户上线后通知
                        addUser(json.data.id, json.data.username);
                        break;
                    case "离线通知":
                        //有用户离线后通知
                        removeUser(json);
                        break;
                    case "离线请求":
                        //接收离线时未读信息
                        refurbishMsg("离线应答", json);
                        break;
                    case "在线请求":
                        //其他人发来的在线请求
                        refurbishMsg("在线应答", json);
                        break;
                    case "在线应答":
                        //本人发送请求的在线应答-删除消息池消息
                        console.log(json);
                        break;
                }
                $("#connect").hide();
                $("#disconnect").show();
            };

            socket.onclose = function (event) {
                console.log(event);
                log('连接关闭：' + getNowFormatDate());
                $("#connect").show();
                $("#disconnect").hide();
            };
        }

        /**
         *在线发送消息
         */
        function sendMsg() {
            var userid=$("#hchooseUserId").val()
            if(userid==undefined || userid==''){
                return alert("请选择一个在线发送消息的用户！");
            }
            var msg = $("#message").val();
            //封装Json
            var sJson = new Object();
            sJson.timeStamp = new Date().getTime();
            sJson.osType = osType;
            sJson.msgType = "在线请求";
            sJson.data = msg;
            sJson.receivemanId =userid;
            if (socket.readyState == socket.OPEN) {
                if (socket != null) {
                    socket.send(JSON.stringify(sJson));
                } else {
                    alert('请先建立连接.');
                }
            }
        }

        function disconnect() {
            if (socket != null) {
                socket.close();
                socket = null;
                RemoveUserList();
            }
        }

        function log(message) {
            var html = '<p style="word-wrap: break-word;">' + message + '</p>';
            $("#console").append(html);
        }
    </script>
    <script type="text/javascript">

        /**
         *   消息确认发送
         * @param msgType
         * @param json
         */
        function sendAnswerMsg(msgType, json) {
            var sJson = new Object();
            sJson.timeStamp = json.timeStamp;
            sJson.osType = json.osType;
            sJson.msgType = msgType;
            sJson.id = json.id;
            if (socket.readyState == socket.OPEN) {
                if (socket != null) {
                    socket.send(JSON.stringify(sJson));
                } else {
                    alert('请先建立连接.');
                }
            }
        }

        function refurbishMsg(msgType, json) {
            //应答服务器将未读置为已读
            sendAnswerMsg(msgType, json);
            for (var o in json.data) {
                console.log(json.data[o]);
            }
        }

        function addUser(id, name) {
            $("#uld").append("<button id=\"btn_" + id + "\" type=\"button\" class=\"list-group-item\" value=\"" + id + "\" onclick=\"chooseUser(" + id + ")\">" + name + "</button>");
        }

        function addUserList(json) {
            console.log(json);
            for (var o in json.data) {
                console.log(json.data);
                addUser(json.data[o].id, json.data[o].username);
            }
        }

        function removeUser(json) {
            if ($("#btn_" + json.data.id).length > 0) {
                var node = document.getElementById("btn_" + json.data.id);
                node.parentNode.removeChild(node);
                console.log(node);
            }
        }

        function RemoveUserList() {
            var s = document.getElementById('uld');
            var t = s.childNodes.length;
            for (var i = t - 1; i > 0; i--) {
                s.removeChild(s.childNodes[i]);
            }
        }

        function chooseUser(id) {
            console.log(id);
            $("#hchooseUserId").val(id);
        }

        function getNowFormatDate() {
            var date = new Date();
            var seperator1 = "-";
            var seperator2 = ":";
            var month = date.getMonth() + 1;
            var strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
            return currentdate;
        }
    </script>
    <script>
        $( document ).ready(function() {
            console.log( "ready!" );
            getLocation();
        });
        var x=document.getElementById("demo");
        function getLocation(){
            if (navigator.geolocation){
                navigator.geolocation.getCurrentPosition(showPosition);
            }else{
                x.innerHTML="Geolocation is not supported by this browser.";
            }
        }
        function showPosition(position){
            x.innerHTML="Latitude: " + position.coords.latitude + "<br />Longitude: " + position.coords.longitude;
            console.log(position.coords.latitude+"    <br />"+position.coords.longitude);
        }
    </script>
</head>
<body>

<div>
    <div id="connect-container" style="text-align: center;margin: 40px;">
        <div>
            <input id="userid" style="width: 300px" placeholder="输入用户ID"/>
            <input id="username" style="width: 300px" placeholder="输入用户名称"/>
            <br/>
            <button id="connect" onclick="connect();">连接</button>
            <button id="disconnect" style="display: none" onclick="disconnect()">关闭连接</button>
            <input id="message" style="width: 800px" placeholder="输入消息内容"/>
            <button onclick="sendMsg();">发送消息</button>
        </div>
        <div id="console-container">
            <div class="col-lg-2 col-lg-offset-2" style="height: 500px;background-color:#2aabd2;">
                <input type="hidden" id="hchooseUserId">
                <%--在线用户列表--%>
                <div id="uld" class="list-group">
                </div>
            </div>
            <div id="console" class="col-lg-6"></div>
            <div id="demo" class="col-lg-6"></div>
        </div>

    </div>
</div>
</body>
</html>