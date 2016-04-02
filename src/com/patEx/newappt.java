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
 * Servlet implementation class newappt
 */
@WebServlet("/newappt")
public class newappt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newappt() {
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
	        HttpSession session=request.getSession(false);
			doctor p4=new doctor();
			p4=(doctor)session.getAttribute("object");
			String x=p4.userid;
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
			String a,u,t,d,re,de;int count1=0;
			ArrayList items= new ArrayList();
			count=0;
			try
			{
				Connection conn=connection.getconnect();
				Statement pst = conn.createStatement();
				Statement pst1=conn.createStatement();
				Statement pst2=conn.createStatement();
				String sql = "select * from apptreq where docid='"+x+"' order by apptdate ASC,reqbegtime ASC";
				String sql1,b,c,cc=null,dob=null,s=null;
				ResultSet rs = pst.executeQuery(sql);
				ResultSet rs1,rs2;
				while(rs.next())
				{
					count1=0;
					a=rs.getString(1);
					u=rs.getString(2);
					t=rs.getString(3);
					d=rs.getString(4);
					re=rs.getString(5);
					de=rs.getString(6);
					String sq="select * from appt where username='"+u+"'"+" and docid='"+x+"'";
					rs2=pst2.executeQuery(sq);
					while(rs2.next())
					{
						count1++;
					}
					System.out.println("a "+u+" count "+count1);
					if(d.compareTo(today)>=0)
					{
						count++;
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
						//items.add(u);
						items.add(cc);
						items.add(dob);
						items.add(s);
						items.add(t);
						items.add(d);
						items.add(re);
						items.add(de);
						items.add(count1);
						//System.out.println("Hey lol"+a+cc+t+d+re+de);
					}
				}
				conn.close();
			}
			catch(Exception e)
			{
				System.out.println("error in newappt.java");
				e.printStackTrace();
			}
			
			if(count==0)
			{
				PrintWriter out = response.getWriter(); 
				out.print("<script>");
				out.print("alert(\"No Appointments to Display\");");
				out.print("setTimeout(function(){document.location = 'docdash'}, 1)");
				out.print("</script>");
				
			}
			else
			{
				request.setAttribute("items",items);
	            RequestDispatcher rd=request.getRequestDispatcher("/newappt.jsp");
	            rd.forward(request, response);
			}
	}

}