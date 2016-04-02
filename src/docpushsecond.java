

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.patEx.connection;

/**
 * Servlet implementation class docpushsecond
 */
@WebServlet("/docpushsecond")
public class docpushsecond extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn=null;
		ResultSet rs=null;
		Statement st=null;
		PreparedStatement pst=null;
		java.sql.Time rbt,nt,et,nt1;
		String app;
		conn=connection.getconnect();
		long ms=0;//ms is the time taken from popup
		//Done for APPT
		String q1="Select apptid,reqbegtime from appt where reqbegtime>curtime()";
		String q2="Update appt set reqbegtime=? where apptid=?";
		try {
			pst=conn.prepareStatement(q2);
			st=conn.createStatement();
			rs=st.executeQuery(q1);
			while(rs.next()){
				rbt=rs.getTime("reqbegtime");
				app=rs.getString("apptid");
				long m1=rbt.getTime();
				m1=m1+ms;
				nt=new Time(m1);
				System.out.println("The new time "+nt);
				pst.setString(2,app);
				pst.setTime(1, nt);
				pst.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String q3="Select apptid,reqbegtime,endtime from newappt where reqbegtime>curtime()";
		String q4="Update newappt set reqbegtime=? , endtime=? where apptid=?";
		try {
			pst=conn.prepareStatement(q4);
			st=conn.createStatement();
			rs=st.executeQuery(q3);
			while(rs.next()){
				rbt=rs.getTime("reqbegtime");
				app=rs.getString("apptid");
				et=rs.getTime("endtime");
				long m1=rbt.getTime();
				long m2=et.getTime();
				m1=m1+ms;
				m2=m2+ms;
				nt=new Time(m1);
				nt1=new Time(m2);
				System.out.println("The new time "+nt);
				pst.setString(3,app);
				pst.setTime(1, nt);
				pst.setTime(2, nt1);
				pst.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
