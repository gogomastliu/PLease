/* -- -- -- --
  Created by Pandora Company Limited.
     User: Liubin
     Date: 2017/11/28 9:32
  Comment: 
 -- -- -- -- */
var osType = "windowsOs";
var socket = null;
new_element=document.createElement("script");
new_element.setAttribute("type","text/javascript");
new_element.setAttribute("src",contextPath+"/reSources/common/Util.js");// 在这里引入了a.js
document.head.appendChild(new_element);
var layimC;
function SetOnlineUsers(json) {
    layui.use('layim', function (layim) {
        layimC=layim;
        //监听layim建立就绪
        layim.on('ready', function(res){
            //查看离线请求
            //封装Json
            var sJson = new Object();
            sJson.timeStamp = new Date().getTime();
            sJson.osType = osType;
            sJson.msgType = "离线请求";
            if (socket.readyState == socket.OPEN) {
                if (socket != null) {
                    socket.send(JSON.stringify(sJson));
                } else {
                    alert('请先建立连接.');
                }
            }
        });
        //基础配置
        layim.config({
            title: json.data.currentum.username,
            min: 'true',
            right: '30px',
            initSkin: '3.jpg',//粉色系默认图片
            notice: true,//是否开启桌面消息提醒
            isfriend: true,
            isgroup: false,
            maxLength: 3000,//可允许的消息最大字符长度
            //copyright: true,//是否授权。如果非授权获得，或将LayIM应用在第三方，建议保留，即不设置。
            init: {
                //我的信息
                mine: json.data.currentum
                //好友列表
                , friend:json.data.groups
            }
            , msgbox: layui.cache.dir + 'css/modules/layim/html/msgbox.html' //消息盒子页面地址，若不开启，剔除该项即可
            , chatLog: layui.cache.dir + 'css/modules/layim/html/chatlog.html?aa=bb' //聊天记录页面地址，若不开启，剔除该项即可
        });
        //监听发送的聊天消息
        layim.on('sendMessage', function(res) {
            var mine = res.mine;
            var to = res.to; //对方的信息
            // if(to.type === 'friend'){
            //     layim.setChatStatus('<span style="color:#FF5722;">对方正在输入。。。</span>');
            // }
            sendMsg(to.id,mine.content);
        });
    });

}
/**
 * 建立WebSocket连接
 */
function connect(url) {
    url=url + "&os=" + osType;
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
                console.log(json);
                //更新所有在线用户
                SetOnlineUsers(json);
                //SetOnlineUsers(json);
                break;
            case "上线通知":
                //用户上线后通知
                setunGray(json.data.id, json.data.username);
                break;
            case "离线通知":
                //有用户离线后通知
                setGray(json.data.id, json.data.username);
                break;
            case "离线请求":
                //接收离线时未读信息
                refurbishMsg("离线应答", json);
                break;
            case "在线请求":
                //其他人发来的在线请求消息
                refurbishMsg("在线应答", json);
                break;
            case "在线应答":
                //本人发送请求的在线应答-删除消息池消息
                console.log(json);
                break;
        }
    };

    socket.onclose = function (event) {
        console.log(event);
        log('连接关闭：' + getNowFormatDate());
    };
}

/**
 * 关闭连接
 */
function disconnect() {
    if (socket != null) {
        socket.close();
        socket = null;
        RemoveUserList();
    }
}

/**
 * 日志输出
 * @param message
 */
function log(message) {
    console.log(message);
}

/**
 * 刷新消息
 * @param msgType
 * @param json
 */
function refurbishMsg(msgType, json) {
    console.log(json.data);
    layimC.getMessage(json.data);
    //应答服务器将未读置为已读
    sendAnswerMsg(msgType, json);
}


/**
 *在线发送消息
 */
function sendMsg(userid,message) {
    if(userid==undefined || userid==''){
        return alert("请选择一个在线发送消息的用户！");
    }
    var msg = message;
    //封装Json
    var sJson = new Object();
    sJson.timeStamp = new Date().getTime();
    sJson.osType = osType;
    sJson.msgType = "在线请求";
    //sJson.data = msg;
    sJson.msgContent=msg;
    sJson.receivemanId =userid;
    if (socket.readyState == socket.OPEN) {
        if (socket != null) {
            socket.send(JSON.stringify(sJson));
        } else {
            alert('请先建立连接.');
        }
    }
}

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

function setGray(id,name) {
    layui.layim.setFriendStatus(id, 'offline');
    layer.msg('已成功将好友['+name+']置灰', {
        icon: 1
    });
}
/**
 * 好友上线
 * @param id
 */
function setunGray(id,name) {
    layui.layim.setFriendStatus(id, 'online');
    layer.msg('成功取消好友['+name+']置灰状态', {
        icon: 1
    });
}