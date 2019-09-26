package se.experis.Task17.model;



public class PhoneNumber {
int phoneID;
int personID;
String workNumber ;
String personalNumber;

    public PhoneNumber(){

    }

    public PhoneNumber(int phoneID, int personID, String workNumber, String personalNumber) {
        this.personalNumber= personalNumber;
        this.workNumber=workNumber;
        this.phoneID = phoneID;
        this.personID = personID;

    }

    public PhoneNumber(String phoneWork, String phonePersonal) {
        this.workNumber = phoneWork;
        this.personalNumber = phonePersonal;
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
