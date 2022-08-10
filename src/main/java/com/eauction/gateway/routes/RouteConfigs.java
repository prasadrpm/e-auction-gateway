package com.eauction.gateway.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eauction.gateway.security.AuthenticationFilter;

@Configuration
public class RouteConfigs {
	
	@Autowired
	private AuthenticationFilter authFilter;
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("buyer-Routes", r -> r.path("/api/v1/buyer/**").uri("lb://e-auction-buyer"))
				.route("seller-Routes", r -> r.path("/api/v1/product/**").filters(f -> f.filter(authFilter)).uri("lb://e-auction-seller"))
				.build();
	}

}
