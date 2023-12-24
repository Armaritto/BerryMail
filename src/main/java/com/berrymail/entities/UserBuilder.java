package com.berrymail.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class UserBuilder implements UserBuilderIF{
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private ArrayList<String> inbox;
    private ArrayList<String> sent;
    private ArrayList<String> trash;
    private ArrayList<String> draft;
    private HashMap<String, ArrayList<String>> customFolder;
    private HashMap<String, ArrayList<String>> contacts;
    @Override
    public void generateFirstName(String fname) {
        this.firstName = fname;
    }

    @Override
    public void generateLastName(String lname) {
        this.lastName = lname;
    }

    @Override
    public void generateUsername(String username) {
        this.username = username;
    }

    @Override
    public void generateEmail(String email) {
        this.email = email;
    }

    @Override
    public void generatePassword(String password) {
        this.password = password;
    }

    @Override
    public void generateInbox(ArrayList<String> inbox) {
        this.inbox = inbox;
    }

    @Override
    public void generateTrash(ArrayList<String> trash) {
        this.trash = trash;
    }

    @Override
    public void generateDraft(ArrayList<String> draft) {
        this.draft = draft;
    }

    @Override
    public void generateSent(ArrayList<String> sent) {
        this.sent = sent;
    }

    @Override
    public void generateCustomFolders(HashMap<String, ArrayList<String>> customFolders) {
        this.customFolder = customFolders;
    }

    @Override
    public void generateContacts(HashMap<String, ArrayList<String>> contacts) {
        this.contacts = contacts;
    }

    public User getUser(){
        return new User(firstName, lastName, email, password, username,inbox, sent, trash, draft, customFolder, contacts);
    }
}
