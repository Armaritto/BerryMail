package com.berrymail.controllers;

import com.berrymail.services.Mediator;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailController {
    Mediator mediator = new Mediator();
    @PostMapping(path = "/send")
    public String addEmails(@RequestParam("from") String from , @RequestParam("to") String to, @RequestParam("subject") String subject
            , @RequestParam("body") String body, @RequestParam("date") String date , @RequestParam("time") String time , @RequestParam("priority") String priority , @RequestParam("attachment") String attachment){
        return mediator.addMails(from, to, subject, body, date, time, priority, attachment);
    }

}

/*
user1

compose email1
sent ['email1']

user2

inbox ['email1']
 */
