package com.hospital.ajax;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dao.AptDAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Apt;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PatientRestController {
	
	@Autowired
	ProcDAO pdao;
	@Autowired
	AptDAO adao;
	@Autowired
	DoctorDAO ddao;
	
	@PostMapping("patient/getSlots.htm")
	public List<LocalTime> checkTimes2(HttpServletRequest request)
	{
		Doctor doc = ddao.getDoctor(request.getParameter("doc"));
		LocalDate date = LocalDate.parse(request.getParameter("date"));
		Proc proc = pdao.getProc(Integer.parseInt(request.getParameter("procId")));
		
		List<LocalTime> aslots = adao.getATimes(proc, doc, date);
		
		for(LocalTime a: aslots)
			System.out.println(a);
		
		return aslots;
		

	}
	
	@PostMapping("patient/getProcDoc.htm")
	public Collection<Doctor> getDocs(HttpServletRequest request)
	{
		
		Proc currentProc = pdao.getProc(Integer.parseInt(request.getParameter("procId")));
		Collection<Doctor> docs = currentProc.getDoctors();
		
		return docs;
	}
	
	@PostMapping("patient/deleteApt.htm")
	public void deleteApt(HttpServletRequest request)
	{
		
		Apt apt = (Apt)adao.getApt(Integer.parseInt(request.getParameter("aptId")));
		adao.deleteApt(apt);
		
		
	}

}
