package br.com.coelhovictor.springapibase.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.coelhovictor.springapibase.controllers.exceptions.FieldMessage;
import br.com.coelhovictor.springapibase.dtos.AddressDTO;

public class AddressValidator implements 
	ConstraintValidator<AddressValid, AddressDTO> {
	
	@Override
	public void initialize(AddressValid ann) {
	}

	@Override
	public boolean isValid(AddressDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		int zip = objDTO.getZip();
		String zipStr = zip + "";
		
		if(zip < 0 || zip > 999999999)
			list.add(new FieldMessage("zip", "Invalid value"));
		
		if(zipStr.length() < 5 || zipStr.length() > 9)
			list.add(new FieldMessage("zip", "The range must be "
					+ "between 5 and 9 characters"));
		
		for(FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
				.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}