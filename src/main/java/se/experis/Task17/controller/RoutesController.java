package se.experis.Task17.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import se.experis.Task17.Task17Application;
import se.experis.Task17.model.Person;

import java.util.ArrayList;
import java.util.Map;

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


//    @GetMapping("/delete")
//    public String deletePerson(Model model) {
//        ArrayList<Person>arr = Task17Application.people;
//
//        model.addAttribute("people" , arr);
//        return "delete";
//    }

    @GetMapping("/person")
    public String PersonGetALL(Model model){
        ArrayList<Person>arr = Task17Application.people;

        model.addAttribute("people" , arr);
        return "peoplePage";
    }
//
//    @GetMapping("/delete/{id}")
//    public String deletePerson(@PathVariable int id , Model model) {
//        System.out.println("here");
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
}
