package dao.repository;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import dao.ConnectDatabase;
import utils.StringUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Flight Repository in order to perform operations with flight table
 *
 * @author Swaraj-Kokate
 */
public class FlightRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRepository.class);
    private Statement statement;

    /**
     * A method to get list of available flights
     *
     * @param from
     * @param to
     * @param departureDate
     * @return list of available flights
     */
    public List<String[]> getAvailableFlights(String from, String to, String departureDate) {

        List<String[]> flights = new ArrayList<>();
        String query=
                "SELECT * FROM flight WHERE from_airport='" +from+ "' AND to_airport='" +to+ "" +
                        "' AND departure_date='" +departureDate+ "'";
        try {
            statement = ConnectDatabase.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                String[] flight=new String[3];
                flight[0]=resultSet.getString("name");
                flight[1]=resultSet.getString("departure_time");
                flight[2]=resultSet.getString("fare");
                flights.add(flight);
                return flights;
            }

        } catch (SQLException exception) {
            LOGGER.error("An error occured while fetching list of flights: {}", exception);
        }

        return null;
    }

    /**
     * A method to insert flight
     *
     * @param flight
     * @return flight is inserted or not
     */
    public boolean insertFlight(HashMap<String, String> flight) {

        String query1 = "INSERT INTO flights (name, from_airport, to_airport, date_of_arrival, departure_time, fare) VALUES" + " ('"
                + StringUtil.fixSqlFieldValue(flight.get("name")) + "'," + " '" + StringUtil.fixSqlFieldValue(flight.get("from_airport")) + "'," + " '"
                + StringUtil.fixSqlFieldValue(flight.get("to_airport")) + "'," + " '" + StringUtil.fixSqlFieldValue(flight.get("departure_date")) + "'," + " '"
                + StringUtil.fixSqlFieldValue(flight.get("departure_time")) + "'," + " '" + StringUtil.fixSqlFieldValue(flight.get("fare")) + "')";

        try {
           statement = ConnectDatabase.connect().createStatement();
           statement.execute(query1);
           return true;
        } catch (SQLException exception) {
            LOGGER.error("An error occurred while adding flight: {}", exception);
        }
        return false;
    }
}
