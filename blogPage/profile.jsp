<html>
<head>
	<meta charset="utf-8" />
	<meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>

	<link rel="stylesheet" type="text/css" href="./css/home/main.css">
	<link rel="stylesheet" type="text/css" href="./css/home/common/header.css">
	<link rel="stylesheet" type="text/css" href="./css/home/footer.css">
	<link rel="stylesheet" type="text/css" href="./css/home/section.css">
</head>
<body> 
<%
	response.setHeader("Cache-control","no-store,no-cache,must-revalidate"); // Http 1.1
	response.setHeader("Pragma","no-cahce"); //Http 1.0
	response.setHeader("Expires","0"); //proxies

	String usrName = (String)session.getAttribute("username");
	if(usrName == null) response.sendRedirect("./index.jsp");
%>
<div id="main-div">
	<div class="header">
		<h3 style="display:inline;" class="logo">BLOG</h3>page
		<div class="header-right">
			<a href="./Home.jsp#blogs" >Home</a>
			<a href="#" class="noActive">Profile</a>
			<a href="Logout" id="Logout" >Logout</a>
		</div>	
	</div>

	<div class="section-normal">
		<div class="profile">
			<img id="profile-img" src="./images/profile2.png" width="100px" height="100px">
			<p>
				<strong><%=usrName%></strong>
			</p>
		</div>
	</div>

	<div class="footer">
		Copyright &copy 2019 D_tomics,All rights Reserved.
	</div>
</div>
</body>
</html>