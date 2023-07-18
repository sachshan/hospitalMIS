package com.hospital.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public class AdminDAO extends DAO {
	
	public boolean checkLoginDetails(int adminID, String password)
    {
    	Session hibSession = getSession();
    	begin();
    	
    	Query q = hibSession.createQuery("from Admin where id=:id AND password=:password")
    			.setParameter("id",adminID)
    			.setParameter("password", password);
    	
    	int len = q.list().size();
    	
    	commit();
    	
    	close();
    	
    	if(len>0)
    		return true;
    	else
    		return false;
    }

}
