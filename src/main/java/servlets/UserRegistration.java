package servlets;

import dao.repository.UserRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Web Servlet for registering the user
 *
 * @author Swaraj-Kokate
 */
@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * POst handler for registering user
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phno = request.getParameter("phno");
        String adno = request.getParameter("adno");

        HashMap<String, String> user = new HashMap<>();
        user.put("email", email);
        user.put("password", password);
        user.put("name", name);
        user.put("phoneNumber", phno);
        user.put("adno", adno);

        boolean result = new UserRepository().insertUser(user);
        HttpSession session = request.getSession();
        if (result) {
            session.setAttribute("message", "User Added Successfully");
        } else {
            session.setAttribute("message", "Invalid Details");
        }

        response.sendRedirect("UserPage.jsp");
    }

}
