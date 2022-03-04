package br.com.coelhovictor.springapibase.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.domain.enums.Role;
import br.com.coelhovictor.springapibase.dtos.RolesDTO;

@Service
public class RolesService {

	@Autowired
	private UserService service;
	
	public User insert(String username, RolesDTO objDTO) {
		User user = service.findByUsername(username);
		Set<Role> roles = user.getRoles();
		
		for(Role item : objDTO.getRoles()) {
			if(!roles.contains(item))
				roles.add(item);
		}
		user.setRoles(roles);
		service.update(user);
		return user;
	}
	
	public User delete(String username, RolesDTO objDTO) {
		User user = service.findByUsername(username);
		Set<Role> roles = user.getRoles();
		
		for(Role item : objDTO.getRoles()) {
			if(roles.contains(item))
				roles.remove(item);
		}
		user.setRoles(roles);
		service.update(user);
		return user;
	}
	
}
