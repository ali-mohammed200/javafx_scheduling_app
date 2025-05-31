package Model;

import java.time.OffsetDateTime;

public class PremiumCustomers extends Customers {
    /**
     * PremiumCustomers Constructor
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
    public PremiumCustomers(String customerName, String address, String postalCode, String phone, OffsetDateTime createDate, String createdBy, OffsetDateTime lastUpdate, String lastUpdatedBy, int divisionId) {
        super(customerName, address, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
    }

    /**
     * PremiumCustomers Constructor
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     */
    public PremiumCustomers(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) {
        super(customerId, customerName, address, postalCode, phone, divisionId);
    }

    /**
     * PremiumCustomers Constructor
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param divisionId
     */
    public PremiumCustomers(int customerId, String customerName, String address, String postalCode, String phone, OffsetDateTime lastUpdate, String lastUpdatedBy, int divisionId) {
        super(customerId, customerName, address, postalCode, phone, lastUpdate, lastUpdatedBy, divisionId);
    }

    @Override
    public String getPreferredGreeting(){
        return "Greetings, old chap";
    }

    @Override
    public String toString() {
        return "This is a premium customer - Customers{" + "firstLevelDivision=" + firstLevelDivision + ", customerId=" + customerId + ", customerName='" + customerName + '\'' + ", address='" + address + '\'' + ", postalCode='" + postalCode + '\'' + ", phone='" + phone + '\'' + ", createDate=" + createDate + ", createdBy='" + createdBy + '\'' + ", lastUpdate=" + lastUpdate + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + ", divisionId=" + divisionId + '}';
    }
}
