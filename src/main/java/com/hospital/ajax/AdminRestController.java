package com.hospital.ajax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.dao.AptDAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Apt;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;

@RestController
public class AdminRestController {
	
	@Autowired
	AptDAO adao;
	
	@Autowired
	ProcDAO pdao;	
	
	@Autowired
	PatientDAO patdao;
	
	@Autowired
	DoctorDAO ddao;
	
	@PostMapping("admin/deleteApt.htm")
	public void deleteApt(HttpServletRequest request)
	{
		
		Apt apt = (Apt)adao.getApt(Integer.parseInt(request.getParameter("aptId")));
		
		adao.deleteApt(apt);
		
		
	}
	
	@PostMapping("admin/deleteProc.htm")
	public void deleteProc(HttpServletRequest request)
	{
		
		Proc proc = (Proc)pdao.getProc(Integer.parseInt(request.getParameter("procId")));
		
		List<Apt> apts = adao.getAptProc(proc);
		
		for(Apt a: apts)
			adao.deleteApt(a);
		
		
		pdao.deleteProc(proc);
		
		
	}
	
	@PostMapping("admin/deletePatient.htm")
	public void deletePatient(HttpServletRequest request)
	{
		
		Patient patient = patdao.getPatientID(request.getParameter("patientID"));
		System.out.println(patient.getFname());
				
		List<Apt> apts = adao.getPatientAptU(patient);
		for(Apt a: apts)
			adao.deleteApt(a);
		
		
		apts = adao.getPatientAptC(patient);
		for(Apt a: apts)
			adao.deleteApt(a);
		
		
		patdao.deletePatient(patient);
		
		
	}
	
	@PostMapping("admin/deleteDoctor.htm")
	public void deleteDoctor(HttpServletRequest request)
	{
		Doctor doc = ddao.getDoctor(request.getParameter("docID"));
		
		List<Apt> apts = adao.getDoctorAptAll(doc);
		
		for(Apt a: apts)
			adao.deleteApt(a);
		
		List<Proc> procs = pdao.getProcs();
		
		for(Proc proc: procs)
		{
			if(proc.getDoctors().contains(doc))
			{
				if(proc.getDoctors().size()==1)
					pdao.deleteProc(proc);
				else
				{
					pdao.removeDoc(proc, doc);
				}
			}
		}
		
		
		ddao.deleteDoctor(doc);
		
	}

}
