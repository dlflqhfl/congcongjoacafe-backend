package com.congcongjoa.congcongjoa.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket 엔드포인트 등록 (SockJS 지원 포함)
        registry.addEndpoint("/websocket") // 브라우저에서 접근할 WebSocket 엔드포인트
                .setAllowedOriginPatterns("*") // 모든 Origin 허용 (CORS)
                .withSockJS(); // SockJS를 통해 WebSocket을 지원하지 않는 브라우저 대체
    }

}
