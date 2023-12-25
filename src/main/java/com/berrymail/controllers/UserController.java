package com.berrymail.controllers;
import com.berrymail.entities.Mail;
import com.berrymail.entities.User;
import com.berrymail.services.UserService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    UserService userService = new UserService();
    @PostMapping(path = "/Signup")
    public String createAccount(@RequestParam("firstName") String fname , @RequestParam("lastName") String lname, @RequestParam("username") String username
            , @RequestParam("email") String email, @RequestParam("password") String password) throws IOException {
        return userService.createAccount(fname,lname,username,email,password,new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(),new ArrayList<String>(), new HashMap<String, ArrayList<String>>(), new HashMap<String, ArrayList<String>>());
    }
    @PostMapping(path = "/Login")
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        return userService.accessAccount(email,password);
    }
    @PostMapping(path = "/createFolder")
    public String createFolder(@RequestParam("email") String email, @RequestParam("folderName") String folderName) throws IOException {
        return userService.createFolder(email,folderName);
    }
    @PostMapping(path = "/addToFolder")
    public String addToFolder(@RequestParam("email") String email, @RequestParam("folderName") String folderName, @RequestParam("mailID") String mailID) throws IOException {
        return userService.addMailToCustomFolder(email,folderName,mailID);
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
    @PostMapping(path = "/customFolder")
    public ArrayList<Mail> customFolderMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria, @RequestParam("folderName") String folderName) throws Exception {
        return userService.customFolderList(email,folderName,SortCriteria);
    }
    @PostMapping(path = "/deleteFolder")
    public HashMap<String, ArrayList<String>> deleteFolder(@RequestParam("email") String email, @RequestParam("name") String name) throws Exception {
        return userService.deleteFolder(email, name);
    }
    @PostMapping(path = "/renameFolder")
    public HashMap<String, ArrayList<String>> renameFolder(@RequestParam("email") String email, @RequestParam("oldFolderName") String oldFolderName,  @RequestParam("newFolderName") String newFolderName) throws Exception {
        return userService.renameFolder(email, oldFolderName, newFolderName );
    }
    @PostMapping(path = "/moveToTrash")
    public String moveToTrash(@RequestParam("email") String email, @RequestParam("id") String id) throws Exception {
        return userService.addMailToTrash(email, id);
    }
    @PostMapping(path = "/emptyTrash")
    public String emptyTrash(@RequestParam("email") String email) throws Exception {
        userService.emptyTrash(email);
        return "Trash emptied";
    }
    @PostMapping(path = "/filterInbox")
    public ArrayList<Mail> filterInbox(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        System.out.println(criteriaMap);
        return userService.filterInbox(email, SortCriteria, type, criteriaMap);
    }
    @PostMapping(path = "/filterDraft")
    public ArrayList<Mail> filterDraft(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        return userService.filterDraft(email, SortCriteria, type, criteriaMap);
    }
    @PostMapping(path = "/filterTrash")
    public ArrayList<Mail> filterTrash(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        return userService.filterTrash(email, SortCriteria, type, criteriaMap);
    }
    @PostMapping(path = "/filterSent")
    public ArrayList<Mail> filterSent(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        return userService.filterSent(email, SortCriteria, type, criteriaMap);
    }
    @PostMapping(path = "/addContact")
    public String addContact(@RequestParam("email") String userEmail, @RequestParam("name") String name, @RequestParam("mails") ArrayList<String> mails) throws Exception {
        return userService.addContact(userEmail, name, mails);
    }
    @PostMapping(path = "/contacts")
    public HashMap<String, ArrayList<String>> contactsList(@RequestParam("email") String email) throws Exception {
        return userService.contactsList(email);
    }
    @PostMapping(path = "/deleteContact")
    public HashMap<String, ArrayList<String>> deleteContact(@RequestParam("email") String email, @RequestParam("name") String name) throws Exception {
        return userService.deleteContact(email, name);
    }
    @PostMapping(path = "/editContact")
    public HashMap<String, ArrayList<String>> editContact(@RequestParam("email") String email, @RequestParam("oldContactName") String oldContactName,  @RequestParam("newContactName") String newContactName,@RequestParam("mails") ArrayList<String> mails) throws Exception {
        return userService.editContact(email, oldContactName,newContactName, mails);
    }
    @PostMapping(path = "/sortContact")
    public Map<String, ArrayList<String>> editContact(@RequestParam("email") String email) throws Exception {
        return userService.sortContacts(email);
    }
    @PostMapping(path = "/searchContact")
    public ArrayList<String> searchContact(@RequestParam("email") String email, @RequestParam("contactName") String contactName) throws Exception {
        return userService.searchContatct(email, contactName);
    }

}
