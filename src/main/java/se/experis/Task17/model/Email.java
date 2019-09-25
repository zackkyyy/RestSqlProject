package se.experis.Task17.model;

public class Email {

    int emailID;
    int personID;
    String emailAddress;
    String emailType;

    public Email(int emailID, int personID, String emailAddress, String emailType) {
        this.emailID = emailID;
        this.personID = personID;
        this.emailAddress = emailAddress;
        this.emailType = emailType;
    }

    public int getEmailID() {
        return emailID;
    }

    public void setEmailID(int emailID) {
        this.emailID = emailID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

}
