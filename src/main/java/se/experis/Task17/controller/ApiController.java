package se.experis.Task17.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.experis.Task17.model.*;

import java.util.ArrayList;

@RestController("/api")
public class ApiController {
    private personController controller = new personController();
    private DbHandler dbHandler = new DbHandler();
    @GetMapping("/api")
    public ArrayList<Person> getAllPersons(){
        return dbHandler.getAllPersons();
    }
    @GetMapping("/api/relations")
    public ArrayList<Relationship> getAllRelations(){
        return dbHandler.getAllRelations();
    }
    @GetMapping("/api/phones")
    public ArrayList<PhoneNumber> getAllPhoneNumbers(){
        return dbHandler.getAllPhoneNumbers();
    }
    @GetMapping("/api/people")
    public ArrayList<Person> getAllPeople(){
        return dbHandler.getAllPersons();
    }
    @GetMapping("/api/emails")
    public ArrayList<Email> getAllEmails(){
        return dbHandler.getALLEmails();
    }
    @GetMapping("/api/addresses")
    public ArrayList<Address> getAllAddresses(){
        return dbHandler.getALLAddress();
    }
    @GetMapping("/api/person/name/{name}")
    public Person customerGet(@PathVariable String name) {
        return controller.customerGet(name);
    }
    @GetMapping("/api/person/number/{phoneNumber}")
    public Person personGet(@PathVariable String phoneNumber){
        return controller.personGet(phoneNumber);
    }
}
