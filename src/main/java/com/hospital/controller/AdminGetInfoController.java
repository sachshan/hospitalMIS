package com.hospital.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hospital.dao.AptDAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Apt;
import com.hospital.pojo.Proc;

@Controller
public class AdminGetInfoController {
	
	@Autowired
	DoctorDAO ddao;
	
	@Autowired
	AptDAO adao;
	
	@Autowired
	PatientDAO pdao;
	
	@Autowired
	ProcDAO procdao;
	
	Comparator<Apt> compareByTime = new Comparator<Apt>() {
		@Override
		public int compare(Apt a1, Apt a2) {
			return a1.getDate().compareTo(a2.getDate());
		}
	};
	
	@GetMapping("admin/getProcs.htm")
	public String getProcs(HttpServletRequest request)
	{
		request.setAttribute("procs", procdao.getProcs());
		return "adminProcs-view";
	}
	
	@GetMapping("admin/getApts.htm")
	public String getApts(HttpServletRequest request)
	{
		List<Apt> aptC = adao.getAptC();
		Collections.sort(aptC,compareByTime); 
		request.setAttribute("aptC", aptC);
		
		List<Apt> aptU = adao.getAptU();
		Collections.sort(aptU,compareByTime); 
		request.setAttribute("aptU", aptU);
		
		return "adminApts-view";
	}
	
	@GetMapping("admin/getDocs.htm")
	public String getDocs(HttpServletRequest request)
	{	
		request.setAttribute("docs", ddao.getDoctors());
		
//		request.setAttribute("doc_procs", procdao.getProcs());
		
		return "adminDocs-view";
	}
	
	@GetMapping("admin/getPatients.htm")
	public String getPatients(HttpServletRequest request)
	{
		request.setAttribute("patients", pdao.getPatients());
		return "adminPatients-view";
	}

}
