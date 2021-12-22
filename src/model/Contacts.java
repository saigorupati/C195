package model;

public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * @param contactID   the contact ID
     * @param contactName the contact name
     * @param email       the contact email address
     */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * @return the contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * @return the contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @return the contact email address
     */
    public String getEmail() {
        return email;
    }

}
