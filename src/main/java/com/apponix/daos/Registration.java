package com.apponix.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration {
	Connection con=null;
public boolean RegisterCheck(String email)
{

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac","root","root");
		
		boolean isCheckCustomer = false;
		PreparedStatement psmt = con.prepareStatement("Select count(*) from users where email = ?");
		psmt.setString(1, email);
		ResultSet resultset = psmt.executeQuery();
		
		if(resultset.next())
		{
			int count = resultset.getInt(1);
			
			
			if(count==1)
			{
				isCheckCustomer = true;
				
			}
	
}
		return isCheckCustomer;
	}catch(Exception e)
	{
		e.printStackTrace();
	  return false;
	}
     finally
     {
    	 try {con.close();} catch(Exception e){}
     }
}
	public void Register(String email,String Password,String Repate_Pass,String role)
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    con= DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac","root","root");
			
			PreparedStatement psmt = con.prepareStatement("insert into users(email,role,Password,Repate_Pass) values(?,?,?,?)");
			psmt.setString(1, email);
			psmt.setString(2, role);
			psmt.setString(3,Password);
			psmt.setString(4, Repate_Pass);
		      psmt.executeUpdate();
	}		
	catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		  
		}
	     finally
	     {
	    	 try {con.close();} catch(Exception e){}
	     }
}
	
	public boolean LoginCheck(String email, String password,String role) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac", "root", "root");

	        boolean isCheckCustomer = false;

	        PreparedStatement psmt = con.prepareStatement("SELECT password FROM users WHERE email = ? and role = ?");
	        psmt.setString(1, email);
	        psmt.setString(2, role);
	        System.out.println(psmt);
	        ResultSet resultSet = psmt.executeQuery();

	        // Check if the result set has any rows
	        if (resultSet.next()) {
	            String storedPassword = resultSet.getString("password");
	            isCheckCustomer = password.equals(storedPassword);
	            System.out.println(storedPassword);
	            System.out.println(isCheckCustomer);
	        } else {
	            // Handle the case where the result set is empty (no matching email)
	            System.out.println("No matching user found for email: " + email);
	        }

	        return isCheckCustomer;
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

public void ForgetPassword(String email, String password,String Repate_pass) {
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/cdac","root","root");
		
		PreparedStatement psmt = con.prepareStatement("UPDATE users SET password = ?,repate_pass = ?  WHERE email = ?");
		psmt.setString(1, password);
		psmt.setString(2, Repate_pass);
		psmt.setString(3, email);
		psmt.executeUpdate();
		
	} catch (ClassNotFoundException | SQLException e) {
		
		e.printStackTrace();
		
	}
	finally {
		try {con.close();}catch(Exception e){}
	}
}



 } 