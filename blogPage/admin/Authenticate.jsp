<%
    response.setHeader("Cache-control","no-store,no-cache,must-revalidate"); // Http 1.1
    response.setHeader("Pragma","no-cahce"); //Http 1.0
    response.setHeader("Expires","0"); //proxies

    String admin = (String)session.getAttribute("admin");
    if(admin == null) {
        response.sendRedirect("../index.jsp");  
    }
%>