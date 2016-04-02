package com.patEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.PreparedStatement;

/**
 * Servlet implementation class admindslottwo
 */
@WebServlet("/admindslottwo")
public class admindslottwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admindslottwo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String date = request.getParameter("date");
		String m=date.substring(0,2);
		String d=date.substring(3,5);
		String y=date.substring(6);
	    String dt=y+"-"+m+"-"+d;//picked date
	    java.sql.Date dat=java.sql.Date.valueOf(dt);
	    admin p4=new admin();
		HttpSession session=request.getSession(false);
		p4=(admin)session.getAttribute("object");
		String x=(String)session.getAttribute("doctor");
		Connection conn=connection.getconnect();
		PreparedStatement pst1 = null;
        ResultSet rs = null;
        ArrayList alist= new ArrayList();
        try{
        	pst1 =conn.prepareStatement("select * from schedule where date=? and app='a' and docid=?");
        	pst1.setDate(1, dat);
        	pst1.setString(2, x);
        	rs = pst1.executeQuery();
        	while(rs.next()){
    			
    			
    			alist.add((String)rs.getString("id"));
    			alist.add((String)rs.getString("begtime"));
    			alist.add((String)rs.getString("endtime"));
    		}
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        request.setAttribute("alist",alist);
        RequestDispatcher rd=request.getRequestDispatcher("adminslotthree.jsp");
        rd.forward(request, response);
		
	}

}