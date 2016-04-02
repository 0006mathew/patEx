package com.patEx;
import java.io.IOException;
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
 * Servlet implementation class bookonetwo
 */
@WebServlet("/bookonetwo")
public class bookonetwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookonetwo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String a=(String)request.getParameter("id");
		//System.out.println("ID "+a);
		patient p4=new patient();
		HttpSession session=request.getSession(false);
		p4=(patient)session.getAttribute("object");
		session.setAttribute("doc", a);
		String sql="select * from docinfo where username='"+a+"'",name1=null,name2=null,name=null;
		Connection com=connection.getconnect();
		try {
			Statement ps = com.createStatement();
			ResultSet rs = ps.executeQuery(sql);
			while(rs.next())
			{
				name1=rs.getString(2);
				name2=rs.getString(3);
				name=name1+" "+name2;
				session.setAttribute("docname", name);
				//System.out.println("What's my name"+name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("bookappt.jsp");
	}

}