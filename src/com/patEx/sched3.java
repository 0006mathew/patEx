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
 * Servlet implementation class sched3
 */
@WebServlet("/sched3")
public class sched3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn=connection.getconnect();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				RequestDispatcher rd=request.getRequestDispatcher("docdash");
		        rd.forward(request, response);
				
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
	}

}
