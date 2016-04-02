package com.patEx;

import java.sql.Connection;
import java.sql.DriverManager;

public class connection {

	static final String jdbc_driver="com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/patEx";
	static final String use = "root";
	static final String pasword = "4677";
			
			
			
		public static Connection getconnect(){
			 Connection conn = null;
	        try{
	        	Class.forName(jdbc_driver);
	        	
	        	conn = DriverManager.getConnection(url,use,pasword);
	        	
	        	
	        }
	        catch (Exception e) {
	        	e.printStackTrace();	
				}
	        return conn;
		}
}