package com.eauction.gateway.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.eauction.gateway.security.model.UserEntity;
import com.google.common.base.Optional;

public interface UserRepo extends MongoRepository<UserEntity, String>{
	
	Optional<UserEntity> findByUserNameAndPassword(String userName, String password);
}
