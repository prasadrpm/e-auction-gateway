package com.eauction.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.eauction.gateway.*")
public class EAuctionGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EAuctionGatewayApplication.class, args);
	}

	/*
	 * @Bean public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) { return
	 * builder.routes() .route(r -> r.path("/api/v1/buyer/**")
	 * .uri("localhost:8090/")) .route(r -> r.path("/api/v1/product/**")
	 * .uri("localhost:8080")) .build(); }
	 */
}
