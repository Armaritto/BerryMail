package com.berrymail.entities;
import java.util.ArrayList;
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private ArrayList<String> inbox;
    private ArrayList<String> sent;
    private ArrayList<String> trash;
    private ArrayList<String> draft;
    public User(String firstName, String lastName, String email, String password, String username, ArrayList<String> inbox, ArrayList<String> sent, ArrayList<String> trash, ArrayList<String> draft) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.inbox = inbox;
        this.sent = sent;
        this.trash = trash;
        this.draft = draft;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getInbox() {
        return inbox;
    }
    public void setInbox(ArrayList<String> inbox) {
        this.inbox = inbox;
    }
    public ArrayList<String> getSent() {
        return sent;
    }
    public void setSent(ArrayList<String> sent) {
        this.sent = sent;
    }
    public ArrayList<String> getTrash() {
        return trash;
    }
    public void setTrash(ArrayList<String> trash) {
        this.trash = trash;
    }
    public ArrayList<String> getDraft() {
        return draft;
    }
    public void setDraft(ArrayList<String> draft) {
        this.draft = draft;
    }
    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
