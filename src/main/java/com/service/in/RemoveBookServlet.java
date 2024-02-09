package com.service.in;

import java.io.IOException;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/removeBook")
public class RemoveBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookId = Integer.parseInt(req.getParameter("id"));

        HttpSession session = req.getSession();
        Set<Integer> cart = (Set<Integer>) session.getAttribute("cart");

        if (cart != null) {
            cart.remove(bookId);
        }

        // Redirect back to the showcart servlet or any other appropriate page
        resp.sendRedirect("showcart");
    }
}
