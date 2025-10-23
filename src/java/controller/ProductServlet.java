/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        //try catch
        try {
            if (action == null) {
                action = "list";
            }
            switch (action) {
                //add new a product by form
                case "add":
                    request.getRequestDispatcher("product-form.jsp").forward(request, response);
                    break;
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    //after delete, back to this page
                    response.sendRedirect("ProductServlet?action=list");
                    break;
                case "edit":
                    int eid = Integer.parseInt(request.getParameter("id"));
                    Product product = dao.getById(eid);
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("product-form.jsp").forward(request, response);
                    break;
                //display list products
                default:
                    //get list products, set data and forward to file "product-list.jsp"
                    List<Product> list = dao.getAll();
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("product-list.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException | SQLException e) {
        }
    }

    //add new a product into DB
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            String desc = request.getParameter("description");

            //if has not idParam, it means use (add) -> add a new Product
            if (idParam == null || idParam.isEmpty()) {
                dao.add(new Product(0, name, price, desc));
            } else {
                //has idParam, it means use (update)
                int id = Integer.parseInt(idParam);
                dao.update(new Product(id, name, price, desc));
            }
            response.sendRedirect("ProductServlet");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
