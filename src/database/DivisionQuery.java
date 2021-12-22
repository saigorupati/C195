package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionQuery {
    /**
     * @return the observable list of all first-level division information
     * @throws SQLException if an SQL exception occurs
     */
    public static ObservableList<FirstLevelDivisions> getDivisions() throws SQLException {
        ObservableList<FirstLevelDivisions> divisionsList = FXCollections.observableArrayList();
        String sql = "SELECT * from first_level_divisions";
        PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int divisionID = resultSet.getInt("Division_ID");
            String division = resultSet.getString("Division");
            int country_ID = resultSet.getInt("COUNTRY_ID");
            FirstLevelDivisions firstLevelDivision = new FirstLevelDivisions(divisionID, division, country_ID);
            divisionsList.add(firstLevelDivision);
        }
        return divisionsList;
    }
}
