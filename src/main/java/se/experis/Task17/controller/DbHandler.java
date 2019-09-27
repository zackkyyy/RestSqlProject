package se.experis.Task17.controller;

import se.experis.Task17.Task17Application;
import se.experis.Task17.model.*;

import java.sql.*;
import java.util.ArrayList;


public class DbHandler {
    public ArrayList<Person> people = new ArrayList<Person>();
    private String databaseURL = "jdbc:sqlite::resource:task17DB.db";
    private Connection conn = null;

    public Connection connect() {
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
//
//
//        final String sqlPersonTable = "CREATE TABLE IF NOT EXISTS \"Person\" (\n" +
//                "\t\"ID\"\tSERIAL NOT NULL PRIMARY KEY UNIQUE,\n" +
//                "\t\"FirstName\"\tvarchar(50) NOT NULL,\n" +
//                "\t\"LastName\"\tvarchar(50) NOT NULL,\n" +
//                "\t\"DateOfBirth\" varchar(50) NOT NULL,\n" +
//                "\t\"AddressID\"\tINTEGER NOT NULL,\n" +
//                "\tFOREIGN KEY(\"AddressID\") REFERENCES \"Address\"(\"ID\")\n" +
//                ")";
//
//
//        final String sqlEmailTable =   "CREATE TABLE IF NOT EXISTS \"Email\" (\n" +
//                "\t\"ID\"\tSERIAL NOT NULL PRIMARY KEY  UNIQUE,\n" +
//                "\t\"PersonID\"\tINTEGER NOT NULL,\n" +
//                "\t\"Work\"\tvarchar(50) NOT NULL UNIQUE,\n" +
//                "\t\"Personal\"\tvarchar(50) NOT NULL UNIQUE,\n" +
//                "\tFOREIGN KEY(\"PersonID\") REFERENCES \"Person\"(\"ID\")\n" +
//                ")";
//
//
//
//
//        final String sqlContactTable = "CREATE TABLE IF NOT EXISTS \"Phonenumber\" (\n" +
//                "\t\"ID\"\tSERIAL NOT NULL PRIMARY KEY  UNIQUE,\n" +
//                "\t\"PersonID\"\tINTEGER NOT NULL,\n" +
//                "\t\"Personal\"\tvarchar(50) NOT NULL UNIQUE,\n" +
//                "\t\"Work\"\tvarchar(50) NOT NULL UNIQUE,\n" +
//                "\tFOREIGN KEY(\"PersonID\") REFERENCES \"Person\"(\"ID\")\n" +
//                ")";
//
//
//
//        final String sqlAddressTable = "CREATE TABLE IF NOT EXISTS \"Address\" (\n" +
//                "\t\"addressId\"\tSERIAL NOT NULL PRIMARY KEY UNIQUE,\n" +
//                "\t\"city\"\tvarchar(50) NOT NULL,\n" +
//                "\t\"street\"\tvarchar(50) NOT NULL,\n" +
//                "\t\"postalCode\"\tvarchar(50)\n" +
//                ")";
//
//
//
//        final String sqlRelationTable =  "CREATE TABLE IF NOT EXISTS \"Relationship\" (\n" +
//                "\t\"relationshipId\"\tSERIAL NOT NULL UNIQUE,\n" +
//                "\t\"PersonID\"\tINTEGER NOT NULL,\n" +
//                "\t\"Person2ID\"\tINTEGER NOT NULL,\n" +
//                "\t\"RelationshipType\"\tvarchar(50) NOT NULL,\n" +
//                "\tPRIMARY KEY(\"PersonID\",\"Person2ID\"),\n" +
//                "\tFOREIGN KEY(\"PersonID\") REFERENCES \"Person\"(\"ID\"),\n" +
//                "\tFOREIGN KEY(\"Person2ID\") REFERENCES \"Person\"(\"ID\")\n" +
//                ")";
//
//        try {
//           conn = connect();
//            Statement stmt  = conn.createStatement();
//            stmt.addBatch(sqlAddressTable);
//            stmt.addBatch(sqlPersonTable);
//            stmt.addBatch(sqlEmailTable);
//            stmt.addBatch(sqlContactTable);
//            stmt.addBatch(sqlRelationTable);
//            stmt.executeBatch();
//            System.out.println("Created");
//        } catch (SQLException e) {
//            System.out.println("there is error ");
//            System.out.println(e.getMessage());
//        }
//    }

    /**
     * retunrs the phone row from phones table associated to one person
     *
     * @param id the person id
     * @return phoneNumber object that represent the row in the database
     */
    public PhoneNumber getPersonsPhoneNumbers(int id) {
        ArrayList<PhoneNumber> phones = getAllPhoneNumbers();
        PhoneNumber returnedPhone = new PhoneNumber();
        for (PhoneNumber phNr : phones) {
            if (phNr.getPersonID() == id) {
                returnedPhone = phNr;
            }
        }
        return returnedPhone;
    }

    /**
     * retunrs the address row associated to one person
     *
     * @param id the person id
     * @return Address object that represent the row in the database
     */
    public Address getPersonsAddresses(int id) {
        ArrayList<Address> addresses = getALLAddress();
        Address returnedAddress = null;
        for (Address address : addresses) {
            if (address.getID() == id) {
                returnedAddress = address;
            }
        }
        return returnedAddress;
    }

    /**
     * retunrs the email row associated to one person
     *
     * @param id the person id
     * @return Email object that represent the row in the database
     */
    public Email getPersonsEmails(int id) {
        ArrayList<Email> emails = getALLEmails();
        Email returnedEmail = null;
        ArrayList<Email> returnedList = new ArrayList<>();
        for (Email email : emails) {

            if (email.getPersonID() == id) {
                returnedEmail = email;
            }
        }
        return returnedEmail;
    }

    /**
     * Method returns list of all people in the database
     *
     * @return Arraylist of all people
     */
    public ArrayList<Person> getAllPersons() {
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
                Person perosn = new Person(name, LName, birthdate, id, rs.getInt("AddressID"));
                perosn.setPhoneNumber(getPersonsPhoneNumbers(id));
                perosn.setRelationship(getPersonsRelation(id));
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

    public ArrayList<PhoneNumber> getAllPhoneNumbers() {
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
                PhoneNumber phoneNumber = new PhoneNumber(id, personID, workNr, personalNr);
                phones.add(phoneNumber);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return phones;
    }

    /**
     * return all addresses saved in the database
     *
     * @return ArrayList of addresses
     */
    public ArrayList<Address> getALLAddress() {
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
                Address address = new Address(id, street, city, country, postalCode);
                addresses.add(address);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return addresses;
    }

    /**
     * returns array list of all emails in the database
     *
     * @return list of emails
     */
    public ArrayList<Email> getALLEmails() {
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
                Email email = new Email(id, personID, workEm, personalEm);
                emails.add(email);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return emails;
    }


    public Person addPerson(Person person, Address address, Email email, PhoneNumber phones) throws SQLException {
        int tempAddressId = getLastIDAdded("address");
        String sql = "INSERT INTO Person(FirstName, LastName, DateOfBirth, AddressID) VALUES(?,?,?,?)";
        conn = connect();
        ArrayList<Address> existsAddresses = getALLAddress();
        PreparedStatement pstmt1 = conn.prepareStatement(sql);
        int addressIDexist =0 ;
        boolean exist = false;
        for (int i = 0; i <existsAddresses.size() ; i++) {
            if(existsAddresses.get(i).toString().equals(address.toString())){
                addressIDexist = existsAddresses.get(i).getID();
                pstmt1.setInt(4, addressIDexist);
                exist= true;
            }
        }
        if(!exist){
            createAddress(address);
            pstmt1.setInt(4, tempAddressId);
        }


        pstmt1.setString(1, person.getFirstName());
        pstmt1.setString(2, person.getLastName());
        pstmt1.setString(3, person.getBirthDate());
        pstmt1.execute();
        person.setAddressID(tempAddressId);
        int thisPersonID = getLastIDAdded("person");
        createEmail(email.getWorkEmail(), email.getPersonalEmail(), thisPersonID);
        createPhoneNumbers(phones.getWorkNumber(), phones.getPersonalNumber(), thisPersonID);
        return getAllPersons().get(getAllPersons().size() - 1);
    }

    /**
     * This method takes the address provided by the user input and create a row in the Address
     * table in the database and associate it with the corresponding person that lives in the address
     *
     * @param address from user inputs
     * @throws SQLException
     */
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
    public void createPhoneNumbers(String work, String personal, int id) throws SQLException {
        Connection con = connect();
        String insertSql = "INSERT INTO Phonenumber( Work, Personal , PersonID ) VALUES(?,?,?)";
        PreparedStatement pstmt1 = con.prepareStatement(insertSql);
        pstmt1.setString(1, work);
        pstmt1.setString(2, personal);
        pstmt1.setInt(3, id);
        pstmt1.execute();
        System.out.println("Contact information Created");
    }

    /**
     * Method that deletes a person and all his information from all tables in the DB
     *
     * @param p the person to be deleted
     */
    public void deletePerson(Person p){

        Address address = getPersonsAddresses(p.getPersonID());
        int addressID = address.getID();
        String sql1 = "DELETE FROM Person WHERE ID = ?; ";
        String sql2 = "DELETE FROM Email WHERE PersonID = ?; ";
        String sql3 = "DELETE FROM Address WHERE ID = ?; ";
        String sql4 = "DELETE FROM Phonenumber WHERE PersonID = ?; ";
        String sql5 = "DELETE FROM Relationship WHERE PersonID =?; ";
        String sql6 = "SELECT COUNT(*) FROM Person WHERE AddressID=?";
        Connection connect = connect();
        try {
            connect.setAutoCommit(false);
            PreparedStatement pstmt = connect.prepareStatement(sql1);
            PreparedStatement pstmt2 = connect.prepareStatement(sql2);
            PreparedStatement pstmt3 = connect.prepareStatement(sql3);
            PreparedStatement pstmt4 = connect.prepareStatement(sql4);
            PreparedStatement pstmt5 = connect.prepareStatement(sql5);
            PreparedStatement pstmt6 = connect.prepareStatement(sql6);

            pstmt.setInt(1, p.getPersonID());
            pstmt2.setInt(1, p.getPersonID());
            pstmt3.setInt(1, addressID);
            pstmt4.setInt(1, p.getPersonID());
            pstmt5.setInt(1, p.getPersonID());
            pstmt.executeUpdate();
            pstmt2.executeUpdate();
            try (ResultSet rs = pstmt6.executeQuery()) {
                int counter = 0;
                while(rs.next()){
                    counter++;
                }
                if (counter == 1) {
                    pstmt3.executeUpdate();
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
            pstmt4.executeUpdate();
            pstmt5.executeUpdate();
            connect.commit();
            System.err.println("Person is deleted");
        } catch (SQLException r) {
            System.out.println(r.getMessage());
        }
    }

    /**
     * Method returns last ID added to the table
     *
     * @param str represent the name of the table
     * @return Integer represent the last ID
     * @throws SQLException
     */
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
            lastID = arr.get(arr.size() - 1);
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

    /**
     * This method is called to update information in the database
     *
     * @return the updated version in JSON object form
     * @throws SQLException
     */
    public Person updatePerson(Person person, Address address, Email email, PhoneNumber phoneNumber) throws SQLException {
        //Logically speaking the DOB should not be changeable. Yes we assume it is correct from start!

        String sql = "SELECT ID, AddressID FROM Person WHERE DateOfBirth = '" + person.getBirthDate() + "'";
        String sql1 = "UPDATE Person SET FirstName=?, LastName=? WHERE ID=?";
        String sql2 = "UPDATE Email SET Personal = ?, Work = ? WHERE PersonID = ?";
        String sql3 = "UPDATE Phonenumber SET Personal = ?, Work = ? WHERE PersonID = ?";
        String sql4 = "UPDATE Address SET Street = ?, PostalCode = ?, City = ?, Country = ? WHERE ID = ?";

        conn = connect();

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int personID = rs.getInt("ID");
        int addressID = rs.getInt("AddressID");

        // update personal information
        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
        pstmt1.setString(1, person.getFirstName());
        pstmt1.setString(2, person.getLastName());
        pstmt1.setInt(3, personID);
        pstmt1.execute();

        //update the email addresses
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        pstmt2.setString(1, email.getPersonalEmail());
        pstmt2.setString(2, email.getWorkEmail());
        pstmt2.setInt(3, personID);
        pstmt2.execute();

        //update the phone numbers
        PreparedStatement pstmt4 = conn.prepareStatement(sql3);
        pstmt4.setString(1, phoneNumber.getPersonalNumber());
        pstmt4.setString(2, phoneNumber.getWorkNumber());
        pstmt4.setInt(3, personID);
        pstmt4.execute();

        // update the address
        PreparedStatement pstmt5 = conn.prepareStatement(sql4);
        pstmt5.setString(1, address.getStreet());
        pstmt5.setString(2, address.getPostalCode());
        pstmt5.setString(3, address.getCity());
        pstmt5.setString(4, address.getCountry());
        pstmt5.setInt(5, addressID);
        pstmt5.execute();


        return getAllPersons().get(personID - 1); //cause array
    }

    /**
     *  Method that returns the relations associated to a person
     *
     * @param id integer represent the id of the person
     *
     * @return Relationship object represent the row
     */
    private Relationship getPersonsRelation(int id) {
        Relationship returnedRelation =null;
        boolean found =false;
        ArrayList<Relationship> relationships = getAllRelations();
       for (Relationship relationship : relationships){
           if(relationship.getPersonID() == id ){
               returnedRelation = relationship;
               found=true;
           }
       }
       if(!found){
           //System.out.println("there are no relations for this person ");
       }
       return returnedRelation;
    }

    /**
     *  Method that returns all relations
     *
     * @return list of all relations
     */
    public ArrayList<Relationship> getAllRelations() {
        String sql = "SELECT * FROM Relationship ;";
        Relationship relationship = null;
        ArrayList<Relationship> relations = new ArrayList<>();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                relationship = new Relationship(rs.getInt("PersonID"),rs.getInt("Person2ID"),rs.getString("RelationshipType"));
                relations.add(relationship);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return relations;
    }

}
