package com.apponix.daos;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@SuppressWarnings("unchecked")
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true); // Create a new session if it doesn't exist
		Set<Integer> cart = (Set<Integer>) session.getAttribute("cart");

		
			session.setAttribute("cart", cart);
			String[] bookIds = req.getParameterValues("book");

			System.out.println(cart);
			System.out.println(bookIds);
			
			for(String bookId:bookIds) {
				int id = Integer.parseInt(bookId);
				//TODO save book id into current user cart
				
				cart.add(id); // add bookid into the cart
			
		     
			
		}
		// Rest of your code...
			RequestDispatcher rd = req.getRequestDispatcher("subject");
			rd.forward(req, resp);

	}
	
		
}

//
//It looks like you've correctly addressed the potential NullPointerException issue by checking if the "cart" attribute in the session is null and initializing it with a new HashSet if necessary. This ensures that the "cart" attribute is always present in the session and ready for use.
//
//Here's a brief breakdown of the code:
//
//HttpSession session = req.getSession(true);: This line retrieves the session associated with the request. If the session does not exist, it creates a new one (true argument).
//
//Set<Integer> cart = (Set<Integer>) session.getAttribute("cart");: This line retrieves the "cart" attribute from the session. If the attribute does not exist, it returns null.
//
//if (cart == null) { ... }: This conditional block checks if the "cart" attribute is null. If it is, a new HashSet is created and set as the "cart" attribute in the session.
//
//This approach ensures that the "cart" attribute is initialized properly, helping to prevent the NullPointerException you were facing. Make sure that you apply this modification to your AddCartServlet as you intended.
//
//If you continue to encounter issues or have further questions, feel free to provide more details or ask for additional assistance!
//
//
