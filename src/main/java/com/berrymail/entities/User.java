package com.berrymail.entities;
import java.util.ArrayList;
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String username;
    private ArrayList<Integer> inbox;
    private ArrayList<Integer> sent;
    private ArrayList<Integer> trash;
    private ArrayList<Integer> draft;
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

    public ArrayList<Integer> getInbox() {
        return inbox;
    }
    public void setInbox(ArrayList<Integer> inbox) {
        this.inbox = inbox;
    }
    public ArrayList<Integer> getSent() {
        return sent;
    }
    public void setSent(ArrayList<Integer> sent) {
        this.sent = sent;
    }
    public ArrayList<Integer> getTrash() {
        return trash;
    }
    public void setTrash(ArrayList<Integer> trash) {
        this.trash = trash;
    }
    public ArrayList<Integer> getDraft() {
        return draft;
    }
    public void setDraft(ArrayList<Integer> draft) {
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
