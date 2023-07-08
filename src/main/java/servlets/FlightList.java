package servlets;

import dao.repository.FlightRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Web Servlet for retrieving list of flights
 *
 * @author Swaraj-Kokate
 */
@WebServlet("/FlightList")
public class FlightList extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Post handler for retrieving list of flights
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String departure = request.getParameter("departure");

        List<String[]> flights = new FlightRepository().getAvailableFlights(from, to, departure);
        HttpSession session = request.getSession();
        session.setAttribute("flights", flights);

        response.sendRedirect("FlightList.jsp");
    }
}

