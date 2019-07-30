<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
 
<html>
   <head>
      <title>profile</title>
      <style>
          body{
              background:#f0f8ff;
              font-family: 'Nunito', sans-serif;
              font-size: 16px;
          }
        table {
            border : 1px solid #ddd;
            border-collapse: collapse;
            border-radius: 10px;
            background:#fff;
            margin:0px auto;
            width:25%;
            margin-top: 150px;
            margin-bottom: 20px;
          }

          th{
              padding:20px;
              border: 1px solid #ccc;
              background:#5d8ffc;
              color:#fff;
              text-align: left;
          }

          td {
              padding:20px;
              border: 1px solid #ccc;
              color : #5d8ffc;
              text-align:left;

              transition: 0.3s;        
              -webkit-transition: 0.3s;
              -moz-transition: 0.3s;
          }

          td:hover {
            background:#ccdcff;
            color : #5d8ffc;
          }
      </style>
   </head>

   <body>
        <%
            String admin = (String)session.getAttribute("admin");
            if(admin == null) response.sendRedirect("../index.jsp");
        %>
        <!-- connecting to database -->
        <sql:setDataSource var = "usersDB"
            driver = "com.mysql.jdbc.Driver"
            url = "jdbc:mysql://localhost/usersDB"
            user = "root"  password = "root"/>
        
        <!-- user names -->
        <sql:query dataSource="${usersDB}" var="userName">
            SELECT DISTINCT username FROM userLog;
        </sql:query>

        <table id="table">
            <caption style="padding:20px"> PROFILE </caption>    
            <tr>
                <th>user</th>
                <th>blog</th>
                <th>visits</th>
            </tr>

            <c:forEach var="user" items="${userName.rows}">
                <sql:query dataSource="${usersDB}" var="userLog">
                    SELECT blog,visits FROM userLog WHERE username='${user.username}';
                </sql:query> 
                <tr>
                    <td rowSpan = "${userLog.rowCount}"><c:out value="${user.username}"/></td>
                    <c:forEach var="blog" items="${userLog.rows}">
                        <td><c:out value="${blog.blog}"/></td>
                        <td><c:out value="${blog.visits}"/></td>
                        </tr>
                        <tr>
                    </c:forEach>
                </tr>
                    

            </c:forEach>

        </table>
   </body>
</html>