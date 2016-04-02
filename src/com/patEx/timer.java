package com.patEx;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


/**
 * Servlet implementation class timer
 */
@WebServlet("/timer")
public class timer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		patient p=(patient)session.getAttribute("object");
		Connection conn=null;
		PreparedStatement pst=null;	
		ResultSet rs=null,n=null;		
		long suni = 0;int flag=0;
		long ss1 = 0,sm1=0,sy1=0,sh1=0;
		String id = null,d1=null,start = null,doc=null,qp = null;
		String ref1=null;			
		conn=connection.getconnect();	

		//Timer implementation:
		
			try{
			String query="Select apptid,reqbegtime,apptdate,docid from patEx.newappt where username=? and apptdate>=curdate() and c='n' order by apptdate ASC,reqbegtime DESC limit 1";
			pst=conn.prepareStatement(query);
			pst.setString(1, p.userid);
			rs=pst.executeQuery();
			while(rs.next()){
				id=rs.getString("apptid");
				//System.out.println("ID's: "+id);
				start=rs.getString("reqbegtime");
				//System.out.println("Start Time: "+start);
				d1=rs.getString("apptdate");
				//System.out.println("ApptDate: "+d1);
				doc=rs.getString("docid");
				//System.out.println("Doctor's ID: "+doc);			
		}
		}catch(Exception e){
					System.out.println("We are the problem:2");
			System.out.println("Some error in the first database shitz");
		}	
			
		//converting start date into unix timestamp
			
		String sm,sh,ss,sd,smt,sy;	
		sm=start.substring(3,5);
		sh=start.substring(0,2);
		ss=start.substring(6);
		sy=d1.substring(0,4);
		ref1=sy.concat("-01-01");
	
		try{
			
		 ss1 = Long.parseLong(ss.trim());
		 sm1=Long.parseLong(sm.trim())-30;
		 sh1=Long.parseLong(sh.trim())-5;
         sy1=(Long.parseLong(sy.trim()))-1900;
		
		}
		catch(Exception e)
		{
			System.out.println("We are the problem:3");
			e.printStackTrace();
		}
		try{
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = formatter.parse(ref1);			
			java.util.Date date2=formatter.parse(d1);
			long diff = date2.getTime() - date1.getTime();
		    long diff2=TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		    suni=ss1 + sm1*60 + sh1*3600 + diff2*86400 +(sy1-70)*31536000 + ((sy1-69)/4)*86400 -((sy1-1)/100)*86400 + ((sy1+299)/400)*86400;
		    //System.out.println("User ID: "+p.userid);
			System.out.println("Start Unix timestamp: "+suni);
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("We are the problem:4");
			e.printStackTrace();
		}
		//Queue Position Implementation
		try{
	    String app = null;
		String q1="Select @rn:=@rn+1 AS rank,apptid,username FROM(Select apptid,username from patEx.appt where apptdate=curdate() and c='n' and docid=? order by reqbegtime ASC) t1, (SELECT @rn:=0) t2";
		pst=conn.prepareStatement(q1);
		pst.setString(1, doc);
		rs=pst.executeQuery();
		while(rs.next()){
			app=rs.getString("apptid");
			//System.out.println("app= "+app+" id= "+id);
			if(app.equals(id)){
				qp=rs.getString("rank");
				//System.out.println("Queue Position: "+qp);
			}
		}
		}catch(Exception e){
			System.out.println("Error in timer yo. Run!");
		}
		long curt=System.currentTimeMillis();        
		//System.out.println("Current time in milis: "+curt);
	/*	if(curt>suni){
			qp=null;
		}*/
			//get session variable and set the value
		 session.setAttribute("queue", qp);
		 session.setAttribute("start1", suni);		
		 RequestDispatcher r1 = request.getRequestDispatcher("patdash.jsp");
	     r1.forward(request,response);
		
	}

}
