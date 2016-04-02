<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>

<head>
    <title>Login Page</title>
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
    <link rel="stylesheet" type="text/css" href="css/loginstyle.css">
    <link rel="stylesheet" type="text/css" href="css/themes/flat-blue.css">

</head>

<body class="flat-blue">
    <div class="app-container">
        <div class="row content-container">
            
            
            
            <!-- Main Content -->
            <div class="container-fluid">
                <div class="side-body padding-top">
                    
                    <div class="row  no-margin-bottom">
                                <div class="col-xs-6">
                                   <table>
                                    <form action="LoginServlet" method="post">
                                    <tr>
                                    <td>
                                    <h2>Login</h2>
                                    <br />
                                    <br />
                                    </td>
                                    </tr>
                                    <tr>
                                    <td>
                                    User ID
                                    </td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td><input type="text" name="uname" value="" required class="form-control" size="30" placeholder="Enter your user id"><br />
                                    </tr>
                                    <tr>
                                    <td>
                                    Password
                                    </td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                    <td><input type="password" name="pass" value="" required class="form-control" size="30" placeholder="Enter your password"><br />
                                    </tr> 
                                    <tr>
                                    <td colspan="3" align="center"><button type="submit" class="btn btn-primary">Sign in</button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="reset" class="btn btn-primary">Reset</button></td>
                                    </tr>                                            

                                     </form>
                                    </table>         
                                </div>
                                
                                <div class="col-xs-6">
                                		
                                		
                                    	<br>
                                		<br>
                                		<br>
                                		<br>
                                		<br>
                                		
                                          <ul class="list-unstyled" style="line-height: 2">
					                          <li> Kerala Institute of Medical Science</li>
					                          <li> P.B.No.1, Anayara P.O</li>
					                          <li> Trivandrum - 695029 </li>
					                          <li> Kerala, India</li>
										  </ul>
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
