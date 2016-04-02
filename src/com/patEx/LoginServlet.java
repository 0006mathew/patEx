package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.RequestDispatcher;
import java.sql.*;



/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		
		String u = request.getParameter("uname");
		String p = request.getParameter("pass");
		String id=u;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pst=null;
		conn=connection.getconnect();
		if(Login.validate(u, p)==2){
			HttpSession session=request.getSession(true);
			doctor d=new doctor(id);
			d.userid=id;
			String role="doctor";
			session.setAttribute("object", d);
			session.setAttribute("role", role);
			RequestDispatcher rd=request.getRequestDispatcher("docdash");    
			rd.forward(request,response); 
        
		}
		else if(Login.validate(u, p)==3){
			HttpSession session=request.getSession(true);
			patient p1=new patient(id);
			p1.userid=id;
			String role="patient";
			session.setAttribute("object", p1);
			session.setAttribute("role", role);
			//System.out.println("hi from loginservelt");
			try{
			String sql1="Select * from appt where username=? and apptdate>=curdate()";
			pst=conn.prepareStatement(sql1);
			pst.setString(1, p1.userid);
			rs=pst.executeQuery();
			}catch(Exception e){
				e.printStackTrace();
			}
			try {
				if(!rs.next()){
				RequestDispatcher rd=request.getRequestDispatcher("patdash.jsp");    
				rd.forward(request,response);
				}
				else
				{
					RequestDispatcher rd=request.getRequestDispatcher("timer");    
					rd.forward(request,response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//response.sendRedirect("patdash.jsp");
		
		}
		else if(Login.validate(u, p)==4){	
			HttpSession session=request.getSession(true);
			admin a=new admin(id);
			a.userid=id;
			String role="admin";
			session.setAttribute("object", a);
			session.setAttribute("role", role);
			RequestDispatcher rd=request.getRequestDispatcher("admdash.jsp");    
	        rd.forward(request,response);   
		}
	else{
		 PrintWriter out = response.getWriter();  
		 response.setContentType("text/html");  
		 out.println("<script type=\"text/javascript\">");  
		 out.println("alert('Incorrect username or password');");  
		 out.println("location='Index.jsp';");
		 out.println("</script>");
	}
	request.setAttribute("attributeName",id);
		
	}

}