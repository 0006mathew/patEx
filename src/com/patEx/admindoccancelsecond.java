package com.patEx;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class doccancelsecond
 */
@WebServlet("/admindoccancelsecond")
public class admindoccancelsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admindoccancelsecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String a=(String)request.getParameter("apptid");
		String why,re,des;
		Connection com=connection.getconnect();
		try
		{   
			String u,d = null,t=null;
			Statement pst = com.createStatement();
			String sql = "select * from appt where apptid='"+a+"' order by apptdate DESC";
			ResultSet rs = pst.executeQuery(sql);
			if(rs.next())
			{   
				u=rs.getString(2);
				t=rs.getString(3);
				d=rs.getString(4);
				re=rs.getString(5);
				des=rs.getString(6);
				why="Your appointment for "+d+" ,time "+t+" has been cancelled. Kindly book another appointment.";
				sql = "Insert into msg (apptid,senderid,receiverid,message,time)values(?,?,?,?,now())";
				try
				{
					PreparedStatement ps=com.prepareStatement(sql);
					ps.setString(1,a);
					ps.setString(2,"d1");
					ps.setString(3,u);
					ps.setString(4,why);
					ps.executeUpdate();
					sql="delete from appt where apptid='"+a+"'";
					ps.executeUpdate(sql);
				}
				catch(Exception e)
				{
					System.out.println("Exception "+e);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception "+e);
		}
		response.sendRedirect("admindoccancel");
	}

	

}