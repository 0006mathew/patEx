package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class admineditpasspat
 */
@WebServlet("/admineditpasspat")
public class admineditpasspat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admineditpasspat() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String a=request.getParameter("opass");
		String b=request.getParameter("npass");
		admin p2=new admin();
		HttpSession session=request.getSession(false);
		p2=(admin)session.getAttribute("object");
		String x=(String)session.getAttribute("pat"),y=null;
		//System.out.println(b+"   b"+x+" user id");
		//System.out.println("b"+b+" user id "+x);
		Connection com=connection.getconnect();
		PrintWriter out = response.getWriter();  
		String sql="select password from credentials where username='"+x+"'";
		try {
			Statement stmt=com.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(!rs.isBeforeFirst()){
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Inactive account: Password cannot be changed');");  
				out.println("location='admdash.jsp';");
			    out.println("</script>");
			}else{
			if(rs.next())
			{
				y=rs.getString(1);
			}
			//System.out.println("Password"+y);
			if(y.equals(a))
			{
				String sql1 = 
						   "UPDATE credentials " + 
						   "SET password = ? WHERE username =?";
				try {
					PreparedStatement updateQuery  = com.prepareStatement(sql1);
					updateQuery.setString(1, b);
					updateQuery.setString(2, x);
					updateQuery.executeUpdate();
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('The password change is successful!');");  
					out.println("location='admdash.jsp';");
				    out.println("</script>");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error e1 "+e1);
					e1.printStackTrace();
				}
			}
			else
			{
				
				 response.setContentType("text/html");  
				 out.println("<script type=\"text/javascript\">");  
				 out.println("alert('The old password entered is incorrect. Try again!');");  
				 out.println("location='admineditpasspat.jsp';");
				 out.println("</script>");
			}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error e "+e);
			e.printStackTrace();
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