package com.patEx;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class deletedocsecond
 */
@WebServlet("/deletedocsecond")
public class deletedocsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletedocsecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String a=(String)request.getParameter("id");
		//System.out.println("ID "+a);
		Connection com=connection.getconnect();
		String pop[] = new String[300];
		Statement pst;
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
		String b,c,d,e;
		int count1=0;
		ArrayList items= new ArrayList();
		try {
			pst = com.createStatement();
			String sql="select * from appt where docid='"+a+"'";
	        ResultSet rs=pst.executeQuery(sql);
	        
	        while(rs.next())
	        {
	        	b=rs.getString(2);
	        	String sql1="select fname,lname from patinfo where username='"+b+"'",v,x,name=null;
	        	Statement pst1=com.createStatement();
	        	ResultSet rs1=pst1.executeQuery(sql1);
	        	if(rs1.next())
	        	{
	        	  v=rs1.getString(1);
	        	  x=rs1.getString(2);
	        	  name=v+" "+x;
	        	}
	        	c=rs.getString(3);
	        	d=rs.getString(4);
	        	e=rs.getString(5);
	        	if(d.compareTo(today)>=0)
	        	{
	        		count1++;
	        		items.add(a);
	        		items.add(name);
	        		items.add(c);
	        		items.add(d);
	        		items.add(e);
	        		System.out.println("Here "+a+name+c+d+e);
	        		
	        	}
	        }
	        
		} catch (SQLException pp) {
			// TODO Auto-generated catch block
			pp.printStackTrace();
		}
		if(count1==0)
		{
			 
			try {
				pst = com.createStatement();
				String sql="delete from credentials where username='"+a+"'";
		        pst.executeUpdate(sql);
			} catch (SQLException ff) {
				// TODO Auto-generated catch block
				ff.printStackTrace();
			}
			 PrintWriter out = response.getWriter(); 
			 response.setContentType("text/html");  
			 out.println("<script type=\"text/javascript\">");  
			 out.println("alert('This doctor has no appointments.');");  
			 out.println("location='deletedoc';");
			 out.println("</script>");
		    //response.sendRedirect("deletedoc");	
		}
		else
		{
			
			admin p4=new admin();
			HttpSession session=request.getSession(false);
			p4=(admin)session.getAttribute("object");
			session.setAttribute("items",items);
			RequestDispatcher rd=request.getRequestDispatcher("/deletedoc2.jsp");
			rd.forward(request,response);
			//response.sendRedirect("deletedoc2.jsp");
		}
	}

}