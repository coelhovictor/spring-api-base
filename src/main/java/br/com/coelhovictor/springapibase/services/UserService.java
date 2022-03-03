package br.com.coelhovictor.springapibase.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.dtos.UserDTO;
import br.com.coelhovictor.springapibase.repositories.UserRepository;
import br.com.coelhovictor.springapibase.services.exceptions.ConflictException;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	public void register(User obj) {
		if(authenticated() != null) 
			throw new ConflictException("You are already "
					+ "authenticated with a user");
		
		insert(obj);
	}
	
	public void insert(User obj) {
		obj.setId(null);
		repository.save(obj);
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(null, objDTO.getName(), objDTO.getUsername(), objDTO.getEmail(),
				passwordEncoder.encode(objDTO.getPassword()), objDTO.getBirthday(),
				new Date(System.currentTimeMillis()));
	}
	
	public static User authenticated() {
		try {
			return (User) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
