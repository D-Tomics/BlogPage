<!DOCTYPE html>
<html>	<meta charset="utf-8" />
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link rel="stylesheet" type="text/css" href="./css/home/main.css">
    <link rel="stylesheet" type="text/css" href="./css/home/header.css">
    <link rel="stylesheet" type="text/css" href="./css/home/footer.css">
    <link rel="stylesheet" type="text/css" href="./css/home/section.css">

    <script src="./scripts/homeScript.js"></script>
<head>
</head>

    <body onload="onLoad()">
        <%
            response.setHeader("Cache-control","no-store,no-cache,must-revalidate"); // Http 1.1
            response.setHeader("Pragma","no-cahce"); //Http 1.0
            response.setHeader("Expires","0"); //proxies

            String usrName = (String)session.getAttribute("username");
            if(usrName == null) response.sendRedirect("./index.jsp");
        %>

        <!-- HEADER  -->
        <div class="header" id="header">
            <div class="header-right">
                <a href="#blogs" class="menu">Home</a>
                <a href="profile.jsp" class="menu">Profile</a>
                <a href="Logout"  class="menu">Logout</a>
            </div>
            <h1>TERRARIA</h1>
        </div>   

        <div class="main-header" id="main-header">
            <!-- <h3 style="display: inline;" class="user">${username}</h3> -->
            <h3 class="logo">BLOG</h3>page
            <div id="header-right">
                <a href="Home.jsp" class="item">Home</a>
                <a href="profile.jsp" class="item">Profile</a>
                <a href="Logout"  class="item">Logout</a>
            </div>
        </div>

        <div class="section-grid" id="blogs">

            <div class="blog">
                <a href="Blog?blog=blog1">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog1</strong><br>this is the discription of blog1</p>
                </a>
            </div>
    
            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>
            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

            <div class="blog">
                <a href="Blog?blog=blog2">
                    <img class="image" src="./images/tempBlog.jpg" width="100px" height="100px">
                    <p class="description">
                        <strong>blog2</strong><br>
                        this is the discription of blog2
                    </p>
                </a>
            </div>

        </div>
        <!-- FOOTER -->
        <div class="footer">
            Copyright &copy 2019 D_tomics,All rights Reserved.
        </div>
    </body>
</html>