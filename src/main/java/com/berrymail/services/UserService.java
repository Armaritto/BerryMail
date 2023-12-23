package com.berrymail.services;
import com.berrymail.entities.*;

import java.io.IOException;
import java.util.*;
public class UserService {
    UserBuilderIF userBuilder = new UserBuilder();
    UserDirector userDir = new UserDirector(userBuilder);
    public String createAccount(String fname, String lname, String username, String email, String password, ArrayList<String> inbox ,ArrayList<String> sent, ArrayList<String> trash ,ArrayList<String> draft) throws IOException {
        if(!email.contains("@berry.com")){
            return "Email is not valid";
        }
        if(password.length()<6){
            return "Password must be at least 6 characters";
        }
        for (Map.Entry<String, User> entry : UserDirector.users.entrySet()) {
            String oldMail = entry.getKey();
            String oldUsername = entry.getValue().getUsername();
            if(username.equals(oldUsername)){
                return "Username already taken";
            }
        }
        if(UserDirector.users.get(email) == null){
            userDir.makeUser(fname, lname, username, email, password, inbox, sent, trash, draft);
            return "Account created";
        }
        return "An account already exists";
    }
    public User accessAccount(String email, String password) throws Exception {
        userDir.loadUserFromFile();
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
    public String addMailToInbox(String email, String mailID) throws IOException {
        UserDirector.users.get(email).getInbox().add(mailID);
        userDir.saveUser(UserDirector.users.get(email));
        return "Mail added to inbox";
    }
    public String addMailToSent(String email, String mailID) throws IOException {
        UserDirector.users.get(email).getSent().add(mailID);
        userDir.saveUser(UserDirector.users.get(email));
        return "Mail sent Successfully";
    }
    public String addMailToDraft(String email, String mailID) throws IOException {
        UserDirector.users.get(email).getDraft().add(mailID);
        userDir.saveUser(UserDirector.users.get(email));
        return "Mail added to draft";
    }
    public String addMailToTrash(String email, String mailID) throws IOException {
        if(UserDirector.users.get(email).getInbox().contains(mailID))
            UserDirector.users.get(email).getInbox().remove(mailID);
        else if(UserDirector.users.get(email).getDraft().contains(mailID))
            UserDirector.users.get(email).getDraft().remove(mailID);
        else if(UserDirector.users.get(email).getSent().contains(mailID))
            UserDirector.users.get(email).getSent().remove(mailID);
        UserDirector.users.get(email).getTrash().add(mailID);
        userDir.saveUser(UserDirector.users.get(email));
        return "Mail added to trash";
    }
    public ArrayList<Mail> inboxList(String email, String Sortcriteria){
        ArrayList<String> inboxID = UserDirector.users.get(email).getInbox();
        ArrayList<Mail> inboxFolder= new ArrayList<>();
        for(int i=0; i<inboxID.size(); i++){
            inboxFolder.add(MailDirector.mails.get(inboxID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(inboxFolder);
        }
        return sortByDate(inboxFolder);
    }
    public ArrayList<Mail> sentList(String email, String Sortcriteria){
        ArrayList<String> sentID = UserDirector.users.get(email).getSent();
        ArrayList<Mail> sentFolder= new ArrayList<>();
        for(int i=0; i<sentID.size(); i++) {
            sentFolder.add(MailDirector.mails.get(sentID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(sentFolder);
        }
        return  sortByDate(sentFolder);
    }
    public ArrayList<Mail> trashList(String email, String Sortcriteria) throws IOException {
        ArrayList<String> trashID = UserDirector.users.get(email).getTrash();
        ArrayList<Mail> trashFolder= new ArrayList<>();
        for(int i=0; i<trashID.size(); i++){
            Mail trashMail = MailDirector.mails.get(trashID.get(i));
            if(isDateMoreThan30DaysAgo(trashMail.getDateNtime(), new Date())){
                trashID.remove(i);
                i--;
                userDir.saveUser(UserDirector.users.get(email));
            }
            else{
                trashFolder.add(MailDirector.mails.get(trashID.get(i)));
            }
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(trashFolder);
        }
        return sortByDate(trashFolder);
    }
    public ArrayList<Mail> draftList(String email, String Sortcriteria){
        ArrayList<String> draftID = UserDirector.users.get(email).getDraft();
        ArrayList<Mail> draftFolder= new ArrayList<>();
        for(int i=0; i<draftID.size(); i++){
            draftFolder.add(MailDirector.mails.get(draftID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(draftFolder);
        }
        return sortByDate(draftFolder);
    }
    public ArrayList<Mail> filterInbox(String email, String sortCriteria,String type, HashMap<String, ArrayList<String>> criteriaMap){ //type is AND - OR
        ArrayList<Mail> inbox = inboxList(email, sortCriteria);
        return filter(inbox, type, criteriaMap);
    }
    public ArrayList<Mail> filterDraft(String email, String sortCriteria,String type, HashMap<String, ArrayList<String>> criteriaMap){
        ArrayList<Mail> draft = draftList(email, sortCriteria);
        return filter(draft, type, criteriaMap);
    }
    public ArrayList<Mail> filterTrash(String email, String sortCriteria,String type, HashMap<String, ArrayList<String>> criteriaMap) throws IOException {
        ArrayList<Mail> trash = trashList(email, sortCriteria);
        return filter(trash, type, criteriaMap);
    }
    public ArrayList<Mail> filterSent(String email, String sortCriteria,String type, HashMap<String, ArrayList<String>> criteriaMap){
        ArrayList<Mail> sent = sentList(email, sortCriteria);
        return filter(sent, type, criteriaMap);
    }
    public ArrayList<Mail> sortByDate(ArrayList<Mail> Folder){
        for(int i = 0; i< Folder.size(); i++){
            for(int j = 0; j< Folder.size()-1; j++){
                if(Folder.get(j).getDateNtime().compareTo(Folder.get(j+1).getDateNtime()) < 0){
                    Mail temp = Folder.get(j);
                    Folder.set(j, Folder.get(j+1));
                    Folder.set(j+1, temp);
                }
            }
        }
        return Folder;
    }
    public void emptyTrash(String email) throws IOException {
        ArrayList<String> trashID = UserDirector.users.get(email).getTrash();
        trashID.clear();
        userDir.saveUser(UserDirector.users.get(email));
    }
    public ArrayList<Mail> sortByPriority(ArrayList<Mail> Folder){
        ArrayList<Mail> folder = sortByDate(Folder);
        PriorityQueue<Mail> priorityQueue = new PriorityQueue<>(new PriorityComparator());
        priorityQueue.addAll(folder); // for loop
        folder.clear();
        int size = priorityQueue.size();
        for(int i=0; i<size; i++){
            folder.add(priorityQueue.poll());
        }
        return folder;
    }
    public ArrayList<Mail> filter(ArrayList<Mail> mails, String type, HashMap<String, ArrayList<String>> criteriaMap){ //type is AND - OR
        CriteriaFactory criteriaFactory = new CriteriaFactory(criteriaMap);
        return criteriaFactory.criteriaPattern(mails, type, criteriaMap);
    }
    private boolean isDateMoreThan30DaysAgo(Date mailDate, Date currentDate) {
        long thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000;
        return (currentDate.getTime() - mailDate.getTime()) > thirtyDaysInMillis;
    }
}
