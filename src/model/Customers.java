package model;

public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;

    /**
     * @param customerID   the customer ID
     * @param customerName the customer name
     * @param address      the customer address
     * @param postalCode   the customer postal code
     * @param phone        the customer phone number
     * @param divisionID   the division ID
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
    }

    /**
     * @return the customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @return the customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @return the customer phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

}
