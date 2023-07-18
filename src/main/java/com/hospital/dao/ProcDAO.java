package com.hospital.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.hospital.pojo.Apt;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;

@Component
public class ProcDAO extends DAO{
	
	public void createProc(Proc proc)
	{
		Session hibSession = getSession();
        
        begin();
        
        hibSession.save(proc);       
        
        commit();
        
        close();
		
	}
	
	public void removeDoc(Proc proc, Doctor doc)
	{
		Session hibSession = getSession();
        
        begin();
        
        Proc proce = hibSession.get(Proc.class, proc.getId());
		
		proce.getDoctors().remove(doc);
        
        
        commit();
        
        close();
        
	}
	
	public boolean checkNameExist(String name)
    {
    	Session hibSession = getSession();
    	begin();
    	
    	Query q = hibSession.createQuery("from Proc where name=:name")
    			.setParameter("name",name);
    	int len = q.list().size();
    	
    	commit();
    	
    	close();
    	
    	if(len>0)
    		return true;
    	else
    		return false;
    	
    }
	
	public List<Proc> getProcs()
	{
		Session hibSession = getSession(); 
		
		begin();
		
		Query q = hibSession.createQuery("from Proc");
		
		List<Proc> currentProcs = q.list();
		
		commit();
		
		close();
		
		return currentProcs;
	}
	
	public Proc getProc(int id)
	{
		Session hibSession = getSession(); 
		
		begin();
		
		Proc currentProc = hibSession.get(Proc.class, id);
		
		commit();
		
		close();
		
		return currentProc;
	}
	
	public void deleteProc(Proc proc)
	{
		Session hibSession = getSession();
		begin();
		hibSession.delete(proc);
		commit();
		close();
	}
	
	
	

}
