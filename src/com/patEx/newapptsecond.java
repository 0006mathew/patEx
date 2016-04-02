package com.patEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class newapptsecond
 */
@WebServlet("/newapptsecond")
public class newapptsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newapptsecond() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bt=request.getParameter("bt");
		System.out.println(bt);
		String a=(String)request.getParameter("appid");
		System.out.println(a);
		if(bt.equalsIgnoreCase("yes"))
		{
			try {
				Connection conn=connection.getconnect();
				Statement pst = conn.createStatement();
				Statement pst2 = conn.createStatement();
				String sql = "select * from apptreq where apptid='"+a+"'";
				ResultSet rs = pst.executeQuery(sql);
				Statement pst1 = conn.createStatement();
				ResultSet rs1;
				if(rs.next()){
					String u=rs.getString(2);
					String t=rs.getString(3);
					String d=rs.getString(4);
					String re=rs.getString(5);
					String de=rs.getString(6);
					String did=rs.getString(7);
					java.sql.Date da=java.sql.Date.valueOf(d);
					java.sql.Time s=java.sql.Time.valueOf(t);
					PreparedStatement st =conn.prepareStatement("INSERT INTO appt (apptid, username, reqbegtime, apptdate, reason, description,c,docid) VALUES (?,?,?,?,?,?,?,?)");
					st.setString(1,a);
					st.setString(2,u);
					st.setTime(3,s);
					st.setDate(4,da);
					st.setString(5,re);
					st.setString(6,de);
					st.setString(7,"n");
					st.setString(8,did);
					st.executeUpdate();
					String sql2="delete from apptreq where apptid='"+a+"'";
					pst2.executeUpdate(sql2);
					String sql1="select endtime from schedule where date='"+d+"' and begtime='"+t+"' and docid='"+did+"'";
					rs1=pst1.executeQuery(sql1);
					if(rs1.next())
					{
					  String ax=rs1.getString(1);
					 // System.out.println("AX "+ax);
				      PreparedStatement st1 =conn.prepareStatement("INSERT INTO newappt (apptid, username, reqbegtime,apptdate, reason, description,c,docid,endtime) VALUES (?,?,?,?,?,?,?,?,?)");
				      java.sql.Time ax1=java.sql.Time.valueOf(ax);
				      st1.setString(1,a);
					  st1.setString(2,u);
					  st1.setTime(3,s);
					  //st1.setTime(4, ax1);
				      st1.setDate(4,da);
					  st1.setString(5,re);
					  st1.setString(6,de);
					  st1.setString(7,"n");
					  st1.setString(8,did);
					  st1.setTime(9,ax1);
					  st1.executeUpdate();
					
					}
					
				}
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception "+e);
				e.printStackTrace();
			}
			
		}
		else
		{
			//System.out.println("\nNo");
			try {
				Connection conn=connection.getconnect();
				Statement pst = conn.createStatement();
				String sql="delete from apptreq where apptid='"+a+"'";
                pst.executeUpdate(sql);
                conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("newappt");
	}

	

}