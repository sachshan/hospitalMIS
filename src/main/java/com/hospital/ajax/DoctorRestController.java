package com.hospital.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dao.AptDAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Apt;

@RestController
public class DoctorRestController {
	
	@Autowired
	ProcDAO pdao;
	@Autowired
	AptDAO adao;
	@Autowired
	DoctorDAO ddao;
	
	
	@PostMapping("doctor/deleteApt.htm")
	public void deleteApt(HttpServletRequest request)
	{
		
		Apt apt = (Apt)adao.getApt(Integer.parseInt(request.getParameter("aptId")));
		adao.deleteApt(apt);		
		
	}
	
	@PostMapping("doctor/completeApt.htm")
	public void completeApt(HttpServletRequest request)
	{
		
		adao.aptChangeStatus(Integer.parseInt(request.getParameter("aptId")),'C');
		
		
	}
	
	@PostMapping("doctor/reportApt.htm")
	public void reportApt(HttpServletRequest request)
	{
		
		adao.aptSubmitReport(Integer.parseInt(request.getParameter("aptId")),request.getParameter("report"));
			
		
	}
	
	
	


}
