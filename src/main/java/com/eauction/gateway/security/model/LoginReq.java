package com.eauction.gateway.security.model;

import lombok.Data;

@Data
public class LoginReq {

	private String userName;
	private String password;
}
