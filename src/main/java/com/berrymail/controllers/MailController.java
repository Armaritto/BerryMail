package com.berrymail.controllers;
import com.berrymail.entities.Mail;
import com.berrymail.services.Mediator;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Map;


@RestController
@CrossOrigin
public class MailController {
    Mediator mediator = new Mediator();
    @PostMapping(path = "/send")

    public String addEmails(@RequestParam("from") String from , @RequestParam("to") ArrayList<String> to, @RequestParam("subject") String subject
            , @RequestParam("priority") String priority, @RequestBody Map<String, Object> payload) throws IOException {
        String body;
        String attachment;
        try {
            body = payload.get("body").toString();
        }
        catch (Exception e){
            body = "";
        }
        try {
            attachment = payload.get("attachment").toString();
        }
        catch (Exception e){
            attachment = "";
        }
        return mediator.addMails(from, to, subject, body, priority, attachment);
    }
    @PostMapping(path = "/addToDraft")
    public String moveToDraft(@RequestParam("from") String from , @RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("priority") String priority, @RequestBody Map<String, Object> payload) throws Exception {
        String body;
        String attachment;
        try {
            body = payload.get("body").toString();
        }
        catch (Exception e){
            body = "";
        }
        try {
            attachment = payload.get("attachment").toString();
        }
        catch (Exception e){
            attachment = "";
        }
        return mediator.moveToDraft(from, to, subject, body, priority, attachment);
    }
    @PostMapping(path = "/getFromDraft")
    public Mail getFromDraft(@RequestParam("email") String email , @RequestParam("id") String id) throws Exception {
        return mediator.getFromDraft(email, id);
    }

}
/*
{
    "from": ....;
    "body": ....;
    "attach": {
                "name": ....;
                "type": ....;
                "data": ....;
               }
}
http://localhost:8080/send?from=armia404@berry.com&to=karene_antoine@berry.com&subject=Zoo&body=I went to the zoo&priority=Default&attachment=PNG
 */
