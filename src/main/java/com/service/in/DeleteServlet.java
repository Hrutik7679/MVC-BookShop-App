package com.service.in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.apponix.daos.BookDao;


@WebServlet("/delete")
@SuppressWarnings("serial")
public class DeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		
	String id = req.getParameter("id");
	int id1=Integer.parseInt(id);
	try {
		BookDao result = new BookDao();
		result.deleteById(id1);
		out.write("<h1>Successful deleted..</h1>");
		out.write("<button type=\"submit\" class=\"delete-button /n\"><a href=\"admin\">Go back </a></button>");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
