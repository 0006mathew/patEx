package com.patEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class doctor extends person {
	doctor()
	{
		
	}
	doctor(String id)
	{		
		String query="Select * from docinfo where username=?";
        try{
        	Connection conn=connection.getconnect();
        	PreparedStatement pst=conn.prepareStatement(query);
        	pst.setString(1, id);
        	ResultSet rs=pst.executeQuery();
        	while(rs.next()){
                userid=rs.getString(1);
                fname=rs.getString(2);
                lname=rs.getString(3);
                name=fname+" "+lname;
                email=rs.getString(4);
                phno=rs.getString(5);
        	}
        	conn.close();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
	}
}