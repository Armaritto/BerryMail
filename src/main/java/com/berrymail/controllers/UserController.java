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
    @GetMapping(path = "/Login")
    public User login(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {
        return userService.accessAccount(email,password);
    }
    @PostMapping(path = "/createFolder")
    public String createFolder(@RequestParam("email") String email, @RequestParam("folderName") String folderName) throws IOException {
        return userService.createFolder(email,folderName);
    }
    @PutMapping(path = "/addToFolder")
    public String addToFolder(@RequestParam("email") String email, @RequestParam("folderName") String folderName, @RequestParam("mailID") String mailID, @RequestParam("oldFolder") String oldFolder) throws IOException {
        userService.removeMail(email,oldFolder,mailID);
        return userService.addMailToCustomFolder(email,folderName,mailID);
    }
    @GetMapping(path = "/inbox")
    public ArrayList<Mail> inboxMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.inboxList(email,SortCriteria);
    }
    @GetMapping(path = "/sent")
    public ArrayList<Mail> sentMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.sentList(email,SortCriteria);
    }
    @GetMapping(path = "/trash")
    public ArrayList<Mail> trashMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.trashList(email,SortCriteria);
    }
    @GetMapping(path = "/draft")
    public ArrayList<Mail> draftMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria) throws Exception {
        return userService.draftList(email,SortCriteria);
    }
    @GetMapping(path = "/customFolder")
    public ArrayList<Mail> customFolderMails(@RequestParam("email") String email , @RequestParam("SortCriteria") String SortCriteria, @RequestParam("folderName") String folderName) throws Exception {
        return userService.customFolderList(email,folderName,SortCriteria);
    }
    @DeleteMapping(path = "/deleteFolder")
    public HashMap<String, ArrayList<String>> deleteFolder(@RequestParam("email") String email, @RequestParam("name") String name) throws Exception {
        return userService.deleteFolder(email, name);
    }
    @PutMapping(path = "/renameFolder")
    public HashMap<String, ArrayList<String>> renameFolder(@RequestParam("email") String email, @RequestParam("oldFolderName") String oldFolderName,  @RequestParam("newFolderName") String newFolderName) throws Exception {
        return userService.renameFolder(email, oldFolderName, newFolderName );
    }
    @PutMapping(path = "/moveToTrash")
    public String moveToTrash(@RequestParam("email") String email, @RequestParam("id") String id, @RequestParam("folderName") String folderName) throws Exception {
        return userService.addMailToTrash(email, folderName, id);
    }
    @DeleteMapping(path = "/emptyTrash")
    public String emptyTrash(@RequestParam("email") String email) throws Exception {
        userService.emptyTrash(email);
        return "Trash emptied";
    }
    @GetMapping(path = "/filterInbox")
    public ArrayList<Mail> filterInbox(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        System.out.println(criteriaMap);
        return userService.filterInbox(email, SortCriteria, type, criteriaMap);
    }
    @GetMapping(path = "/filterDraft")
    public ArrayList<Mail> filterDraft(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        return userService.filterDraft(email, SortCriteria, type, criteriaMap);
    }
    @GetMapping(path = "/filterTrash")
    public ArrayList<Mail> filterTrash(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        return userService.filterTrash(email, SortCriteria, type, criteriaMap);
    }
    @GetMapping(path = "/filterSent")
    public ArrayList<Mail> filterSent(@RequestParam("email") String email, @RequestParam("SortCriteria") String SortCriteria, @RequestParam("Type") String type, @RequestBody HashMap<String, ArrayList<String>> criteriaMap) throws Exception {
        return userService.filterSent(email, SortCriteria, type, criteriaMap);
    }
    @PostMapping(path = "/addContact")
    public String addContact(@RequestParam("email") String userEmail, @RequestParam("name") String name, @RequestParam("mails") ArrayList<String> mails) throws Exception {
        return userService.addContact(userEmail, name, mails);
    }
    @GetMapping(path = "/contacts")
    public HashMap<String, ArrayList<String>> contactsList(@RequestParam("email") String email) throws Exception {
        return userService.contactsList(email);
    }
    @DeleteMapping(path = "/deleteContact")
    public HashMap<String, ArrayList<String>> deleteContact(@RequestParam("email") String email, @RequestParam("name") String name) throws Exception {
        return userService.deleteContact(email, name);
    }
    @PutMapping(path = "/editContact")
    public HashMap<String, ArrayList<String>> editContact(@RequestParam("email") String email, @RequestParam("oldContactName") String oldContactName,  @RequestParam("newContactName") String newContactName,@RequestParam("mails") ArrayList<String> mails) throws Exception {
        return userService.editContact(email, oldContactName,newContactName, mails);
    }
    @GetMapping(path = "/sortContact")
    public Map<String, ArrayList<String>> editContact(@RequestParam("email") String email) throws Exception {
        return userService.sortContacts(email);
    }
    @GetMapping(path = "/searchContact")
    public ArrayList<String> searchContact(@RequestParam("email") String email, @RequestParam("contactName") String contactName) throws Exception {
        return userService.searchContatct(email, contactName);

    }
}
