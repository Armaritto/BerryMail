package com.berrymail.entities;

import java.util.ArrayList;

public class UserBuilder implements UserBuilderIF{
    private User user;
    public UserBuilder(){
        this.user = new User();
    }
    @Override
    public void generateFirstName(String fname) {
        this.user.setFirstName(fname);

    }
    @Override
    public void generateLastName(String lname) {
        this.user.setLastName(lname);
    }
    @Override
    public void generateUsername(String username) {
        this.user.setUsername(username);
    }
    @Override
    public void generateEmail(String email) {
        this.user.setEmail(email);
    }
    @Override
    public void generatePassword(String password) {
        this.user.setPassword(password);
    }

    @Override
    public void generateInbox(ArrayList<Integer> inbox) {
        this.user.setInbox(inbox);
    }

    @Override
    public void generateTrash(ArrayList<Integer> trash) {
        this.user.setTrash(trash);
    }

    @Override
    public void generateDraft(ArrayList<Integer> draft) {
        this.user.setDraft(draft);
    }

    @Override
    public void generateSent(ArrayList<Integer> sent) {
        this.user.setSent(sent);
    }

    @Override
    public User getUser() {
        return this.user;
    }
}

