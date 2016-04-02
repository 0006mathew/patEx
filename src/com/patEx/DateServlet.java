package com.patEx;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/DateServlet")
public class DateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String day = request.getParameter("seldate");//read the details from book.jsp
		String m=day.substring(0,2);
		String d=day.substring(3,5);
		String y=day.substring(6);
	   String dt=y+"-"+m+"-"+d;//picked date
	   java.sql.Date dat=java.sql.Date.valueOf(dt);
	   
	   	doctor p4=new doctor();
		HttpSession session=request.getSession(false);
		p4=(doctor)session.getAttribute("object");
		
	   
	  //System.out.println(dat);
	   try{

		   	ArrayList appoint= new ArrayList();
				Connection conn = connection.getconnect();  
	       	PreparedStatement pst = conn.prepareStatement("select apptid,username,reqbegtime,reason,description from appt where apptdate=? and docid=?");  
	           pst.setDate(1, dat);
	           pst.setString(2, p4.userid);
	           ResultSet rs = pst.executeQuery();
	           while (rs.next()) {
	           	appoint.add(rs.getString(1));
	           	appoint.add(rs.getString(2));
	           	appoint.add(rs.getString(3));
	           	appoint.add(rs.getString(4));
	           	appoint.add(rs.getString(5));
	           //	System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3)+rs.getString(4)+rs.getString(5));
	           } 
	           request.setAttribute("appointments",appoint);
	           RequestDispatcher rd=request.getRequestDispatcher("viewappt.jsp");
	           rd.forward(request, response);
	           conn.close();
	    }
		catch(Exception e){
			e.printStackTrace();
		}       
	}
}