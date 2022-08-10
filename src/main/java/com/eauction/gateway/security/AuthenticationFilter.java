package com.eauction.gateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.eauction.gateway.exception.JwtTokenMalformedException;
import com.eauction.gateway.exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AuthenticationFilter implements GatewayFilter {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.error("filter invoked =======<<<>>>");
		ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();
		if (!request.getHeaders().containsKey("Authorization")) {
			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.UNAUTHORIZED);
			return response.setComplete();
		}
		final String token = request.getHeaders().getOrEmpty("Authorization").get(0);
		try {
			jwtTokenUtil.validateToken(token);
		} catch (JwtTokenMalformedException | JwtTokenMissingException e) {
			// e.printStackTrace();

			ServerHttpResponse response = exchange.getResponse();
			response.setStatusCode(HttpStatus.BAD_REQUEST);

			return response.setComplete();
		}
		String t = token.substring(7);
		Claims claims = jwtTokenUtil.getClaims(t);
		exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
		return chain.filter(exchange);
	}

}
