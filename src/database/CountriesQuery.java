package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesQuery {
    /**
     * @return the observable list of all the country information
     * @throws SQLException if an SQL exception occurs
     */
    public static ObservableList<Countries> getCountries() throws SQLException {
        ObservableList<Countries> countriesList = FXCollections.observableArrayList();
        String sql = "SELECT * from countries";
        PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int countryID = resultSet.getInt("Country_ID");
            String name = resultSet.getString("Country");
            Countries country = new Countries(countryID, name);
            countriesList.add(country);
        }
        return countriesList;
    }
}
