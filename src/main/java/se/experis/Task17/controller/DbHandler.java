package se.experis.Task17.controller;

import se.experis.Task17.model.Address;
import se.experis.Task17.model.Email;
import se.experis.Task17.model.Person;
import se.experis.Task17.model.PhoneNumber;

import java.sql.*;
import java.util.ArrayList;

/**
 * Author : Zacky Kharboutli
 * Date : 2019-09-26
 * Project: Task17
 */

public class DbHandler {
    private  String databaseURL = "jdbc:sqlite::resource:task17DB.db";
    private  Connection conn = null ;
    public   ArrayList<Person> people = new ArrayList<Person>();
    public   ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public  ArrayList<PhoneNumber> getPersonsPhoneNumbers(int id ){
        ArrayList<PhoneNumber> phones = getAllPhoneNumbers();
        ArrayList<PhoneNumber> returnedList = new ArrayList<>();
        for (PhoneNumber phNr : phones){
            if(phNr.getPersonID() == id){
                returnedList.add(phNr);
            }
        }
        return returnedList;
    }

    public  Address getPersonsAddresses(int id ){
        ArrayList<Address> addresses = getALLAddress();
        Address returnedAddress = null;
        for (Address address : addresses){
            if(address.getID() == id){
                returnedAddress = address;
            }
        }
        return returnedAddress;
    }

    public  ArrayList<Email> getPersonsEmails(int id ){
        ArrayList<Email> emails = getALLEmails();
        System.out.println(emails);
        ArrayList<Email> returnedList = new ArrayList<>();
        for (Email email : emails){

            if(email.getPersonID() == id){
                returnedList.add(email);
            }
        }
        return returnedList;
    }
    public  ArrayList<Person> getAllPersons() {
        String sql = "SELECT ID, FirstName, LastName , DateOfBirth , AddressID FROM Person";
        try {
            conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("FirstName");
                String LName = rs.getString("LastName");
                String birthdate = rs.getString("DateOfBirth");
                Person perosn = new Person(name, LName, birthdate , id, rs.getInt("AddressID"));
                perosn.setPhoneNumber(getPersonsPhoneNumbers(id));
                perosn.setEmails(getPersonsEmails(id));
                perosn.setAddress(getPersonsAddresses(rs.getInt("AddressID")));
                people.add(perosn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    ;
                }
            }
        }
        return people;
    }


    public  ArrayList<PhoneNumber> getAllPhoneNumbers(){
        ArrayList<PhoneNumber> phones = new ArrayList<>();
        String sql = "SELECT ID , Personal , Work  , PersonID FROM Phonenumber";
        try {
            conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String workNr = rs.getString("Work");
                String personalNr = rs.getString("Personal");
                int personID = rs.getInt("PersonID");
                PhoneNumber phoneNumber = new PhoneNumber(id , personID, workNr , personalNr);
                phones.add(phoneNumber);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return phones;
    }
    public  ArrayList<Address> getALLAddress(){
        ArrayList<Address> addresses = new ArrayList<>();
        String sql = "SELECT ID , Street , City  , PostalCode, Country FROM Address";
        try {
            conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String street = rs.getString("Street");
                String city = rs.getString("City");
                String postalCode = rs.getString("PostalCode");
                String country = rs.getString("Country");
                Address address = new Address(id,street, city ,country,postalCode);
                addresses.add(address);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return addresses;
    }
    public  ArrayList<Email> getALLEmails(){
        ArrayList<Email> emails = new ArrayList<>();
        String sql = "SELECT ID , Personal , Work  , PersonID FROM Email";
        try {
            conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String workEm = rs.getString("Work");
                String personalEm = rs.getString("Personal");
                int personID = rs.getInt("PersonID");
                Email email = new Email(id , personID, workEm , personalEm);
                emails.add(email);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return emails;
    }



    public  Person addPerson(Person person) {
        //person.setPersonID();
        String sql = "INSERT INTO Person(FirstName, LastName, DateOfBirth, AddressID) VALUES(?,?,?,?)";
        Connection conn = null;
        try {
            conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person.getFirstName());
            pstmt.setString(2, person.getLastName());
            pstmt.setString(3, person.getBirthDate());
            pstmt.setInt(4, person.getAddressID());
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(person);
        return person;
    }
}
