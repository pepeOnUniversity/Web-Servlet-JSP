/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import model.User;

public class AuthFilter implements Filter {

    @Override
    //chain : help continous threads (call servlet)
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //casting: ServletRequest -> HttpServletRequest to use API HTTP
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // set header chống cache
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);

        //get uri. uri include (url & urn)
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        User user = (session == null) ? null : (User) session.getAttribute("user");

        // Nếu chưa login và cố truy cập trang bảo mật -> redirect login
        boolean isLoginRequest = uri.endsWith("login.jsp") || uri.endsWith("LoginServlet");
        if (user == null && !isLoginRequest) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Nếu truy cập ProductServlet và hành động nhạy cảm -> kiểm tra role
        if (uri.contains("ProductServlet")) {
            String action = request.getParameter("action");
            // danh sách các action cần quyền ADMIN
            if (action != null && (action.equals("new") || action.equals("edit")
                                   || action.equals("delete") || "add".equals(action))) {
                if (user == null || !"ADMIN".equalsIgnoreCase(user.getRole())) {
                    // Ghi log nếu cần
                    // response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    response.sendRedirect(request.getContextPath() + "/403.jsp");
                    return;
                }
            }
        }

        // if all require above is true -> continue thread
        chain.doFilter(request, response);
    }
}
