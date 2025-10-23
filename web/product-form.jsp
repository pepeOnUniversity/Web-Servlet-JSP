<%-- 
    Document   : product-form
    Created on : Oct 23, 2025, 3:25:28 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product" %>

<!--if it has product forward from ProductServlet (edit) -> isEdit = true
else it is (add) a new product-->
<%
            Product product = (Product) request.getAttribute("product");
            boolean isEdit = (product != null);
%>

<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2><%= isEdit ? "Sửa sản phẩm" : "Thêm sản phẩm mới" %></h2>

        <form action="ProductServlet" method="post">
            <% if (isEdit) { %>
            <input type="hidden" name="id" value="<%= product.getId() %>">
            <% } %>

            <label>Tên sản phẩm:</label><br>
            <input type="text" name="name" value="<%= isEdit ? product.getName() : "" %>" required><br><br>

            <label>Giá:</label><br>
            <input type="number" step="0.01" name="price" value="<%= isEdit ? product.getPrice() : "" %>" required><br><br>

            <label>Mô tả:</label><br>
            <textarea name="description" rows="3" cols="30"><%= isEdit ? product.getDescription() : "" %></textarea><br><br>

            <button type="submit"><%= isEdit ? "Cập nhật" : "Thêm mới" %></button>
            <a href="ProductServlet">Hủy</a>
        </form>
    </body>
</html>
