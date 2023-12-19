package com.berrymail.entities;

import java.util.ArrayList;

public interface UserBuilderIF {
    public void generateFirstName(String fname);
    public void generateLastName(String lname);
    public void generateUsername(String username);
    public void generateEmail(String email);
    public void generatePassword(String password);
    public void generateInbox(ArrayList<Integer> inbox);
    public void generateTrash(ArrayList<Integer> trash);
    public void generateDraft(ArrayList<Integer> draft);
    public void generateSent(ArrayList<Integer> sent);
    public User getUser();
}
