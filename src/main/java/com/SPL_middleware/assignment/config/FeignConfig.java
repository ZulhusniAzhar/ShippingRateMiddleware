package com.SPL_middleware.assignment.config;
import feign.RequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    private static final Logger logger = LoggerFactory.getLogger(FeignConfig.class);

    @Value("${feign.request.origin}")
    private String origin;

    @Value("${feign.request.x-requested-with}")
    private String xRequestedWith;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Add headers from application.properties
            requestTemplate.header("Origin", origin);
            requestTemplate.header("X-Requested-With", xRequestedWith);

            logger.info("Feign Request Headers:");
            requestTemplate.headers().forEach((key, value) ->
                    logger.info("{}: {}", key, value)
            );
        };
    }
}
