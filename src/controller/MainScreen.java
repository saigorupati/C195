package controller;

import database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import model.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.text.DateFormatSymbols;

public class MainScreen implements Initializable {

    //Initializes Customers Tab Elements
    @FXML TextField customerIDTextField;
    @FXML TextField customerNameTextField;
    @FXML TextField addressTextField;
    @FXML ComboBox<String> countryComboBox;
    @FXML ComboBox<String> firstLevelDivisionComboBox;
    @FXML TextField postalCodeTextField;
    @FXML TextField phoneNumberTextField;
    @FXML Button addCustomerButton;
    @FXML Button updateCustomerButton;
    @FXML Button deleteCustomerButton;
    @FXML Button customersClearAllFieldsButton;

    @FXML TableView<Customers> customersTabTableView;
    @FXML TableColumn<Customers, Integer> customersTabIDColumn;
    @FXML TableColumn<Customers, String> customersTabNameColumn;
    @FXML TableColumn<Customers, String> customersTabAddressColumn;
    @FXML TableColumn<Customers, String> customersTabDivisionColumn;
    @FXML TableColumn<Customers, String> customersTabPostalCodeColumn;
    @FXML TableColumn<Customers, String> customersTabPhoneNumberColumn;

    //Initializes Appointments Tab Elements
    @FXML Tab appointmentsWeekTab;
    @FXML TableView<Appointments> appointmentsWeekTableView;
    @FXML TableColumn<Appointments, Integer> weekAppointmentIDColumn;
    @FXML TableColumn<Appointments, String> weekTitleColumn;
    @FXML TableColumn<Appointments, String> weekDescriptionColumn;
    @FXML TableColumn<Appointments, String> weekLocationColumn;
    @FXML TableColumn<Appointments, Integer> weekContactColumn;
    @FXML TableColumn<Appointments, String> weekTypeColumn;
    @FXML TableColumn<Appointments, LocalDateTime> weekStartColumn;
    @FXML TableColumn<Appointments, LocalDateTime> weekEndColumn;
    @FXML TableColumn<Appointments, Integer> weekCustomerIDColumn;
    @FXML TableColumn<Appointments, Integer> weekUserIDColumn;

    @FXML Tab appointmentsMonthTab;
    @FXML TableView<Appointments> appointmentsMonthTableView;
    @FXML TableColumn<Appointments, Integer> monthAppointmentIDColumn;
    @FXML TableColumn<Appointments, String> monthTitleColumn;
    @FXML TableColumn<Appointments, String> monthDescriptionColumn;
    @FXML TableColumn<Appointments, String> monthLocationColumn;
    @FXML TableColumn<Appointments, Integer> monthContactColumn;
    @FXML TableColumn<Appointments, String> monthTypeColumn;
    @FXML TableColumn<Appointments, LocalDateTime> monthStartColumn;
    @FXML TableColumn<Appointments, LocalDateTime> monthEndColumn;
    @FXML TableColumn<Appointments, Integer> monthCustomerIDColumn;
    @FXML TableColumn<Appointments, Integer> monthUserIDColumn;


    @FXML Tab appointmentsAllTab;
    @FXML TableView<Appointments> appointmentsAllTableView;
    @FXML TableColumn<Appointments, Integer> allAppointmentIDColumn;
    @FXML TableColumn<Appointments, String> allTitleColumn;
    @FXML TableColumn<Appointments, String> allDescriptionColumn;
    @FXML TableColumn<Appointments, String> allLocationColumn;
    @FXML TableColumn<Appointments, Integer> allContactColumn;
    @FXML TableColumn<Appointments, String> allTypeColumn;
    @FXML TableColumn<Appointments, LocalDateTime> allStartColumn;
    @FXML TableColumn<Appointments, LocalDateTime> allEndColumn;
    @FXML TableColumn<Appointments, Integer> allCustomerIDColumn;
    @FXML TableColumn<Appointments, Integer> allUserIDColumn;


    @FXML TextField appointmentIDTextField;
    @FXML TextField appointmentTitleTextField;
    @FXML TextField appointmentDescriptionTextField;
    @FXML TextField appointmentLocationTextField;
    @FXML ComboBox<String> appointmentContactComboBox;
    @FXML TextField appointmentTypeTextField;
    @FXML DatePicker appointmentStartDatePicker;
    @FXML ComboBox<String> appointmentStartTimeComboBox;
    @FXML DatePicker appointmentEndDatePicker;
    @FXML ComboBox<String> appointmentEndTimeComboBox;
    @FXML TextField appointmentCustomerIDTextField;
    @FXML TextField appointmentUserIDTextField;

    @FXML Button addAppointmentButton;
    @FXML Button updateAppointmentButton;
    @FXML Button deleteAppointmentButton;
    @FXML Button appointmentsClearAllFieldsButton;

    //Initializes Reports Tab elements
    @FXML Tab reportsTypeTab;
    @FXML TableView<Appointments> reportsTypeTableView;
    @FXML TableColumn<Appointments, Integer> typeAppointmentIDColumn;
    @FXML TableColumn<Appointments, String> typeTitleColumn;
    @FXML TableColumn<Appointments, Integer> typeCustomerIDColumn;
    @FXML TableColumn<Appointments, Integer> typeContactColumn;
    @FXML TableColumn<Appointments, String> typeDescriptionColumn;
    @FXML TableColumn<Appointments, String> typeLocationColumn;
    @FXML TableColumn<Appointments, String> typeTypeColumn;
    @FXML TableColumn<Appointments, LocalDateTime> typeStartColumn;
    @FXML TableColumn<Appointments, LocalDateTime> typeEndColumn;
    @FXML ComboBox<String> reportsAppointmentTypeComboBox;
    @FXML Label reportsTypeTotalCountLabel;


    @FXML Tab reportsMonthTab;
    @FXML TableView<Appointments> reportsMonthTableView;
    @FXML TableColumn<Appointments, Integer> reportsmonthAppointmentIDColumn;
    @FXML TableColumn<Appointments, String> reportsmonthTitleColumn;
    @FXML TableColumn<Appointments, Integer> reportsmonthCustomerIDColumn;
    @FXML TableColumn<Appointments, Integer> reportsmonthContactColumn;
    @FXML TableColumn<Appointments, String> reportsmonthDescriptionColumn;
    @FXML TableColumn<Appointments, String> reportsmonthLocationColumn;
    @FXML TableColumn<Appointments, String> reportsmonthTypeColumn;
    @FXML TableColumn<Appointments, LocalDateTime> reportsmonthStartColumn;
    @FXML TableColumn<Appointments, LocalDateTime> reportsmonthEndColumn;
    @FXML ComboBox<String> reportsAppointmentMonthComboBox;
    @FXML Label reportsMonthTotalCountLabel;


    @FXML Tab reportsContactTab;
    @FXML TableView<Appointments> reportsContactTableView;
    @FXML TableColumn<Appointments, Integer> contactAppointmentIDColumn;
    @FXML TableColumn<Appointments, String> contactTitleColumn;
    @FXML TableColumn<Appointments, Integer> contactCustomerIDColumn;
    @FXML TableColumn<Appointments, Integer> contactContactColumn;
    @FXML TableColumn<Appointments, String> contactDescriptionColumn;
    @FXML TableColumn<Appointments, String> contactLocationColumn;
    @FXML TableColumn<Appointments, String> contactTypeColumn;
    @FXML TableColumn<Appointments, LocalDateTime> contactStartColumn;
    @FXML TableColumn<Appointments, LocalDateTime> contactEndColumn;
    @FXML ComboBox<String> reportsAppointmentContactComboBox;
    @FXML Label reportsContactTotalCountLabel;

    @FXML Tab reportsPastTab;
    @FXML TableView<Appointments> reportsPastTableView;
    @FXML TableColumn<Appointments, Integer> pastAppointmentIDColumn;
    @FXML TableColumn<Appointments, String> pastTitleColumn;
    @FXML TableColumn<Appointments, Integer> pastCustomerIDColumn;
    @FXML TableColumn<Appointments, Integer> pastContactColumn;
    @FXML TableColumn<Appointments, String> pastDescriptionColumn;
    @FXML TableColumn<Appointments, String> pastLocationColumn;
    @FXML TableColumn<Appointments, String> pastTypeColumn;
    @FXML TableColumn<Appointments, LocalDateTime> pastStartColumn;
    @FXML TableColumn<Appointments, LocalDateTime> pastEndColumn;
    @FXML Label reportsPastTotalCountLabel;

    @FXML Label currentUserLabel;
    @FXML Label generalUseLabel;
    private int buttonClickedValue = -1;


    /**
     * This method checks for appointments within 15 minutes of login time
     * @throws SQLException if exception has occurred
     */
    public void checkForUpcomingAppointment() throws SQLException {
        int currentUserID = 1;
        LocalDateTime timeAtLogin = LocalDateTime.now();
        LocalDateTime timeOfLoginMinus15 = LocalDateTime.now().minusMinutes(15);
        LocalDateTime timeOfLoginPlus15 = LocalDateTime.now().plusMinutes(15);
        LocalDateTime startTimeCheck;
        LocalDateTime endTimeCheck;
        int apptIDDisplay = 0;
        LocalDateTime startTimeDisplay = null;
        LocalDateTime endTimeDisplay = null;
        boolean uppcomingAppt = false;
        boolean currentAppt = false;
        ObservableList<Appointments> appointmentsObservableList = AppointmentsQuery.getAppointments();
        for (Appointments appointment : appointmentsObservableList) {
            startTimeCheck = appointment.getStart();
            endTimeCheck = appointment.getEnd();
            int apptUserID = appointment.getUserID();
            if ((currentUserID == apptUserID) &&
                    (startTimeCheck.isAfter(timeOfLoginMinus15) || startTimeCheck.isEqual(timeOfLoginMinus15)) &&
                    (startTimeCheck.isBefore(timeOfLoginPlus15) || (startTimeCheck.isEqual(timeOfLoginPlus15)))) {
                apptIDDisplay = appointment.getAppointmentID();
                startTimeDisplay = startTimeCheck;
                endTimeDisplay = endTimeCheck;
                uppcomingAppt = true;
            }
            else if ((currentUserID == apptUserID) &&
                    (timeAtLogin.isAfter(startTimeCheck) || timeAtLogin.isEqual(startTimeCheck)) &&
                    (timeAtLogin.isBefore(endTimeCheck) || (timeAtLogin.isEqual(endTimeCheck)))) {
                apptIDDisplay = appointment.getAppointmentID();
                startTimeDisplay = startTimeCheck;
                endTimeDisplay = endTimeCheck;
                currentAppt = true;
            }
        }
        if (uppcomingAppt) {
            new Alert(Alert.AlertType.INFORMATION,
                    "There is an appointment within 15 minutes." +
                    "\nAppointment ID: " + apptIDDisplay +
                    "\nStart Date: " + startTimeDisplay.toLocalDate() +
                    "\nEnd Date: " + endTimeDisplay.toLocalDate() +
                    "\nStart Time: " + startTimeDisplay.toLocalTime() +
                    "\nEnd Time: " + endTimeDisplay.toLocalTime()).showAndWait();
        }
        else if (currentAppt) {
            new Alert(Alert.AlertType.INFORMATION,
                    "There is a current appointment." +
                    "\nAppointment ID: " + apptIDDisplay +
                    "\nStart Date: " + startTimeDisplay.toLocalDate() +
                    "\nEnd Date: " + endTimeDisplay.toLocalDate() +
                    "\nStart Time: " + startTimeDisplay.toLocalTime() +
                    "\nEnd Time: " + endTimeDisplay.toLocalTime()).showAndWait();
        }
        else {
            new Alert(Alert.AlertType.INFORMATION,"There are no upcoming appointments.").showAndWait();
        }
    }

    //Customers Tab Methods
    /**
     * This methods sets the options for the divisions combo box
     * @throws SQLException if exception has occurred
     */
    public void setDivisions () throws SQLException {
        ObservableList<FirstLevelDivisions> divisionsList = DivisionQuery.getDivisions();
        ObservableList<String> divisionsUS = FXCollections.observableArrayList();
        ObservableList<String> divisionsUK = FXCollections.observableArrayList();
        ObservableList<String> divisionsCA = FXCollections.observableArrayList();
        for (FirstLevelDivisions i : divisionsList) {
            if (i.getCountryID() == 1) {
                divisionsUS.add(i.getDivision());
            }
            else if (i.getCountryID() == 2) {
                divisionsUK.add(i.getDivision());
            }
            else if (i.getCountryID() == 3) {
                divisionsCA.add(i.getDivision());
            }
        }
        String country = countryComboBox.getSelectionModel().getSelectedItem();
        switch (country) {
            case "U.S":
                firstLevelDivisionComboBox.setItems(divisionsUS);
                break;
            case "UK":
                firstLevelDivisionComboBox.setItems(divisionsUK);
                break;
            case "Canada":
                firstLevelDivisionComboBox.setItems(divisionsCA);
                break;
        }
    }

    /**
     * @throws SQLException if SQL error occurs
     */
    public void fillCustomersTable() throws SQLException {
        ObservableList<Customers> customersList = CustomersQuery.getCustomers();
        customersTabTableView.setItems(customersList);
    }


    /**
     * This method fills the selected customer's information from the table to the form
     * @throws SQLException if SQL error occurs
     */
    public void getSelectedCustomerData() throws SQLException {
        Customers selectedCustomer = customersTabTableView.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            ObservableList<Countries> allCountries = CountriesQuery.getCountries();
            ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DivisionQuery.getDivisions();
            ObservableList<String> firstLevelDivisionAllNames = FXCollections.observableArrayList();
            for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {
                firstLevelDivisionAllNames.add(firstLevelDivision.getDivision());
            }
            firstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
            customerIDTextField.setText(String.valueOf((selectedCustomer.getCustomerID())));
            customerNameTextField.setText(selectedCustomer.getCustomerName());
            addressTextField.setText(selectedCustomer.getAddress());
            postalCodeTextField.setText(selectedCustomer.getPostalCode());
            phoneNumberTextField.setText(selectedCustomer.getPhone());
            int divisionID = selectedCustomer.getDivisionID();
            String divison = "";
            int countryID;
            String country = "";
            for (FirstLevelDivisions i : allFirstLevelDivisions) {
                if (divisionID == i.getDivisionID()) {
                    divison = i.getDivision();
                    countryID = i.getCountryID();
                    for (Countries j : allCountries) {
                        if (countryID == j.getCountryID()) {
                            country = j.getCountry();
                        }
                    }
                }
            }
            firstLevelDivisionComboBox.setValue(divison);
            countryComboBox.setValue(country);
            addCustomerButton.setDisable(true);
            updateCustomerButton.setDisable(false);
            deleteCustomerButton.setDisable(false);
            customersClearAllFieldsButton.setDisable(false);
        }
    }

    /**
     * This method lets customers be added into the database and adds to the table
     * @throws SQLException if exception has occurred
     */
    public void addCustomer() throws SQLException {
        if (!customerNameTextField.getText().equals("") && !addressTextField.getText().equals("") &&
                !countryComboBox.getValue().equals("") && !firstLevelDivisionComboBox.getValue().equals("") &&
                !postalCodeTextField.getText().equals("") && !phoneNumberTextField.getText().equals("")) {
            int lastUsedID = 0;
            ObservableList<Customers> customersList = CustomersQuery.getCustomers();
            for (Customers customer : customersList) {
                if(customer.getCustomerID() > lastUsedID){
                    lastUsedID = customer.getCustomerID();
                }
            }
            int newID = lastUsedID + 1;
            String customerName = customerNameTextField.getText();
            String customerAddress = addressTextField.getText();
            String customerPostalCode = postalCodeTextField.getText();
            String customerPhone = phoneNumberTextField.getText();
            int division = 0;
            String firstLevelDivision = firstLevelDivisionComboBox.getSelectionModel().getSelectedItem();
            ObservableList<FirstLevelDivisions> divisionsList = DivisionQuery.getDivisions();
            for (FirstLevelDivisions i : divisionsList) {
                if (firstLevelDivision.equals(i.getDivision())) {
                    division = i.getDivisionID();
                }
            }
            CustomersQuery.addCustomerToDatabase(newID, customerName, customerAddress, customerPostalCode, customerPhone, division);
            fillCustomersTable();
            clearAllCustomersFields();
            generalLabelNotification(customerName);
        }
        else{
            generalUseLabel.setText("Customer Not Added! Fill out all fields.");
            generalUseLabel.setTextFill(Color.web("#BA181B"));
            generalUseLabel.setVisible(true);
        }
    }

    /**
     * This method gets the data from the form and updates database
     * @throws SQLException if SQL error occurs
     */
    public void updateCustomer() throws SQLException {
        int customerID = Integer.parseInt(customerIDTextField.getText());
        String customerName = customerNameTextField.getText();
        String customerAddress = addressTextField.getText();
        String customerPostalCode = postalCodeTextField.getText();
        String customerPhone = phoneNumberTextField.getText();
        String division = firstLevelDivisionComboBox.getValue();

        int divisionID = 0;
        ObservableList<FirstLevelDivisions> divisionsList = DivisionQuery.getDivisions();
        for (FirstLevelDivisions i : divisionsList) {
            if (division.equals(i.getDivision())) { divisionID = i.getDivisionID(); }
        }
        CustomersQuery.updateCustomerToDatabase(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divisionID);
        fillCustomersTable();
        generalLabelNotification(customerName);
        clearAllCustomersFields();
    }

    /**
     * This method deletes selected customer from database
     * If the customer has appointments deletes the customers appointments first
     * @throws SQLException if SQL error occurs
     */
    public void deleteCustomer() throws SQLException {
        String deleteQuery = "DELETE FROM customers WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(JDBC.getConnection(), deleteQuery);
        PreparedStatement ps = DBQuery.getPreparedStatement();
        int customerID = customersTabTableView.getSelectionModel().getSelectedItem().getCustomerID();
        ObservableList<Appointments> appointmentsObservableList = AppointmentsQuery.getAppointments();
        for (Appointments appointment : appointmentsObservableList) {
            int customerIDToCheck = appointment.getCustomerID();
            int appointmentIDToDelete = appointment.getAppointmentID();
            String appointmentTypeToDisplay = appointment.getType();
            if (customerID == customerIDToCheck) {
                String deleteStatementAppointments = "DELETE FROM appointments WHERE Appointment_ID = ?";
                DBQuery.setPreparedStatement(JDBC.getConnection(), deleteStatementAppointments);
                PreparedStatement psAppointments = DBQuery.getPreparedStatement();
                psAppointments.setInt(1, appointmentIDToDelete);
                psAppointments.execute();
                new Alert(Alert.AlertType.INFORMATION, "This appointment will be deleted" +
                        "\nAppointment_ID: " + appointmentIDToDelete +
                        "\nType of Appointment: " + appointmentTypeToDisplay).showAndWait();
            }
            initializeAppointmentsTab();
        }
        ps.setInt(1, customerID);
        ps.execute();
        fillCustomersTable();
        clearAllCustomersFields();
        generalUseLabel.setText("Customer (Customer ID: " + customerID + ") has been deleted.");
        generalUseLabel.setTextFill(Color.web("#BA181B"));
        generalUseLabel.setVisible(true);
    }

    /**
     * This method clears all fields in the customers tab
     */
    public void clearAllCustomersFields() {
        customersTabTableView.getSelectionModel().clearSelection();
        customerIDTextField.clear();
        customerIDTextField.setDisable(true);
        customerNameTextField.clear();
        addressTextField.clear();
        postalCodeTextField.clear();
        phoneNumberTextField.clear();
        countryComboBox.setValue("");
        firstLevelDivisionComboBox.setValue("");
        addCustomerButton.setDisable(false);
        updateCustomerButton.setDisable(true);
        deleteCustomerButton.setDisable(true);
        customersClearAllFieldsButton.setDisable(true);
    }

    /**
     * This method references lambda #1
     * the method makes it easier to confirm if customer add/update was successful
     * @param string beginning part of total string
     * @param function variable string based on which button is clicked
     * @return appropriate user notification to set for label
     */
    public String notifyUserAppropriateMessage(String string, Function<String, String> function) {
        return function.apply(string);
    }
    /**
     *  This method updates label when customer information is added or updated.
     * @param customerName the customer name
     */
    public void generalLabelNotification(String customerName) {
        Function<String, String> function = null;
        if (buttonClickedValue == 1) { function = parameter -> parameter + " " + customerName + " added to database"; }
        if (buttonClickedValue == 2) { function = parameter -> parameter + " " + customerName + " updated in database"; }
        String userNotification = notifyUserAppropriateMessage("Customer", function);
        generalUseLabel.setText(userNotification);
        generalUseLabel.setTextFill(Color.web("#DBFEB8"));
        generalUseLabel.setVisible(true);
        buttonClickedValue = -1;
    }
    /**
     * This method sets the button values for the buttonClickedValue variable
     * lambda 1: this lambda makes it easier to confirm when customer is added/updated
     */
    public void setCustomerOptionAddAndUpdateButtonActions() {
        addCustomerButton.setOnAction(e -> { buttonClickedValue = 1;try { addCustomer();
        } catch (SQLException exception) { exception.printStackTrace();}});
        updateCustomerButton.setOnAction(e -> { buttonClickedValue = 2;try { updateCustomer();
        } catch (SQLException exception) { exception.printStackTrace();}});
    }


    /**
     * This method initializes the customers tab table
     * lambda 2: it makes it easier to add the first level divisions to the combo box
     * @throws SQLException if SQL error occurs
     */
    public void initializeCustomersTab() throws SQLException{
        customersTabIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customersTabNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customersTabAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        customersTabDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customersTabPostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customersTabPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        ObservableList<Countries> allCountries = CountriesQuery.getCountries();
        ObservableList<String> countryNames = FXCollections.observableArrayList();
        for (Countries country : allCountries) {
            countryNames.add(country.getCountry());
        }
        countryComboBox.setItems(countryNames);
        countryComboBox.setEditable(true);
        countryComboBox.getEditor().setEditable(false);
        ObservableList<FirstLevelDivisions> allFirstLevelDivisions = DivisionQuery.getDivisions();
        ObservableList<String> firstLevelDivisionAllNames = FXCollections.observableArrayList();

        // lambda #2
        allFirstLevelDivisions.forEach(firstLevelDivision -> firstLevelDivisionAllNames.add(firstLevelDivision.getDivision()));
        setCustomerOptionAddAndUpdateButtonActions();

        firstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
        firstLevelDivisionComboBox.setItems(firstLevelDivisionAllNames);
        firstLevelDivisionComboBox.setEditable(true);
        firstLevelDivisionComboBox.getEditor().setEditable(false);
        fillCustomersTable();
    }

    //Appointments Tab

    /**
     * This method fills the three tables in the appointment tab
     * @throws SQLException if SQL error occurs
     */
    public void fillAppointmentsTables() throws SQLException {
        ObservableList<Appointments> allList = AppointmentsQuery.getAppointments();
        ObservableList<Appointments> monthList = FXCollections.observableArrayList();
        ObservableList<Appointments> weekList = FXCollections.observableArrayList();

        LocalDateTime currentMonthStart = LocalDateTime.now().minusSeconds(1);
        LocalDateTime currentMonthEnd = LocalDateTime.now().plusMonths(1);

        LocalDateTime currentWeekStart = LocalDateTime.now().minusSeconds(1);
        LocalDateTime currentWeekEnd = LocalDateTime.now().plusWeeks(1);

        for (Appointments i : allList) {
            if (i.getEnd().isAfter(currentMonthStart) && i.getEnd().isBefore(currentMonthEnd)) {
                monthList.add(i);
            }
            if (i.getEnd().isAfter(currentWeekStart) && i.getEnd().isBefore(currentWeekEnd)) {
                weekList.add(i);
            }
        }
        appointmentsMonthTableView.setItems(monthList);
        appointmentsWeekTableView.setItems(weekList);
        appointmentsAllTableView.setItems(allList);
    }

    /**
     * This method fills the selected appointment's information from the table to the form
     * @throws SQLException if SQL error occurs
     */
    public void getSelectedAppointmentData() throws SQLException {
        Appointments selectedAppointment = null;
        if (appointmentsMonthTab.isSelected()) {
            selectedAppointment = appointmentsMonthTableView.getSelectionModel().getSelectedItem();
        }
        if (appointmentsWeekTab.isSelected()) {
            selectedAppointment = appointmentsWeekTableView.getSelectionModel().getSelectedItem();
        }
        if (appointmentsAllTab.isSelected()) {
            selectedAppointment = appointmentsAllTableView.getSelectionModel().getSelectedItem();
        }
        if (selectedAppointment != null) {
            appointmentIDTextField.setText(String.valueOf((selectedAppointment.getAppointmentID())));
            appointmentTitleTextField.setText(selectedAppointment.getTitle());
            appointmentDescriptionTextField.setText(selectedAppointment.getDescription());
            appointmentLocationTextField.setText(selectedAppointment.getLocation());
            int contactID = selectedAppointment.getContactID();
            String contactName = "";
            ObservableList<Contacts> contactsObservableList = ContactsQuery.getContacts();
            for (Contacts contact : contactsObservableList) {
                if (contactID == contact.getContactID()) {
                    contactName = contact.getContactName();
                }
            }
            appointmentContactComboBox.setValue(contactName);
            appointmentTypeTextField.setText(selectedAppointment.getType());
            appointmentStartDatePicker.setValue(selectedAppointment.getStart().toLocalDate());
            appointmentEndDatePicker.setValue(selectedAppointment.getEnd().toLocalDate());
            appointmentStartTimeComboBox.setValue(selectedAppointment.getStart().toLocalTime().toString());
            appointmentEndTimeComboBox.setValue(selectedAppointment.getEnd().toLocalTime().toString());
            appointmentCustomerIDTextField.setText(String.valueOf(selectedAppointment.getCustomerID()));
            appointmentUserIDTextField.setText(String.valueOf(selectedAppointment.getUserID()));
            addAppointmentButton.setDisable(true);
            updateAppointmentButton.setDisable(false);
            deleteAppointmentButton.setDisable(false);
            appointmentsClearAllFieldsButton.setDisable(false);
        }
    }



    /**
     * This method clears all fields in the appointments tab
     */
    public void clearAllAppointmentsFields() {
        appointmentsMonthTableView.getSelectionModel().clearSelection();
        appointmentTitleTextField.clear();
        appointmentDescriptionTextField.clear();
        appointmentLocationTextField.clear();
        appointmentIDTextField.clear();
        appointmentTypeTextField.clear();
        appointmentStartDatePicker.setValue(null);
        appointmentEndDatePicker.setValue(null);
        appointmentStartTimeComboBox.setValue("");
        appointmentEndTimeComboBox.setValue("");
        appointmentCustomerIDTextField.clear();
        appointmentUserIDTextField.clear();
        appointmentContactComboBox.setValue("");
        addAppointmentButton.setDisable(false);
        updateAppointmentButton.setDisable(true);
        deleteAppointmentButton.setDisable(true);
        appointmentsClearAllFieldsButton.setDisable(true);
    }

    /**
     * This method initializes tables in the appointments tab
     * @throws SQLException if SQL error occurs
     */
    public void initializeAppointmentsTab() throws SQLException {
        monthAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        monthTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        monthContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        monthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        monthEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        monthCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        monthUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        weekAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        weekTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weekDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weekLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        weekContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        weekTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        weekStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        weekEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        weekCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        weekUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        allAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        allTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        allDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        allLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        allContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        allTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        allStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        allEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        allCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        allUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("userID"));

        ObservableList<Contacts> contactsList = ContactsQuery.getContacts();
        ObservableList<String> allContacts = FXCollections.observableArrayList();
        contactsList.forEach(contacts -> allContacts.add(contacts.getContactName()));
        appointmentContactComboBox.setItems(allContacts);
        appointmentContactComboBox.setEditable(true);
        appointmentContactComboBox.getEditor().setEditable(false);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm");
        ObservableList<String> appointmentTimesDuringDay = FXCollections.observableArrayList();
        LocalTime firstApptTime = LocalTime.MIN.plusHours(8);
        LocalTime lastApptTime = LocalTime.MAX.minusHours(1).minusMinutes(59);
        while (firstApptTime.isBefore(lastApptTime)) {
            appointmentTimesDuringDay.add(dt.format(firstApptTime));
            firstApptTime = firstApptTime.plusMinutes(30);
        }
        appointmentStartTimeComboBox.setItems(appointmentTimesDuringDay);
        appointmentStartTimeComboBox.setEditable(true);
        appointmentStartTimeComboBox.getEditor().setEditable(false);
        appointmentEndTimeComboBox.setItems(appointmentTimesDuringDay);
        appointmentEndTimeComboBox.setEditable(true);
        appointmentEndTimeComboBox.getEditor().setEditable(false);
        fillAppointmentsTables();
        generalUseLabel.setText("Not being used.");
        generalUseLabel.setVisible(false);
    }

    /**
     * This method is used to add appointment to the database
     * This method checks if the appointment is within business hours and days
     * This method also checks if there are any overlapping appointments
     * @throws SQLException if SQL error occurs
     */
    public void addAppointment() throws SQLException {
        if (!appointmentTitleTextField.getText().equals("") && !appointmentDescriptionTextField.getText().equals("") &&
                !appointmentLocationTextField.getText().equals("") && !appointmentContactComboBox.getValue().equals("") &&
                !appointmentTypeTextField.getText().equals("") && !appointmentCustomerIDTextField.getText().equals("") &&
                !appointmentUserIDTextField.getText().equals("") && (appointmentStartDatePicker.getValue() != null) &&
                (appointmentEndDatePicker.getValue() != null) && !appointmentStartTimeComboBox.getValue().equals("") &&
                !appointmentEndTimeComboBox.getValue().equals("")) {

            ObservableList<Customers> customersList = CustomersQuery.getCustomers();
            ObservableList<Integer> customerIDs = FXCollections.observableArrayList();
            for (Customers i : customersList) {
                customerIDs.add(i.getCustomerID());
            }
            if (!customerIDs.contains(Integer.parseInt(appointmentCustomerIDTextField.getText()))) {
                generalUseLabel.setText("Customer ID does not exist. Appointment not added.");
                generalUseLabel.setTextFill(Color.web("#BA181B"));
                generalUseLabel.setVisible(true);
            }
            ObservableList<Users> usersList = UsersQuery.getUsers();
            ObservableList<Integer> userIDs = FXCollections.observableArrayList();
            for (Users i : usersList) {
                userIDs.add(i.getUserID());
            }
            if (!userIDs.contains(Integer.parseInt(appointmentUserIDTextField.getText()))) {
                generalUseLabel.setText("User ID does not exist. Appointment not added.");
                generalUseLabel.setTextFill(Color.web("#BA181B"));
                generalUseLabel.setVisible(true);
            }

            int lastApptID = 0;
            ObservableList<Appointments> appointmentsList = AppointmentsQuery.getAppointments();
            for (Appointments i : appointmentsList) {
                lastApptID = i.getAppointmentID();
            }
            int appointmentID = lastApptID + 1;
            String title = appointmentTitleTextField.getText();
            String description = appointmentDescriptionTextField.getText();
            String location = appointmentLocationTextField.getText();
            String type = appointmentTypeTextField.getText();
            String contactName = appointmentContactComboBox.getValue();
            int contactID = 0;
            ObservableList<Contacts> contactsList = ContactsQuery.getContacts();
            for (Contacts i : contactsList) {
                if (contactName.equals(i.getContactName())) {
                    contactID = i.getContactID();
                }
            }
            LocalDate startLD = appointmentStartDatePicker.getValue();
            LocalDate endLD = appointmentEndDatePicker.getValue();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime startLT = LocalTime.parse(appointmentStartTimeComboBox.getValue(), dtf);
            LocalTime endLT = LocalTime.parse(appointmentEndTimeComboBox.getValue(), dtf);
            LocalDateTime startLDT = LocalDateTime.of(startLD, startLT);
            LocalDateTime endLDT = LocalDateTime.of(endLD, endLT);

            //checks if appointment is within business hours
            ZonedDateTime startLDTToZDT = ZonedDateTime.of(startLDT, ZoneId.systemDefault());
            ZonedDateTime startZDTToZDTEST = startLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalTime startAppointmentTimeToCheck = startZDTToZDTEST.toLocalTime();
            DayOfWeek startAppointmentDayToCheck = startZDTToZDTEST.toLocalDate().getDayOfWeek();
            int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();
            ZonedDateTime endLDTToZDT = ZonedDateTime.of(endLDT, ZoneId.systemDefault());
            ZonedDateTime endZDTToZDTEST = endLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalTime endAppointmentTimeToCheck = endZDTToZDTEST.toLocalTime();
            DayOfWeek endAppointmentDayToCheck = endZDTToZDTEST.toLocalDate().getDayOfWeek();
            int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();
            LocalTime startTime = LocalTime.of(8, 0, 0);
            LocalTime endTime = LocalTime.of(22, 0, 0);
            int startOfWeekInt = DayOfWeek.MONDAY.getValue();
            int endOfWeekInt = DayOfWeek.FRIDAY.getValue();
            if (startAppointmentTimeToCheck.isBefore(startTime) ||
                    startAppointmentTimeToCheck.isAfter(endTime) ||
                    endAppointmentTimeToCheck.isBefore(startTime) ||
                    endAppointmentTimeToCheck.isAfter(endTime)) {
                generalUseLabel.setText("Time is outside business hours of 08:00 and 22:00 EST.");
                generalUseLabel.setTextFill(Color.web("#BA181B"));
                generalUseLabel.setVisible(true);
                return;
            }
            if (startAppointmentDayToCheckInt < startOfWeekInt || startAppointmentDayToCheckInt > endOfWeekInt ||
                    endAppointmentDayToCheckInt < startOfWeekInt || endAppointmentDayToCheckInt > endOfWeekInt) {
                generalUseLabel.setText("Day is outside of business days of Monday through Friday.");
                generalUseLabel.setTextFill(Color.web("#BA181B"));
                generalUseLabel.setVisible(true);
                return;
            }
            Timestamp createdDate = Timestamp.valueOf(LocalDateTime.now());
            String createdBy = "admin";
            Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
            String lastUpdatedBy = "admin";
            int customerID = Integer.parseInt(appointmentCustomerIDTextField.getText());

            // checks if the appointments are overlapping
            ObservableList<Appointments> allAppointmentsList = AppointmentsQuery.getAppointments();
            for (Appointments i : allAppointmentsList) {
                LocalDateTime startTimesToCheck = i.getStart();
                LocalDateTime endTimesToCheck = i.getEnd();
                int customerIDsToCheck = i.getCustomerID();
                if (customerID == customerIDsToCheck &&
                        (startLDT.isEqual(startTimesToCheck) ||
                                startLDT.isAfter(startTimesToCheck)) &&
                        (startLDT.isEqual(endTimesToCheck) ||
                                startLDT.isBefore(endTimesToCheck))) {
                    generalUseLabel.setText("Appointment start time overlaps with existing appointment for customer.");
                    generalUseLabel.setTextFill(Color.web("#BA181B"));
                    generalUseLabel.setVisible(true);
                    return;
                }
                if (customerID == customerIDsToCheck &&
                        (endLDT.isEqual(startTimesToCheck) ||
                                endLDT.isAfter(startTimesToCheck)) &&
                        (endLDT.isEqual(endTimesToCheck) ||
                                endLDT.isBefore(endTimesToCheck))) {
                    generalUseLabel.setText("Appointment end time overlaps with existing appointment for customer.");
                    generalUseLabel.setTextFill(Color.web("#BA181B"));
                    generalUseLabel.setVisible(true);
                    return;
                }
            }
            int userID = Integer.parseInt(appointmentUserIDTextField.getText());
            AppointmentsQuery.addAppointment(appointmentID, title, description, location, type, startLDT, endLDT, createdDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contactID);
            fillAppointmentsTables();
            generalUseLabel.setText("Appointment (Appointment ID: " + appointmentID + " Type: " + type + ") added to the database");
            generalUseLabel.setTextFill(Color.web("#DBFEB8"));
            generalUseLabel.setVisible(true);
            clearAllAppointmentsFields();
        } else{
            generalUseLabel.setText("Appointment Not Added! Fill out all fields.");
            generalUseLabel.setTextFill(Color.web("#BA181B"));
            generalUseLabel.setVisible(true);
        }
    }

    /**
     * This method is used to update an appointment to the database
     * This method checks if the appointment is within business hours and days
     * This method also checks if there are any overlapping appointments
     * @throws SQLException if SQL error occurs
     */
    public void updateAppointment() throws SQLException {
        ObservableList<Customers> customersList = CustomersQuery.getCustomers();
        ObservableList<Integer> customerIDs = FXCollections.observableArrayList();
        for (Customers i : customersList) {
            customerIDs.add(i.getCustomerID());
        }
        if (!customerIDs.contains(Integer.parseInt(appointmentCustomerIDTextField.getText()))) {
            generalUseLabel.setText("Customer ID does not exist. Appointment not added.");
            generalUseLabel.setTextFill(Color.web("#BA181B"));
            generalUseLabel.setVisible(true);
        }
        ObservableList<Users> usersList = UsersQuery.getUsers();
        ObservableList<Integer> userIDs = FXCollections.observableArrayList();
        for (Users i : usersList) {
            userIDs.add(i.getUserID());
        }
        if (!userIDs.contains(Integer.parseInt(appointmentUserIDTextField.getText()))) {
            generalUseLabel.setText("User ID does not exist. Appointment not added.");
            generalUseLabel.setTextFill(Color.web("#BA181B"));
            generalUseLabel.setVisible(true);
        }

        int appointmentID = Integer.parseInt(appointmentIDTextField.getText());
        String title = appointmentTitleTextField.getText();
        String description = appointmentDescriptionTextField.getText();
        String location = appointmentLocationTextField.getText();
        String type = appointmentTypeTextField.getText();
        String contactName = appointmentContactComboBox.getValue();
        int contactID = 0;
        ObservableList<Contacts> contactsList = ContactsQuery.getContacts();
        for (Contacts i : contactsList) {
            if (contactName.equals(i.getContactName())) {
                contactID = i.getContactID();
            }
        }
        LocalDate startLD = appointmentStartDatePicker.getValue();
        LocalDate endLD = appointmentEndDatePicker.getValue();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startLT = LocalTime.parse(appointmentStartTimeComboBox.getValue(), dtf);
        LocalTime endLT = LocalTime.parse(appointmentEndTimeComboBox.getValue(), dtf);
        LocalDateTime startLDT = LocalDateTime.of(startLD, startLT);
        LocalDateTime endLDT = LocalDateTime.of(endLD, endLT);

        //checks if outside business hours
        ZonedDateTime startLDTToZDT = ZonedDateTime.of(startLDT, ZoneId.systemDefault());
        ZonedDateTime startZDTToZDTEST = startLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime startAppointmentTimeToCheck = startZDTToZDTEST.toLocalTime();
        DayOfWeek startAppointmentDayToCheck = startZDTToZDTEST.toLocalDate().getDayOfWeek();
        int startAppointmentDayToCheckInt = startAppointmentDayToCheck.getValue();
        // end time
        ZonedDateTime endLDTToZDT = ZonedDateTime.of(endLDT, ZoneId.systemDefault());
        ZonedDateTime endZDTToZDTEST = endLDTToZDT.withZoneSameInstant(ZoneId.of("America/New_York"));
        LocalTime endAppointmentTimeToCheck = endZDTToZDTEST.toLocalTime();
        DayOfWeek endAppointmentDayToCheck = endZDTToZDTEST.toLocalDate().getDayOfWeek();
        int endAppointmentDayToCheckInt = endAppointmentDayToCheck.getValue();

        // business operation hours/days
        LocalTime startTime = LocalTime.of(8, 0, 0);
        LocalTime endTime = LocalTime.of(22, 0, 0);
        int startOfWeekInt = DayOfWeek.MONDAY.getValue();
        int endOfWeekInt = DayOfWeek.FRIDAY.getValue();
        if (startAppointmentTimeToCheck.isBefore(startTime) ||
                startAppointmentTimeToCheck.isAfter(endTime) ||
                endAppointmentTimeToCheck.isBefore(startTime) ||
                endAppointmentTimeToCheck.isAfter(endTime)) {
            generalUseLabel.setText("Time is outside business hours of 08:00 and 22:00 EST.");
            generalUseLabel.setTextFill(Color.web("#BA181B"));
            generalUseLabel.setVisible(true);
            return;
        }
        if (startAppointmentDayToCheckInt < startOfWeekInt || startAppointmentDayToCheckInt > endOfWeekInt ||
                endAppointmentDayToCheckInt < startOfWeekInt || endAppointmentDayToCheckInt > endOfWeekInt) {
            generalUseLabel.setText("Day is outside of business days of Monday through Friday.");
            generalUseLabel.setTextFill(Color.web("#BA181B"));
            generalUseLabel.setVisible(true);
            return;
        }
        Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
        String lastUpdatedBy = "admin";
        int customerID = Integer.parseInt(appointmentCustomerIDTextField.getText());

        // checks if appointments are overlapping
        ObservableList<Appointments> allAppointmentsList = AppointmentsQuery.getAppointments();
        int currentAppointmentID = 0;
        if (appointmentsMonthTab.isSelected()) {
            currentAppointmentID = appointmentsMonthTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        }
        if (appointmentsWeekTab.isSelected()) {
            currentAppointmentID = appointmentsWeekTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        }
        if (appointmentsAllTab.isSelected()) {
            currentAppointmentID = appointmentsAllTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        }
        for (Appointments appointment : allAppointmentsList) {
            LocalDateTime startTimesToCheck = appointment.getStart();
            LocalDateTime endTimesToCheck = appointment.getEnd();
            int customerIDsToCheck = appointment.getCustomerID();
            if ((appointment.getAppointmentID() != currentAppointmentID) &&
                    (customerID == customerIDsToCheck) &&
                    (startLDT.isEqual(startTimesToCheck) ||
                            startLDT.isAfter(startTimesToCheck)) &&
                    (startLDT.isEqual(endTimesToCheck) ||
                            startLDT.isBefore(endTimesToCheck))) {
                generalUseLabel.setText("Appointment start time overlaps with existing appointment for customer.");
                generalUseLabel.setTextFill(Color.web("#BA181B"));
                generalUseLabel.setVisible(true);
                return;
            }
            if ((appointment.getAppointmentID() != currentAppointmentID) &&
                    (customerID == customerIDsToCheck) &&
                    (endLDT.isEqual(startTimesToCheck) ||
                            endLDT.isAfter(startTimesToCheck)) &&
                    (endLDT.isEqual(endTimesToCheck) ||
                            endLDT.isBefore(endTimesToCheck))) {
                generalUseLabel.setText("Appointment end time overlaps with existing appointment for customer.");
                generalUseLabel.setTextFill(Color.web("#BA181B"));
                generalUseLabel.setVisible(true);
                return;
            }
        }
        int userID = Integer.parseInt(appointmentUserIDTextField.getText());
        AppointmentsQuery.updateAppointment(appointmentID, title, description, location, type, startLDT, endLDT,
                lastUpdate, lastUpdatedBy, customerID, userID, contactID);
        fillAppointmentsTables();
        generalUseLabel.setText("Appointment (Appointment ID: " + appointmentID + " Type: " + type + ") updated in the database");
        generalUseLabel.setTextFill(Color.web("#DBFEB8"));
        generalUseLabel.setVisible(true);
        clearAllAppointmentsFields();
    }

    /**
     * This method is used to delete an appointment to the database
     * @throws SQLException if exception has occurred
     */
    public void deleteAppointment() throws SQLException {
        int appointmentID = 0;
        String type = "";
        if (appointmentsMonthTab.isSelected()) {
            appointmentID = appointmentsMonthTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            type = appointmentsMonthTableView.getSelectionModel().getSelectedItem().getType();
        }
        if (appointmentsWeekTab.isSelected()) {
            appointmentID = appointmentsWeekTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            type = appointmentsWeekTableView.getSelectionModel().getSelectedItem().getType();

        }
        if (appointmentsAllTab.isSelected()) {
            appointmentID = appointmentsAllTableView.getSelectionModel().getSelectedItem().getAppointmentID();
            type = appointmentsAllTableView.getSelectionModel().getSelectedItem().getType();
        }
        AppointmentsQuery.deleteAppointment(appointmentID);
        fillAppointmentsTables();
        generalUseLabel.setText("Appointment (Appointment ID: " + appointmentID + " Type: " + type + ") deleted from the database");
        generalUseLabel.setTextFill(Color.web("#DBFEB8"));
        generalUseLabel.setVisible(true);
        clearAllAppointmentsFields();
    }

    // Reports Tab

    /**
     * This method fills the ComboBox in the Reports Contact tab
     * @throws SQLException if SQL error occurs
     */
    public void fillContactsComboBox() throws SQLException {
        reportsAppointmentContactComboBox.setVisible(true);
        ObservableList<Contacts> contactsList = ContactsQuery.getContacts();
        ObservableList<String> contactNames = FXCollections.observableArrayList();
        for (Contacts i : contactsList) {
            String contactName = i.getContactName();
            contactNames.add(contactName);
        }
        reportsAppointmentContactComboBox.setItems(contactNames);
    }

    /**
     * This method fills the table in Appointments By Contact with appointments including the selected contact
     * @throws SQLException if SQL error occurs
     */
    public void getAppointmentsByContact() throws SQLException {
        int contactID = 0;
        int totalCount = 0;
        Appointments selectedAppointment;
        String selectedContactName = reportsAppointmentContactComboBox.getSelectionModel().getSelectedItem();
        if (selectedContactName != null) {
            ObservableList<Contacts> allContactData = ContactsQuery.getContacts();
            for (Contacts i : allContactData) {
                if (selectedContactName.equals(i.getContactName())) {
                    contactID = i.getContactID();
                }
            }
            ObservableList<Appointments> allAppointmentData = AppointmentsQuery.getAppointments();
            ObservableList<Appointments> appointmentDataForSelectedContact = FXCollections.observableArrayList();
            for (Appointments i : allAppointmentData) {
                if (contactID == i.getContactID()) {
                    selectedAppointment = i;
                    appointmentDataForSelectedContact.add(selectedAppointment);
                    totalCount++;
                }
            }
            reportsContactTableView.setItems(appointmentDataForSelectedContact);
        }
        reportsContactTotalCountLabel.setText("Total Count: " + totalCount);
    }

    /**
     * This method fills the ComboBox in the Reports Months tab
     * @throws SQLException if SQL error occurs
     */
    public void fillMonthsComboBox() throws SQLException {
        reportsAppointmentMonthComboBox.setVisible(true);
        ObservableList<String> monthsList = FXCollections.observableArrayList();
        String[] months = new DateFormatSymbols().getMonths();
        monthsList.addAll(Arrays.asList(months));
        reportsAppointmentMonthComboBox.setItems(monthsList);
    }

    /**
     * This method fills the table in Appointments By Month with appointments including the selected month
     * @throws SQLException if SQL error occurs
     */
    public void getAppointmentsByMonth() throws SQLException {
        int totalCount = 0;
        Appointments selectedAppointment;
        String selectedMonth = reportsAppointmentMonthComboBox.getSelectionModel().getSelectedItem();
        if (selectedMonth != null) {
            ObservableList<Appointments> allAppointmentData = AppointmentsQuery.getAppointments();
            ObservableList<Appointments> appointmentDataForSelectedMonth = FXCollections.observableArrayList();
            for (Appointments i : allAppointmentData) {
                String startMonth = i.getStart().getMonth().toString();
                String endMonth = i.getEnd().getMonth().toString();
                if (startMonth.equalsIgnoreCase(selectedMonth)|| endMonth.equalsIgnoreCase(selectedMonth)) {
                    selectedAppointment = i;
                    appointmentDataForSelectedMonth.add(selectedAppointment);
                    totalCount++;
                }
            }
            reportsMonthTableView.setItems(appointmentDataForSelectedMonth);
        }
        reportsMonthTotalCountLabel.setText("Total Count: " + totalCount);

    }

    /**
     * This method fills the ComboBox in the Reports Type tab
     * @throws SQLException if SQL error occurs
     */
    public void fillTypeComboBox() throws SQLException {
        reportsAppointmentTypeComboBox.setVisible(true);
        ObservableList<Appointments> appointmentsList = AppointmentsQuery.getAppointments();
        ObservableList<String> appointmentTypes = FXCollections.observableArrayList();
        for (Appointments i : appointmentsList) {
            String type = i.getType();
            if (!appointmentTypes.contains(type)) appointmentTypes.add(type);
        }
        reportsAppointmentTypeComboBox.setItems(appointmentTypes);
    }

    /**
     * This method fills the table in Appointments By Type with appointments with the selected type
     * @throws SQLException if SQL error occurs
     */
    public void getAppointmentsByType() throws SQLException {
        int totalCount = 0;
        Appointments selectedAppointment;
        String selectedType = reportsAppointmentTypeComboBox.getSelectionModel().getSelectedItem();
        if (selectedType != null) {
            ObservableList<Appointments> allAppointmentData = AppointmentsQuery.getAppointments();
            ObservableList<Appointments> appointmentDataForSelectedType = FXCollections.observableArrayList();
            for (Appointments i : allAppointmentData) {
                if (selectedType.equalsIgnoreCase(i.getType())) {
                    selectedAppointment = i;
                    appointmentDataForSelectedType.add(selectedAppointment);
                    totalCount++;
                }
            }
            reportsTypeTableView.setItems(appointmentDataForSelectedType);
        }
        reportsTypeTotalCountLabel.setText("Total Count: " + totalCount);
    }

    /**
     * This method fills the table in Past Appointments with past appointments
     * @throws SQLException if SQL error occurs
     */
    public void getPastAppointments() throws SQLException{
        int totalCount = 0;
        Appointments selectedAppointment;
        LocalDateTime currentTime = LocalDateTime.now();
        ObservableList<Appointments> allAppointmentData = AppointmentsQuery.getAppointments();
        ObservableList<Appointments> appointmentDataForPastAppointments = FXCollections.observableArrayList();
        for (Appointments i : allAppointmentData) {
            if(i.getEnd().isBefore(currentTime)){
                selectedAppointment = i;
                appointmentDataForPastAppointments.add(selectedAppointment);
                totalCount++;
            }
        }
        reportsPastTableView.setItems(appointmentDataForPastAppointments);
        reportsPastTotalCountLabel.setText("Total Count: " + totalCount);
    }

    /**
     * This method initializes tables in the Reports tab
     * @throws SQLException if SQL error occurs
     */
    public void initializeReportsTab() throws SQLException {
        fillTypeComboBox();
        getPastAppointments();
        generalUseLabel.setText("Not being used.");
        generalUseLabel.setVisible(false);
        reportsAppointmentTypeComboBox.setEditable(true);
        reportsAppointmentTypeComboBox.getEditor().setEditable(false);
        reportsAppointmentMonthComboBox.setEditable(true);
        reportsAppointmentMonthComboBox.getEditor().setEditable(false);
        reportsAppointmentContactComboBox.setEditable(true);
        reportsAppointmentContactComboBox.getEditor().setEditable(false);

        typeAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        typeTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        typeContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        typeLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        typeEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        reportsmonthAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        reportsmonthTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportsmonthCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        reportsmonthContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        reportsmonthDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportsmonthLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        reportsmonthTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportsmonthStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        reportsmonthEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        contactAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        contactTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        contactCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        contactContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        contactDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        contactEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        pastAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        pastTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        pastCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        pastContactColumn.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        pastDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        pastLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        pastTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        pastStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        pastEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentUserLabel.setText("Current User: test");
        try {
            checkForUpcomingAppointment();
            initializeCustomersTab();
            initializeAppointmentsTab();
            initializeReportsTab();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
