<%-- 
    Document   : error
    Created on : Oct 26, 2025, 9:32:24 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("error");
        %>
        <h1>${error}</h1>
    </body>
</html>
