package com.hospital.validator;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hospital.dao.PatientDAO;
import com.hospital.pojo.Patient;

@Component
public class PatientValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		
		return Patient.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname","fname-empty", "Please enter first name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email","email-empty", "Please enter email.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname","lname-empty", "Please enter last name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uname","uname-empty", "Please enter User name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","pass-empty", "Please enter password.");

		Patient patient = (Patient)target;
		
		if(patient.getGender()==('U'))
		{
			errors.rejectValue("gender","gender-not-selected","Please choose Gender");
			System.out.println("Gender not selected");
		}
		
		if(patient.getDob().equals(LocalDate.now()))
		{
			errors.rejectValue("dob","dob-empty","Please enter data of Birth");
			System.out.println("true date not filled");
		}
		
		
		PatientDAO pdao = new PatientDAO();
		
		if(pdao.checkUsernameExist(patient.getUname()))
		{
			errors.rejectValue("uname","uname-exists","Please enter different User name, as this one is taken.");
			System.out.println("Uname already exists");
		}
		
	
		
	
	
	}
	
	

}
