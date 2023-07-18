package com.hospital.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hospital.dao.DoctorDAO;
import com.hospital.pojo.Doctor;


@Component
public class DoctorValidator implements Validator {

	@Autowired
	DoctorDAO ddao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Doctor.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"fname", "fname-empty", "Please enter first name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname","lname-empty", "Please enter last name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uname","uname-empty", "Please enter User name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","pass-empty", "Please enter password.");
		
		Doctor doctor = (Doctor)target;
		
		
		if(ddao.checkUsernameExist(doctor.getUname()))
		{
			errors.rejectValue("uname","uname-exists","Please enter different User name, as this one is taken.");
			System.out.println("Uname already exists");
		}
	}

}
