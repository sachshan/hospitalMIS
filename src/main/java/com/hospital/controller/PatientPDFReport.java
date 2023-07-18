package com.hospital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.hospital.dao.AptDAO;
import com.hospital.pdf.PatientReportPDF;

@Controller
public class PatientPDFReport {
	
	@Autowired
	AptDAO adao;
	
	@GetMapping("/patient/pdfreport.pdf")
	public View getPDFReport(HttpServletRequest request)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		View view = new PatientReportPDF(id,adao.getApt(id));
		
				
		return view;
	}

}
