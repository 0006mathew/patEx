<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.patEx.dashboard" %> 
    <%@ page import="com.patEx.connection" %> 
       <%@ page import="com.patEx.admin" %>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
	<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
	
<!DOCTYPE html>
<html>

<head>
    <title>Cancel Appointment</title>
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
	admin a=(admin)session.getAttribute("object");
	String user=a.userid,name=a.name,email=a.email;
	String role=(String)session.getAttribute("role");
	if(role.equals("admin")){
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
                        
                        <li class="dropdown profile">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><% out.println(user); %><span class="caret"></span></a>
                            <ul class="dropdown-menu animated fadeInDown">
                                
                                <li>
                                    <div class="profile-info">
                                        <h4 class="username"><% out.println(name); %></h4>
                                        <p><% out.println(email); %></p>
                                        <div class="btn-group margin-bottom-2x" role="group">
                                            <button type="button" class="btn btn-default"><i class="fa fa-user"></i> Profile</button>
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
                                <a href="admdash.jsp">
                                    <span class="icon fa fa-heartbeat"></span><span class="title">Home</span>
                                </a>
                            </li>
                            
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-doc">
                                    <span class="icon fa fa-user-md"></span><span class="title">Manage Doctor</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-doc" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="createdoc.jsp">Create Doctor</a>
                                            </li>
                                            <li><a href="deletedoc">Delete Doctor</a>
                                            </li>
                                            <li><a href="editdoc">Edit Doctor Profile</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-pat">
                                    <span class="icon fa fa-users"></span><span class="title">Manage Patient</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-pat" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="createpat.jsp">Create Patient</a>
                                            </li>
                                            <li><a href="deletepat">Delete Patient</a>
                                            </li>
                                            <li><a href="editpat">Edit Patient Profile</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li class="panel panel-default dropdown active">
                                <a data-toggle="collapse" href="#dropdown-appt">
                                    <span class="icon fa fa-table"></span><span class="title">Manage Appointments</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-appt" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="bookadmin.jsp">Book Appointment</a>
                                            </li>
                                            <li><a href="#">Cancel Appointments</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <a href="viewpatpro.jsp">
                                    <span class="icon fa fa-user"></span><span class="title">View Patient Profile</span>
                                </a>
                            </li>
                            <li>
                                <a href="populate">
                                    <span class="icon fa fa-calendar-o"></span><span class="title">Set Slots</span>
                                </a>
                            </li>
                            <li>
                                <a href="admindslot.jsp">
                                    <span class="icon fa fa-calendar-o"></span><span class="title">Delete Slots</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>
            </div> <!-- Main Content -->
            <div class="container-fluid">
                <div class="side-body padding-top">
                    <div class="row  no-margin-bottom">
                                <div class="col-xs-12">
                                <div class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                        <div class="title">Cancel Appointments</div>
                                    </div>
                                </div>
                                <div class="card-body">
  <script type="text/javascript">
     function sub1()
     {
         var r=confirm("Are you sure you wish to cancel the appointment?");
         return r;
    }
   </script>
                            
                                            <table class="table table-bordered" width="100%">
                                                <thead>
                                                    <tr>
                                                        <th>Appt ID</th>
                                                        <th>Name</th>
                                                        <th>Date Of Birth</th>
                                                        <th>Sex</th>	
                                                        <th>Timeslot</th>
                                                        <th>Date</th>
                                                        <th>Reason</th>
                                                        <th>Description</th>
                                                        <th>Do you want to cancel appointments</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                 <% 
                                                 	if (request.getAttribute("items") != null) {
														ArrayList itemsArray = (ArrayList) request.getAttribute("items");
    													for (int i=0; i <itemsArray.size();i++) {
    														%>
 
												    <form name="formname" action="admindoccancelsecond" method="post" onsubmit="return sub1();">
                                                    <tr> 
             											<%String b=(String)itemsArray.get(i);%>
             											<th scope="row"> <% out.println(itemsArray.get(i++)); %>   </th>
              											
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
              											<td> <% out.println(itemsArray.get(i++)); %>   </td>
               											<td> <% out.println(itemsArray.get(i)); %>   </td>
               											<input type="hidden" name="apptid" value=<%=b%>>     
              											<td> <input type="submit" name="bt" value="Yes"/> </td>
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
</body>

</html>
