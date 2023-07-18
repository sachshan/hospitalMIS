package com.hospital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppointmentCreateController {
	
	@GetMapping(value="/patient/createapt.htm")
	public String handleGet()
	{
		return "patientBookApt-view";
	}
	
	@PostMapping(value="/patient/createapt.htm")
	public String handlePost()
	{
		return "";
	}

}
