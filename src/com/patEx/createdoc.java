package com.patEx;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class createdoc
 */
@WebServlet("/createdoc")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB

public class createdoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createdoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		InputStream inputStream = null; // input stream of the upload file

        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
           // System.out.println(filePart.getName());
            //System.out.println(filePart.getSize());
            //System.out.println(filePart.getContentType());

             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

		//System.out.println("here");
		String f=request.getParameter("fn");
		//System.out.println(f);
		String u=request.getParameter("u");
		//System.out.println(u);
		String l=request.getParameter("ln");
		String s=request.getParameter("sp");
		String e=request.getParameter("email");
		String p=request.getParameter("ph");
		String da=request.getParameter("dd");
		String mo=request.getParameter("mm");
		String y=request.getParameter("yy");
		String pass=request.getParameter("pass");
		int mm=0;
		String m;int count=0, r;
		String month[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
		int i;
		//System.out.println("Month "+mo);
		for(i=0;i<12;i++)
		{    //System.out.println(month[i]);
			if(mo.equalsIgnoreCase(month[i]))
			{
				mm=i+1;
				break;
			}
		}
		//System.out.println(mm);
		m=Integer.toString(mm);
		
		while(mm!=0)
		{
			r=mm%10;
			mm=mm/10;
			count++;
			
		}
		if(count==1)
			m="0"+m;
		String d=y+"-"+m+"-"+da;
		//System.out.println("yay"+f+" "+u+" "+p+" "+"Date "+d);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed;
        java.sql.Date dob;
            
		try {
			dob = java.sql.Date.valueOf(d);
		
		String sql="insert into docinfo values(?,?,?,?,?,?,?)";
		
		Connection com=connection.getconnect();
		
		PreparedStatement ps=com.prepareStatement(sql);
		ps.setString(1, u);
		ps.setString(2, f);
		ps.setString(3,l);
		ps.setString(4, e);
		ps.setString(5, p);
		ps.setDate(6, dob);
		ps.setString(7, s);
		ps.executeUpdate();
		String role="doctor";
		sql="insert into credentials values(?,?,?)";
		ps=com.prepareStatement(sql);
		ps.setString(1, u);
		ps.setString(2, pass);
		ps.setString(3, role);
		
		ps.executeUpdate();
		
		/*String sql1 = "INSERT INTO idpic (username,name,image) values (?, ?,?)";
        //PreparedStatement statement = com.prepareStatement(sql1);
        //statement.setString(1,u);
        //statement.setString(2, f);
        

         
        if (inputStream != null) {
            // fetches input stream of the upload file for the blob column
            statement.setBlob(3, inputStream);
        }


        // sends the statement to the database server
        statement.executeUpdate();*/

		}
		catch(Exception x)
		{
			System.out.println(x);
		}
		response.sendRedirect("admdash.jsp");
		
	}

	

}