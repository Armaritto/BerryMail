package com.berrymail.entities;
import java.util.ArrayList;
import java.util.HashMap;
public class UserDirector {
    public static HashMap <String, User> users = new HashMap<>();
    private final UserBuilderIF userbuilder;
    public UserDirector(UserBuilderIF userbuilder){
        this.userbuilder= userbuilder;
    }
    public User getUser(){
        return this.userbuilder.getUser();
    }
    public void makeUser(String fname, String lname, String username, String email, String password, ArrayList<Integer> inbox ,ArrayList<Integer> sent, ArrayList<Integer> trash ,ArrayList<Integer> draft){
        if(users.get(email) == null){
            this.userbuilder.generateFirstName(fname);
            this.userbuilder.generateLastName(lname);
            this.userbuilder.generateUsername(username);
            this.userbuilder.generateEmail(email);
            this.userbuilder.generatePassword(password);
            this.userbuilder.generateInbox(inbox);
            this.userbuilder.generateTrash(trash);
            this.userbuilder.generateDraft(draft);
            this.userbuilder.generateSent(sent);
            users.put(email, this.userbuilder.getUser());
        }

    }
}
