package br.com.coelhovictor.springapibase.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.coelhovictor.springapibase.controllers.exceptions.FieldMessage;
import br.com.coelhovictor.springapibase.dtos.RegisterDTO;
import br.com.coelhovictor.springapibase.repositories.UserRepository;

public class RegisterValidator implements 
	ConstraintValidator<RegisterValid, RegisterDTO> {
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public void initialize(RegisterValid ann) {
	}

	@Override
	public boolean isValid(RegisterDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		try {
			if(repository.findByEmailIgnoreCase(objDTO.getEmail()) != null)
				list.add(new FieldMessage("name", "A user with that "
						+ "email already exists"));
			
			if(repository.findByUsernameIgnoreCase(objDTO.getUsername()) != null)
				list.add(new FieldMessage("name", "A user with that "
						+ "username already exists"));
		} catch (NullPointerException e) {
			return false;
		}
		
		for(FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
				.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}