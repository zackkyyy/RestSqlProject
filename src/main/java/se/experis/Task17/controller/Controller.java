package se.experis.Task17.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author : Zacky Kharboutli
 * Date : 2019-09-26
 * Project: Task17
 */

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/create")
    public String createPerson(){
        return "addPerson";
    }
}
