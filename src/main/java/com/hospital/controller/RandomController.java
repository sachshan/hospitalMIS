package com.hospital.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hospital.dao.AptDAO;
import com.hospital.dao.DAO;
import com.hospital.dao.DoctorDAO;
import com.hospital.dao.PatientDAO;
import com.hospital.dao.ProcDAO;
import com.hospital.pojo.Admin;
import com.hospital.pojo.Apt;

@Controller
public class RandomController extends DAO {
	
	@Autowired
	AptDAO adao;
	
	@Autowired
	DoctorDAO ddao;
	
	@Autowired
	PatientDAO pdao;
	
	@Autowired
	ProcDAO procdao;
	
	Comparator<Apt> compareByTime = new Comparator<Apt>() {
		@Override
		public int compare(Apt a1, Apt a2) {
			return a1.getBeginTime().compareTo(a2.getBeginTime());
		}
	};
	
	@GetMapping("/random.htm")
	public void addApt()
	{
		List<Apt> apts = adao.getAptU();
		
		Collections.sort(apts, compareByTime);
		
		for(Apt apt: apts)
			System.out.println(apt.getId() +" " +apt.getBeginTime());
		
		
		 
		
	}
	
	


}
