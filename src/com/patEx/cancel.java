package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class cancel
 */
@WebServlet("/cancel")
public class cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cancel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			patient p4=new patient();
			HttpSession session=request.getSession(false);
			p4=(patient)session.getAttribute("object");
			String u=p4.userid;
			Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
	    	Date time = localCalendar.getTime();
			response.setContentType("text/html");
	    	int td = localCalendar.get(Calendar.DATE); //Extracting today's date
	    	int tm = localCalendar.get(Calendar.MONTH)+1;
	    	int ty = localCalendar.get(Calendar.YEAR);
			String d1=Integer.toString(td),docid;
			String month = Integer.toString(tm);
			String year=Integer.toString(ty),fn,ln,n=null,s=null;
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
			String a,d,t,re,de,b=null;
			ArrayList array= new ArrayList();
			count=0;
			try
			{
				Statement pst = com.createStatement();
				String sql = "select * from appt where username='"+u+"' order by apptdate ASC,reqbegtime DESC";
				ResultSet rs = pst.executeQuery(sql);
			while(rs.next())
			{
				a=rs.getString(1);
				//u=rs.getString(2);
				t=rs.getString(3);
				d=rs.getString(4);
				re=rs.getString(5);
				if(d.compareTo(today)>=0)
				{
					count++;
					
					try {
					    final SimpleDateFormat sd = new SimpleDateFormat("H:mm");
					    final Date dateObj = sd.parse(t);
					    b=new SimpleDateFormat("hh:mm aa").format(dateObj);
					    
					} catch (final ParseException e) {
					    e.printStackTrace();
					}
					//array.add(a);
					//array.add(b);
					//array.add(t);
					//array.add(d);
					//array.add(re);
					
				}
				
			}
			}
			catch(Exception e)
			{
				
			}
			try
			{
				b=null;
			Statement pst = com.createStatement();
			String sql = "select * from appt where username='"+u+"'";
			Statement pst1 = com.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next())
			{
				a=rs.getString(1);
				u=rs.getString(2);
				t=rs.getString(3);
				d=rs.getString(4);
				re=rs.getString(5);
				docid=rs.getString(8);
				//System.out.println("YAy"+a+t+d+re+docid);
				String sql1="select * from docinfo where username='"+docid+"'";
				ResultSet rs1 = pst1.executeQuery(sql1);
				
				if(d.compareTo(today)>=0)
				{
					count++;
					
					try {
					    final SimpleDateFormat sd = new SimpleDateFormat("H:mm");
					    final Date dateObj = sd.parse(t);
					    b=new SimpleDateFormat("hh:mm aa").format(dateObj);
					    if(rs1.next())
						{
							fn=rs1.getString(2);
							ln=rs1.getString(3);
							n=fn+" "+ln;
							s=rs1.getString(7);
							System.out.println("Specializ "+s);
						}
					} catch (final ParseException e) {
					    e.printStackTrace();
					}
					array.add(a);
					array.add(b);
					array.add(d);
					array.add(re);
					array.add(n);
					array.add(s);
					System.out.println("Yay"+a+b+d+re+n+s);
					
				}
				
			}
			}
			catch(Exception e)
			{
				
			}
			if(count==0)
			{
				PrintWriter out = response.getWriter(); 
				//out.print("<center><h1>No appointments to display </center></h1>");
				out.print("<script>");
				out.print("alert(\"No Appointments to Display\");");
				out.print("setTimeout(function(){document.location = 'patdash.jsp'}, 1)");
				out.print("</script>");
				
			}
			else
			{
				request.setAttribute("array",array);
	            RequestDispatcher rd=request.getRequestDispatcher("/cancel.jsp");
	            rd.forward(request, response);
			}
			
	}

	

}