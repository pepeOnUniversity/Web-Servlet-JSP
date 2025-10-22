<%-- 
    Document   : login
    Created on : Oct 22, 2025, 8:10:48 AM
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
        <!--submit -> LoginServlet.java-->
        <form action="LoginServlet" method="post">
            <h2>Login</h2>
            <p>Username: <input type="text" name="username"></p>
            <p>Password: <input type="password" name="password"></p>
            <p><button type="submit">Login</button></p>
        </form>

    </body>
</html>
