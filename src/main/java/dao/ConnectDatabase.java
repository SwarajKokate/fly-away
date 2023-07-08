package dao;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class for connecting to the database
 *
 * @author Swaraj-Kokate
 */
public class ConnectDatabase implements AutoCloseable{

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectDatabase.class);

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/fly_away";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    /**
     * A method to connect to database
     *
     * @return connection to database
     */
    public static Connection connect() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
            return connection;
        } catch (ClassNotFoundException | SQLException exception) {
            LOGGER.error("An error occured while connecting to database: {}", exception.getMessage());
        }

        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
