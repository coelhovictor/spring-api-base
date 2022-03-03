package br.com.coelhovictor.springapibase.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.services.UserService;

@RestController
public class AuthController {

	@GetMapping(value = "/me")
	public ResponseEntity<User> me() {
		return ResponseEntity.ok(UserService.authenticated());
	}
	
}
