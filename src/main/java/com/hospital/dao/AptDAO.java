package com.hospital.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.hospital.pojo.Apt;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;

@Component
public class AptDAO extends DAO{
	
	public List<LocalTime> getATimes(Proc proc, Doctor doc, LocalDate date)
	{
		Session hibSession = getSession();
	    
	    begin();
		
		Query q = hibSession.createQuery("from Apt where date=:date and doc=:doc")
				.setParameter("date",date)
				.setParameter("doc", doc);
		
		
		
		List<Apt> bslots = q.list();
		int procD= proc.getDuration();
		
		LocalTime begin = LocalTime.of(8, 0);
		List<LocalTime> aSlots = new ArrayList<>();
		

		while(begin.plusMinutes(procD).isBefore(LocalTime.of(12, 1)))
		{
			boolean check = true;
			
			for(Apt a: bslots)
			{
				LocalTime start = a.getBeginTime();
				LocalTime end = start.plusMinutes(a.getProc().getDuration());
				
				if(!(end.isBefore(begin)|| end.equals(begin) || begin.plusMinutes(procD).isBefore(start)|| begin.plusMinutes(procD).equals(start) ))
				{
					check = false;
					break;
				}
				
			}
			
			if(check)
			{
				aSlots.add(begin);
				System.out.println(begin);
			}
			

			begin = begin.plusMinutes(30);
		}
		
		begin = LocalTime.of(13, 0);
		
		while(begin.plusMinutes(procD).isBefore(LocalTime.of(20, 1)))
		{
			boolean check = true;
			
			for(Apt a: bslots)
			{
				LocalTime start = a.getBeginTime();
				LocalTime end = start.plusMinutes(a.getProc().getDuration());
				
				if(!(end.isBefore(begin)|| end.equals(begin) || begin.plusMinutes(procD).isBefore(start)|| begin.plusMinutes(procD).equals(start) ))
				{
					check = false;
					break;
				}
				
			}
			
			if(check)
			{
				aSlots.add(begin);
				System.out.println(begin);
			}
			

			begin = begin.plusMinutes(30);
		}
		
		
		commit();
		close();
		
		return aSlots;
		
	}
	
	public List<Apt> getAptU()
	{
		updateStatus();
		
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where status='U'");
		
		List<Apt> aptList = q.list();
		
		commit();
		close();
		
		return aptList;
	}
	
	public List<Apt> getAptC()
	{
		updateStatus();
		
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where status='C'");
		
		List<Apt> aptList = q.list();
		
		commit();
		close();
		
		return aptList;
	}
	
	public List<Apt> getAptProc(Proc proc)
	{
		updateStatus();
		
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where proc=:proc")
				.setParameter("proc", proc);
		
		List<Apt> aptList = q.list();
		
		commit();
		close();
		
		return aptList;
	}
	
	
	
	public List<Apt> getPatientAptU(Patient patient)
	{
		updateStatus();
		
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where patient=:patient and status='U'")
				.setParameter("patient",patient);
		
		List<Apt> aptList = q.list();
		
		commit();
		close();
		
		return aptList;
		
	}
	
	public List<Apt> getPatientAptC(Patient patient)
	{
		updateStatus();
		
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where patient=:patient and status='C'")
				.setParameter("patient",patient);
		
		List<Apt> aptList = q.list();
		
		commit();
		close();
		
		return aptList;
		
	}
	

	
	public List<List<Apt>> getDoctorApt(Doctor doc)
	{
		updateStatus();
		
		List<List<Apt>> docApts = new ArrayList<>();
				
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where doc=:doc")
				.setParameter("doc",doc);
		
		List<Apt> apts = q.list();
		
		List<Apt> Uapts = new ArrayList<>();
		List<Apt> Tapts = new ArrayList<>();
		List<Apt> Capts = new ArrayList<>();
		List<Apt> Papts = new ArrayList<>();
		
		for(Apt a: apts)
		{
			if(a.getStatus()=='U' && a.getDate().isAfter(LocalDate.now()))
			{
				Uapts.add(a);
			}
			else if(a.getStatus()=='U' && a.getDate().equals(LocalDate.now()))
			{
				Tapts.add(a);
			}
			else if(a.getStatus()=='C' && a.getResult()==null)
			{
				Capts.add(a);
			}
			else
			{
				Papts.add(a);
			}
		}
		
		docApts.add(Uapts);
		docApts.add(Tapts);
		docApts.add(Capts);
		docApts.add(Papts);
					
		commit();
		close();
		
		return docApts;
		
	}
	
	
	public List<Apt> getDoctorAptAll(Doctor doc)
	{
		updateStatus();
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where doc=:doc")
				.setParameter("doc",doc);
		
		List<Apt> aptList = q.list();
		
		commit();
		close();
		
		return aptList;
		
	}
	
	public Apt getApt(int id)
	{
		updateStatus();
		Session hibSession = getSession();
		begin();
		Apt a = hibSession.get(Apt.class, id );
		commit();
		close();
		return a;
	}
	
	
	public void saveApt(Apt apt)
	{
		Session hibSession = getSession();
		begin();
		hibSession.persist(apt);
		commit();
		close();
	}
	
	public void deleteApt(Apt apt)
	{
		Session hibSession = getSession();
		begin();
		hibSession.delete(apt);
		commit();
		close();
	}
	
	public void updateStatus()
	{
		Session hibSession = getSession();
		begin();
		
		Query q = hibSession.createQuery("from Apt where status='U'");
		List<Apt> apts = q.list();
		
		for(Apt a: apts)
		{
			if(a.getDate().isBefore(LocalDate.now()))
				a.setStatus('C');
				
			if(a.getDate().isEqual(LocalDate.now()))
				if(a.getBeginTime().isBefore(LocalTime.now()))
					a.setStatus('C');
		
		}
			
		
		commit();
		close();
	
	}
	
	public void aptChangeStatus(int id, char status)
	{
		updateStatus();
		Session hibSession = getSession();
		begin();
		Apt a = hibSession.get(Apt.class, id );
		a.setStatus(status);
		commit();
		close();
	}
	
	public void aptSubmitReport(int id, String report)
	{
		updateStatus();
		Session hibSession = getSession();
		begin();
		Apt a = hibSession.get(Apt.class, id );
		a.setResult(report);
		commit();
		close();
	}
	
	
	
	
	
	

}
