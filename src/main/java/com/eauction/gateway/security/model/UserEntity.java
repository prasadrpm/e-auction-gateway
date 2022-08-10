package com.eauction.gateway.security.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Document("user")
@Data
public class UserEntity {

	@MongoId
	private String id;
	private String userName;
	private String password;
}
