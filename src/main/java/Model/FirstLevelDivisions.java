package Model;

import java.time.OffsetDateTime;

/**
 * FirstLevelDivisions Model
 */
public class FirstLevelDivisions {
    private Countries country;
    private int divisionId;
    private String divisionName;
    private OffsetDateTime createDate;
    private String createdBy;
    private OffsetDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryId;

    /**
     * FirstLevelDivisions Constructor
     * @param divisionId
     * @param divisionName
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param countryId
     */
    public FirstLevelDivisions(int divisionId, String divisionName, OffsetDateTime createDate, String createdBy, OffsetDateTime lastUpdate, String lastUpdatedBy, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryId = countryId;
    }

    /**
     * FirstLevelDivisions Constructor
     * @param divisionId
     * @param divisionName
     * @param countryId
     */
    public FirstLevelDivisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Function to get divisionId
     * @return int
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Function to set divisionId
     * @param divisionId
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Function to get divisionName
     * @return String
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Function to set divisionName
     * @param divisionName
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
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
     * @param createDate
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
     * @param createdBy
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
     * @param lastUpdate
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
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Function to get countryId
     * @return int
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Function to set countryId
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Function to get country
     * @return Countries
     */
    public Countries getCountry() {
        return country;
    }

    /**
     * Function to set country
     * @param country
     */
    public void setCountry(Countries country) {
        this.country = country;
    }

    /**
     * Function to return string version of model
     * @return String
     */
    @Override
    public String toString() {
        return divisionName;
    }
}
