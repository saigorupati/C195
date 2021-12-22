package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsQuery {
    /**
     * @return the observable list of the contacts information
     * @throws SQLException if an SQL exception occurs
     */
    public static ObservableList<Contacts> getContacts() throws SQLException {
        ObservableList<Contacts> contactsList = FXCollections.observableArrayList();
        String sql = "SELECT * from contacts";
        PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int contactID = resultSet.getInt("Contact_ID");
            String name = resultSet.getString("Contact_Name");
            String email = resultSet.getString("Email");
            Contacts contact = new Contacts(contactID, name, email);
            contactsList.add(contact);
        }
        return contactsList;
    }

}
