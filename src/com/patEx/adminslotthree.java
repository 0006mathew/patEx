package com.patEx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class adminslotthree
 */
@WebServlet("/adminslotthree")
public class adminslotthree extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminslotthree() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection conn=connection.getconnect();
		String[] id=request.getParameterValues("check");
		PreparedStatement pst1 = null;
		int s=id.length;
		//System.out.println("going to execute");
		try{
			for(int i=0;i<id.length;i++)
			{
				pst1 =conn.prepareStatement("update schedule set app='N' where id='"+id[i]+"'");
				pst1.executeUpdate();
				//System.out.println("executed");
				RequestDispatcher rd=request.getRequestDispatcher("admdash.jsp");
		        rd.forward(request, response);
				
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
	}

}
