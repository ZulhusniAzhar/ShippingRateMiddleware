package com.SPL_middleware.assignment.config;


import feign.RequestInterceptor;
import feign.okhttp.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private static final Logger logger = LoggerFactory.getLogger(FeignConfig.class);

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Add missing headers since I cant set it up using @Header in the Feign Interface
            requestTemplate.header("Origin", "https://www.jtexpress.my");
            requestTemplate.header("X-Requested-With", "XMLHttpRequest");

            logger.info("🚀 Feign Request Headers:");
            requestTemplate.headers().forEach((key, value) ->
                    logger.info("{}: {}", key, value)
            );
        };
    }
    @Bean
    public feign.Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
