package se.experis.Task17.controller;


import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import se.experis.Task17.Task17Application;
import se.experis.Task17.model.Address;
import se.experis.Task17.model.Email;
import se.experis.Task17.model.Person;
import se.experis.Task17.model.PhoneNumber;


import java.util.ArrayList;

@RestController("/person")
public class personController {
    DbHandler dbHandler = new DbHandler();


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



    @RequestMapping(method = RequestMethod.POST
            , consumes = {"application/x-www-form-urlencoded"}
            ,value = "/addPerson"
    )
    public
    @ResponseBody
    Person createPerson(@RequestBody MultiValueMap params) throws Exception {
        String firstname = params.get("firstname").toString();
        String lastname = params.get("lastname").toString();
        String birthofdate = params.get("birthdate").toString();

        firstname =firstname.substring(1,firstname.length()-1);
        lastname =lastname.substring(1,lastname.length()-1);
        birthofdate = birthofdate.substring(1,birthofdate.length()-1);

        String street = params.get("street").toString();
        street = street.substring(1 , street.length()-1 );

        String city = params.get("city").toString();
        city = city.substring(1 , city.length()-1 );

        String country = params.get("country").toString();
        country = country.substring(1 , country.length()-1 );

        String postalCode = params.get("postalCode").toString();
        postalCode = postalCode.substring(1 , postalCode.length()-1 );

        String workEmail = params.get("workEmail").toString();
        workEmail = workEmail.substring(1 , workEmail.length()-1 );

        String personalEmail = params.get("personalEmail").toString();
        personalEmail = personalEmail.substring(1 , personalEmail.length()-1 );


        String phoneWork = params.get("workPhone").toString();
        phoneWork = phoneWork.substring(1 , phoneWork.length()-1 );


        String phonePersonal = params.get("personalPhone").toString();
        phonePersonal = phonePersonal.substring(1 , phonePersonal.length()-1 );
        Email email = new Email(workEmail ,personalEmail);
        PhoneNumber phoneNumber = new PhoneNumber(phoneWork, phonePersonal);
        Address address = new Address(street, city,country, postalCode);
        Person person = new Person(firstname, lastname , birthofdate );
        DbHandler dbHandler = new DbHandler();
        return dbHandler.addPerson(person, address , email , phoneNumber);
    }

    @RequestMapping(method = RequestMethod.POST
            , consumes = {"application/x-www-form-urlencoded"}
            ,value = "/deleting"
    )
    public
    @ResponseBody
    void deletePerson(@RequestBody MultiValueMap params) throws Exception {
        System.out.println("params are " + params);
        String searchWord = params.get("searchField").toString();
        searchWord = searchWord.substring(1,searchWord.length()-1);
        System.out.println(params.get("searchField").toString());

        Person returnPerson = null;
        for (Person person : Task17Application.people) {

            if(person.getName().toLowerCase().contains(searchWord.toLowerCase())){
                returnPerson = person;
                System.out.println("found");
            }
        }
        if(returnPerson != null){
            dbHandler.deletePerson(returnPerson);

        }else{
            System.out.println("Person not found");
        }
    }


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
