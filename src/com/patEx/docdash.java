package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
 * Servlet implementation class doccancel
 */
@WebServlet("/docdash")
public class docdash extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public docdash() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
        Date time = localCalendar.getTime();
		response.setContentType("text/html");
        int td = localCalendar.get(Calendar.DATE); //Extracting today's date
        int tm = localCalendar.get(Calendar.MONTH)+1;
        int ty = localCalendar.get(Calendar.YEAR);
        doctor p2=new doctor();
		HttpSession session=request.getSession(false);
		p2=(doctor)session.getAttribute("object");
		String x=p2.userid;
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
		String today=year+"-"+month+"-"+d1;
		Connection com=connection.getconnect();
		String a,u,d,t,re,de,docid;
		ArrayList items= new ArrayList();
		count=0;
		
		try
		{
		Statement pst = com.createStatement();
		String sql = "select * from appt where apptdate='"+today+"'"+"and c='"+"n"+"' order by reqbegtime ASC";		
		ResultSet rs = pst.executeQuery(sql);
		while(rs.next())
		{
			a=rs.getString(1);
			u=rs.getString(2);
			t=rs.getString(3);
			d=rs.getString(4);
			re=rs.getString(5);
			de=rs.getString(6);
			docid=rs.getString(8);
			//System.out.println("Docid"+docid+"    "+x);
			if(docid.compareTo(x)==0)
			{
				count++;
				items.add(a);
				items.add(u);
				items.add(t);
				items.add(d);
				items.add(re);
				items.add(de);
			}
		}
		}
		catch(Exception e)
		{
			
		}
		
		if(count==0)
		{
			RequestDispatcher rd=request.getRequestDispatcher("/docdash.jsp");
            rd.forward(request, response);
		}
		else
		{
			request.setAttribute("items",items);
            RequestDispatcher rd=request.getRequestDispatcher("/docdash.jsp");
            rd.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
}