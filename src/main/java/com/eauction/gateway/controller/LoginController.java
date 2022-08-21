package com.eauction.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eauction.gateway.repo.UserRepo;
import com.eauction.gateway.security.JwtTokenUtil;
import com.eauction.gateway.security.model.LoginReq;
import com.eauction.gateway.security.model.LoginRes;
import com.eauction.gateway.security.model.UserEntity;
import com.google.common.base.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
		
	@GetMapping()
	public ResponseEntity<String> getInfo() {
		return ResponseEntity.ok("Gateway Service");
	}
	
	@PostMapping("login")
	public ResponseEntity<?> authenticate(@RequestBody LoginReq req) {
		if(this.authenticateUser(req)) {
			final String token = jwtTokenUtil.generateToken(req.getUserName());
			LoginRes loginRes = new LoginRes();
			loginRes.setToken(token);
			loginRes.setUserName(req.getUserName());
			return ResponseEntity.ok(loginRes);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Autherized");
		}
	}
	
	private boolean authenticateUser(LoginReq req) {
		Optional<UserEntity> user = userRepo.findByUserNameAndPassword(req.getUserName(), req.getPassword());
		if(user.isPresent()) {
			return true;
		}
		return false;
	}
	
	
}
