package com.service.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.apponix.daos.Registration;

@SuppressWarnings("serial")
@WebServlet("/Forget")
public class Forgetpassword extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		String password = req.getParameter("pwd");
		String email = req.getParameter("email");
		String Repate_Pass =req.getParameter("rep_pwd");
		//Base64.Encoder encoder = Base64.getEncoder();
		
		//String estr = encoder.encodeToString(password.getBytes());
		
		 Registration forget = new Registration();
		 forget.ForgetPassword(email,password,Repate_Pass);
		 res.sendRedirect("message3.html");
		    
		    
		    
		}

		
	

}
