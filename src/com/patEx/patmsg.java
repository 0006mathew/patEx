package com.patEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class patmsg
 */
@WebServlet("/patmsg")
public class patmsg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public patmsg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("\nControl reached here");
	    patient p4=new patient();
		HttpSession session=request.getSession(false);
		p4=(patient)session.getAttribute("object");
		String sql="select senderid,time,message,apptid from msg where receiverid='"+p4.userid+"'";
		Connection com=connection.getconnect();
		Statement pst;
		try {
			pst = com.createStatement();
			String a,b,c,d;
			ResultSet rs = pst.executeQuery(sql);
			ArrayList items= new ArrayList();
			while(rs.next())
			{
				a=rs.getString(1);
				b=rs.getString(2);
				c=rs.getString(3);
				d=rs.getString(4);
				items.add(a);
				items.add(b);
				items.add(c);
				items.add(d);
				//System.out.println("Message "+a+" "+b+" "+c+" "+d);
				
			}
			request.setAttribute("items",items);
            RequestDispatcher rd=request.getRequestDispatcher("/patmsg.jsp");
            rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}