package br.com.coelhovictor.springapibase.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coelhovictor.springapibase.dtos.RolesDTO;
import br.com.coelhovictor.springapibase.services.RolesService;

@RestController
@RequestMapping("/users/{id}/roles")
public class RolesController {
	
	@Autowired
	private RolesService service;

	@PutMapping()
	public ResponseEntity<Void> update(@PathVariable Integer id, 
			@Valid @RequestBody RolesDTO objDTO) {
		service.insert(id, objDTO);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping()
	public ResponseEntity<Void> delete(@PathVariable Integer id, 
			@Valid @RequestBody RolesDTO objDTO) {
		service.delete(id, objDTO);
		return ResponseEntity.noContent().build();
	}
	
}
