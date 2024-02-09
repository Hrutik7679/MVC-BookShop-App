package com.service.in;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import com.apponix.daos.Registration;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		res.setContentType("text/html");
		String email = req.getParameter("uname");
		String password = req.getParameter("psw");
		String role = req.getParameter("role");
		PrintWriter out=res.getWriter();
		Registration obj = new Registration();
		  boolean isValid   = obj.LoginCheck(email,password,role);
	      
		   //System.out.println(isValid);
		  if(isValid)
		  {
				 if(role.equals("admin"))
				 {
					 String name	= req.getParameter("uname");
					 Cookie c = new Cookie("uname", java.net.URLEncoder.encode(name, "UTF-8"));

						res.addCookie(c);
						c.setMaxAge(360000);
					res.sendRedirect("admin"); 
				 }else {
				try {
		            
					 HttpSession session = req.getSession(); // first call to getSession() creates new session --> session.isNew()
						// returns true
		           Set<Integer> cart = new LinkedHashSet<>();
		           session.setAttribute("cart", cart);
					 String name	= req.getParameter("uname");
					 Cookie c = new Cookie("uname", java.net.URLEncoder.encode(name, "UTF-8"));

						res.addCookie(c);
						c.setMaxAge(360000);
						res.sendRedirect("subject");
						
						
					  
					  
					  
					  out.write("<h1>Successful Registration..</h1>");
					  RequestDispatcher result=req.getRequestDispatcher("subject"); 
						result.forward(req, res);
						
						
		        } catch (Exception e) {
		            // Handle exceptions
		            e.printStackTrace();
		            
		        }
		}
				  		  }
		  else
		  {
		  
					  out.write("<html><head>\r\n"
						  		+ "    <meta charset=\"UTF-8\">\r\n"
						  		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
						  		+ "    <title>Login Page</title>\r\n"
						  		+ "    <style>\r\n"
						  		+ "        body {\r\n"
						  		+ "            font-family: Arial, sans-serif;\r\n"
						  		+ "            background-color: #f4f4f4;\r\n"
						  		+ "            text-align: center;\r\n"
						  		+ "        }\r\n"
						  		+ "\r\n"
						  		+ "        h1 {\r\n"
						  		+ "            color: red;\r\n"
						  		+ "        }\r\n"
						  		+ "\r\n"
						  		+ "        button {\r\n"
						  		+ "            background-color: #4CAF50;\r\n"
						  		+ "            color: white;\r\n"
						  		+ "            padding: 10px 20px;\r\n"
						  		+ "            text-align: center;\r\n"
						  		+ "            text-decoration: none;\r\n"
						  		+ "            display: inline-block;\r\n"
						  		+ "            font-size: 16px;\r\n"
						  		+ "            margin: 4px 2px;\r\n"
						  		+ "            cursor: pointer;\r\n"
						  		+ "        }\r\n"
						  		+ "    </style>\r\n"
						  		+ "</head><body>");
						  out.write("<h1>Invalid Password </h1><br><br>");
						  out.write("<button type=\"button\"><a href=\"index.html\">Go to Login Page.</a></button>");
						  out.write("</body></html>");
						  
					
			  
		  }
	
	}



}
