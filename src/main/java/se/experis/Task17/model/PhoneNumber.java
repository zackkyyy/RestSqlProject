package se.experis.Task17.model;



public class PhoneNumber {
int phoneID;
int personID;
String phoneNumber ;
String PhoneNumberType;

    public PhoneNumber(){

    }

    public PhoneNumber(int phoneID, int personID, String phoneNumber, String phoneNumberType) {
        this.phoneID = phoneID;
        this.personID = personID;
        this.phoneNumber = phoneNumber;
        PhoneNumberType = phoneNumberType;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberType() {
        return PhoneNumberType;
    }

    public void setPhoneNumberType(String phoneNumberType) {
        PhoneNumberType = phoneNumberType;
    }
}
