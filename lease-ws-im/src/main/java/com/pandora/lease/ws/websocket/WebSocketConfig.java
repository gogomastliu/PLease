/* (non-Javadoc)
 * 
 */
package com.pandora.lease.ws.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;



/**
 * @ClassName: WebSocketConfig
 * @Description: 注册服务
 * @author 刘斌
 * @createTime 2016年6月28日 上午11:15:49
 *
 */
@Configuration
@EnableWebMvc//这个标注可以不加，如果有加，要extends WebMvcConfigurerAdapter
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer
{
	private Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
	{
		// 用来注册websocket server实现类，第二个参数是访问websocket的地址
		registry.addHandler(chatTextHandler(), "/imServer.do").addInterceptors(new MyHandshakeInterceptor()).setAllowedOrigins("*");
		logger.info("==================================================================================");
		logger.info("==                                                                             ==");
		logger.info("WebSocket系统服务-----------------------------启  动-------------------------------");
		logger.info("==                                                                             ==");
		logger.info("==================================================================================");
		// ----------我们注册两种：ws 和http 实现websocket。
		// ----------这个是使用Sockjs的注册方法
		// registry.addHandler(systemWebSocketHandler(),"/sockjs/webSocketServer").addInterceptors(new MyHandshakeInterceptor()).withSockJS();

	}

	@Bean
	public WebSocketHandler chatTextHandler()
	{
		return new ChatTextHandler();
	}
}