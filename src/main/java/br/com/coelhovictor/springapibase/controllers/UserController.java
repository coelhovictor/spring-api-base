package br.com.coelhovictor.springapibase.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.dtos.RegisterDTO;
import br.com.coelhovictor.springapibase.dtos.UserDTO;
import br.com.coelhovictor.springapibase.services.UserService;

@RestController
@RequestMapping(value = "/users")
@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping(value = "/{userId}")
	public ResponseEntity<User> find(@PathVariable Integer userId) {
		return ResponseEntity.ok(service.findById(userId));
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<User> find(@RequestParam(value = "value")
			String email) {
		return ResponseEntity.ok(service.findByEmail(email));
	}

	@GetMapping()
	public ResponseEntity<Page<User>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "size", defaultValue = "5") Integer size, 
			@RequestParam(value = "orderBy", defaultValue = "createdAt") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<User> list = service.findAll(page, size, orderBy, direction);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody RegisterDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{userId}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Void> update(@PathVariable Integer userId, 
			@Valid @RequestBody UserDTO objDTO) {
		User obj = service.fromDTO(objDTO);
		obj.setId(userId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> delete(@PathVariable Integer userId) {
		service.delete(userId);
		return ResponseEntity.noContent().build();
	}
	
}
