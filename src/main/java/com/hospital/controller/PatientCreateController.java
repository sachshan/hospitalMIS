package com.hospital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.hospital.dao.PatientDAO;
import com.hospital.pojo.Patient;
import com.hospital.validator.PatientValidator;

@Controller
public class PatientCreateController {
	
	@Autowired
	PatientValidator validator;
	
	@GetMapping("/patient/create.htm")
	public String doGet(ModelMap model, Patient patient)
	{	
		System.out.println("hi this is the create controller.");
		model.addAttribute("patient", patient);
		return "patientCreate-view";
	}
	
	@PostMapping("/patient/create.htm")
	public String doPost(@ModelAttribute("patient")Patient patient, SessionStatus status, HttpServletRequest request, BindingResult result)
	{	
		validator.validate(patient, result);
		
		if(result.hasErrors())
			return "patientCreate-view";
		
		PatientDAO pdao = new PatientDAO();
		pdao.createPatient(patient);
		
		request.setAttribute("patient", patient);
		status.setComplete();
		
		
		
		return "patientSaved-view";
	}

}
