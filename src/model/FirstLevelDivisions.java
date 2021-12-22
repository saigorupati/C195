package model;

public class FirstLevelDivisions {
    private int divisionID;
    private String division;
    private int countryID;

    /**
     * @param divisionID the division ID
     * @param division   the division
     * @param countryID  the country ID
     */
    public FirstLevelDivisions(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * @return the division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the country ID
     */
    public int getCountryID() {
        return countryID;
    }
}
