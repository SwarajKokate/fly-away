package servlets;

import dao.repository.FlightRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Web Servlet for insertion of flight
 *
 * @author Swaraj-Kokate
 */
@WebServlet("/InsertFlight")
public class InsertFlight extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * A method to insert flight into flight table
     *
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String departure = request.getParameter("departure");
        String time = request.getParameter("time");
        String fare = request.getParameter("fare");

        HashMap<String, String> flight = new HashMap<>();
        flight.put("name", name);
        flight.put("from_airport", from);
        flight.put("to_airport", to);
        flight.put("departure_date", departure);
        flight.put("departure_time", time);
        flight.put("fare", fare);


        HttpSession session = request.getSession();
        if (new FlightRepository().insertFlight(flight))
            session.setAttribute("message", "Flight Added Successfully !!");
        else
            session.setAttribute("message", "Invalid Details");

        response.sendRedirect("AdminHome.jsp");

    }

}

