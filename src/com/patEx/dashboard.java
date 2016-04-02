package com.patEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class dashboard {
	public static String apptreq(String user){
		String apptno="No";
    	try {
    		Connection conn=connection.getconnect();	
    		PreparedStatement pst = conn.prepareStatement("select count(*) from apptreq where apptdate>CURDATE() and docid='"+user+"'");
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				if(rs.getInt(1)!=0)
					apptno=Integer.toString(rs.getInt(1));
				else
					apptno="0";
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in dashboard java-1");
			e.printStackTrace();
		}
    	
		return apptno;
	}
	public static String messages(String user){	
		String msgno="No";
    	try {
    		Connection conn=connection.getconnect();	
    		PreparedStatement pst = conn.prepareStatement("select count(*) from msg where receiverid='"+user+"' and rd='n' ");
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				if(rs.getInt(1)!=0)
					msgno=Integer.toString(rs.getInt(1));
				else
					msgno="0";
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in dashboard java-2");
			e.printStackTrace();
		}
    	
		return msgno;
	}
	public static String completed(String user){	
		String compno="None";
    	try {
    		Connection conn=connection.getconnect();	
    		PreparedStatement pst = conn.prepareStatement("select count(*) from appt where apptdate=CURDATE() and docid='"+user+"' and c='c'");
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				if(rs.getInt(1)!=0)
					compno=Integer.toString(rs.getInt(1));
				else
					compno="0";
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in dashboard java-3");
			e.printStackTrace();
		}
    	
		return compno;
	}
	public static String toComplete(String user){
		String tocompno="None";
    	try {
    		Connection conn=connection.getconnect();	
    		PreparedStatement pst = conn.prepareStatement("select count(*) from appt where apptdate=CURDATE() and docid='"+user+"' and c='n'");
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				if(rs.getInt(1)!=0)
					tocompno=Integer.toString(rs.getInt(1));
				else
					tocompno="0";
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("error in dashboard java-4");
			e.printStackTrace();
		}

    	
		return tocompno;
    	
	}
}





