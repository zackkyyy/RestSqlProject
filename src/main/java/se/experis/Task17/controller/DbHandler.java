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


    public  Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
//
//    public void createTables(){
//        final String sqlPersonTable = ""
//                + "CREATE TABLE IF NOT EXISTS Person "
//                + "(PersonID INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + "FirstName TEXT NOT NULL, "
//                + "LastName TEXT NOT NULL, "
//                + "FOREIGN KEY (AddressID) REFERENCES Address(ID) "
//                + "DateOfBirth TEXT NOT NULL);";
//
//        final String sqlEmailTable = ""
//                + "CREATE TABLE IF NOT EXISTS Email "
//                + "(PersonID INTEGER NOT NULL UNIQUE, "
//                + "Work TEXT, "
//                + "Personal TEXT, "
//                + "FOREIGN KEY (PersonID) REFERENCES Person(PersonID));";
//
//        final String sqlContactTable = ""
//                + "CREATE TABLE IF NOT EXISTS Phonenumber ( "
//                + "PersonID INTEGER NOT NULL UNIQUE, "
//                + "Personal TEXT, "
//                + "Work TEXT, "
//                + "FOREIGN KEY (PersonID) REFERENCES Person(PersonID));";
//
//        final String sqlAddressTable = ""
//                + "CREATE TABLE IF NOT EXISTS Address ("
//                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + "Street TEXT, "
//                + "PostalCode TEXT, "
//                + "City TEXT, "
//                + "Country TEXT);";
//
//        final String sqlRelationTable = "" +
//                "CREATE TABLE IF NOT EXISTS Relationship (" +
//                "PersonID INTEGER NOT NULL, " +
//                "Person2ID INTEGER NOT NULL, " +
//                "ID INTEGER NOT NULL, " +
//                "RelationshipType TEXT NOT NULL, " +
//                "PRIMARY KEY (personID, ID));";
//
//        try {
//           // Connection conn = getConn();
//            Statement stmt  = conn.createStatement();
//            stmt.addBatch(sqlPersonTable);
//            stmt.addBatch(sqlEmailTable);
//            stmt.addBatch(sqlContactTable);
//            stmt.addBatch(sqlAddressTable);
//            stmt.addBatch(sqlRelationTable);
//            stmt.executeBatch();
//        } catch (SQLException e) {
//        }
//    }


    public  PhoneNumber getPersonsPhoneNumbers(int id ){
        ArrayList<PhoneNumber> phones = getAllPhoneNumbers();
        PhoneNumber returnedPhone = new PhoneNumber();
        for (PhoneNumber phNr : phones){
            if(phNr.getPersonID() == id){
                returnedPhone = phNr;
            }
        }
        return returnedPhone;
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

    /**
     *
     * @param id
     * @return
     */
    public  Email getPersonsEmails(int id ){
        ArrayList<Email> emails = getALLEmails();
        Email returnedEmail = null;
        ArrayList<Email> returnedList = new ArrayList<>();
        for (Email email : emails){

            if(email.getPersonID() == id){
                    returnedEmail = email;
            }
        }
        return returnedEmail;
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
        }
        return people;
    }

    /**
     * Method returns all Phone numbers in the database
     *
     * @return array list contains the numbers
     */

    public  ArrayList<PhoneNumber> getAllPhoneNumbers(){
        ArrayList<PhoneNumber> phones = new ArrayList<>();
        String sql = "SELECT * FROM Phonenumber";
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

    /**
     *
     * @return
     */
    public  ArrayList<Address> getALLAddress(){
        ArrayList<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM Address";
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

    /**
     *
     * @return
     */
    public  ArrayList<Email> getALLEmails(){
        ArrayList<Email> emails = new ArrayList<>();
        String sql = "SELECT * FROM Email";
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



    public  Person addPerson(Person person , Address address, Email email , PhoneNumber phones) throws SQLException {

            int tempAddressId = getLastIDAdded("address");
            String sql = "INSERT INTO Person(FirstName, LastName, DateOfBirth, AddressID) VALUES(?,?,?,?)";
            conn = connect();
            createAddress(address);
            PreparedStatement pstmt1 = conn.prepareStatement(sql);
            pstmt1.setString(1, person.getFirstName());
            pstmt1.setString(2, person.getLastName());
            pstmt1.setString(3, person.getBirthDate());
            pstmt1.setInt(4, tempAddressId);
            pstmt1.execute();
            person.setAddressID(tempAddressId);
            int thisPersonID= getLastIDAdded("person");
            createEmail(email.getWorkEmail() , email.getPersonalEmail(), thisPersonID);
            createPhoneNumbers(phones.getWorkNumber() , phones.getPersonalNumber() , thisPersonID);
        return getAllPersons().get(getAllPersons().size()-1);
    }

    public void createAddress(Address address) throws SQLException {
        conn = this.connect();
        Statement stmt = conn.createStatement();
        String insertSql = "INSERT INTO Address(Street, City, PostalCode, Country) VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSql);
        pstmt.setString(1, address.getStreet());
        pstmt.setString(2, address.getCity());
        pstmt.setString(3, address.getPostalCode());
        pstmt.setString(4, address.getCountry());
        pstmt.execute();
    }
    /**
     * This method takes the emails provided by the user input and create a row in the Email
     * table in the database and associate it with the corresponding person that the email belongs to
     *
     * @param work     String provided by the user
     * @param personal String provided by the user
     * @throws SQLException (to be handled late in the process)
     */
    public void createEmail(String work, String personal, int id) throws SQLException {
        Connection con = connect();
        String insertSql = "INSERT INTO Email(Work, Personal, PersonID ) VALUES(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(insertSql);
        pstmt.setString(1, work);
        pstmt.setString(2, personal);
        pstmt.setInt(3, id);
        pstmt.execute();
        System.out.println("Email Created");
    }

    /**
     * This method takes the phone numbers provided by the user input and create a row in the PhoneNumbers table
     * in the database and associate it with the corresponding person that the numbers belong to
     *
     * @param work     String provided by the user
     * @param personal String provided by the user
     * @throws SQLException
     */
    public void createPhoneNumbers(String work, String personal, int id ) throws SQLException {
        Connection con = connect();
        System.out.println(work);
        System.out.println(personal);
        String insertSql = "INSERT INTO Phonenumber( Work, Personal , PersonID ) VALUES(?,?,?)";
        PreparedStatement pstmt1 = con.prepareStatement(insertSql);
        pstmt1.setString(1, work);
        pstmt1.setString(2, personal);
        pstmt1.setInt(3, id);
        pstmt1.execute();
        System.out.println("Contact information Created");
    }

    public Person deletePerson(Person p) throws SQLException {

        System.out.println(p.toString());
        Address address = getPersonsAddresses(p.getPersonID());
        System.out.println(address.toString());
        int addressID = address.getID();
        String sql1 = "DELETE FROM Person WHERE ID = ?; ";
        String sql2 = "DELETE FROM Email WHERE PersonID = ?; ";
        String sql3 = "DELETE FROM Address WHERE ID = ?; ";
        String sql4 = "DELETE FROM Phonenumber WHERE PersonID = ?; ";
        String sql5 = "DELETE FROM Relationship WHERE PersonID =?; ";
        Connection connect = connect();
        try{
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(sql1);
            PreparedStatement pstmt2 = connect.prepareStatement(sql2);
            PreparedStatement pstmt3 = connect.prepareStatement(sql3);
            PreparedStatement pstmt4 = connect.prepareStatement(sql4);
            PreparedStatement pstmt5 = connect.prepareStatement(sql5);

            pstmt.setInt(1, p.getPersonID());
            pstmt2.setInt(1, p.getPersonID());
            pstmt3.setInt(1, addressID);
            pstmt4.setInt(1, p.getPersonID());
            pstmt5.setInt(1, p.getPersonID());
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            pstmt3.executeUpdate();
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            connect.commit();
            System.err.println("Person is deleted");
        }
        catch (SQLException r){
            System.out.println(r.getMessage());
        }
        return p;
    }

    public int getLastIDAdded(String str) throws SQLException {

        String sql = (str.equals("address")) ? "SELECT ID FROM Address" : "SELECT ID FROM Person";

        int lastID = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                arr = new ArrayList<Integer>();
                arr.add(rs.getInt("ID"));
            }
            lastID = arr.get(arr.size()-1);
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
        return lastID;
    }
}
