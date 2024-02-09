package com.service.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import com.apponix.daos.Registration;

@SuppressWarnings("serial")
@WebServlet("/SignUp")
public class SigUpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
	    PrintWriter out  = res.getWriter();
	    
	    String email = req.getParameter("email");
	    String password  = req.getParameter("psw");
	    String rep_pass = req.getParameter("psw-repeat");
	    String role = req.getParameter("role");
	 //   Base64.Encoder encode = Base64.getEncoder();
	    
	  //  String estr = encode.encodeToString(password.getBytes());
	   // String estr1 = encode.encodeToString(rep_pass.getBytes());
	   // System.out.println(email + password + rep_pass);
	    
	    out.write("<html><body>");
	    Registration obj =new Registration();
	    boolean isValid = obj.RegisterCheck(email);
	    if(isValid) {
	    	out.write("It Seems You Are Already a Registration..<br><br>");
	    	out.write("  <button type=\"button\" class=\"cancelbtn\"><a href=\"Login.html\">Go to Login Page</a></button>");
	    }
	    else {
	    	obj.Register(email,password,rep_pass,role);
	    	out.write("Registration Successful..<br><br>");
	    	out.write("  <button type=\"button\" class=\"cancelbtn\"><a href=\"index.html\">Go to Login Page</a></button>");
	    }
	    out.write("</body></html>");
	}
}
	    