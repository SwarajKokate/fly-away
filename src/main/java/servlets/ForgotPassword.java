package servlets;

import dao.repository.AdminRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Web Servlet for admin password change
 *
 * @author Swaraj-Kokate
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Post request handler for admin password change
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        if (new AdminRepository().changeAdminPassword(email, password)) {
            session.setAttribute("message", "Password Changed Successfully");
        } else {
            session.setAttribute("message", "Invalid Details");
        }

        response.sendRedirect("AdminPage.jsp");

    }

}
