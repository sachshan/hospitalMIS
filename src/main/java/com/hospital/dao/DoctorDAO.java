package com.hospital.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;

@Component
public class DoctorDAO extends DAO {
	
	public List<Doctor> getDoctors()
	{
		Session hibSession = getSession(); 
		
		begin();
		
		Query q = hibSession.createQuery("from Doctor");
		
		List<Doctor> currentDocs = q.list();
		
		commit();
		
		close();
		
		return currentDocs;
	}
	
	public boolean checkUsernameExist(String username)
    {
    	Session hibSession = getSession();
    	begin();
    	
    	Query q = hibSession.createQuery("from Doctor where uname=:uname ")
    			.setParameter("uname",username);
    	int len = q.list().size();
    	
    	commit();
    	
    	close();
    	
    	if(len>0)
    		return true;
    	else
    		return false;   	
    	
    }
	
	public void createDoctor(Doctor doctor)
    {
    	Session hibSession = getSession();
        
        begin();
        
        hibSession.save(doctor);       
        
        commit();
        
        close();
        
    }
	public Doctor getDoctorU(String uname)
	{
		Session hibSession = getSession();
        
        begin();
        
        Query q = hibSession.createQuery("from Doctor where uname=:uname")
                .setParameter("uname", uname);
        
        Doctor result = (Doctor) q.uniqueResult();
        
        
        
        commit();
        
        close();
        
        return result;
	}
	
	public Doctor getDoctor(String id)
	{
		Session hibSession = getSession();
        
        begin();
        
        Doctor doc = hibSession.get(Doctor.class,Integer.parseInt(id));       
        
        commit();
        
        close();
        
        return doc;
	}
	
	public boolean checkLoginDetails(String uname, String password)
    {
    	Session hibSession = getSession();
    	begin();
    	
    	Query q = hibSession.createQuery("from Doctor where uname=:uname AND password=:password")
    			.setParameter("uname",uname)
    			.setParameter("password", password);
    	
    	int len = q.list().size();
    	
    	commit();
    	
    	close();
    	
    	if(len>0)
    		return true;
    	else
    		return false;
    }
	
	public void deleteDoctor(Doctor doc)
	{
		Session hibSession = getSession();
        
        begin();
        
        hibSession.delete(doc);       
        
        commit();
        
        close();
        
        
	}
	
	
	
//	public List<Doctor> getDocProc(Proc proc)
//	{
//		Session hibSession = getSession(); 
//		
//		begin();
//		
//		Proc proc = hibSession.get(Proc.class, proc.getId());
//		
//		
//		List<Doctor> currentDocs = q.list();
//		
//		commit();
//		
//		close();
//		
//		return currentDocs;
//	}

}
