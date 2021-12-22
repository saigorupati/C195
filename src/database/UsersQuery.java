package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersQuery {
    /**
     * @return the observable list of all user information
     * @throws SQLException if an SQL occurs
     */
    public static ObservableList<Users> getUsers() throws SQLException {
        ObservableList<Users> usersList = FXCollections.observableArrayList();
        String sql = "SELECT * from users";
        PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int userID = resultSet.getInt("User_ID");
            String userName = resultSet.getString("User_Name");
            String userPassword = resultSet.getString("Password");
            Users user = new Users(userID, userName, userPassword);
            usersList.add(user);
        }
        return usersList;
    }
}

