package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class setslot
 */
@WebServlet("/setslot")
public class setslot extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public setslot() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String day=request.getParameter("day");
		String n1=request.getParameter("number");
		int n=Integer.parseInt(n1);
		String dur=request.getParameter("dur");
		String s=request.getParameter("start");
		String e=request.getParameter("end");
		String bs=request.getParameter("breakstart");
		String be=request.getParameter("breakend");
		String bs1=request.getParameter("breakstart1");
		String be1=request.getParameter("breakend1");
		String m=day.substring(0,2);
		String d=day.substring(3,5);
		String y=day.substring(6);
	    String fd=y+"-"+m+"-"+d;
		admin p2=new admin();
		HttpSession session=request.getSession(false);
		p2=(admin)session.getAttribute("object");
		String fd1=fd,fd2;
		p2.number=n;
		p2.id=id;
		p2.cdate=fd;
		p2.dur=dur;
		p2.s=s;
		p2.e=e;
		p2.bs=bs;
		p2.be=be;
		p2.bs1=bs1;
		p2.be1=be1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int i,count=0;
		PrintWriter out = response.getWriter(); 
		SimpleDateFormat p = new SimpleDateFormat("HH:mm");
		java.util.Date start;
		java.util.Date end ;
		java.util.Date bss ;
		java.util.Date bee ;
		java.util.Date bss1 ;
		java.util.Date bee1 ;
		try {
			start = p.parse(s);
			end =p.parse(e);
			bss=p.parse(bs);
			bee=p.parse(be);
			bss1=p.parse(bs1);
			bee1=p.parse(be1);
			if(end.before(start)||end.equals(start))
			{
				count=1;
	            out.println("<script type=\"text/javascript\">");  
				out.println("alert('Incorrect start and end timing!');");  
				out.println("location='populate';");
			    out.println("</script>");
			}
			if(bee.before(bss)||bee1.before(bss1))
			{
				count=1;
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Incorrect break timing!');");  
				out.println("location='populate';");
			    out.println("</script>");
			}
			if(be.compareTo("5:00")!=0)
			{
				if(bee.before(start)||bee.equals(bss)||bss.before(start))
				{
					
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Incorrect break timing!');");  
					out.println("location='populate';");
				    out.println("</script>");
				}
			}
			if(be1.compareTo("5:00")!=0)
			{
				if(bee1.before(start)||bss1.before(start)||bee1.equals(bss1))
				{
					
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Incorrect break timing!');");  
					out.println("location='populate';");
				    out.println("</script>");
				}
			}
			
				if(bee.before(end)||bee1.before(end)||bss.before(end)||bss1.before(end))
				{
					
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Incorrect break timing!');");  
					out.println("location='populate';");
				    out.println("</script>");
				}
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
			if(count==0)
			{
		ArrayList items= new ArrayList();
		for(i=0;i<n;i++)
		{
		try {
			c.setTime(sdf.parse(fd1));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		 d=fd1.substring(8);
		 m=fd1.substring(5,7);
		 y=fd1.substring(0,4);
	     fd2=d+"-"+m+"-"+y;
		 items.add(fd2);
		 c.add(Calendar.DATE, 1);
		 fd1 = sdf.format(c.getTime());
		// System.out.println(fd1);
		  
		 
		}
		
		 request.setAttribute("availableItems",items);
         RequestDispatcher rd=request.getRequestDispatcher("/setslotsecond.jsp");
         rd.forward(request, response);
			}
	}
}