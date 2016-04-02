package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Statement;

/**
 * Servlet implementation class admineditdocone
 */
@WebServlet("/admineditdocone")
public class admineditdocone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admineditdocone() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("docid");
		HttpSession session=request.getSession(false);
		//admin d=new admin(id);
		//d.doc=id;
		int count=0;
	    String sql="select *from docinfo where username='"+id+"'";
	    Connection conn=connection.getconnect();
	    Statement pst;
		try {
			pst = conn.createStatement();
			ResultSet rs=pst.executeQuery(sql);
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
			out.println("alert('Incorrect doctor id. Try again!');");  
			out.println("location='admindoceditone.jsp';");
		    out.println("</script>");
	    }
	    else
	    {
		session.setAttribute("doc", id);
		RequestDispatcher rd=request.getRequestDispatcher("admineditdoc");    
	    rd.forward(request,response); 
	    }
	}


}