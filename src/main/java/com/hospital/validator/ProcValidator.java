package com.hospital.validator;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hospital.dao.PatientDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;

@Component
public class ProcValidator implements Validator {
	
	@Autowired
	ProcDAO pdao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		
		return Proc.class.isAssignableFrom(clazz);
	}
	
	
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
				
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","name-empty", "Please enter name of procedure.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration","cost-empty", "Please enter cost.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cost","cost-empty", "Please enter duration in minutes.");


		Proc proc = (Proc)target;
		
		if(proc.getCost()<=0)
		{
			errors.rejectValue("cost","cost-positive","Please enter valid cost");
			System.out.println("Cost is negative or zero");
		}
		
		if(proc.getDuration()<=0)
		{
			errors.rejectValue("duration","duration-positive","Please enter valid duration");
			System.out.println("Duration is negative or zero");
		}
		
		
		
		
		if(pdao.checkNameExist(proc.getName()))
		{
			errors.rejectValue("name","name-exists","Please enter different User name, as this one already exists.");
			System.out.println("Name already exists");
		}
		
	
		
	
	
	}

}
