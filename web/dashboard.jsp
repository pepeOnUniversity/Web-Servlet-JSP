<%-- 
    Document   : dashboard
    Created on : Oct 22, 2025, 9:19:18 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <!--get data by session-->
        <%
            User user = (User) session.getAttribute("user");
            if(user == null){
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <!--display-->
        <h1>Welcome, <%=user.getUsername()%></h1>
        <h3>Role: <%=user.getRole()%></h3>


        <% if(user.getRole().equals("ADMIN")){%>
        <a href="ProductServlet?action=list">Quản lí danh sách sản phẩm</a><br>
        <% } else {%>
        <h2>You only see your information</h2>
        <a href="ProductServlet?action=list">Xem danh sách sản phẩm</a>
        <% } %>

        <a href="LogoutServlet">
            <button type="button">Logout</button>
        </a>
    </body>
</html>
