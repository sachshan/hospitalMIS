package com.hospital.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.hospital.dao.AptDAO;
import com.hospital.pojo.Apt;
import com.hospital.pojo.Doctor;
import com.hospital.pojo.Patient;
import com.hospital.pojo.Proc;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.fonts.*;



public class PatientReportPDF extends AbstractPdfView {
	
//	@Autowired
//	AptDAO adao;
	
	int id;
	Apt a;
	public PatientReportPDF(int id, Apt a) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.a = a;
		
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(id);
		com.lowagie.text.Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    
		Paragraph p1 = new Paragraph("NEU Hospital",fontTiltle);
		
		Doctor doc = a.getDoc();
		Proc proc = a.getProc();
		Patient patient = a.getPatient();
		
		String text = patient.getFname()+" "+patient.getLname()+" had an appoinment with Dr."+doc.getFname()+
				" "+doc.getLname()+", on "+a.getDate()+" from "+a.getBeginTime()+" to "
						+a.getBeginTime().plusMinutes(proc.getDuration())+".";
		
		Paragraph p2 = new Paragraph(text);
		
		String text2 = "Report from advising Dr. "+doc.getLname()+": "+a.getResult();
		
		
		Paragraph p3 = new Paragraph(text2);
		String text3;
		
		if(patient.getGender()=='M')
		{
			text3 = "Thank you for visiting Mr. "+patient.getLname();
		}
		else
		{
			text3 = "Thank you for visiting Ms. "+patient.getLname();
		}
		
		Paragraph p4 = new Paragraph(text3);
		
		document.add(p1);
		document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
		document.add(p2);
		document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
		document.add(p3);
		document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
		document.add(p4);
		 
		
		
	}

	
}
