<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.patEx.dashboard" %> 
    <%@ page import="com.patEx.connection" %> 
       <%@ page import="com.patEx.patient" %>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
	<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String id=request.getParameter("q");
Connection conn=null;
ResultSet rs=null;
Statement st=null;
conn=connection.getconnect();
//System.out.println("The value in q is "+id);
String q1="Select * from credentials where username='"+id+"'";	
st=conn.createStatement();
rs=st.executeQuery(q1);
if(!rs.isBeforeFirst()){
	 out.println("<font color=green>");
     out.println("Username Available");
     out.println("</font>");
}
else{
	 out.println("<font color=red>");
     out.println("Username already taken");
     out.println("</font>");
}
%>

</body>
</html>