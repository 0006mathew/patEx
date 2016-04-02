package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class pateditthird
 */
@WebServlet("/pateditthird")
public class pateditthird extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pateditthird() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		
		patient p2=new patient();
		HttpSession session=request.getSession(false);
		p2=(patient)session.getAttribute("object");
		String x=p2.userid;
		
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
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/html");  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('The changes have been saved');");  
			out.println("location='patdash.jsp';");
			out.println("</script>");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}
}