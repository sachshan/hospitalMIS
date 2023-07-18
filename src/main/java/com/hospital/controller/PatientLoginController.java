package com.hospital.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hospital.dao.AptDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.pojo.Apt;
import com.hospital.pojo.Patient;

@Controller
public class PatientLoginController {
	
	@Autowired
	PatientDAO pdao;
	
	@Autowired
	AptDAO adao;
	
	Comparator<Apt> compareByTime = new Comparator<Apt>() {
		@Override
		public int compare(Apt a1, Apt a2) {
			return a1.getDate().compareTo(a2.getDate());
		}
	};
	
	@GetMapping("/patient/login.htm")
	public String throwLoginPage(HttpServletRequest request, HttpServletResponse response)
	{

		
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
	
			String user = (String)session.getAttribute("pname");
			String pass = (String)session.getAttribute("ppass");
			if(user!=null && pass!=null)
				if(pdao.checkLoginDetails(user, pass))
				{
					List<Apt> aptC = adao.getPatientAptC(pdao.getPatient(user));
					Collections.sort(aptC,compareByTime); 
					request.setAttribute("aptC", aptC);
					
					List<Apt> aptU = adao.getPatientAptU(pdao.getPatient(user));
					Collections.sort(aptU,compareByTime); 
					request.setAttribute("aptU", aptU);
					return "patientHome-view";
				}
					
		}
		
		
		return "patientLogin-view";
		
	}
	
	@PostMapping("/patient/login.htm")
	public String getLoginPageInfo(HttpServletRequest request, HttpServletResponse response)
	{
		
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		if(pdao.checkLoginDetails(uname, password))
		{
			HttpSession session = request.getSession();
			session.setAttribute("pname", uname);
			session.setAttribute("ppass", password);
			
			Patient pat = pdao.getPatient(uname);
			request.setAttribute("patient", pat);
			
			
			List<Apt> aptC = adao.getPatientAptC(pdao.getPatient(uname));
			Collections.sort(aptC,compareByTime.reversed()); 
			request.setAttribute("aptC", aptC);
			
			List<Apt> aptU = adao.getPatientAptU(pdao.getPatient(uname));
			Collections.sort(aptU,compareByTime); 
			request.setAttribute("aptU", aptU);
			return "patientHome-view";
		}
		else
		{	
			request.setAttribute("loginStatus", "Wrong Username or Password");
			return "patientLogin-view";
		}
		
	}

}
