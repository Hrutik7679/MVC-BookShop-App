package com.service.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.apponix.daos.Book;
import com.apponix.daos.BookDao;
@WebServlet("/admin")
@SuppressWarnings("serial")
public class AdminServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			res.setContentType("text/html");
			PrintWriter out= res.getWriter();
			BookDao obj =new BookDao();
		List<Book> result	= obj.findAll();
		
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
	        
	        
	        
	        out.println("<html><head>");
	        out.println("<link rel=\"stylesheet\" href=\"stylesheet.css\">");
	        out.println("</head><body>");

			 out.println("<table border='1'>");
		        out.println("<tr>");
		        out.println("<th>Id</th>");
		        out.println("<th>Name</th>");
		        out.println("<th>Author</th>");
		        out.println("<th>Subject</th>");
		        out.println("<th>Price</th>");
		        out.println("<th>Operation</th>");
		        out.println("<th>Operation</th>");
		        out.println("</tr>");
			
		for(@SuppressWarnings("unused") Book b:result)
		{
			
	        // find bookdetails of each book and display
	        
	        
	            out.println("<tr>");
	            out.printf("<td>%d</td>\r\n", b.getId());
	            out.printf("<td>%s</td>\r\n", b.getName());
	            out.printf("<td>%s</td>\r\n", b.getAuthor());
	            out.printf("<td>%s</td>\r\n", b.getSubject());
	            out.printf("<td>%.2f</td>\r\n", b.getPrice());
	            out.printf("<td><a href=\"Edit.html?id=%d\">Edit</a></td>\r\n", b.getId());
	            out.printf("<td><a href=\"Delete.html?id=%d\">Delete</a></td>\r\n",b.getId());
	            out.println("</tr>");
		}
	        out.println("</table>");
	      out.println("<button type=\"button\" class=\"cancelbtn\"><a href=\"AddItems.html\">Add Book</a></button>");
	      out.println("<button type=\"button\" class=\"cancelbtn\"><a href=\"index.html\">Sign Out</a></button>");
	        out.println("</body></html>");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
