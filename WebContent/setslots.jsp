<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    <%@ page import="com.patEx.dashboard" %> 
    <%@ page import="com.patEx.connection" %>
   <%@ page import="com.patEx.admin" %>
    <%@ page import="java.io.*,java.util.*,java.sql.*"%>
	<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
	
	
<!DOCTYPE html>
<html>

<head>
    <title>Set Slots</title>
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
    <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/> 
	
	<script type="text/javascript" src="lib/js/jquery.min.js"></script>
            
	<script type="text/javascript">
	var myarray=['5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
	</script>
	
	
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
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><% out.println(user); %> <span class="caret"></span></a>
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
                                            <li><a href="admindoceditone.jsp">Edit Doctor Profile</a>
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
                                            <li><a href="admineditpatone.jsp">Edit Patient Profile</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </li>
                            <li class="panel panel-default dropdown">
                                <a data-toggle="collapse" href="#dropdown-appt">
                                    <span class="icon fa fa-table"></span><span class="title">Manage Appointments</span>
                                </a>
                                <!-- Dropdown level 1 -->
                                <div id="dropdown-appt" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <ul class="nav navbar-nav">
                                            <li><a href="bookadmin.jsp">Book Appointment</a>
                                            </li>
                                            <li><a href="admincancel">Cancel Appointments</a>
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
                            <li class="active">
                                <a href="#">
                                    <span class="icon fa fa-calendar-o"></span><span class="title">Set Slots</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>
            </div><!-- Main Content -->
            <div class="container-fluid">
                <div class="side-body padding-top">
                    <div class="row  no-margin-bottom">
                    	<div class="col-xs-12">
                            <div class="card">
                            <div class="card-header">
                                    <div class="card-title">
                                        <div class="title">Set Slots</div>
                                    </div>
                                </div>
                                
                                <div class="card-body">
                                  <form name="setslot" action="setslot" autocomplete="off">
                                  <table width="100%">
                                  <tr>
                                  <td>Doctor id</td>
                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td>
                                  <select name="id" id="id" class="form-control" style="height:30px;width:200px" required>
                                  <% 
                                                 	if (request.getAttribute("items") != null) {
														ArrayList itemsArray = (ArrayList) request.getAttribute("items");
    													for (int i=0; i <itemsArray.size();) {
    														%>
    														<option> <% out.println(itemsArray.get(i++)); %> </option>
    														<%  
       												}
    											
    											
												}
                                                 else {
                                                	 System.out.println("no data in array");
												}  

												%>
                                  
                                  </select>
                                  </td>
                                  </tr>
                                  <tr><td><br></td></tr>
                                  <tr>
                                  <td>Set slots from</td>
                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type="text" id="datepicker" name="day" class="form-control" style="height:30px;width:200px" required></td>
                                  </tr>
                                  <tr><td><br></td></tr>
                                  <tr>
                                  <td>Number of days</td>
                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type="text" name="number" id="number" class="form-control" style="height:30px;width:200px" required></td>
                                  </tr>
                                  <tr><td><br></td></tr>
                                  <tr>
                                  <td>Duration of the slots</td>
                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td><input type="text" name="dur" id="dur" class="form-control" style="height:30px;width:200px" required></td><td>Minutes</td>
                                  </tr>
                                  <tr><td><br></td></tr>
                                  <tr>
                                  <td><h4>Details of everyday</h4></td>
                                  </tr>
                                  <tr>
                                  <td>Timing</td>
                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                  <td>
                                  <select name="start" id="start" class="form-control" style="height:30px;width:80px" required>
<script>
        var myArray=['5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
        for(i=0; i<myArray.length; i++) {  
            document.write('<option value="' + myArray[i] +'">' + myArray[i] + '</option>');
        }
</script>
</select> 
to <select name="end" id="end" class="form-control" style="height:30px;width:80px" required>
<script>
        var myArray=['5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
        for(i=0; i<myArray.length; i++) {  
            document.write('<option value="' + myArray[i] +'">' + myArray[i] + '</option>');
        }
</script>
</select>

</td>
                                  </tr>
                                  <tr><td><br></td></tr>
                                  <tr>
                                  <td>Break Timing</td>
                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                  <td>
                                  <select name="breakstart" id="breakstart" class="form-control" style="height:30px;width:80px"> <script>
        var myArray=['5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
        for(i=0; i<myArray.length; i++) {  
            document.write('<option value="' + myArray[i] +'">' + myArray[i] + '</option>');
        }
</script>
</select> to 
<select name="breakend" id="breakend" class="form-control" style="height:30px;width:80px"> 
<script>
        var myArray=['5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
        for(i=0; i<myArray.length; i++) {  
            document.write('<option value="' + myArray[i] +'">' + myArray[i] + '</option>');
        }
</script>
</select> 
                                  
                                  </td>
                                  </tr>
                                  <tr><td><br></td></tr>
                                  <tr>
                                  <td></td>
                                  <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                  <td><select name="breakstart1" id="breakstart1" class="form-control" style="height:30px;width:80px"> 
<script>
        var myArray=['5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
        for(i=0; i<myArray.length; i++) {  
            document.write('<option value="' + myArray[i] +'">' + myArray[i] + '</option>');
        }
</script>
</select> to 
<select name="breakend1" id="breakend1" class="form-control" style="height:30px;width:80px">
<script>
        var myArray=['5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00'];
        for(i=0; i<myArray.length; i++) {  
            document.write('<option value="' + myArray[i] +'">' + myArray[i] + '</option>');
        }
</script>
</select> 
                                  
                                  </td>
                                  </tr>
                                  </table>

 <br><br>
 <script type="text/javascript">
</script>
<input type="submit" value="Submit" name="submit" class="btn btn-primary">
</form>


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
            <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js">
	</script>
  	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js">
  	</script>
	<script>
	$(function(){
		$("#datepicker").datepicker();	
	});
	</script>   
	
            
</body>

</html>
