package se.experis.Task17.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.experis.Task17.Task17Application;
import se.experis.Task17.model.*;

import java.util.ArrayList;



@org.springframework.stereotype.Controller
public class RoutesController {
    @GetMapping("create")
    public String createPerson(){
        return "addPerson";
    }

    @GetMapping("/person/delete")
    public String deletePerson(){
        return "delete";
    }

    @GetMapping("/person")
    public String PersonGetALL(Model model){
        ArrayList<Person> arr = Task17Application.people;
        model.addAttribute("people" , arr);
        return "peoplePage";
    }

    public Person findbyID(int id){
        Person p = null;
        for( Person pers : Task17Application.people){
            if(pers.getPersonID()==id){
                p=pers;
            }
        }
        return p;
    }

    @GetMapping("/person/delete/{id}")
    public void deletePerson(@PathVariable int id){
        System.out.println("here");
        DbHandler dbHandler = new DbHandler();
        dbHandler.deletePerson(findbyID(id));
    }
    @GetMapping("/person/id/{ID}")
    public String personGet(@PathVariable int ID, Model model) {

        Person returnPerson = null;
        PhoneNumber returnPhoneNumber = null;
        Email returnEmail = null;
        Address returnAddress = null;
        ArrayList<Relationship> returnedRelationList =new ArrayList<>();
        int addressId = 0;
        for (Person person : Task17Application.people) {
            if (person.getPersonID() == ID) {
                System.out.println(" --- PERSON FOUND --- ");
                returnPerson = person;
                addressId = person.getAddressID();
            }
        }
        for (PhoneNumber number : Task17Application.phoneNumbers) {
            if (number.getPersonID() == ID ) {
                System.out.println(" --- Phne FOUND --- ");
                returnPhoneNumber = number;
            }
        }
        for (Email mail : Task17Application.emails) {
            //System.out.println(mail.getEmailID());
            if (mail.getPersonID() == ID ) {
                System.out.println(" --- email FOUND --- ");
                returnEmail = mail;
            }
        }
       for (Address address : Task17Application.addresses) {
            //System.out.println(addressId);
            if (address.getID() == addressId ) {
                System.out.println(" --- Addresses FOUND --- ");
                returnAddress = address;
            }
        }
       String name =null;
       for(Relationship relationship : Task17Application.relationships){
           if(relationship.getPersonID() == ID){
               System.out.println("Relation founds");
               returnedRelationList.add(relationship);
               for(Person person :Task17Application.people){
                   if(person.getPersonID() == relationship.getPerson2ID()){
                       name = person.getName();
                   }
               }
           }
       }
        model.addAttribute("people" , returnPerson);
        model.addAttribute("phoneNumbers" , returnPhoneNumber);
        model.addAttribute("emails" , returnEmail);
        model.addAttribute("addresses" , returnAddress);
        model.addAttribute("relations",returnedRelationList);
        model.addAttribute("name", name);
        return "personProfile";
    }

    @GetMapping("/person/update/{ID}")
    public String updatePerson(@PathVariable int ID, Model model){
        Person returnPerson = null;
        Email returnEmail = null;
        PhoneNumber returnPhoneNumber = null;
        Address returnAddress = null;
        for (Person person: Task17Application.people) {
            if(person.getPersonID() == ID){
                returnPerson = person;
                DbHandler db = new DbHandler();
                for (Email email:db.getALLEmails()) {
                    if(email.getPersonID() == ID)
                        returnEmail = email;
                }
                for (PhoneNumber ph:Task17Application.phoneNumbers) {
                    if(ph.getPersonID() == ID)
                        returnPhoneNumber = ph;
                }
                returnAddress = returnPerson.getAddress();
            }
        }
        model.addAttribute("person", returnPerson);
        model.addAttribute("email", returnEmail);
        model.addAttribute("phoneNumber", returnPhoneNumber);
        model.addAttribute("address", returnAddress);
        return "updatePerson";
    }
}
