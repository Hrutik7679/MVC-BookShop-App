package com.apponix.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;

@WebFilter("/*")
@SuppressWarnings("serial")
public class HitCount extends HttpFilter  {
       
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException ,IOException {
		ServletContext sCtx = request.getServletContext();
		
		Integer hitCounter = (Integer)sCtx.getAttribute("hitCounter");
		
		if(hitCounter == null)
		{
			hitCounter = 0;
			
		}
		hitCounter++;
		sCtx.setAttribute("hitCounter", hitCounter);
		
		chain.doFilter(request, response);
	}

	

}
