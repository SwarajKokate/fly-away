package servlets;

import dao.repository.AdminRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Web Servlet for admin login
 *
 * @author Swaraj-Kokate
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Post request handler for admin login
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (new AdminRepository().checkAdmin(email, password)) {
            response.sendRedirect("AdminHome.jsp");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("message", "Invalid credentials. Please try again !!");
            response.sendRedirect("AdminPage.jsp");
        }

    }
}