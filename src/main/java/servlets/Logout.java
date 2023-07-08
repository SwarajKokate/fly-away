package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Web Servlet for logout
 *
 * @author Swaraj-Kokate
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * A method to logout the user
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session=request.getSession();
        session.setAttribute("user", null);
        response.sendRedirect("HomePage.jsp");
    }

}
