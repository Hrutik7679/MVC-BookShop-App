package com.apponix.daos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

@SuppressWarnings("serial")
@WebServlet("/subject")
public class BookList extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		BookDao obj = null;
		try {
			obj = new BookDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  Set<String> result= obj.findAllSubjects();
	 
	 

			out.write("<html><head><title> BookList </title>");
			out.write("<style>");
	        // Apply CSS styles here
	        out.write("body { font-family: Arial, sans-serif; background-color: #f0f0f0; }");
	        out.write("form { margin: 20px; }");
	        out.write("button { background-color: #4caf50; color: white; padding: 10px 15px; font-size: 16px; border: none; cursor: pointer; }");
	        out.write("</style>");
	    	out.write("</head><body>");
	    	
			out.write("<form action=\"FinalList\" method=\"doGet\">");
		//	 String name	= req.getParameter("uname");
			// Cookie c = new Cookie("uname", java.net.URLEncoder.encode(name, "UTF-8"));
               
			String name = null;
			
			Cookie[] arr = req.getCookies();
			if(arr != null) {
				for ( Cookie c1 : arr) {
					if(c1.getName().equals("uname")) {
						name = c1.getValue();
						break;
					}
				}
			}
			out.printf("<h1>Hello, %s<br/><br/>\r\n</h1>", name);
			for(String subject:result)
			 {
			
		      out.printf("<input type = \"radio\" name = \"subject\" value=\"%s\"> %s <br><br>",subject,subject);
	 }
	 out.write("<button>Get Books</button>  ");
	 out.write("<button><a href=\"showcart\">Show List</a></button> ");
		      out.write("</form>");
		     
			out.write("</body></html>");
			
	 
	  
	
	}

}
