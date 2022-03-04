package br.com.coelhovictor.springapibase.controllers;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.dtos.MeDTO;
import br.com.coelhovictor.springapibase.dtos.RegisterDTO;
import br.com.coelhovictor.springapibase.dtos.UserDTO;
import br.com.coelhovictor.springapibase.security.JWTUtil;
import br.com.coelhovictor.springapibase.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private UserService service;

	@Autowired
	private JWTUtil jwtUtil;
	
	@GetMapping("/me")
	public ResponseEntity<MeDTO> me() {
		return ResponseEntity.ok(new MeDTO(UserService.authenticated()));
	}
	
	@PutMapping("/me")
	public ResponseEntity<Void> updateMe(@Valid @RequestBody UserDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj.setUsername(UserService.authenticated().getUsername());
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> insert(@Valid @RequestBody RegisterDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		service.register(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/me").build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		User user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
}
