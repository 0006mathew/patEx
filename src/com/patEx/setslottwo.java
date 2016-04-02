package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class setslottwo
 */
@WebServlet("/setslottwo")
public class setslottwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setslottwo() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	    String[] slot=new String[100];
	    String[] slot1=new String[100];
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
		String newTime;
		int du=Integer.parseInt(dur);
		int l,k=0,so=0,so1=0;
		slot1[so1++]=s;
		String q=null;
		if(e.compareTo(s)<0)
		{
			PrintWriter out = response.getWriter(); 
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Incorrect start and end timing!');");  
			out.println("location='setslots.jsp';");
		    out.println("</script>");
		}
		if(be.compareTo(bs)<0||be1.compareTo(bs1)<0)
		{
			PrintWriter out = response.getWriter(); 
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Incorrect break timing!');");  
			out.println("location='setslots.jsp';");
		    out.println("</script>");
		}
		if(be.compareTo("5:00")!=0)
		{
			if(be.compareTo(s)<0||be.compareTo(s)<0)
			{
				PrintWriter out = response.getWriter(); 
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Incorrect break timing!');");  
				out.println("location='setslots.jsp';");
			    out.println("</script>");
			}
		}
		if(be1.compareTo("5:00")!=0)
		{
			if(be1.compareTo(s)<0||be1.compareTo(s)<0)
			{
				PrintWriter out = response.getWriter(); 
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Incorrect break timing!');");  
				out.println("location='setslots.jsp';");
			    out.println("</script>");
			}
		}
		
			if(be.compareTo(e)>0||be1.compareTo(e)>0||bs.compareTo(e)>0||bs1.compareTo(e)>0)
			{
				PrintWriter out = response.getWriter(); 
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Incorrect break timing!');");  
				out.println("location='setslots.jsp';");
			    out.println("</script>");
			}
		
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
		 java.util.Date f=null;int count=0;
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
		
	}

	

}
