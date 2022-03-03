package br.com.coelhovictor.springapibase.services.validation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.coelhovictor.springapibase.controllers.exceptions.FieldMessage;
import br.com.coelhovictor.springapibase.dtos.ContractDTO;

public class ContractValidator implements 
	ConstraintValidator<ContractValid, ContractDTO> {
	
	@Override
	public void initialize(ContractValid ann) {
	}

	@Override
	public boolean isValid(ContractDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		Calendar calendarMin = Calendar.getInstance();
		calendarMin.add(Calendar.YEAR, -150);
		Date minDate = calendarMin.getTime();
		
		Calendar calendarMax = Calendar.getInstance();
		calendarMax.add(Calendar.YEAR, 100);
		Date maxDate = calendarMax.getTime();
		
		if(objDTO.getStartDate().before(minDate))
			list.add(new FieldMessage("startDate", "Must be greater "
					+ "than " + dateFormat.format(minDate)));
		
		if(objDTO.getEndDate() != null) {
			if(objDTO.getEndDate().after(maxDate))
				list.add(new FieldMessage("endDate", "Must be less "
						+ "than " + dateFormat.format(maxDate)));
			
			if(objDTO.getEndDate().before(objDTO.getStartDate()))
				list.add(new FieldMessage("endDate", "Must be greater than "
						+ "the startDate"));
		}
		
		for(FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
				.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
	
}