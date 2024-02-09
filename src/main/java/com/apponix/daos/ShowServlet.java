package com.apponix.daos;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/showcart")
public class ShowServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}
	@SuppressWarnings("unchecked")
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("text/html");
	    PrintWriter out = resp.getWriter();
	    HttpSession session = req.getSession();
	    Set<Integer> cart = (Set<Integer>) session.getAttribute("cart");

        System.out.println(cart);
        
        BookDao dao = null;
		try {
			dao = new BookDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
        out.println("<table");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Name</th>");
        out.println("<th>Author</th>");
        out.println("<th>Subject</th>");
        out.println("<th>Price</th>");
        out.println("<th>Operation</th>");
        out.println("</tr>");
        // find bookdetails of each book and display
        for(int id: cart) {
        	Book b = dao.findById(id);
            out.println("<tr>");
            out.printf("<td>%d</td>\r\n", b.getId());
            out.printf("<td>%s</td>\r\n", b.getName());
            out.printf("<td>%s</td>\r\n", b.getAuthor());
            out.printf("<td>%s</td>\r\n", b.getSubject());
            out.printf("<td>%.2f</td>\r\n", b.getPrice());
            out.printf("<td><a href=\"removeBook?id=%d\">Remove</a></td>\r\n", b.getId());
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.write("<button><a href=\"subject\">Add Books</a></button> ");
        out.write("<button><a href=\"index.html\">Sign Out</a></button> ");
        out.println("</html>");
}

	
}









