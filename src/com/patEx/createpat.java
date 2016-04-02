package com.patEx;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class createpat
 */
@WebServlet("/createpat")

@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class createpat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createpat() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		InputStream inputStream = null; // input stream of the upload file

        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
           // System.out.println(filePart.getName());
          //  System.out.println(filePart.getSize());
           // System.out.println(filePart.getContentType());

             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }

		//System.out.println("here in create");
		String f=request.getParameter("fn");
		//System.out.println(f);
		String u=request.getParameter("u");
		//System.out.println(u);
		String l=request.getParameter("ln");
		String ad=request.getParameter("addr");
		String email=request.getParameter("email");
		String s=request.getParameter("s");
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
		{   
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
		//SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
       // Date parsed;
        java.sql.Date dob;
            
		try {
			dob = java.sql.Date.valueOf(d);

		String sql="insert into patinfo values(?,?,?,?,?,?,?,?)";
		
		Connection com=connection.getconnect();
		
		PreparedStatement ps=com.prepareStatement(sql);
		ps.setString(1, u);
		ps.setString(2, f);
		ps.setString(3,l);
		ps.setDate(4, dob);
		ps.setString(5, s);
		ps.setString(6, p);
		ps.setString(7, ad);
		ps.setString(8, email);
		ps.executeUpdate();
		String role="patient";
		sql="insert into credentials values(?,?,?)";
		ps=com.prepareStatement(sql);
		ps.setString(1, u);
		ps.setString(2, pass);
		ps.setString(3, role);
		
		ps.executeUpdate();
		String sql1 = "INSERT INTO idpic (username,name,image) values (?, ?,?)";
        PreparedStatement statement = com.prepareStatement(sql1);
        statement.setString(1,u);
        statement.setString(2, f);
        

         
        if (inputStream != null) {
            // fetches input stream of the upload file for the blob column
            statement.setBlob(3, inputStream);
        }


        // sends the statement to the database server
        statement.executeUpdate();

		}
		catch(Exception x)
		{
			System.out.println(x);
		}
		response.sendRedirect("admdash.jsp");
	}

	

}