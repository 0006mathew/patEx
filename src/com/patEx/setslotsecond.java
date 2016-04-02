package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class setslotsecond
 */
@WebServlet("/setslotsecond")
public class setslotsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setslotsecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sql="select * from slot";
		String[] days=request.getParameterValues("days");
		String[] b=new String[100];
		String[] x=new String[100];
		String a=null,d,m,y,fd,fd1,v;
		int i,h;
		if(days!=null){
		for(i=0;i<days.length;i++)
		{
			a=days[i];
			d=a.substring(0, 2);
			m=a.substring(3, 5);
			y=a.substring(6);
			a=y+"-"+m+"-"+d;
			b[i]=a;
		}
		}
		admin p2=new admin();
		HttpSession session=request.getSession(false);
		p2=(admin)session.getAttribute("object");
	    int n=p2.number;
	    String[] slot=new String[200];
	    String[] slot1=new String[200];
		String id=p2.id;
		String day=p2.cdate;
		fd=day;
		String dur=p2.dur;
		String s=p2.s;
		String e=p2.e;
		String bs=p2.bs;
		String be=p2.be;
		String bs1=p2.bs1;
		String be1=p2.be1;
		String newTime=null;
		int du=Integer.parseInt(dur);
		int l,k=0,so=0,so1=0;
		slot1[so1++]=s;
		
		String q=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		for(i=0;i<n;i++)
		{
		try {
			c.setTime(sdf.parse(fd));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if(days!=null)
		{
		for(l=0;l<days.length;l++)
		{
			if(b[l].equals(fd))
            {}
			else
			{  
				x[k++]=fd;
				}
		}
		}
		else{
			x[k++]=fd;
		}
		c.add(Calendar.DATE, 1);
		fd = sdf.format(c.getTime()); 
		}
		 String myTime = s;
		 SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		 java.util.Date d1=null;
		 java.util.Date breakbeg=null;
		 java.util.Date breakend=null;
		 java.util.Date breakbeg1=null;
		 java.util.Date end=null;
		 java.util.Date f=null;
		 int count=0;
		 try {
				d1 = df.parse(myTime);
				breakbeg=df.parse(bs);
				breakend=df.parse(be);
				breakbeg1=df.parse(bs1);
				end=df.parse(e);
			} catch (ParseException e1) {
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 Calendar cal = Calendar.getInstance();
		 //starting time to break 1
		 do 
		 {
	     if((bs.compareTo("5:00")==0))
	     {
	    	 count=1;
	     }
	     else
	     {
		 cal.setTime(d1);
		 cal.add(Calendar.MINUTE, du);
		 newTime = df.format(cal.getTime());
		 try {
			d1=df.parse(newTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 if((d1.before(breakbeg)||d1.equals(breakbeg)))
		 {
			 q = df.format(cal.getTime());
	         slot[so++]=q;
	         
		 }
		 else
		 {
			 count=1;
		 } 
		 }
		 }while(count==0);
		 if((bs.compareTo("5:00")!=0))
	     {
			 //slot1[so1++]=be;
	     
		 for(i=so1;i<so;i++)
		 {
			 slot1[so1++]=slot[i-1];
		 }
	     }
		 System.out.println("First");
		 for(i=0;i<so;i++)
			 System.out.println(slot1[i]+" "+slot[i]);
		 count=0;
		 //break1end to break 2
		 try {
			 if((be.compareTo("5:00")==0))
				 d1=df.parse(s);
		     else
			   d1 = df.parse(be);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 do
		 {
		 if(bs1.compareTo("5:00")==0)
		 {
			 count=1;
		 }
		 else
		 {
		 cal.setTime(d1);
		 cal.add(Calendar.MINUTE, du);
		 newTime = df.format(cal.getTime());
		 try {
			d1=df.parse(newTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 if(d1.before(breakbeg1)||d1.equals(breakbeg1))
		 {
			 q = df.format(cal.getTime());
	         slot[so++]=q;
		 }
		 else
			 count=1;
		 }
		 }while(count==0);
		 if((bs1.compareTo("5:00")!=0))
	     {
			 slot1[so1++]=be;
			 for(i=so1;i<so;i++)
				 slot1[so1++]=slot[i-1];
	     }
		 System.out.println("Second");
		 for(i=0;i<so;i++)
			 System.out.println(slot1[i]+" "+slot[i]);
		 count=0;
		 //break2end to end
		 try {
			 if((bs1.compareTo("5:00")==0)&&(bs.compareTo("5:00")==0))
				 d1=df.parse(s);
			 else if((bs1.compareTo("5:00")==0)&&(bs.compareTo("5:00")!=0))
				 d1=df.parse(be);
		     else 
			   d1 = df.parse(be1);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 System.out.println("d1 "+d1);
		 do
		 {
		 cal.setTime(d1);
		 cal.add(Calendar.MINUTE, du);
		 newTime = df.format(cal.getTime());
		 try {
			d1=df.parse(newTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 if(d1.before(end)||d1.equals(end))
		 {
			 q = df.format(cal.getTime());
	         slot[so++]=q;
	         System.out.println("Slot do while "+slot[so-1]);
		 }
		 else
			 count=1;
		 }while(count==0);
		 if(bs1.compareTo("5:00")!=0)
		 {
			 slot1[so1++]=be1;
			 System.out.println("Slot1 in the if "+slot1[so1-1]);
		 }
		 for(i=so1;i<so;i++)
		 {
			 slot1[so1++]=slot[i-1];
			 System.out.println("Slot1 "+slot1[so-1]+" Slot "+slot[i-1]);

		 }
		 System.out.println("Third");
		 for(i=0;i<so;i++)
			 System.out.println(slot1[i]+" "+slot[i]);
		 String z,g,p;
		 p="a";
		 String da;int j;
		 java.sql.Date dat;
		 java.sql.Time t1;
		 java.sql.Time t2;
		 Connection com=connection.getconnect();
		 for(i=0;i<k;i++)
		 {
			
			 da=x[i];
			 dat=java.sql.Date.valueOf(da);
			 int count1=0;
			 String sql1="select * from schedule",pp=null,qq="";
			 try {
				Statement pst;
				 ResultSet rs1;
			     pst=com.createStatement();
			     rs1=pst.executeQuery(sql1);
				 while(rs1.next())
				 {
					 pp=rs1.getString(2);
					 qq=rs1.getString(6);
					 System.out.println("qq "+qq);
			        if((pp.compareTo(da)==0)&&((qq.compareTo(id)==0)||(qq.compareTo("")==0)))
					 {
					    count1++;
					 }
					 //System.out.println("Value of count= "+count1+" Date found= "+da+" Date from the DB "+pp+" DocID found = "+id+" Doctor ID from DB " +qq);
				 }
				 
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(count1==0)
			{
			 for(j=0;j<so;j++)
			 {
				z=slot1[j];
				z=z+":00";
				g=slot[j];
				g=g+":00";
				t1=java.sql.Time.valueOf(z);
				t2=java.sql.Time.valueOf(g);
				System.out.println(t1+" "+t2);
				try
				{    
					    			
					   PreparedStatement st =com.prepareStatement("INSERT INTO schedule (date, begtime, endtime, app, docid) VALUES (?,?,?,?,?)");
 				                 st.setDate(1,dat);
				                 st.setTime(2,t1);
				                 st.setTime(3,t2);
				                 st.setString(4,p);
				                 st.setString(5,id);
				                 st.executeUpdate();
				                
				
				}
				catch(Exception e2)
				{
					System.out.println("Error : "+e2);
				}
			 }
			 }
			else if(count1>0)
			{
				 
				 System.out.println("Entered else count= "+count1); 
				 PrintWriter out = response.getWriter();   
				 response.setContentType("text/html");  
				 out.println("<script type=\"text/javascript\">");  
				 out.println("alert('The old password entered is incorrect. Try again!');");  
				 out.println("</script>");
			}
		 }
		 RequestDispatcher rd=request.getRequestDispatcher("admdash.jsp");
	     rd.forward(request, response);
	}
}