package com.wuxinhua.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSocketMessageBroker  //开启消息代理
public class websocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //配合连接点
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }



    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置消息代理
        registry.enableSimpleBroker("/queue"); //一对一

    }
}
