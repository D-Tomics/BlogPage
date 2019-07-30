<!DOCTYPE html>
<html>
    <head>
        <style>
            body {
                background: #fff;
                padding: 0px;
                margin: 0px;
                font-family: 'Nunito', sans-serif;
                font-size: 16px;
            }

            .main-div {
                width: 20%;
                margin: 0px auto;
                margin-top: 150px;
                padding: 20px;
            }

            .main-div .button:focus {
                border: 1px solid #777;
            }

            .main-div .button {
                text-decoration: none;
                text-align: center;
                display: block;
                outline: none;
                background: #5d8ffc;
                color: #fff;
                border: 1px solid #5d8ffc;
                border-radius: 5px;
                padding: 15px;
                margin-bottom: 20px;
                display: block;
                width: 100%;
                transition: 0.3s;
                -webkit-transition: 0.3s;
                -moz-transition: 0.3s;
            }

            .main-div .button:hover  {
                background: #fff;
                color: #5d8ffc;
                border: 1px solid #5d8ffc;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <%
            response.setHeader("Cache-control","no-store,no-cache,must-revalidate"); // Http 1.1
            response.setHeader("Pragma","no-cahce"); //Http 1.0
            response.setHeader("Expires","0"); //proxies
    
            String admin = (String)session.getAttribute("admin");
            if(admin == null) response.sendRedirect("./index.jsp");
        %>
        <div class="main-div">
            <div class="ui">
                <a href="./admin/profile.jsp" class="button">profile</a>
                <a href="./admin/analytics.jsp" class="button">analytics</a>
                <a href="Logout" class="button">Logout</a>
            </div>
        </div>
    </body>
</html>