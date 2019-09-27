package se.experis.Task17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.experis.Task17.controller.DbHandler;
import se.experis.Task17.model.*;

import java.util.ArrayList;

@SpringBootApplication
public class Task17Application {

	public static ArrayList<Person> people = new ArrayList<Person>();
	public static ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
	public static ArrayList<Email> emails = new ArrayList<Email>();
	public static ArrayList<Address> addresses = new ArrayList<Address>();
	public static ArrayList<Relationship> relationships = new ArrayList<Relationship>();

	private static DbHandler dbHandler = new DbHandler();

	public static void main(String[] args) {
		people = dbHandler.getAllPersons();
		phoneNumbers = dbHandler.getAllPhoneNumbers();
		emails = dbHandler.getALLEmails();
		addresses = dbHandler.getALLAddress();
		relationships = dbHandler.getAllRelations();
		SpringApplication.run(Task17Application.class, args);
	}
}
