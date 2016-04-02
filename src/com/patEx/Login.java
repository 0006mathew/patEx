package com.patEx;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login {

		public static int validate(String name,String pass){
			int x=0;
			PreparedStatement pst = null;  
	        ResultSet rs = null;
	        try{

	    		Connection conn=connection.getconnect();	
	    		pst =  conn.prepareStatement("select password,role from credentials where username=?");
	        	pst.setString(1, name);
	        	rs = pst.executeQuery();
	        	if(rs.next()){
	        		String upass = rs.getString(1);
	        		String tp	 = rs.getString(2);
	        		if(pass.equals(upass)){
	        			
	        			if(tp.equals("doctor")){
	        				x=2;
	        			}
	        			else if(tp.equals("patient")){
	        				x=3;
	        			}
	        			else if(tp.equals("admin")){
	        				x=4;
	        			}
	        		}
	        		else{
	        			x=5;
	        		}
	        	}
	        	else{
	        		x=5;
	        	}
	        	rs.close();
	        	conn.close();
	        	pst.close();
	        	
	        }
	        
	        catch (Exception e) {
	        	e.printStackTrace();	
			}
	        return x;
	}

}