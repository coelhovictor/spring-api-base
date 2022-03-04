package br.com.coelhovictor.springapibase.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.coelhovictor.springapibase.controllers.exceptions.FieldMessage;
import br.com.coelhovictor.springapibase.domain.enums.Role;
import br.com.coelhovictor.springapibase.dtos.RolesDTO;

public class RoleValidator implements 
	ConstraintValidator<RoleValid, RolesDTO> {
	
	@Override
	public void initialize(RoleValid ann) {
	}

	@Override
	public boolean isValid(RolesDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		try {
			for(String roleName : objDTO.getRolesNames()) {
				if(Role.toEnum(roleName) == null)
					list.add(new FieldMessage("roles", "Role '" + roleName + "' " +
							"does not exist"));
			}
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