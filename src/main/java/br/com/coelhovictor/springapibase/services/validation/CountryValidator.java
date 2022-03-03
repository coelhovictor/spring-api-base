package br.com.coelhovictor.springapibase.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.coelhovictor.springapibase.controllers.exceptions.FieldMessage;
import br.com.coelhovictor.springapibase.dtos.CountryDTO;
import br.com.coelhovictor.springapibase.repositories.CountryRepository;

public class CountryValidator implements 
	ConstraintValidator<CountryValid, CountryDTO> {
	
	@Autowired
	private CountryRepository repository;
	
	@Override
	public void initialize(CountryValid ann) {
	}

	@Override
	public boolean isValid(CountryDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(repository.findByNameIgnoreCase(objDTO.getName()) != null)
			list.add(new FieldMessage("name", "A country with that "
					+ "name already exists"));
		
		for(FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
				.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}