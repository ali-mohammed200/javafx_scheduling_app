package Model;

import java.time.OffsetDateTime;

/**
 * Users Model
 */
public class Users {
    private int userID;
    private String userName;
    private String password;
    private OffsetDateTime createDate;
    private String createdBy;
    private OffsetDateTime lastUpdate;
    private String lastUpdatedBy;

    /**
     * Users Constructor
     * @param userID userID
     * @param userName userName
     * @param password password
     * @param createDate createDate
     * @param createdBy createdBy
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     */
    public Users(int userID, String userName, String password, OffsetDateTime createDate, String createdBy, OffsetDateTime lastUpdate, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Users Constructor
     * @param userID userID
     * @param userName userName
     */
    public Users(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     * Function to get userID
     * @return int
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Function to set userID
     * @param userID userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Function to get userName
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Function to set userName
     * @param userName userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Function to get password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Function to set password
     * @param password password
     */
    public void setPassword(String password) {
        this.password = password;
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
}
