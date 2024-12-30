package Model;

import java.time.OffsetDateTime;

/**
 * Customers Model
 */
public class Customers {
    private FirstLevelDivisions firstLevelDivision;
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private OffsetDateTime createDate;
    private String createdBy;
    private OffsetDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;

    /**
     * Customers Constructor
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     */
    public Customers(String customerName, String address, String postalCode, String phone, OffsetDateTime createDate, String createdBy, OffsetDateTime lastUpdate, String lastUpdatedBy, int divisionId) {
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }

    /**
     * Customers Constructor
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
    }

    /**
     * Customers Constructor
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     */
    public Customers(int customerId, String customerName, String address, String postalCode, String phone, OffsetDateTime lastUpdate, String lastUpdatedBy, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }

    /**
     * Function to get customerId
     * @return int
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Function to set customerId
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Function to get customerName
     * @return String
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Function to set customerName
     * @param customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Function to get address
     * @return String
     */
    public String getAddress() {
        return address;
    }

    /**
     * Function to set address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Function to get postalCode
     * @return String
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Function to set postalCode
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Function to get phone
     * @return String
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Function to set phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * Function to get lastUpdateBy
     * @return String
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Function to set lastUpdateBy
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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
     * Function to get firstLevelDivision
     * @return FirstLevelDivisions
     */
    public FirstLevelDivisions getFirstLevelDivision() {
        return firstLevelDivision;
    }

    /**
     * Function to set firstLevelDivision
     * @param firstLevelDivision
     */
    public void setFirstLevelDivision(FirstLevelDivisions firstLevelDivision) {
        this.firstLevelDivision = firstLevelDivision;
    }

    /**
     * Function to return string version of model
     * @return String
     */
    @Override
    public String toString() {
        return "Customers{" + "firstLevelDivision=" + firstLevelDivision + ", customerId=" + customerId + ", customerName='" + customerName + '\'' + ", address='" + address + '\'' + ", postalCode='" + postalCode + '\'' + ", phone='" + phone + '\'' + ", createDate=" + createDate + ", createdBy='" + createdBy + '\'' + ", lastUpdate=" + lastUpdate + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + ", divisionId=" + divisionId + '}';
    }
}
