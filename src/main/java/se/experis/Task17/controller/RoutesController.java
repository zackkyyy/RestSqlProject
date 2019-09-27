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
