package hr.fer.spring.projekt_opp_proba;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.spring.projekt_opp_proba.model.Doctor_Coach;

import javax.servlet.http.*;

@WebServlet(urlPatterns= {"/image"})
public class DisplayImageServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public DisplayImageServlet() {
		super();
	}
	
	 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String userId="";
			
			try {
				userId=request.getParameter("id");
			}catch(Exception e) {
				
			}
			
			Doctor_Coach docCho=loadDoctor_CoachFromDB(userId);
			
			 String contentType=this.getServletContext().getMimeType(docCho.getDcUserName());
			 
			 response.setHeader("Content-Type", contentType);
			 response.setHeader("Content-Length", String.valueOf(docCho.getDcImage().length()));
			 response.setHeader("Content-Disposition", "inline; filename=\""+docCho.getDcUserName()+"\"");
			 
			 int blobLength=(int)docCho.getDcImage().length();
			 byte[] blobImage=docCho.getDcImage().getBytes(1, blobLength);
			 
			 response.getOutputStream().write(blobImage);
			 
		}catch(Exception e) {
			
		}
	}
	  
	private Doctor_Coach loadDoctor_CoachFromDB(String userId) {
		// TODO Auto-generated method stub
		
		//Find and return Doctor_Coach object with userId
		
		return null;
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doGet(request, response);
	  }
}
