package com.patEx;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deletepat
 */
@WebServlet("/deletepat")
public class deletepat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletepat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection com=connection.getconnect();
		ArrayList items=new ArrayList();
		String a,b,c,d,e,f,g,h,i,j;
		int count=0;
		try
		{
		Statement pst=com.createStatement();
		Statement pst1=com.createStatement();
		String x="patient";
		String sql="select * from credentials where role='"+x+"'";
		ResultSet rs=pst.executeQuery(sql);
		ResultSet rs1;
		String sql1;
		while(rs.next())
		{
			a=rs.getString(1);
			//System.out.println("AAAA"+a);
			sql1="select * from patinfo where username='"+a+"'";
			rs1=pst1.executeQuery(sql1);
			if(rs1.next())
			{
			c=rs1.getString(2);
			d=rs1.getString(3);
			e=rs1.getString(4);
			f=rs1.getString(5);
			g=rs1.getString(6);
			b=rs1.getString(7);
			//System.out.println(a+" "+c+" "+d+" "+e+" "+f+" "+g+" ");
			count++;
			items.add(a);
			items.add(c);
			items.add(d);
			items.add(e);
			items.add(f);
			items.add(g);
			items.add(b);
			}
		}
		}
		catch(Exception n)
		{
			System.out.println(n);
		}
		if(count>0)
		{
			request.setAttribute("items", items);
			RequestDispatcher rd=request.getRequestDispatcher("/deletepat.jsp");
			rd.forward(request, response);
		}
		else
		{
			PrintWriter out=response.getWriter();
			out.print("No patients to display");
		}

	}

}