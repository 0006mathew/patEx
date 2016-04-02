package com.patEx;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 * Servlet implementation class docpush
 */
@WebServlet("/docpush")
public class docpush extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String apptid;
		HttpSession session= request.getSession(false);
		apptid=(String) session.getAttribute("id");
		Connection conn=null;
		PreparedStatement pst=null,pst1=null;
		ResultSet rs=null;
		String doc=null;
		conn=connection.getconnect();
		//System.out.println("The appt got is: "+apptid);
		//updating the value of c in appt and newappt to indicate that the appointment is completed
		String q1="Update appt set c='"+"c"+"' where apptid=?";
		String q2="Update newappt set c='"+"c"+"' where apptid=?";
		try {
			pst=conn.prepareStatement(q1);
			pst.setString(1, apptid);
			pst.execute();
			pst=conn.prepareStatement(q2);
			pst.setString(1, apptid);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//selecting the completed row from newappt and putting in fin
		String q4="Select * from newappt where apptid=?";
		String q5="Insert into patEx.fin values(?,?,?,?,?,curtime(),?)";
		try {
			pst=conn.prepareStatement(q4);
			pst.setString(1, apptid);
			rs=pst.executeQuery();
			pst1=conn.prepareStatement(q5);
			while(rs.next()){
				pst1.setString(1,rs.getString("username"));
				pst1.setString(2, rs.getString("apptid"));
				pst1.setDate(3, rs.getDate("apptdate"));
				pst1.setTime(4, rs.getTime("reqbegtime"));
				pst1.setTime(5, rs.getTime("endtime"));
				pst1.setString(6, rs.getString("docid"));
			}
			pst1.execute();
			//System.out.println("Doctor's ID: "+doc);
						
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//selecting the row from newappt and checking if original end time < now()
		String q3="Select * from patEx.fin where apptid=?";
		java.sql.Time t = null,e1 = null,e2 = null,rbt=null,nt=null;
		try {
			pst=conn.prepareStatement(q3);
			pst.setString(1, apptid);
			rs=pst.executeQuery();
			while(rs.next()){
				doc=rs.getString("docid");
				t=rs.getTime("starttime");	
				e1=rs.getTime("actualend");
				e2=rs.getTime("endtime");
				//System.out.println("t and e1 and e2 "+t+" "+e1+" "+e2);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//calculating the difference between endtime and actualend and changing all values in newappt
		long ms=e1.getTime()-e2.getTime();
		String app;
		String q6="Select reqbegtime,apptid from newappt where reqbegtime>? and apptdate=curdate() and c='n' and docid=?";
		String q7="Update newappt set reqbegtime=? where apptid=?";
		try {
			pst=conn.prepareStatement(q6);
			pst.setTime(1,t);
			pst.setString(2, doc);
			rs=pst.executeQuery();
			pst1=conn.prepareStatement(q7);
			/*if(!rs.isBeforeFirst()){
				System.out.println("Bae, you fine as hell!");
			}else
			{*/
				while(rs.next()){
					rbt=rs.getTime("reqbegtime");
					app=rs.getString("apptid");
					long m1=rbt.getTime();
					m1=m1+ms;
					nt=new Time(m1);
					//System.out.println("The new time "+nt);
					pst1.setString(2,app);
					pst1.setTime(1, nt);
					pst1.execute();
				}
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
				pst.close();
				pst1.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("docdash");
		
	}

}
