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

/**
 * Servlet implementation class doccancel
 */
@WebServlet("/admindoccancel")
public class admindoccancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admindoccancel() {
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
		String a,u,d,t,re,de,c,cc=null,dob=null,s=null;
		ArrayList items= new ArrayList();
		count=0;
		String b=null;
		try
		{
		Statement pst = com.createStatement();
		Statement pst1=com.createStatement();
		String sql = "select * from appt ",sql1;
		ResultSet rs = pst.executeQuery(sql),rs1;
		while(rs.next())
		{
			a=rs.getString(1);
			u=rs.getString(2);
			t=rs.getString(3);
			d=rs.getString(4);
			re=rs.getString(5);
			de=rs.getString(6);
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
				sql1="select fname,lname,dob,sex from patinfo where username='"+u+"'";
				rs1=pst1.executeQuery(sql1);
				if(rs1.next())
				{
					b=rs1.getString(1);
					c=rs1.getString(2);
					cc=b+" "+c;
					dob=rs1.getString(3);
					s=rs1.getString(4);
				}
				items.add(a);
				items.add(cc);
				items.add(dob);
				items.add(s);
				items.add(b);
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
			PrintWriter out = response.getWriter(); 
			out.print("<script>");
			out.print("alert(\"No Appointments to Display\");");
			out.print("setTimeout(function(){document.location = 'admdash.jsp'}, 1)");
			out.print("</script>");
			
		
		}
		else
		{
			request.setAttribute("items",items);
            RequestDispatcher rd=request.getRequestDispatcher("/admindoccancel.jsp");
            rd.forward(request, response);
		}
	}

	
	
}