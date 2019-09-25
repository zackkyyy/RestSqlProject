package se.experis.Task17.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.experis.Task17.Task17Application;
import se.experis.Task17.model.Person;
import se.experis.Task17.model.PhoneNumber;


import java.util.ArrayList;

@RestController("/person")
public class personController {
    @GetMapping("/person")
    public ArrayList<Person> PersonGetALL(){
        return Task17Application.people;
    }

    @GetMapping("/person/number/{phoneNumber}")
    public Person personGet(@PathVariable String phoneNumber){
        Person p = null;
        System.out.println("Trying to find person with phone number : " +phoneNumber);
        for (PhoneNumber phone : Task17Application.phoneNumbers){
            if (phone.getWorkNumber().equals(phoneNumber)|| phone.getPersonalNumber().equals(phoneNumber)){
                p = findPersonByID(phone.getPersonID());
            }
        }
        return p;
    }

    @GetMapping("/person/id/{ID}")
    public Person personGet(@PathVariable int ID) {
        System.out.println("Trying to find person: " + ID);
        Person returnPerson = null;
        for (Person person : Task17Application.people) {
            if (person.getPersonID() == ID) {
                System.out.println(" --- PERSON FOUND --- ");
                returnPerson = person;
            }
        }
        return returnPerson;
    }
    @GetMapping("/person/name/{name}")
    public Person customerGet(@PathVariable String name) {
        System.out.println("Trying to find person: " + name);
        Person returnPerson = null;
        for (Person person : Task17Application.people) {
            if (person.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(" --- PERSON FOUND --- ");
                returnPerson = person;
            }
        }
        if(returnPerson == null)
            System.out.println(" --- PERSON WAS NOT FOUND --- ");
        return returnPerson;
    }

    @PostMapping("/add/{name} {lastname}")
    public void personPost(@PathVariable  String name , String lastname ){>>>>>>> master

    @PostMapping("/addPerson")
    public Person createPerson(@RequestBody Person person) {
        return Task17Application.addPerson(person);
    }
    //public void personPost(@PathVariable  String name , String lastname ){
    //
    //}

    public Person findPersonByID(int id ){
        Person person = null ;
    for( Person p : Task17Application.people){
        if(p.getPersonID() == id ){
            person = p ;
        }
    }
    if(person == null){
        System.out.println("Nothing was found");
    }
    return person;
    }

}
