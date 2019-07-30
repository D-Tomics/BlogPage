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

          tr:nth-child(even) {
              background: #dbf2ff;
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


        <sql:query dataSource="${usersDB}" var="blogNames">
            SELECT DISTINCT blog FROM userLog;
        </sql:query>

        
        <table id="table">
            <caption style="padding:20px"> ANALYTICS </caption>    
            <tr>
                <th>Blog</th>
                <th>visits</th>
            </tr>
            <c:forEach var="row" items="${blogNames.rows}">
                <tr>
                    <td><c:out value="${row.blog}"/></td>
                    <sql:query dataSource="${usersDB}" var="blogTable">
                            SELECT visits FROM userLog WHERE blog="${row.blog}";
                    </sql:query>

                    <c:set var="totalVisits" value="0"/>
                    <c:forEach var="row2" items="${blogTable.rows}">
                        <c:set var="totalVisits" value="${totalVisits=totalVisits+row2.visits}"/>
                    </c:forEach>
                    <td><c:out value="${totalVisits}"/></td>
                </tr>
            </c:forEach>
        </table>
   </body>
</html>