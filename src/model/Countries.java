package model;

public class Countries {
    private int countryID;
    private String country;

    /**
     * @param countryID the country ID
     * @param country   the country name
     */
    public Countries(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * @return the country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @return the country name
     */
    public String getCountry() {
        return country;
    }

}
