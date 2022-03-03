package br.com.coelhovictor.springapibase.services.validation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.coelhovictor.springapibase.controllers.exceptions.FieldMessage;
import br.com.coelhovictor.springapibase.dtos.CompanyDTO;

public class CompanyValidator implements 
	ConstraintValidator<CompanyValid, CompanyDTO> {
	
	@Override
	public void initialize(CompanyValid ann) {
	}

	@Override
	public boolean isValid(CompanyDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Calendar calendarMin = Calendar.getInstance();
		calendarMin.add(Calendar.YEAR, -550);
		Date minDate = calendarMin.getTime();
		
		Date maxDate = new Date(System.currentTimeMillis());
		
		if(objDTO.getFoundationDate().before(minDate))
			list.add(new FieldMessage("foundationDate", "Must be greater "
					+ "than " + dateFormat.format(minDate)));
		
		if(objDTO.getFoundationDate().after(maxDate))
			list.add(new FieldMessage("foundationDate", "Must be less "
					+ "than " + dateFormat.format(maxDate)));
		
		for(FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
				.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}