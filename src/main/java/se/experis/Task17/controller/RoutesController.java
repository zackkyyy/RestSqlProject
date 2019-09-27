package se.experis.Task17.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.experis.Task17.Task17Application;
import se.experis.Task17.model.Address;
import se.experis.Task17.model.Email;
import se.experis.Task17.model.Person;
import se.experis.Task17.model.PhoneNumber;

import java.util.ArrayList;

/**
 * Author : Zacky Kharboutli
 * Date : 2019-09-26
 * Project: Task17
 */

@org.springframework.stereotype.Controller
public class RoutesController {
    @GetMapping("/create")
    public String createPerson(){
        return "addPerson";
    }

    @GetMapping("/delete")
    public String deletePerson(){
        return "delete";
    }

    @GetMapping("/person")
    public String PersonGetALL(Model model){
        ArrayList<Person> arr = Task17Application.people;
        model.addAttribute("people" , arr);
        return "peoplePage";
    }
    @GetMapping("/person/id/{ID}")
    public String personGet(@PathVariable int ID, Model model) {
        //System.out.println("Trying to find person: " + ID);
        Person returnPerson = null;
        PhoneNumber returnPhoneNumber = null;
        Email returnEmail = null;
        Address returnAddress = null;
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
        model.addAttribute("people" , returnPerson);
        model.addAttribute("phoneNumbers" , returnPhoneNumber);
        model.addAttribute("emails" , returnEmail);
        model.addAttribute("addresses" , returnAddress);

        return "personProfile";
    }

//    @GetMapping("/delete/{id}")
//    public String deletePerson(@PathVariable int id , Model model) {
//        Person returnPerson = null;
//        for (Person person : Task17Application.people) {
//            if (person.getPersonID() == id) {
//                System.out.println(" --- PERSON FOUND --- ");
//                returnPerson = person;
//            }
//        }
//        model.addAttribute("person" , returnPerson);
//        return "delete";
//    }

    @GetMapping("/update/{ID}")
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
