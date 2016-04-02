package com.patEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class populate
 */
@WebServlet("/populate")
public class populate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public populate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sql="select * from credentials where role='doctor'";
		Connection com=connection.getconnect();
		ArrayList items= new ArrayList();
		try {
			Statement pst = com.createStatement();
			String a;
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next())
			{
				a=rs.getString(1);
				items.add(a);
				//System.out.println("Pika"+a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("items",items);
        RequestDispatcher rd=request.getRequestDispatcher("/setslots.jsp");
        rd.forward(request, response);
        
	}

	

}
