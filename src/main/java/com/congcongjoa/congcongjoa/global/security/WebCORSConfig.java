package com.congcongjoa.congcongjoa.global.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
<<<<<<< HEAD
                .allowedOrigins("http://localhost:9090", "http://43.200.245.38", "http://localhost:3000")
=======
                .allowedOrigins("http://localhost:9090",
                        "http://43.200.245.38",
                        "http://localhost:3000")
>>>>>>> e5c60ec859000ea562f078ce098f213e869bfc16
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

}
