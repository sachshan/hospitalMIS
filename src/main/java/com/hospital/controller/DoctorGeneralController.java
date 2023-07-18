package com.hospital.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorGeneralController {
	
	@GetMapping("/doctor/logout.htm")
	public void logout(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		
		try {
			response.sendRedirect("/HospitalMIS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
