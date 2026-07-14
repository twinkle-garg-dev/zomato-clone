package com.zomato.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("auth-service", r -> r
                        .path("/api/auth/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8081"))

                .route("product-service", r -> r
                        .path("/api/product/**")
                        .filters(f -> f.stripPrefix(2))
                        .uri("http://localhost:8082"))

                .build();
    }
}
