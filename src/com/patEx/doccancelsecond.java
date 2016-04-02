package com.patEx;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class doccancelsecond
 */
@WebServlet("/doccancelsecond")
public class doccancelsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doccancelsecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession(false);
		String a=(String)request.getParameter("apptid");
		String pp=(String)request.getParameter("reas");
		String why,re,des;
		doctor p2=(doctor)session.getAttribute("object");
		String x=p2.userid;
		Connection com=connection.getconnect();
		try
		{   
			String u,d = null,t=null;
			Statement pst = com.createStatement();
			String sql = "select * from appt where apptid='"+a+"'";
			ResultSet rs = pst.executeQuery(sql);
			if(rs.next())
			{   
				u=rs.getString(2);
				t=rs.getString(3);
				d=rs.getString(4);
				re=rs.getString(5);
				des=rs.getString(6);
				why="Your appointment for "+d+" ,time "+t+" has been cancelled. Kindly book another appointment.The doctor has sent you te following message"+pp;
				sql = "Insert into msg (apptid,senderid,receiverid,message,time)values(?,?,?,?,now())";
				try
				{
					PreparedStatement ps=com.prepareStatement(sql);
					ps.setString(1,a);
					ps.setString(2,x);
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
		 PrintWriter out = response.getWriter(); 
		 response.setContentType("text/html");  
		 out.println("<script type=\"text/javascript\">");  
		 out.println("alert('The appointment has been cancelled.');");  
		 out.println("location='doccancel';");
		 out.println("</script>");
	
	}

	

}