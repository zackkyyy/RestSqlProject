package se.experis.Task17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.experis.Task17.model.Address;
import se.experis.Task17.model.Email;
import se.experis.Task17.model.Person;
import se.experis.Task17.model.PhoneNumber;

import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
public class Task17Application {
	private static String databaseURL = "jdbc:sqlite::resource:task17DB.db";
	private static Connection conn = null ;
	public static ArrayList<Person> people = new ArrayList<Person>();
	public static ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();


	public static void main(String[] args) {
		conn = connect();
		people = getAllPersons();
		phoneNumbers= getAllPhoneNumbers();
		SpringApplication.run(Task17Application.class, args);

	}
	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(databaseURL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static ArrayList<PhoneNumber> getPersonsPhoneNumbers(int id ){
		ArrayList<PhoneNumber> phones = getAllPhoneNumbers();
		ArrayList<PhoneNumber> returnedList = new ArrayList<>();
		for (PhoneNumber phNr : phones){
			if(phNr.getPersonID() == id){
				returnedList.add(phNr);
			}
		}
		return returnedList;
	}

	public static Address getPersonsAddresses(int id ){
		ArrayList<Address> addresses = getALLAddress();
		Address returnedAddress = null;
		for (Address address : addresses){
			if(address.getID() == id){
				returnedAddress = address;
			}
		}
		return returnedAddress;
	}

	public static ArrayList<Email> getPersonsEmails(int id ){
		ArrayList<Email> emails = getALLEmails();
		ArrayList<Email> returnedList = new ArrayList<>();
		for (Email email : emails){

			if(email.getPersonID() == id){
				returnedList.add(email);
			}
		}
		return returnedList;
	}
	public static ArrayList<Person> getAllPersons() {
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

	public static ArrayList<PhoneNumber> getAllPhoneNumbers(){
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
	public static ArrayList<Address> getALLAddress(){
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
	public static ArrayList<Email> getALLEmails(){
		ArrayList<Email> emails = new ArrayList<>();
		String sql = "SELECT ID , Personal , Work  , PersonID FROM Phonenumber";
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
	
}
