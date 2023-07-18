package com.hospital.dao;


import com.hospital.pojo.Patient;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

/**
 *
 * @author shantanusachdeva
 */
@Component
public class PatientDAO extends DAO {
    
    public Patient getPatient(String uname)
    {
        Session hibSession = getSession();
        
        begin();
        
        Query q = hibSession.createQuery("from Patient where uname=:uname")
                .setParameter("uname", uname);
        
        Patient result = (Patient) q.uniqueResult();
        
        
        
        commit();
        
        close();
        
        return result;
    }
    public Patient getPatientID(String id)
    {
        Session hibSession = getSession();
        
        begin();
        
        Query q = hibSession.createQuery("from Patient where id=:id")
                .setParameter("id", Integer.parseInt(id));
        
        Patient result = (Patient) q.uniqueResult();
        
        
        
        commit();
        
        close();
        
        return result;
    }
    
    public List<Patient> getPatients()
    {
        Session hibSession = getSession();
        
        begin();
        
        Query q = hibSession.createQuery("from Patient");
  
        
        List<Patient> pat = q.list();
        
        
        
        commit();
        
        close();
        
        return pat;
    }
    
    public void createPatient(Patient patient)
    {
    	Session hibSession = getSession();
        
        begin();
        
        hibSession.save(patient);       
        
        commit();
        
        close();
        
    }
    
    public boolean checkUsernameExist(String username)
    {
    	Session hibSession = getSession();
    	begin();
    	
    	Query q = hibSession.createQuery("from Patient where uname=:uname ")
    			.setParameter("uname",username);
    	int len = q.list().size();
    	
    	commit();
    	
    	close();
    	
    	if(len>0)
    		return true;
    	else
    		return false;
    	
    	
    	
    	
    }
    
    public boolean checkLoginDetails(String uname, String password)
    {
    	Session hibSession = getSession();
    	begin();
    	
    	Query q = hibSession.createQuery("from Patient where uname=:uname AND password=:password")
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
    
    public void deletePatient(Patient patient)
    {
    	Session hibSession = getSession();
        
        begin();
        
        hibSession.delete(patient);       
        
        commit();
        
        close();
    }
    
    
    
}

