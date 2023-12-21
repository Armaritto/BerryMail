package com.berrymail.controllers;
import com.berrymail.entities.Mail;
import com.berrymail.entities.User;
import com.berrymail.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class UserController {
    UserService userService = new UserService();
    @PostMapping(path = "/Signup")
    public String createAccount(@RequestParam("firstName") String fname , @RequestParam("lastName") String lname, @RequestParam("username") String username
            , @RequestParam("email") String email, @RequestParam("password") String password){
        return userService.createAccount(fname,lname,username,email,password,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>());
    }
    @PostMapping(path = "/Login")
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        return userService.accessAccount(email,password);
    }
    @PostMapping(path = "/inbox")
    public ArrayList<Mail> inboxMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.inboxList(email,SortCriteria);
    }
    @PostMapping(path = "/sent")
    public ArrayList<Mail> sentMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.sentList(email,SortCriteria);
    }
    @PostMapping(path = "/trash")
    public ArrayList<Mail> trashMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.trashList(email,SortCriteria);
    }
    @PostMapping(path = "/draft")
    public ArrayList<Mail> draftMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.draftList(email,SortCriteria);
    }
    @PostMapping(path = "/delete")
    public String deleteMail(@RequestParam("email") String email, @RequestParam("id") String id) throws Exception {
        return userService.addMailToTrash(email, id);
    }
    @PostMapping(path = "/filterInbox")
    public ArrayList<Mail> filterInbox(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("FilterCriteria") String FilterCriteria, @RequestParam("Objects") ArrayList<String> objects) throws Exception {
        return userService.filterInbox(email, SortCriteria, FilterCriteria, objects);
    }
    @PostMapping(path = "/filterDraft")
    public ArrayList<Mail> filterDraft(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("FilterCriteria") String FilterCriteria, @RequestParam("Objects") ArrayList<String> objects) throws Exception {
        return userService.filterDraft(email, SortCriteria, FilterCriteria, objects);
    }
    @PostMapping(path = "/filterTrash")
    public ArrayList<Mail> filterTrash(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("FilterCriteria") String FilterCriteria, @RequestParam("Objects") ArrayList<String> objects) throws Exception {
        return userService.filterTrash(email, SortCriteria, FilterCriteria, objects);
    }
    @PostMapping(path = "/filterSent")
    public ArrayList<Mail> filterSent(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("FilterCriteria") String FilterCriteria, @RequestParam("Objects") ArrayList<String> objects) throws Exception {
        return userService.filterSent(email, SortCriteria, FilterCriteria, objects);
    }
}
