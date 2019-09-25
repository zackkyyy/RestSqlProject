package se.experis.Task17.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.experis.Task17.model.Person;


public class personController {
    @GetMapping("/person/{ID}")
    public Person personGet(@PathVariable String Id){

    }
    @PostMapping("/add/{name} {lastname}")
    public void personPost(@PathVariable  String name , String lastname ){

    }

}
