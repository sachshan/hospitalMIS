package com.hospital.HospitalMIS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hospital.interceptor.AdminInterceptor;
import com.hospital.interceptor.DoctorInterceptor;
import com.hospital.interceptor.PatientInterceptor;

@SpringBootApplication
@ComponentScan({"com.hospital.controller","com.hospital.validator","com.hospital.interceptor","com.hospital.dao","com.hospital.ajax", "com.hospital.pdf"})
public class HospitalMisApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(HospitalMisApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/").setViewName("start-view");
	}
	
	@Autowired
	PatientInterceptor patientInt;
	
	@Autowired
	DoctorInterceptor doctorInt;
	
	@Autowired
	AdminInterceptor adminInt;
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		
		
		registry.addInterceptor(patientInt).addPathPatterns("/patient/*").excludePathPatterns("/patient/login.htm").excludePathPatterns("/patient/create.htm");
		registry.addInterceptor(doctorInt).addPathPatterns("/doctor/*").excludePathPatterns("/doctor/login.htm");
		registry.addInterceptor(adminInt).addPathPatterns("/admin/*").excludePathPatterns("/admin/login.htm");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	

}
