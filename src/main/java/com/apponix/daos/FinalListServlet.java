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
import java.util.List;

@SuppressWarnings("serial")
@WebServlet("/FinalList")
public class FinalListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        String subject = req.getParameter("subject");

        BookDao obj = null;
		try {
			obj = new BookDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Book> subjectBooks = obj.findBySubject(subject);
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
        out.write("<html><head><title>Book List</title>");
        out.write("<style>");
        out.write("body { font-family: 'Arial', sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
        out.write(".container { width: 80%; margin: 20px auto; }");
        out.write(".book-item { display: flex; align-items: center; margin-bottom: 10px; background-color: #fff; padding: 10px; border-radius: 5px; }");
        out.write(".book-checkbox { margin-right: 10px; }");
        out.write(".add-book-button { background-color: #4caf50; color: white; padding: 10px 15px; font-size: 16px; border: none; cursor: pointer; text-decoration: none; border-radius: 5px; }");
        out.write("</style>");
        out.write("</head><body>");
       
        out.write("<div class=\"container\">");
        out.println("<form method='doGet' action='addcart'>");
        for(Book b:subjectBooks) {
        	out.printf("<input type='checkbox' name='book' value='%d'/> %s (%s) <br/>\r\n", 
        					b.getId(), b.getName(), b.getAuthor());
        }
        out.write("<button class=\"add-book-button\">Add Books</button>");
         out.write("</form>");
        out.write("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}