package com.patEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class adminpateditthird
 */
@WebServlet("/adminpateditthird")
public class adminpateditthird extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminpateditthird() {
        super();
        // TODO Auto-generated constructor stub
    }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		admin p2=new admin();
		HttpSession session=request.getSession(false);
		p2=(admin)session.getAttribute("object");
		String x=(String)session.getAttribute("pat");
		
		String a=request.getParameter("fn");
		String b=request.getParameter("ln");
		String c1=request.getParameter("dob");
		java.sql.Date c=java.sql.Date.valueOf(c1);
		String d=request.getParameter("sex");
		String e=request.getParameter("ph");
		String f=request.getParameter("ad");
		//System.out.println("Yes"+a+b+c+d+e+f+x);
		Connection conn=connection.getconnect();
		String sql = 
				   "UPDATE patinfo " + 
				   "SET fname = ?, lname = ?, dob = ?, sex = ?,  phno = ?, addr = ? WHERE username =?";
		try {
			PreparedStatement updateQuery  = conn.prepareStatement(sql);
			updateQuery.setString(1, a);
			updateQuery.setString(2, b);
			updateQuery.setDate(3, c);
			updateQuery.setString(4, d);
			updateQuery.setString(5, e);
			updateQuery.setString(6, f);
			updateQuery.setString(7, x);
			updateQuery.executeUpdate();
			RequestDispatcher rd=request.getRequestDispatcher("/admdash.jsp");
	        rd.forward(request, response);
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}
}