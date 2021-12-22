package model;

public class Users {
    private int userID;
    private String username;
    private String password;

    /**
     * @param userID   the user ID
     * @param username the username
     * @param password the password
     */
    public Users(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    /**
     * @return the user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}
