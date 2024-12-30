package Model;

import java.time.OffsetDateTime;

/**
 * Countries Model
 */
public class Countries {
    private int countryID;
    private String countryName;
    private OffsetDateTime createDate;
    private String createdBy;
    private OffsetDateTime lastUpdate;
    private String lastUpdatedBy;

    /**
     * Countries Constructor
     * @param countryID countryID
     * @param countryName countryName
     * @param createDate createDate
     * @param createdBy createdBy
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     */
    public Countries(int countryID, String countryName, OffsetDateTime createDate, String createdBy, OffsetDateTime lastUpdate, String lastUpdatedBy) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Countries Constructor
     * @param countryID countryID
     * @param countryName countryName
     */
    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * Function to get countryID
     * @return int
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Function to set countryID
     * @param countryID countryID
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Function to get countryName
     * @return String
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Function to set countryName
     * @param countryName countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Function to get createDate
     * @return OffsetDateTime
     */
    public OffsetDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Function to set createDate
     * @param createDate createDate
     */
    public void setCreateDate(OffsetDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Function to get createdBy
     * @return String
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Function to set createdBy
     * @param createdBy createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Function to get lastUpdate
     * @return OffsetDateTime
     */
    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Function to set lastUpdate
     * @param lastUpdate lastUpdate
     */
    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Function to get lastUpdatedBy
     * @return String
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Function to set lastUpdatedBy
     * @param lastUpdatedBy lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Function to return string version of model
     * @return String
     */
    @Override
    public String toString() {
        return countryName;
    }
}
