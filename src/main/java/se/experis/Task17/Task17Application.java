package se.experis.Task17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.experis.Task17.controller.DbHandler;
import se.experis.Task17.model.Person;
import se.experis.Task17.model.PhoneNumber;
import java.util.ArrayList;

@SpringBootApplication
public class Task17Application {

	public static ArrayList<Person> people = new ArrayList<Person>();
	public static ArrayList<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
	private static DbHandler dbHandler = new DbHandler();

	public static void main(String[] args) {
		people = dbHandler.getAllPersons();
		phoneNumbers = dbHandler.getAllPhoneNumbers();
		SpringApplication.run(Task17Application.class, args);
	}
}
