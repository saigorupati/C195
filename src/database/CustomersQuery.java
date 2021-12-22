package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;
import java.time.LocalDateTime;

public class CustomersQuery {
    /**
     * @return the observable list of all the customers information
     * @throws SQLException if an SQL exception occurs
     */
    public static ObservableList<Customers> getCustomers() throws SQLException {
        ObservableList<Customers> customersList = FXCollections.observableArrayList();
        String sql = "SELECT * from customers";
        PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int customerID = resultSet.getInt("Customer_ID");
            String name = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            int divisionID = resultSet.getInt("Division_ID");
            Customers customer = new Customers(customerID, name, address, postalCode, phone, divisionID);
            customersList.add(customer);
        }
        return customersList;
    }

    /**
     * The method called on by addCustomer in the MainScreen controller
     * @param newID the ID of customer
     * @param customerName the customer's name
     * @param customerAddress the customer's address
     * @param customerPostalCode the customer's postal code
     * @param customerPhone the customer's phone number
     * @param division the customers division
     * @throws SQLException if SQL error occurs
     */
    public static void addCustomerToDatabase(int newID, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int division) throws SQLException {
        LocalDateTime createdDateToAdd = LocalDateTime.now();
        String createdByToAdd = "admin";
        Timestamp lastUpdateToAdd = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedByToAdd = "admin";
        String insertStatement = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                "Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(JDBC.getConnection(), insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, newID);
        statement.setString(2, customerName);
        statement.setString(3, customerAddress);
        statement.setString(4, customerPostalCode);
        statement.setString(5, customerPhone);
        statement.setTimestamp(6, Timestamp.valueOf(createdDateToAdd));
        statement.setString(7, createdByToAdd);
        statement.setTimestamp(8, lastUpdateToAdd);
        statement.setString(9, lastUpdatedByToAdd);
        statement.setInt(10, division);
        statement.execute();
    }

    /**
     * The method called on by updateCustomer in the MainScreen controller
     * @param customerID the customer ID
     * @param customerName the customer's name
     * @param customerAddress the customer's address
     * @param customerPostalCode the customer's postal code
     * @param customerPhone the customer's phone number
     * @param divisionID the customer's division ID
     * @throws SQLException if SQL error occurs
     */
    public static void updateCustomerToDatabase(int customerID, String customerName, String customerAddress,
                                                String customerPostalCode, String customerPhone, int divisionID) throws SQLException {
        Timestamp updatedTime = Timestamp.valueOf(LocalDateTime.now());
        String updatedBy = "admin";
        String updateQuery = "UPDATE customers SET Customer_Name = ?, Address = ?, "
                + "Postal_Code = ?, Phone = ?, Last_Update = ?, Last_Updated_By = ?, "
                + "Division_ID = ? WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), updateQuery);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, customerName);
        statement.setString(2, customerAddress);
        statement.setString(3, customerPostalCode);
        statement.setString(4, customerPhone);
        statement.setTimestamp(5, updatedTime);
        statement.setString(6, updatedBy);
        statement.setInt(7, divisionID);
        statement.setInt(8, customerID);
        statement.execute();
    }


    /**
     * A method called by deleteCustomer in the MainScreen controller
     * @param customerID the customer ID
     * @throws SQLException if SQL error occurs
     */
    public static void deleteCustomerFromDatabase(int customerID) throws SQLException {
        String deleteQuery = "DELETE FROM customers WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), deleteQuery);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, customerID);
        statement.execute();
    }

    /**
     * A method called by deleteCustomer in the MainScreen controller
     * @param appointmentID the appointment ID
     * @throws SQLException if SQL error occurs
     */
    public static void deleteAppointmentFromDatabase(int appointmentID) throws SQLException {
        String deleteAppointmentQuery = "DELETE FROM appointments WHERE Appointment_ID = ?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), deleteAppointmentQuery);
        PreparedStatement appointmentsStatement = DBQuery.getPreparedStatement();
        appointmentsStatement.setInt(1, appointmentID);
        appointmentsStatement.execute();
    }


}

