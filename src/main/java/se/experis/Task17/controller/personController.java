package se.experis.Task17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.experis.Task17.Task17Application;
import se.experis.Task17.model.Person;

import javax.validation.Valid;

@RestController
public class personController {
   /* @GetMapping("/person/{ID}")
    public Person personGet(@PathVariable String Id){

    }*/

    @PostMapping("/addPerson")
    public Person createPerson(@RequestBody Person person) {
        return Task17Application.addPerson(person);
    }
    //public void personPost(@PathVariable  String name , String lastname ){
    //
    //}

}
