/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.User;

import org.mindrot.jbcrypt.BCrypt;
/**
 *
 * @author ADMIN
 */
public class RegisterServlet extends HttpServlet {
    
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //get attribute
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        if(username == null || username.trim().isEmpty() || password == null ||
                password.trim().isEmpty()){
            request.setAttribute("error", "Username or password must not empty");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        
        //hash password with BCrypt
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        
        User user = new User();
        user.setUsername(username.trim());
        user.setPassword(hashed);
        user.setRole((role == null || role.isEmpty()) ? "USER" : role.toUpperCase());
        
        try {
            userDAO.create(user);
            response.sendRedirect("login.jsp?registered=1");
        } catch (IOException | SQLException e) {
            throw new ServletException(e);
        }
    }
}
