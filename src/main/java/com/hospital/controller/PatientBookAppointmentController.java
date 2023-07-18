package com.hospital.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hospital.dao.AptDAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Apt;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;


@Controller
public class PatientBookAppointmentController {
	
	@Autowired
	ProcDAO pdao;
	
	@Autowired
	PatientDAO patdao;
	
	@Autowired
	DoctorDAO ddao;
	
	@Autowired
	AptDAO adao;
	
	@GetMapping("patient/bookapt.htm")
	public String showAptForm(HttpServletRequest request)
	{
		request.setAttribute("procs",pdao.getProcs() );
		pdao.getProcs().get(0);
		
		return "patientBookApt-view";
	}
	
	@PostMapping("patient/bookapt.htm")
	public String getAppt(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		Patient patient = patdao.getPatient((String)session.getAttribute("pname"));
		Doctor doctor = ddao.getDoctor(request.getParameter("docList"));
		LocalDate date = LocalDate.parse(request.getParameter("date1"));
		LocalTime beginTime = LocalTime.parse(request.getParameter("slotList"));
		Proc proc = pdao.getProc(Integer.parseInt(request.getParameter("proc")));
		
		Apt apt = new Apt();
		apt.setPatient(patient);
		apt.setBeginTime(beginTime);
		apt.setDate(date);
		apt.setDoc(doctor);
		apt.setProc(proc);
		apt.setStatus('U');
		
		adao.saveApt(apt);
		
		request.setAttribute("aptC", adao.getPatientAptC(patient));
		request.setAttribute("aptU", adao.getPatientAptU(patient));
		
		return "patientHome-view";
	}
}
