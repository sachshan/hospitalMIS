package com.hospital.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hospital.dao.AdminDAO;
import com.hospital.dao.DoctorDAO;

@Component
public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	AdminDAO adao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hey, this is the admin prehandle method!");
		HttpSession session = request.getSession(false);
		
		if(session==null)
		{
			response.sendRedirect("/HospitalMIS/admin/login.htm");
			return false;
		}
		else if(session.getAttribute("adminId")!=null && session.getAttribute("apass")!=null)
		{
			if(!adao.checkLoginDetails((int)session.getAttribute("adminId"), (String) session.getAttribute("apass")))
			{
				response.sendRedirect("/HospitalMIS/admin/login.htm");
				return false;
			}
			else
			{
				return super.preHandle(request, response, handler);
			}
			
		}
		else
		{
			response.sendRedirect("/HospitalMIS/admin/login.htm");
			return false;
		}
		
		
		
	}

}
