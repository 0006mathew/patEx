package com.patEx;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class bookadminone
 */
@WebServlet("/bookadminone")
public class bookadminone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookadminone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		// TODO Auto-generated method stub
		//doGet(request, response);
		admin a1=new admin();
		HttpSession session=request.getSession(false);
		a1=(admin)session.getAttribute("patobject");
		
		String a="doctor",sql="select * from credentials where role='"+a+"'",b=null,sql1;
		Connection com=connection.getconnect();
		String d,e,f,g,s;
		ArrayList items= new ArrayList();
        try
        {
        	Statement pst = com.createStatement();
        	Statement pst1 = com.createStatement();
        	ResultSet rs = pst.executeQuery(sql);
        	ResultSet rs1;
        	while(rs.next())
        	{
        		b=rs.getString(1);
        		//System.out.println("B is "+b);
        		sql1="select * from docinfo where username='"+b+"'";
        		rs1 = pst1.executeQuery(sql1);
        		
        		if(rs1.next())
        		{
        			e=rs1.getString(1);
        			f=rs1.getString(2);
        			g=rs1.getString(3);
        			s=rs1.getString(7);
        			items.add(e);
        			items.add(f);
        			items.add(g);
        			items.add(s);
        			//System.out.println("E is "+e);
        			//System.out.println("F is "+f);
        			//System.out.println("G is "+g);
        			
        		}
    
        	}
        }
        catch(Exception k)
        {
        	System.out.println(k);
        }
        request.setAttribute("items",items);
       // System.out.println("heyaa");
        RequestDispatcher rd=request.getRequestDispatcher("/bookadminone.jsp");
        rd.forward(request, response);
	}

}