package com.hospital.controller;

import java.util.Collection;
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
import com.hospital.dao.DoctorDAO;
import com.hospital.pojo.Apt;
import com.hospital.pojo.Doctor;

@Controller
public class DoctorLoginController {
	
	@Autowired
	DoctorDAO ddao;
	
	@Autowired
	AptDAO adao;
	
	Comparator<Apt> compareByTime = new Comparator<Apt>() {
		@Override
		public int compare(Apt a1, Apt a2) {
			return a1.getDate().compareTo(a2.getDate());
		}
	};
	
	@GetMapping("/doctor/login.htm")
	public String throwLoginPage(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
	
			String user = (String)session.getAttribute("dname");
			String pass = (String)session.getAttribute("dpass");
			if(user!=null && pass!=null)
				if(ddao.checkLoginDetails(user, pass))
				{
					Doctor doc = ddao.getDoctorU(user);
					request.setAttribute("doctor", doc);
					List<List<Apt>> dapts = adao.getDoctorApt(doc);
					
					
					List<Apt> aptU = dapts.get(0);
					Collections.sort(aptU, compareByTime);
					
					List<Apt> aptT = dapts.get(1);
					Collections.sort(aptT, compareByTime);
					
					List<Apt> aptC = dapts.get(2);
					Collections.sort(aptC, compareByTime);
					
					List<Apt> aptP = dapts.get(3);
					Collections.sort(aptP, compareByTime.reversed());
					
					
					
					request.setAttribute("aptU",aptU);
					request.setAttribute("aptT",aptT);
					request.setAttribute("aptC",aptC);
					request.setAttribute("aptP",aptP);					
					
					return "doctorHome-view";
				}
		}
		
		
		
		return "doctorLogin-view";
		
	}
	
	
	@PostMapping("/doctor/login.htm")
	public String getLoginPageInfo(HttpServletRequest request, HttpServletResponse response)
	{
		
		
		String uname = request.getParameter("uname");
		String password = request.getParameter("password");
		
		if(ddao.checkLoginDetails(uname, password))
		{
			HttpSession session = request.getSession();
			session.setAttribute("dname", uname);
			session.setAttribute("dpass", password);
			
			Doctor doc = ddao.getDoctorU(uname);
			request.setAttribute("doctor", doc);
			List<List<Apt>> dapts = adao.getDoctorApt(doc);
			
			
			List<Apt> aptU = dapts.get(0);
			Collections.sort(aptU, compareByTime);
			
			List<Apt> aptT = dapts.get(1);
			Collections.sort(aptT, compareByTime);
			
			List<Apt> aptC = dapts.get(2);
			Collections.sort(aptC, compareByTime);
			
			List<Apt> aptP = dapts.get(3);
			Collections.sort(aptP, compareByTime.reversed());
			
			
			
			request.setAttribute("aptU",aptU);
			request.setAttribute("aptT",aptT);
			request.setAttribute("aptC",aptC);
			request.setAttribute("aptP",aptP);
//			request.setAttribute("aptU",dapts.get(0));
//			request.setAttribute("aptT",dapts.get(1));
//			request.setAttribute("aptC",dapts.get(2));
//			request.setAttribute("aptP",dapts.get(3));	
			
			
			for(Apt a: dapts.get(0))
				System.out.println("Upcoming: "+a.getId());
			
			for(Apt a: dapts.get(1))
				System.out.println("Today: "+a.getId());
			
			for(Apt a: dapts.get(2))
				System.out.println("Completed: "+a.getId());
			
			for(Apt a: dapts.get(3))
				System.out.println("Past: "+a.getId());
			
			
			
			
			return "doctorHome-view";
		}
		else
		{	
			request.setAttribute("loginStatus", "Wrong Username or Password");
			return "doctorLogin-view";
		}
	}


}
