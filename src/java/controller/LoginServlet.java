package controller;

import DAO.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDAO userDAO = new UserDAO();
        try {
            //go to DAO to handle and get Data
            User user = userDAO.findByUserName(username);

            System.out.println("LOGIN: username=" + username);
            System.out.println("USER obj = " + user);
            if (user != null) {
                System.out.println("hash = " + user.getPassword());
            }

            //check
            if (username != null && BCrypt.checkpw(password, user.getPassword())) {
                // -> login success
                //remove password before put it into session
                user.setPassword(null);
                //init session
                HttpSession session = request.getSession();
                //set value for session
                session.setAttribute("user", user);
                //link to dashboard
                response.sendRedirect("dashboard.jsp");
            } else {
                //handle other case
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (IOException e) {
            throw new ServletException(e);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
