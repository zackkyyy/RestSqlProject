package se.experis.Task17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.experis.Task17.model.Person;

import javax.validation.constraints.Null;
import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
public class Task17Application {
	private static String databaseURL = "jdbc:sqlite::resource:task17DB.db";
	private static Connection conn = null ;
	public static ArrayList<Person> people = new ArrayList<Person>();

	public static void main(String[] args) {
		conn = connect();
		people = getAllPersons();
		SpringApplication.run(Task17Application.class, args);
	}

	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(databaseURL);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("SQLExeption");
		}finally {
			try {
				if (conn != null)
				conn.close();
			}
			catch (Exception e){
				System.out.println("Something went wrong.");
				System.out.println(e.toString());
			}
		}
		return conn;
	}


	public static ArrayList<Person> getAllPersons() {
		String sql = "SELECT ID, FirstName, LastName , DateOfBirth , AddressID FROM Person";
		Connection conn = null;
		try {
			conn = connect();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("FirstName");
				String LName = rs.getString("LastName");
				String birthdate = rs.getString("DateOfBirth");
				Person perosn = new Person(name, LName, birthdate);
				perosn.setPersonID(id);
				perosn.setAddressID(rs.getInt("AddressID"));
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
}
