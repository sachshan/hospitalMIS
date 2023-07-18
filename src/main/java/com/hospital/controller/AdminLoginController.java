package com.hospital.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.hospital.dao.AdminDAO;
import com.hospital.dao.AptDAO;

@Controller
public class AdminLoginController {
	
	@Autowired
	AdminDAO adao;
	
	@Autowired
	AptDAO aptdao;
	
	@GetMapping("admin/login.htm")
	public String viewLogin(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session!=null)
		{
			if(session.getAttribute("adminId")!=null && session.getAttribute("apass")!=null)
			{
				int adminID = (int)session.getAttribute("adminId");
				String pass = (String)session.getAttribute("apass");
				if(adminID!=0 && pass!=null)
					if(adao.checkLoginDetails(adminID, pass))
					{
		
						return "adminHome-view";
					}
				
			}
			
			session.invalidate();
			
					
		}
		
		return "adminLogin-view";
	}
	
	@PostMapping("admin/login.htm")
	public String checkLogin(HttpServletRequest request)
	{
		
		if(request.getParameter("adminID")==null||request.getParameter("password")==null)
			return "adminLogin-view";
		
		
		int adminID= Integer.parseInt(request.getParameter("adminID"));
		String password = request.getParameter("password");
		
		
		if(adao.checkLoginDetails(adminID, password))
		{
			HttpSession session = request.getSession();
			session.setAttribute("adminId", adminID);
			session.setAttribute("apass", password);
			return "adminHome-view";
		}
		else
		{	
			request.setAttribute("loginStatus", "Wrong Username or Password");
			return "adminLogin-view";
		}
		

	}
}
