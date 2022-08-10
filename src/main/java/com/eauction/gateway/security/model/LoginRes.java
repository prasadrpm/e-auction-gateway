package com.eauction.gateway.security.model;

import lombok.Data;

@Data
public class LoginRes {

	private String token;
	private String userName;
}
