package com.berrymail.services;
import com.berrymail.entities.*;
import java.util.Map;
import java.util.TreeMap;
import java.io.IOException;
import java.util.*;
public class UserService {
    UserBuilderIF userBuilder = new UserBuilder();
    UserDirector userDir = new UserDirector(userBuilder);
    public String createAccount(String fname, String lname, String username, String email, String password, ArrayList<String> inbox ,ArrayList<String> sent, ArrayList<String> trash ,ArrayList<String> draft, HashMap<String, ArrayList<String>> customFolders, HashMap<String, ArrayList<String>> contacts) throws IOException {
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
            userDir.makeUser(fname, lname, username, email, password, inbox, sent, trash, draft,customFolders, contacts);
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
    public String createFolder(String email, String folderName) throws IOException {
        if(UserDirector.users.get(email).getCustomFolders().containsKey(folderName)){
            return "Folder already exists";
        }
        UserDirector.users.get(email).getCustomFolders().put(folderName, new ArrayList<String>());
        userDir.saveUser(UserDirector.users.get(email));
        return "Folder created";
    }
    public  HashMap<String, ArrayList<String>>  deleteFolder(String email, String folderName) throws IOException {
        if(UserDirector.users.get(email).getCustomFolders().containsKey(folderName)){
            UserDirector.users.get(email).getCustomFolders().remove(folderName);
            userDir.saveUser(UserDirector.users.get(email));
        }
        return UserDirector.users.get(email).getCustomFolders();
    }
    public HashMap<String, ArrayList<String>> renameFolder(String email, String oldFolderName, String newFolderName) throws IOException {
        if(UserDirector.users.get(email).getCustomFolders().containsKey(oldFolderName)){
            ArrayList<String> contentIDs = UserDirector.users.get(email).getCustomFolders().get(oldFolderName);
            deleteFolder(email, oldFolderName);
            UserDirector.users.get(email).getCustomFolders().put(newFolderName, contentIDs);
            userDir.saveUser(UserDirector.users.get(email));

        }
        return UserDirector.users.get(email).getCustomFolders();
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
        Date newDate = new Date();
        MailDirector.mails.get(mailID).setDateNtime(newDate);
        UserDirector.users.get(email).getTrash().add(mailID);
        userDir.saveUser(UserDirector.users.get(email));
        return "Mail added to trash";
    }
    public String addMailToCustomFolder(String email, String folderName, String mailID) throws IOException {
        UserDirector.users.get(email).getCustomFolders().get(folderName).add(mailID);
        userDir.saveUser(UserDirector.users.get(email));
        return "Mail added to folder" + folderName;
    }
    public ArrayList<Mail> customFolderList(String email, String folderName, String Sortcriteria){
        ArrayList<String> customFolderID = UserDirector.users.get(email).getCustomFolders().get(folderName);
        ArrayList<Mail> customFolder= new ArrayList<>();
        for(int i=0; i<customFolderID.size(); i++){
            customFolder.add(MailDirector.mails.get(customFolderID.get(i)));
        }
        if(Sortcriteria.equals("Priority")){
            return sortByPriority(customFolder);
        }
        return sortByDate(customFolder);
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
    public String addContact(String userMail, String name, ArrayList<String> emails) throws IOException {
        if(UserDirector.users.get(userMail).getContacts().containsKey(name)){
            return "Name already taken";
        }
        UserDirector.users.get(userMail).getContacts().put(name, emails);
        userDir.saveUser(UserDirector.users.get(userMail));
        return "Contact added";
    }
    public HashMap<String, ArrayList<String>> contactsList(String email) {
        return UserDirector.users.get(email).getContacts();
    }
    public HashMap<String, ArrayList<String>> deleteContact(String userMail, String name) throws IOException {
        if(UserDirector.users.get(userMail).getContacts().containsKey(name)){
            UserDirector.users.get(userMail).getContacts().remove(name);
            userDir.saveUser(UserDirector.users.get(userMail));
        }
        return UserDirector.users.get(userMail).getContacts();
    }
    public HashMap<String, ArrayList<String>> editContact(String userMail, String oldContactName, String newContactName, ArrayList<String> emails) throws IOException {
        if(UserDirector.users.get(userMail).getContacts().containsKey(oldContactName)){
            deleteContact(userMail, oldContactName);
            UserDirector.users.get(userMail).getContacts().put(newContactName, emails);
            userDir.saveUser(UserDirector.users.get(userMail));
        }
        return UserDirector.users.get(userMail).getContacts();
    }
    public Map<String, ArrayList<String>> sortContacts(String userMail){
        Map<String, ArrayList<String>> sortedMap = new TreeMap<>(String::compareTo);
        sortedMap.putAll(UserDirector.users.get(userMail).getContacts());
        return sortedMap;
    }


    public ArrayList<String> searchContatct(String email, String contactName) throws IOException {
        if(UserDirector.users.get(email).getContacts().containsKey(contactName)) {
            return UserDirector.users.get(email).getContacts().get(contactName);
        }
        return  null ;

    }

}
