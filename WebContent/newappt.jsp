<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.patEx.dashboard" %> 
    <%@ page import="com.patEx.connection" %> 
       <%@ page import="com.patEx.doctor" %>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
	<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
	
<!DOCTYPE html>
<html>

<head>
    <title>New Appointments</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <!-- CSS Libs -->
    <link rel="stylesheet" type="text/css" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="lib/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="lib/css/bootstrap-switch.min.css">
    <link rel="stylesheet" type="text/css" href="lib/css/checkbox3.min.css">
    <link rel="stylesheet" type="text/css" href="lib/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="lib/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="lib/css/select2.min.css">
    <!-- CSS App -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/themes/flat-blue.css">

</head>
<%
	doctor d=(doctor)session.getAttribute("object");
	String user=d.userid,name=d.name,email=d.email;
	String role=(String)session.getAttribute("role");
	if(role.equals("doctor")){
					;
	}
	else
		response.sendRedirect("index.jsp");
%>

<body class="flat-blue">
    <div class="app-container">
        <div class="row content-container">
            <nav class="navbar navbar-default navbar-fixed-top navbar-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-expand-toggle">
                            <i class="fa fa-bars icon"></i>
                        </button>
                        <ol class="breadcrumb navbar-breadcrumb">
                            <li class="active">Home</li>
                        </ol>
                        <ol class="breadcrumb navbar-breadcrumb">
                            <li class="active">/patEx</li>
                        </ol>
                        <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                            <i class="fa fa-th icon"></i>
                        </button>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                            <i class="fa fa-times icon"></i>
                        </button>
                        <li class="dropdown">
                            <a href="docmsg" class="dropdown-toggle" role="button" aria-expanded="false"><i class="fa fa-comments-o"></i></a>
                        </li>
                        <li class="dropdown profile">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><% out.println(user); %><span class="caret"></span></a>
                            <ul class="dropdown-menu animated fadeInDown">
                                
                                <li>
                                    <div class="profile-info">
                                        <h4 class="username"><% out.println(name); %></h4>
                                        <p><% out.println(email); %></p>
                                        <div class="btn-group margin-bottom-2x" role="group">
                                            <a href="docedit"><button type="button" class="btn btn-default"><i class="fa fa-user"></i> Profile</button></a>
                                            <a href="LogoutServlet"><button type="button" class="btn btn-default"><i class="fa fa-sign-out"></i> Logout</button></a>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            <!-- side menu bar-->
            <div class="side-menu sidebar-inverse">
                <nav class="navbar navbar-default" role="navigation">
                    <div class="side-menu-container">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="#">
                                <div class="icon fa fa-paper-plane"></div>
                                <div class="title">patEx</div>
                            </a>
                            <button type="button" class="navbar-expand-toggle pull-right visible-xs">
                                <i class="fa fa-times icon"></i>
                            </button>
                        </div>
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="docdash">
                                    <span class="icon fa fa-heartbeat"></span><span class="title">Home</span>
                                </a>
                            </li>
                            <li class="active">
                                <a href="#">
                                    <span class="icon fa fa-thumbs-o-up"></span><span class="title">New Appointments</span>
                                </a>
                            </li>
                           <li >
                                <a href="doccancel">
                                    <span class="icon fa fa-table"></span><span class="title">Cancel Appointments</span>
                                </a>
                            </li> <li>
                                <a href="pat.jsp">
                                    <span class="icon fa fa-user"></span><span class="title">Patients</span>
                                </a>
                            </li>
                            <li>
                                <a href="sched.jsp">
                                    <span class="icon fa fa-ban"></span><span class="title">Block Slot</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>
            </div>
            <!-- Main Content -->
            <div class="container-fluid">
                <div class="side-body padding-top">
                    <div class="row  no-margin-bottom">
                                <div class="col-xs-12">
                                    <div class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                        <div class="title">Pending Approval</div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <table class="table table-bordered" width="100%">
                                                <thead>
                                                    <tr>
                                                        
                                                        <th>Name</th>	
                                                        <th>Date Of Birth</th>
                                                        <th>Sex</th>
                                                        <th>Time</th>
                                                        <th>Date</th>
                                                        <th>Reason</th>
                                                        <th>Description</th>
                                                        <th> Number of times of Visit</th>
                                                        <th colspan="2">Approve</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                 <% 
                                                 	if (request.getAttribute("items") != null) {
														ArrayList itemsArray = (ArrayList) request.getAttribute("items");
    													for (int i=0; i <itemsArray.size(); i++) {
    														%>
 
												    <form name="formname" action="newapptsecond" method="post">
                                                    <tr> 
             											<%String a=(String)itemsArray.get(i++);%>
             											<!-- <th scope="row"> <% //out.println(itemsArray.get(i++)); %>   </th> -->
              											<!--<td> <a href='#' onclick='javascript:window.open("http://www.google.com", "_blank", "scrollbars=1,resizable=1,height=200,width=300");' title='Pop Up'><% out.println(itemsArray.get(i+1)); %></a>   </td>-->
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
               											<td> <% out.println(itemsArray.get(i++)); %>   </td>
               											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
               											<td> <% out.println(itemsArray.get(i)); %>   </td>
               											<input type="hidden" name="appid" value=<%=a%>>     
              											<td> <input type="submit" name="bt" value="Yes"/> </td>
              											<td> <input type="submit" name="bt" value="No"/> </td>
               										</tr>
               										</form>
               
                                                    
                                                    
                                                    
                                                <%  
       												}
												}
                                                 else {
                                                	 System.out.println("no data in array");
												}  

												%>

                                                </tbody>
                                            </table>

                                        </div>
                                </div>
                                </div>
                           
                     </div>
                </div>
            </div>
        </div>
        <center><footer>patEx Inc. Copyrighted 2015-2016</footer></center>
            <!-- Javascript Libs -->
            <script type="text/javascript" src="lib/js/jquery.min.js"></script>
            <script type="text/javascript" src="lib/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="lib/js/Chart.min.js"></script>
            <script type="text/javascript" src="lib/js/bootstrap-switch.min.js"></script>
            <script type="text/javascript" src="lib/js/jquery.matchHeight-min.js"></script>
            <script type="text/javascript" src="lib/js/jquery.dataTables.min.js"></script>
            <script type="text/javascript" src="lib/js/dataTables.bootstrap.min.js"></script>
            <script type="text/javascript" src="lib/js/select2.full.min.js"></script>
            <script type="text/javascript" src="lib/js/ace/ace.js"></script>
            <script type="text/javascript" src="lib/js/ace/mode-html.js"></script>
            <script type="text/javascript" src="lib/js/ace/theme-github.js"></script>
            <!-- Javascript -->
            <script type="text/javascript" src="js/app.js"></script>
            <script type="text/javascript" src="js/index.js"></script>
            <script type="text/javascript" src="lib/js/datepicker.js"></script>
</body>

</html>
