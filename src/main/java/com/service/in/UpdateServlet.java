package com.service.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.apponix.daos.Book;
import com.apponix.daos.BookDao;

@WebServlet("/update")
@SuppressWarnings("serial")
public class UpdateServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		
	String id = req.getParameter("id");
	String name = req.getParameter("name");
	String author = req.getParameter("author");
	String subject = req.getParameter("subject");
	String price = req.getParameter("price");
	int id1=Integer.parseInt(id);
	double price1=Double.parseDouble(price);
	Book obj =new Book(id1,name,author,subject,price1);
	try {
		BookDao result = new BookDao();
		result.update(obj);
		out.write("<h1>Successful Update..</h1>");
		out.write("<button type=\"submit\" class=\"delete-button /n\"><a href=\"admin\">Go back </a></button>");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
}
