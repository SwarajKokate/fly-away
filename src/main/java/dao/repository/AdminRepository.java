package dao.repository;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import dao.ConnectDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Admin Repository in order to perform operations with admin table
 *
 * @author Swaraj-Kokate
 */
public class AdminRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminRepository.class);
    private Statement statement;

    /**
     * Method to check for the admin
     *
     * @param email
     * @param password
     * @return is admin present
     */
    public boolean checkAdmin(String email, String password) {

        try {
            statement = ConnectDatabase.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM admin WHERE email='" +email+ "' and password='" +password+ "'"
            );

            if(resultSet.next())
                return true;

        } catch (SQLException exception) {
            LOGGER.error("An error occured while checking admin: {}", exception.getMessage());
        }
        return false;
    }

    /**
     * A method to change admin password
     *
     * @param email
     * @param password
     * @return whether the password was changed
     */
    public boolean changeAdminPassword(String email, String password) {

        try {
            statement = ConnectDatabase.connect().createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM admin WHERE email='" +email+ "'"
            );

            if(!resultSet.next()) {
                return false;
            }
            statement.execute("UPDATE admin SET password='" +password+ "' WHERE email='" +email+ "'");
            return true;
        } catch (SQLException exception) {
            LOGGER.error("An error occured while changing password of admin: {}", exception.getMessage());
        }
        return false;
    }

}
