package model;

import java.time.LocalDateTime;

public class Appointments {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * @param appointmentID the appointment ID
     * @param title         the appointment title
     * @param description   the appointment description
     * @param location      the appointment description
     * @param type          the appointment type
     * @param start         the appointment start time
     * @param end           the appointment end time
     * @param customerID    the customer id
     * @param userID        the user id
     * @param contactID     the contact id
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * @return the appointment ID is returned
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * @return the appointment title is returned
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the appointment description is returned
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the appointment location is returned
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the appointment type is returned
     */
    public String getType() {
        return type;
    }

    /**
     * @return the appointment start time is returned
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @return the appointment end time is returned
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @return the customer ID is returned
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @return the user id is returned
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @return the contact id is returned
     */
    public int getContactID() {
        return contactID;
    }
}
