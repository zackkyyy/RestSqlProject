package se.experis.Task17.model;

public class Email {

    int emailID;
    int personID;
    String workEmail;
    String personalEmail;

    public Email(int emailID, int personID, String workEmail, String personalEmail) {
        this.emailID = emailID;
        this.personID = personID;
        this.workEmail = workEmail;
        this.personalEmail = personalEmail;
    }

    public Email(String workEmail, String personalEmail) {
        this.workEmail = workEmail;
        this.personalEmail = personalEmail;
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

    public String getWorkEmail() {
        return workEmail;
    }

    public void setWorkEmail(String workEmail) {
        this.workEmail = workEmail;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }
}
