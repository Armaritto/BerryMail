package com.berrymail.entities;

import java.util.ArrayList;
import java.util.HashMap;

public interface UserBuilderIF {
    public void generateFirstName(String fname);
    public void generateLastName(String lname);
    public void generateUsername(String username);
    public void generateEmail(String email);
    public void generatePassword(String password);
    public void generateInbox(ArrayList<String> inbox);
    public void generateTrash(ArrayList<String> trash);
    public void generateDraft(ArrayList<String> draft);
    public void generateSent(ArrayList<String> sent);
    public void generateCustomFolders(HashMap<String, ArrayList<String>> customFolders);
    public void generateContacts(HashMap<String, ArrayList<String>> contacts );
    public User getUser();

}
