<%-- 
    Document   : product-list
    Created on : Oct 23, 2025, 3:36:03 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Product" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Danh sách sản phẩm</h2>
        <a href="ProductServlet?action=add">Thêm sản phẩm</a>
        <!--get list product--> 
        <%
            List<Product> list = (List<Product>) request.getAttribute("list");
        %>
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <th>ID</th><th>Tên</th><th>Giá</th><th>Mô tả</th><th>Hành động</th>
            </tr>
            <% if (list != null) {
            for (Product p : list) { %>
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getDescription() %></td>
                <td class="action">
                    <a href="ProductServlet?action=delete&id=<%= p.getId() %>">Xóa</a>
                    <a href="ProductServlet?action=edit&id=<%=p.getId()%>">Sửa</a>
                </td>
            </tr>
            <% } } else { %>
            <tr><td colspan="5">Không có sản phẩm nào!</td></tr>
            <% } %>
        </table>

        <br><a href="dashboard.jsp">Quay lại Dashboard</a>
        
        <style>
            .action {
                display: flex;
                justify-content: space-around;
            }
        </style>
    </body>
</html>
