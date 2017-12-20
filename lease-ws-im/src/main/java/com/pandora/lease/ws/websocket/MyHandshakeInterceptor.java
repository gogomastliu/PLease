/* (non-Javadoc)
 * 
 */
package com.pandora.lease.ws.websocket;

import com.pandora.lease.model.enums.OsTypeEnum;
import com.pandora.lease.model.manager.user.UserModel;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author 刘斌
 * @ClassName: MyHandshakeInterceptor
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @createTime 2016年6月28日 上午11:17:06
 */
public class MyHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map map) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            if (session != null) {
            Integer id = Integer.parseInt(servletRequest.getServletRequest().getParameter("id"));
            String name = servletRequest.getServletRequest().getParameter("name");
            OsTypeEnum os = OsTypeEnum.valueOf(servletRequest.getServletRequest().getParameter("os"));
            UserModel userModel = new UserModel();
            userModel.setId(id);
            userModel.setUsername(name);//用户名称如：访客001
            userModel.setOstype(os);
            //用户终端类型
            map.put("userModel", userModel);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
            String formatStr = formatter.format(new Date());
            System.out.println("[Sout ][" + formatStr + "] ===》用户ID：" + id + "   ===》用户名称: " + name + "     =====》握 手 成 功！《=====");
//            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {

    }
}
