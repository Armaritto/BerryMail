package com.berrymail.controllers;
import com.berrymail.entities.Mail;
import com.berrymail.services.Mediator;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailController {
    Mediator mediator = new Mediator();
    @PostMapping(path = "/send")
    public String addEmails(@RequestParam("from") String from , @RequestParam("to") String to, @RequestParam("subject") String subject
            , @RequestParam("body") String body, @RequestParam("priority") String priority , @RequestParam("attachment") String attachment){
        return mediator.addMails(from, to, subject, body, priority, attachment);
    }
    @PostMapping(path = "/addToDraft")
    public String moveToDraft(@RequestParam("from") String from , @RequestParam("to") String to, @RequestParam("subject") String subject
            , @RequestParam("body") String body, @RequestParam("priority") String priority , @RequestParam("attachment") String attachment) throws Exception {
        return mediator.moveToDraft(from, to, subject, body, priority, attachment);
    }
    @PostMapping(path = "/getFromDraft")
    public Mail getFromDraft(@RequestParam("email") String email , @RequestParam("id") String id) throws Exception {
        return mediator.getFromDraft(email, id);
    }

}

/*
bulider
mediator
singleton
filter
(fly weight)
 */
