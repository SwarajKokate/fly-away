package dao.repository;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import dao.ConnectDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * User Repository in order to perform operations with user table
 *
 * @author Swaraj-Kokate
 */
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private Statement statement;

    /**
     * A method to authenticate user
     *
     * @param email
     * @param password
     * @return user details
     */
    public HashMap<String, String> checkUser(String email, String password) {

        HashMap<String,String> user=null;
        String query="SELECT * FROM user WHERE email='" +email+ "' and password='" +password+ "'";

        try {
            statement = ConnectDatabase.connect().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                user=new HashMap<>();
                user.put("name", resultSet.getString("name"));
                user.put("email",resultSet.getString("email"));
                user.put("phoneNumber",resultSet.getString("phone_number"));
                user.put("adno",resultSet.getString("adno"));
            }
            return user;
        } catch (SQLException exception) {
            LOGGER.error("An exception occurred while checking user: {}", exception);
        }

        return user;
    }

    /**
     * A method to insert user into user table
     *
     * @param user
     * @return user is added or not
     */
    public boolean insertUser(HashMap<String, String> user) {

        String query= "INSERT INTO user (email, password, name, phone_number, adno)" +
                        " values(" +
                        "'"+user.get("email")+"'," +
                        "'"+user.get("password")+"'," +
                        "'"+user.get("name")+"'," +
                        "'"+user.get("phoneNumber")+"'," +
                        "'"+user.get("adno")+"')";

        try {
            statement = ConnectDatabase.connect().createStatement();
            statement.execute(query);
            return true;
        } catch (SQLException exception) {
            LOGGER.error("An error occurred while adding user: {}", exception);
        }
        return false;
    }


}
