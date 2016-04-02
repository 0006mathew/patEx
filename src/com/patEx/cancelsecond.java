package com.patEx;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.PreparedStatement;

/**
 * Servlet implementation class cancelsecond
 */
@WebServlet("/cancelsecond")
public class cancelsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cancelsecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String aid =(String)request.getParameter("apptid");
		String reason =(String)request.getParameter("reas");
		String msg="Cancel this appointment";
		if(reason.isEmpty())
			;
		else
			msg+=" because "+reason;
		patient p4=new patient();
		HttpSession session=request.getSession(false);
		p4=(patient)session.getAttribute("object");
		String u=p4.userid;
		String f="00";
		String sql = "Insert into msg (apptid,senderid,receiverid,message,time) values (?,?,?,?,now())";
		String sq="select * from apptreq where apptid='"+aid+"'";
		String sqlo="select * from appt where apptid='"+aid+"'";
		try
		{
			Connection com=connection.getconnect();
			Statement ps = com.createStatement();
			Statement ps1 = com.createStatement();
			Statement pst1 = com.createStatement();
			Statement pst2 = com.createStatement();
			ResultSet rs = ps.executeQuery(sq);
			ResultSet rs1 = ps1.executeQuery(sqlo);
			String sql1="delete from apptreq where apptid='"+aid+"'";
			pst1.executeUpdate(sql1);
			String sql2="delete from appt where apptid='"+aid+"'";
			pst2.executeUpdate(sql2);
			String f2=null,f1=null;
			if(rs.next())
			f2=rs.getString(7);
			else if(rs1.next())
			f1=rs1.getString(7);
			if(f2==null)
				f=f1;
			else
				f=f2;
			PreparedStatement pst=com.prepareStatement(sql);
			pst.setString(1,aid);
			pst.setString(2,u);	
			pst.setString(3,f);
			pst.setString(4,msg);
			pst.executeUpdate();
			
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert(\"Appointment cancelled\");");
			out.print("</script>");
			
			response.sendRedirect("cancel");
			//out.println("Cancellation has been completed");
		}
		catch(Exception e)
		{
			System.out.println("Error is"+e);
		}
	
	}
}