package com.hospital.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hospital.dao.DoctorDAO;
import com.hospital.dao.PatientDAO;

@Component
public class DoctorInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	DoctorDAO ddao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hey, this is the doctor prehandle method!");
		HttpSession session = request.getSession(false);
		
		if(session==null)
		{
			response.sendRedirect("/HospitalMIS/doctor/login.htm");
			return false;
		}
		else if(!ddao.checkLoginDetails((String)session.getAttribute("dname"), (String) session.getAttribute("dpass")))
		{
			response.sendRedirect("/HospitalMIS/doctor/login.htm");
			return false;
		}
		else
		{	
			return super.preHandle(request, response, handler);
		}
		
	}


}
