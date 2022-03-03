package br.com.coelhovictor.springapibase.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.dtos.RegisterDTO;
import br.com.coelhovictor.springapibase.services.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService service;
	
	@GetMapping("/me")
	public ResponseEntity<User> me() {
		return ResponseEntity.ok(UserService.authenticated());
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> insert(@Valid @RequestBody RegisterDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		service.register(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/me").build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
