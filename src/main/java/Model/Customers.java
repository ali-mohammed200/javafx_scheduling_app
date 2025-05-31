package Model;

import Controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Customers Model
 */
public class Customers {
    protected FirstLevelDivisions firstLevelDivision;
    protected int customerId;
    protected String customerName;
    protected String address;
    protected String postalCode;
    protected String phone;
    protected OffsetDateTime createDate;
    protected String createdBy;
    protected OffsetDateTime lastUpdate;
    protected String lastUpdatedBy;
    protected int divisionId;
    private static FilteredList<Customers> allCustomers = new FilteredList<>(FXCollections.observableList(new ArrayList<Customers>()));

    /**
     * Customers Constructor
     * @param customerName customerName
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param createDate createDate
     * @param createdBy createdBy
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     * @param divisionId divisionId
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
     * @param customerId customerId
     * @param customerName customerName
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param divisionId divisionId
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
     * @param customerId customerId
     * @param customerName customerName
     * @param address address
     * @param postalCode postalCode
     * @param phone phone
     * @param lastUpdate lastUpdate
     * @param lastUpdatedBy lastUpdatedBy
     * @param divisionId divisionId
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
     * Function to get all Customers
     * @return FilteredList Customers
     */
    public static FilteredList<Customers> getAllCustomers() {
        return allCustomers;
    }

    /**
     * Function to set Customers
     * @param customers Customers
     */
    public static void setAllCustomers(ObservableList<Customers> customers) {
        allCustomers = new FilteredList<>(customers);
    }

    /**
     * Function to filter the customer list
     * A lambda is used to take each customer in the list and filter to the
     * customer that match the criteria
     * @param cusId
     * @return
     */
    public static Predicate<Customers> getSearchCustomersByID(Integer cusId) {
        Predicate<Customers> found = i -> i.getCustomerId() == cusId;
        return found;
    }


    /**
     * Function to filter the customer list
     * A lambda is used to take each customer in the list and filter to the
     * customer that match the criteria
     * @param cusName
     * @return
     */
    public static Predicate<Customers> getSearchCustomersByName(String cusName) {
        Predicate<Customers> found = i -> i.getCustomerName().toLowerCase().contains(cusName.toLowerCase()) ;
        return found;
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
     * @param customerId customerId
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
     * @param customerName customerName
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
     * @param address address
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
     * @param postalCode postalCode
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
     * @param phone phone
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
     * Function to get lastUpdateBy
     * @return String
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Function to set lastUpdateBy
     * @param lastUpdatedBy lastUpdatedBy
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
     * @param divisionId divisionId
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
     * @param firstLevelDivision firstLevelDivision
     */
    public void setFirstLevelDivision(FirstLevelDivisions firstLevelDivision) {
        this.firstLevelDivision = firstLevelDivision;
    }

    /**
     * Function to get preferredGreeting
     * @return String
     */
    public String getPreferredGreeting(){
        return "What's up!";
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
