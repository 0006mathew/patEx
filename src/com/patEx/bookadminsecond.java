package com.patEx;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.UUID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Servlet implementation class booksecond
 */
@WebServlet("/bookadminsecond")
public class bookadminsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookadminsecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session=request.getSession(false);
		admin a=new admin();
		a=(admin)session.getAttribute("object");
		
		String r=request.getParameter("reason");
		String d=request.getParameter("description");
		String patient=(String)session.getAttribute("patient");
		
		java.sql.Date da=java.sql.Date.valueOf((String)session.getAttribute("reqdate"));
		//java.sql.Time s=java.sql.Time.valueOf((String)request.getParameter("slot"));
		String s=request.getParameter("slot");
		// System.out.println("\nSlot: "+s);
		 
	      //Format of the date defined in the input String
	      DateFormat df = new SimpleDateFormat("hh:mmaa");
	      //Desired format: 24 hour format: Change the pattern as per the need
	      DateFormat outputformat = new SimpleDateFormat("HH:mm");
	      Date date = null;
	      String output = null;
	      try{
	         //Converting the input String to Date
	    	 date= df.parse(s);
	         //Changing the format of date and storing it in String
	    	 output = outputformat.format(date);
	         //Displaying the date
	    	// System.out.println(output);
	      }catch(ParseException pe){
	         pe.printStackTrace();
	       }
		    //String si= f2.format(daa);
		   String si=output+":00";
	     
	  //  System.out.println("Time has be converted from "+s+" to "+si);
	    java.sql.Time sii=java.sql.Time.valueOf(si);
	  //  System.out.println("Time "+sii);
		String di=(String)session.getAttribute("doc");
		String p=(String)session.getAttribute("pat");
		System.out.println("Patid="+patient);
		String x= UUID.randomUUID().toString().replace("-","").substring(0,14);
		
		
		try
		{
				Connection conn=connection.getconnect();
				PreparedStatement st =conn.prepareStatement("INSERT INTO apptreq (apptid,username,reqbegtime,apptdate,reason,description,docid) VALUES (?,?,?,?,?,?,?)");
		        st.setString(1,x);
		        st.setString(2,patient);
		        st.setTime(3,sii);
		        st.setDate(4,da);
		        st.setString(5,r);
		        st.setString(6,d);
		        st.setString(7,di);
		        st.executeUpdate();
		        String y=a.pat;
				// System.out.println("Patient ID = "+y);
				// System.out.println("Doc ID = "+di);
				 int count=0;
				 String c="b";
				 String sql1 = "update schedule set app=? WHERE date =? AND begtime=? AND docid=?";
				 try
				 {
					 PreparedStatement ps = conn.prepareStatement(sql1);
					 ps.setString(1,c);
					 ps.setDate(2, da);
					 ps.setTime(3, sii);
					 ps.setString(4,di);
					 ps.executeUpdate();
				 }
				 catch(Exception e){
					 System.out.println("error"+e);
				 }
				 String sq="select * from docpat where patid='"+y+"'";
				 Statement pst;
				 try {
					pst = conn.createStatement();
					ResultSet rs = pst.executeQuery(sq);
					while(rs.next())
					{
						String q=rs.getString(1);
						
						String w=rs.getString(2);
						String e=rs.getString(3);
						//System.out.println("mo "+q+w+e);
						if(w.compareTo(di)==0)
						{
							count=1;
							break;
							
						}

					}
					if(count==0)
					{
						st =conn.prepareStatement("INSERT INTO docpat (docid, patid) VALUES (?,?)");
						st.setString(1,di);
		               st.setString(2,patient);
		               st.executeUpdate();
					}
						
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("error"+e);
				}
		        conn.close();
		        RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/admdash.jsp");
				RequetsDispatcherObj.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e);
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
		
		
	}

}