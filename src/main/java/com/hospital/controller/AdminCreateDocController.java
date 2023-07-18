package com.hospital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hospital.dao.DoctorDAO;
import com.hospital.pojo.Doctor;
import com.hospital.validator.DoctorValidator;

@Controller
public class AdminCreateDocController {
	
	@Autowired
	DoctorDAO ddao;
	
	@Autowired
	DoctorValidator validator;
	
	@GetMapping("admin/createDoctor.htm")
	public String showForm(ModelMap model, Doctor doctor)
	{
		model.addAttribute("doctor", doctor);
		return "doctorCreate-view";
		
	}
	
	@PostMapping("/admin/createDoctor.htm")
	public String getForm(@ModelAttribute("doctor")Doctor doctor, HttpServletRequest request, BindingResult result, Errors errors)
	{
		validator.validate(doctor,result);
		
		if(result.hasErrors())
			return "doctorCreate-view";
		
		//Save the doctor profile.
		ddao.createDoctor(doctor);
		
		return "doctorSaved-view";
		
	}

}
