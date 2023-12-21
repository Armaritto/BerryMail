package com.berrymail.services;
import com.berrymail.entities.*;
import jakarta.annotation.Priority;


import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

public class UserService {
    UserBuilderIF userBuilder = new UserBuilder();
    UserDirector userDir = new UserDirector(userBuilder);
    public String createAccount(String fname, String lname, String username, String email, String password, ArrayList<String> inbox ,ArrayList<String> sent, ArrayList<String> trash ,ArrayList<String> draft){
        if(UserDirector.users.get(email) == null){
            userDir.makeUser(fname, lname, username, email, password, inbox, sent, trash, draft);
            return "Account created";
        }
        return "An account already exists";
    }
    public User accessAccount(String email, String password) throws Exception {
        if(UserDirector.users.get(email) == null){
            throw new Exception("Account doesn't exist");
        }
        else{
           if(Objects.equals(UserDirector.users.get(email).getPassword(), password)) {
               return UserDirector.users.get(email);
           }
           else if(!Objects.equals(UserDirector.users.get(email).getPassword(), password)){
               throw new Exception("Wrong password!");
           }
        }
        return null;
    }
    public String addMailToInbox(String email, String mailID){
        UserDirector.users.get(email).getInbox().add(mailID);
        return "Mail added to inbox";
    }
    public String addMailToSent(String email, String mailID){
        UserDirector.users.get(email).getSent().add(mailID);
        return "Mail sent Successfully";
    }
    public String addMailToDraft(String email, String mailID){
        UserDirector.users.get(email).getDraft().add(mailID);
        return "Mail added to draft";
    }
    public ArrayList<Mail> inboxList(String email, String Sortcriteria){ // criteria
        ArrayList<String> inboxID = UserDirector.users.get(email).getInbox();
        ArrayList<Mail> inboxFolder= new ArrayList<>();
        for(int i=0; i<inboxID.size(); i++){
            inboxFolder.add(MailDirector.mails.get(inboxID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(email, inboxFolder);
        }
        return sortByDate(email, inboxFolder);
    }
    public ArrayList<Mail> sentList(String email, String Sortcriteria){
        ArrayList<String> sentID = UserDirector.users.get(email).getSent();
        ArrayList<Mail> sentFolder= new ArrayList<>();
        for(int i=0; i<sentID.size(); i++) {
            sentFolder.add(MailDirector.mails.get(sentID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(email, sentFolder);
        }
        return  sortByDate(email, sentFolder);
    }
    public ArrayList<Mail> trashList(String email, String Sortcriteria){
        ArrayList<String> trashID = UserDirector.users.get(email).getTrash();
        ArrayList<Mail> trashFolder= new ArrayList<>();
        for(int i=0; i<trashID.size(); i++){
            trashFolder.add(MailDirector.mails.get(trashID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(email, trashFolder);
        }
        return sortByDate(email, trashFolder);
    }
    public ArrayList<Mail> draftList(String email, String Sortcriteria){
        ArrayList<String> draftID = UserDirector.users.get(email).getDraft();
        ArrayList<Mail> draftFolder= new ArrayList<>();
        for(int i=0; i<draftID.size(); i++){
            draftFolder.add(MailDirector.mails.get(draftID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(email, draftFolder);
        }
        return sortByDate(email, draftFolder);
    }
    public String addMailToTrash(String email, String mailID){
        if(UserDirector.users.get(email).getInbox().contains(mailID))
            UserDirector.users.get(email).getInbox().remove(mailID);
        else if(UserDirector.users.get(email).getDraft().contains(mailID))
            UserDirector.users.get(email).getDraft().remove(mailID);
        else if(UserDirector.users.get(email).getSent().contains(mailID))
            UserDirector.users.get(email).getSent().remove(mailID);
        UserDirector.users.get(email).getTrash().add(mailID);
        return "Mail added to trash";
    }
    public ArrayList<Mail> sortByDate(String email, ArrayList<Mail> Folder){
        ArrayList<Mail> folder = Folder;
        for(int i=0; i<folder.size(); i++){
            for(int j=0; j<folder.size()-1; j++){
                if(folder.get(j).getDateNtime().compareTo(folder.get(j+1).getDateNtime()) < 0){
                    Mail temp = folder.get(j);
                    folder.set(j, folder.get(j+1));
                    folder.set(j+1, temp);
                }
            }
        }
        return folder;
    }
    public ArrayList<Mail> sortByPriority(String email, ArrayList<Mail> Folder){
        ArrayList<Mail> folder = sortByDate(email, Folder);
        PriorityQueue<Mail> priorityQueue = new PriorityQueue<>(new PriorityComparator());
        priorityQueue.addAll(folder); // for loop
        folder.clear();
        int size = priorityQueue.size();
        for(int i=0; i<size; i++){
            folder.add(priorityQueue.poll());
        }
        return folder;
    }
}
