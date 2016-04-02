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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class admineditpat
 */
@WebServlet("/admineditpat")
public class admineditpat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admineditpat() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String a,b,c,d,e,f,g;
		admin p2=new admin();
		HttpSession session=request.getSession(false);
		p2=(admin)session.getAttribute("object");
		String x=(String)session.getAttribute("pat");
		//System.out.println("Patient id "+x);
		String sql="select * from patinfo where username='"+x+"'";
		Connection com=connection.getconnect();
		ArrayList items= new ArrayList();
		try {
			Statement pst = com.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			 if(rs.next())
				{
				    a=rs.getString(1);
				    b=rs.getString(2);
				    c=rs.getString(3);
				    d=rs.getString(4);
				    e=rs.getString(5);
				    f=rs.getString(6);		
				    g=rs.getString(7);
				    items.add(a);
				    items.add(b);
				    items.add(c);
				    items.add(d);
				    items.add(e);
				    items.add(f);
				    items.add(g);
				   // System.out.println("Yes"+a+b+c+d+e+f+g);
				    
				}
			 
				
		} catch (SQLException q) {
			// TODO Auto-generated catch block
			System.out.println("Error "+q);
			q.printStackTrace();
		}
		request.setAttribute("items",items);
		RequestDispatcher rd=request.getRequestDispatcher("/admineditpat.jsp");
        rd.forward(request, response);
	}

	

}