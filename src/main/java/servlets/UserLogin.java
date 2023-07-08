package servlets;

import dao.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Web Servlet for logging user in
 *
 * @author Swaraj-Kokate
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Post handler for logging the user
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HashMap<String, String> user = new UserRepository().checkUser(email, password);
        HttpSession session = request.getSession();
        if (user != null) {
            session.setAttribute("user", user);
            response.sendRedirect("HomePage.jsp");
        } else {
            session.setAttribute("message", "Invalid Details");
            response.sendRedirect("UserPage.jsp");
        }


    }
}

