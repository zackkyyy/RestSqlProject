package se.experis.Task17.model;



public class PhoneNumber {
int phoneID;
int personID;
String workNumber ;
String personalNumber;

    public PhoneNumber(){

    }

    public PhoneNumber(int phoneID, int personID, String workNumber, String personalNumber) {
        this.phoneID = phoneID;
        this.personID = personID;
        this.workNumber = workNumber;
        this.personalNumber = personalNumber;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public int getPersonID() {
        return personID;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }
}
