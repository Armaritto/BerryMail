package com.berrymail.controllers;
import com.berrymail.entities.Attachment;
import com.berrymail.entities.Mail;
import com.berrymail.services.Mediator;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@RestController
@CrossOrigin
public class MailController {
    Mediator mediator = new Mediator();
    @PostMapping(path = "/send")
    public String addEmails(@RequestParam("from") String from , @RequestParam("to") ArrayList<String> to, @RequestParam("subject") String subject
            , @RequestParam("priority") String priority, @RequestBody Map<String, Object> payload) throws IOException {
        System.out.println(payload);
        String body;
        Attachment attachment;
        ArrayList<Attachment> attachments = new ArrayList<>();
        try{
            body = payload.get("body").toString();
        }
        catch (Exception e){
            body = "";
        }
        if(payload.containsKey("attachment")){
            for(HashMap<String,Object> temp : (ArrayList<HashMap<String,Object>>)payload.get("attachment")){
                try{
                    attachment = new Attachment();
                    attachment.setName(temp.get("name").toString());
                    attachment.setType(temp.get("type").toString());
                    attachment.setContent(temp.get("content").toString().getBytes(StandardCharsets.UTF_8));
                    attachments.add(attachment);
                }
                catch(Exception e){
                    attachment = null;
                }
            }
        }
        return mediator.addMails(from, to, subject, body, priority, attachments);
    }
    @PostMapping(path = "/addToDraft")
    public String moveToDraft(@RequestParam("from") String from , @RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("priority") String priority, @RequestBody Map<String, Object> payload) throws Exception {
        String body;
        Attachment attachment;
        ArrayList<Attachment> attachments = new ArrayList<>();
        try{
            body = payload.get("body").toString();
        }
        catch (Exception e){
            body = "";
        }
        if(payload.containsKey("attachment")){
            for(HashMap<String,Object> temp : (ArrayList<HashMap<String,Object>>)payload.get("attachment")){
                try{
                    attachment = new Attachment();
                    attachment.setName(temp.get("name").toString());
                    attachment.setType(temp.get("type").toString());
                    attachment.setContent(temp.get("content").toString().getBytes(StandardCharsets.UTF_8));
                    attachments.add(attachment);
                }
                catch(Exception e){
                    attachment = null;
                }
            }
        }
        return mediator.moveToDraft(from, to, subject, body, priority, attachments);
    }
    @GetMapping(path = "/getFromDraft")
    public Mail getFromDraft(@RequestParam("email") String email , @RequestParam("id") String id) throws Exception {
        return mediator.getFromDraft(email, id);
    }
    @GetMapping(path = "/getEmail")
    public Mail getEmail(@RequestParam("id") String id) throws Exception {
        return mediator.getEmail(id);
    }
}
