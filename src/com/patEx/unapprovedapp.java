package com.patEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class unapprovedreq
 */
@WebServlet("/unapprovedapp")
public class unapprovedapp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public unapprovedapp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        Date time = localCalendar.getTime();
		response.setContentType("text/html");
        int td = localCalendar.get(Calendar.DATE); //Extracting today's date
        int tm = localCalendar.get(Calendar.MONTH)+1;
        int ty = localCalendar.get(Calendar.YEAR);
		
		String d1=Integer.toString(td);
		String month = Integer.toString(tm);
		String year=Integer.toString(ty);
		int count=0, r;
		while(tm!=0)
		{
			r=tm%10;
			tm=tm/10;
			count++;
			
		}
		if(count==1)
			month="0"+month;
		
		count=0;
		while(td!=0)
		{
			r=td%10;
			td=td/10;
			count++;
			
		}
		if(count==1)
			d1="0"+d1;
		
		String today=year+"-"+month+"-"+d1;//today's date
		//System.out.println("today"+today);
		//System.out.println("kiran mathew mohan");
		Connection com=connection.getconnect();
		Statement pst;
		ArrayList items= new ArrayList();
		try {
			pst = com.createStatement();
			patient p2=new patient();
			HttpSession session=request.getSession(false);
			p2=(patient)session.getAttribute("object");
			String x=p2.userid;
			String sql = "select * from apptreq where username='"+x+"' and apptdate>CURDATE();";
			ResultSet rs = pst.executeQuery(sql);
			System.out.println("pwolich bro"+x);
			while(rs.next())
			{
			   String a=rs.getString(1);
			   String b=rs.getString(2);
			  // System.out.println(b);
			   String c=rs.getString(3);
			   String d=rs.getString(4);
			   String e=rs.getString(5);
			   String f=rs.getString(6);
			   String g=rs.getString(7);
			  // System.out.println("rofl");
			   if(b.compareTo(today)>0) 
				{
				   items.add(a);
				   items.add(b);
				   items.add(c);
				   items.add(d);
				   items.add(e);
				   items.add(f);
				   items.add(g);
				 /*  System.out.println(a);
				   System.out.println(b);
				   System.out.println(c);
				   System.out.println(d);
				   System.out.println(e);
				   System.out.println(g);*/
				   
				}
			    
			}
			request.setAttribute("items",items);
			RequestDispatcher rd=request.getRequestDispatcher("/unapprovedapp.jsp");    
		    rd.forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}