package com.hospital.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.hospital.dao.PatientDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Proc;
import com.hospital.validator.ProcValidator;


@Controller
public class AdminCreateProcController {
	
	@Autowired
	DoctorDAO ddao;
	
	@Autowired
	ProcDAO pdao;
	
	@Autowired
	ProcValidator pval;
	
	@GetMapping("admin/createProcedure.htm")
	public String showForm(HttpServletRequest request)
	{	
		
		
		List<Doctor> doctors = ddao.getDoctors();
		request.setAttribute("doctors", doctors);
		return "adminCreateProcedure-view";
	}
	
	@PostMapping("admin/createProcedure.htm")
	public String getData(HttpServletRequest request)
	{
		Proc proc = new Proc();
		proc.setName(request.getParameter("name"));
		proc.setCost(Integer.parseInt(request.getParameter("cost")));
		proc.setDuration(Integer.parseInt(request.getParameter("duration")));
		
		ArrayList<String> finalDocs = new ArrayList<>();
		
		for(String i: request.getParameterValues("doc"))
		{
			if(!finalDocs.contains(i))
				finalDocs.add(i);
		}
		
		for(String i:finalDocs)
		{
			proc.getDoctors().add(ddao.getDoctor(i)); 
		}
		
		
		pdao.createProc(proc);		
		

		return "adminProcedureSaved-view";
	}
	
	

}
