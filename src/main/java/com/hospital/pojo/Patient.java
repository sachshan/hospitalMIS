package com.hospital.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Patient {
    
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    private int id;
    
    
    private String uname;
    
    private String password;
    
    
    private String fname;
    
    private String lname;
    
    
    private LocalDate dob;
    
    private String email;
    
    private char gender;
    
    
    
    

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDob() {
		return dob;
	}

	public void setDob(String dob) {
		if(dob.equals(""))
			this.dob = LocalDate.now();
		else
		{
			this.dob = LocalDate.now().parse(dob);
		}
			
	}

	
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }


    public char getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.equals(""))
        	this.gender='U';
        else
        {
        	this.gender = gender.charAt(0);
        }
        	
    }
    
    
     
    
    
}
