package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * Servlet implementation class bookadmin
 */
@WebServlet("/bookadmin")
public class bookadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookadmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		int count=0;
		String sql="select * from patinfo where username='"+id+"'";
		Connection conn=connection.getconnect();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count==0)
		{
			PrintWriter out = response.getWriter(); 
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Incorrect patient id. Try again!');");  
			out.println("location='bookadmin.jsp';");
		    out.println("</script>");
		}
		else
		{
		HttpSession session=request.getSession(false);
		admin a1=new admin(id);
		a1.pat=id;
		session.setAttribute("patient", id);
		session.setAttribute("patobject", a1);
		//System.out.println("I am here " + id);
		response.sendRedirect("bookadminone");
		}
	}

	
}