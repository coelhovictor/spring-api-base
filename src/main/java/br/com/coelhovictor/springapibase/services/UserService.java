package br.com.coelhovictor.springapibase.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.coelhovictor.springapibase.domain.User;
import br.com.coelhovictor.springapibase.dtos.RegisterDTO;
import br.com.coelhovictor.springapibase.dtos.UserDTO;
import br.com.coelhovictor.springapibase.repositories.UserRepository;
import br.com.coelhovictor.springapibase.services.exceptions.ConflictException;
import br.com.coelhovictor.springapibase.services.exceptions.DataIntegrityException;
import br.com.coelhovictor.springapibase.services.exceptions.NotFoundException;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	public User findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> 
			new NotFoundException("User", id));
	}
	
	public Page<User> findAll(Integer page, Integer linesPerPage,
			String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, 
				Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
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
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void delete(Integer id) {
		User currentUser = authenticated();
		if(currentUser.getId() == id)
			throw new ConflictException("You can't delete yourself");
		
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete this user");
		}
	}
	
	public User fromDTO(RegisterDTO objDTO) {
		return new User(null, objDTO.getName(), objDTO.getUsername(), objDTO.getEmail(),
				passwordEncoder.encode(objDTO.getPassword()), objDTO.getBirthday(),
				new Date(System.currentTimeMillis()));
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(null, objDTO.getName(), null, null,
				passwordEncoder.encode(objDTO.getPassword()), objDTO.getBirthday(),
				new Date(System.currentTimeMillis()));
	}
	
	private void updateData(User newObj, User obj) {
		//can't update username and email
		newObj.setName(obj.getName());
		newObj.setPassword(obj.getPassword());
		newObj.setBirthday(obj.getBirthday());
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
