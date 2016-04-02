package com.patEx;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
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
 * Servlet implementation class LoginServlet
 */
     
@WebServlet(description = "A simple pgrm", urlPatterns = { "/book" })
public class book extends HttpServlet {
	java.sql.Date fdate;
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String day = request.getParameter("day");//read the details from book.jsp
		String m=day.substring(0,2);
		String d=day.substring(3,5);
		String y=day.substring(6);
	    String fd=y+"-"+m+"-"+d;
		
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
		String today=year+"-"+month+"-"+d1;
		int j;
		//System.out.println("fd is "+fd+"today is"+today);
		if(fd.compareTo(today)<0) 
		{    
			// System.out.println("The value "+fd.compareTo(today));
			 j=1;
			 request.setAttribute("error","error"); //redirecting to the same page
			 String errorMsg=(String)request.getAttribute("ErrorMessage");
             RequestDispatcher dispatcher =  request.getRequestDispatcher("bookappt.jsp");
             dispatcher.forward(request, response);
		}
			
		else
		{
					
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-dd-MM");
		java.util.Date date;
		try {
			date = sdf1.parse(fd);
			 fdate = new java.sql.Date(date.getTime());
			 
			 
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection com=connection.getconnect();
		try {
			
			
			Statement pst = com.createStatement();
			patient p2=new patient();
			HttpSession session=request.getSession(false);
			p2=(patient)session.getAttribute("object");
			String x=(String)session.getAttribute("doc");
			String sql = "select * from schedule where docid='"+x+"' and app='"+"a"+"'";
			ResultSet rs = pst.executeQuery(sql);
			  
			 
             int p=0,k=0;
             String a;
             ArrayList items= new ArrayList();
             while(rs.next())
			{
            	 Date dat =rs.getDate(2);  // wherever you get this
            	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            	 String text = df.format(dat);
			
			    //java.sql.Date dbdate = rs.getDate(1);
			    String dbstime = rs.getString(3); 
			    String dbetime = rs.getString(4); 
	            a=rs.getString(5);
			   // System.out.println("At book. Status "+a+" Time "+rs.getString(3));
			 if(text.equals(fd)&&a.equals("a"))
			 {
				 p++;
				 
				 //items.add(dbstime);
				 //items.add(dbetime);
				 try {
					    final SimpleDateFormat sd = new SimpleDateFormat("H:mm");
					    final Date dateObj = sd.parse(dbstime);
					    String b=new SimpleDateFormat("hh:mmaa").format(dateObj);
					    items.add(b);
					} catch (final ParseException e) {
					    e.printStackTrace();
					}
				 
				 
			 }
    	}
             
			 if(p==0)
			 {
				 PrintWriter out = response.getWriter(); 
				 response.setContentType("text/html");  
				 out.println("<script type=\"text/javascript\">");  
				 out.println("alert('There are no free slots. Kindly check for another day.');");  
				 out.println("location='bookappt.jsp';");
				 out.println("</script>");
				 
				 
			 }
			 else
			 {
				    patient p4=new patient();
					//HttpSession session=request.getSession(false);
					p4=(patient)session.getAttribute("object");
					session.setAttribute("reqdate", fd);
					session.setAttribute("availableItems",items);
					//RequestDispatcher rd=request.getRequestDispatcher("/bookappt2.jsp");
					//rd.forward(request, response);
					response.sendRedirect("bookappt2.jsp");
			 }
     
		} 
			 catch (SQLException e) {
				 System.out.println("Exception="+e);
			// TODO Auto-generated catch block
			e.printStackTrace();
			 }	}
	}
	}