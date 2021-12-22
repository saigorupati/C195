package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentsQuery {
    /**
     * @return the observable list of all the appointments information
     * @throws SQLException if an SQL exception occurs
     */
    public static ObservableList<Appointments> getAppointments() throws SQLException {
        ObservableList<Appointments> appointmentsList = FXCollections.observableArrayList();
        String sql = "SELECT * from appointments";
        PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int appointmentID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = resultSet.getTimestamp("End").toLocalDateTime();
            int customerID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");
            Appointments appointment = new Appointments(appointmentID, title, description,
                    location, type, start, end, customerID, userID, contactID);
            appointmentsList.add(appointment);
        }
        return appointmentsList;
    }

    /**
     * This method is called by the addAppointment method in the MainScreen controller
     *
     * @param appointmentID the appointment ID
     * @param title         the title
     * @param description   the description
     * @param location      the location
     * @param type          the type
     * @param startLDT      the start time
     * @param endLDT        the end time
     * @param createdDate   the date created
     * @param createdBy     the person who created appointment
     * @param lastUpdate    the date updated
     * @param lastUpdatedBy the person who updated appointment
     * @param customerID    the customer ID
     * @param userID        the user ID
     * @param contactID     the contact ID
     * @throws SQLException if SQL error occurs
     */
    public static void addAppointment(int appointmentID, String title, String description, String location, String type,
                                      LocalDateTime startLDT, LocalDateTime endLDT, Timestamp createdDate, String createdBy,
                                      Timestamp lastUpdate, String lastUpdatedBy, int customerID, int userID,
                                      int contactID) throws SQLException {
        String insertStatement = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, " +
                "Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(JDBC.getConnection(), insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, appointmentID);
        statement.setString(2, title);
        statement.setString(3, description);
        statement.setString(4, location);
        statement.setString(5, type);
        statement.setTimestamp(6, Timestamp.valueOf((startLDT)));
        statement.setTimestamp(7, Timestamp.valueOf((endLDT)));
        statement.setTimestamp(8, createdDate);
        statement.setString(9, createdBy);
        statement.setTimestamp(10, lastUpdate);
        statement.setString(11, lastUpdatedBy);
        statement.setInt(12, customerID);
        statement.setInt(13, userID);
        statement.setInt(14, contactID);
        statement.execute();
    }

    /**
     * This method is called by the updateAppointment method in the MainScreen controller
     * @param appointmentID the appointment ID
     * @param title         the title
     * @param description   the description
     * @param location      the location
     * @param type          the type
     * @param startLDT      the start time
     * @param endLDT        the end time
     * @param lastUpdate    the date updated
     * @param lastUpdatedBy the person who updated appointment
     * @param customerID    the customer ID
     * @param userID        the user ID
     * @param contactID     the contact ID
     * @throws SQLException if SQL error occurs
     */
    public static void updateAppointment(int appointmentID, String title, String description, String location,
                                         String type, LocalDateTime startLDT, LocalDateTime endLDT, Timestamp lastUpdate,
                                         String lastUpdatedBy, int customerID, int userID, int contactID) throws SQLException {
        String updateStatement = "UPDATE appointments SET Appointment_ID = ?, Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, " +
                "Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), updateStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, appointmentID);
        statement.setString(2, title);
        statement.setString(3, description);
        statement.setString(4, location);
        statement.setString(5, type);
        statement.setTimestamp(6, Timestamp.valueOf(startLDT));
        statement.setTimestamp(7, Timestamp.valueOf(endLDT));
        statement.setTimestamp(8, lastUpdate);
        statement.setString(9, lastUpdatedBy);
        statement.setInt(10, customerID);
        statement.setInt(11, userID);
        statement.setInt(12, contactID);
        statement.setInt(13, appointmentID);
        statement.execute();
    }

    /**
     * This method is called by the deleteAppointment method in the MainScreen controller
     * @param appointmentID the appointment ID
     * @throws SQLException if an SQL error occurs
     */
    public static void deleteAppointment(int appointmentID) throws SQLException {
        String deleteStatement = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), deleteStatement);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        ps.setInt(1, appointmentID);
        ps.execute();
    }

}

