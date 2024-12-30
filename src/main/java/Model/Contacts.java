package Model;

/**
 * Contacts Model
 */
public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

    /**
     * Contacts Constructor
     * @param contactID
     * @param contactName
     * @param email
     */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Function to get contactID
     * @return int
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Function to set contactID
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Function to get contactName
     * @return String
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Function to set contactName
     * @param contactName
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * Function to get email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Function to set email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Function to return string version of model
     * @return String
     */
    @Override
    public String toString() {
        return contactID + ": " + contactName + " - " + email;
    }
}
