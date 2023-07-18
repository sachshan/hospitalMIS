package com.hospital.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hospital.dao.PatientDAO;

@Component
public class PatientInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	PatientDAO pdao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hey, this is the prehandle method!");
		HttpSession session = request.getSession(false);
		
		if(session==null)
		{
			response.sendRedirect("/HospitalMIS/patient/login.htm");
			return false;
		}
		else if(!pdao.checkLoginDetails((String)session.getAttribute("pname"), (String) session.getAttribute("ppass")))
		{
			response.sendRedirect("login.htm");
			return false;
		}
		else
		{	
			return super.preHandle(request, response, handler);
		}
		
	}

	

}
