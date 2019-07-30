<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>

        <link rel="stylesheet" type="text/css" href="./css/home/main.css"/>
        <link rel="stylesheet" type="text/css" href="./css/home/common/header.css"/>
        <link rel="stylesheet" type="text/css" href="./css/home/footer.css"/>
        <link rel="stylesheet" type="text/css" href="./css/home/section.css"/>
        <link rel="stylesheet" type="text/css" href="./css/menuicon.css"/>
        <script src="./scripts/blogScript.js"></script>
    </head>
    <body>
        <%
            response.setHeader("Cache-control","no-store,no-cache,must-revalidate"); // Http 1.1
            response.setHeader("Pragma","no-cahce"); //Http 1.0
            response.setHeader("Expires","0"); //proxies

            String usrName = (String)session.getAttribute("username");
            if(usrName == null) response.sendRedirect("./index.jsp");
        %>
        <div class="header">
            <div class="menu-icon" onclick="openNav()">
                <div class="bar1"></div>
                <div class="bar2"></div>
                <div class="bar3"></div>
            </div>
            <h3 style="display:inline;" class="logo">${head}</h3>
            <div class="header-right">
                <a href="Home.jsp#blogs">Home</a>
                <a href="profile.jsp">Profile</a>
                <a href="Logout">Logout</a>
            </div>
        </div>
    
        <!-- navigation -->
        <div class="menu" id="menu">
            <img id="close" src="./images/close.png" width="25px" onclick="closeNav()">
            <a href="Blog?blog=blog1" >blog1</a>
            <a href="Blog?blog=blog2" >blog2</a>
            <a href="#" >blog3</a>
            <a href="#" >blog4</a>
        </div>
    
        <!-- content -->
        <div class="section section-anim" id="section">
            ${content}
        </div>
    
        <!-- FOOTER -->
        <div class="footer">
            Copyright &copy 2019 D_tomics,All rights Reserved.
        </div>
    </body>
</html>