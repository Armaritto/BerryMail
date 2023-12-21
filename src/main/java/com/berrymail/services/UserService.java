package com.berrymail.services;
import com.berrymail.entities.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class UserService {
    UserBuilderIF userBuilder = new UserBuilder();
    UserDirector userDir = new UserDirector(userBuilder);
    public String createAccount(String fname, String lname, String username, String email, String password, ArrayList<String> inbox ,ArrayList<String> sent, ArrayList<String> trash ,ArrayList<String> draft){
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
    public ArrayList<Mail> trashList(String email, String Sortcriteria){
        ArrayList<String> trashID = UserDirector.users.get(email).getTrash();
        ArrayList<Mail> trashFolder= new ArrayList<>();
        for(int i=0; i<trashID.size(); i++){
            trashFolder.add(MailDirector.mails.get(trashID.get(i)));
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
    public ArrayList<Mail> filterInbox(String email, String sortCriteria, String filterCriteria, ArrayList<String> objects){
        ArrayList<Mail> inbox = inboxList(email, sortCriteria);
        if(filterCriteria.equals("Subject")){
            return filterBySubject(inbox, objects);
        }
        else if(filterCriteria.equals("Sender")){
            return filterBySender(inbox, objects);
        }
        else if(filterCriteria.equals("Receiver")){
            return filterByReceiver(inbox, objects);
        }
        else if(filterCriteria.equals("Priority")){
            return filterByPriority(inbox, objects);
        }
        else if(filterCriteria.equals("Attachment")){
            return filterByAttachment(inbox, objects);
        }
        else if(filterCriteria.equals("Date")){
            return filterByDate(inbox, objects);
        }
        else if(filterCriteria.equals("Body")){
            return filterByBody(inbox, objects);
        }
        else {
            return inbox;
        }

    }
    public ArrayList<Mail> filterDraft(String email, String sortCriteria, String filterCriteria, ArrayList<String> objects){
        ArrayList<Mail> draft = inboxList(email, sortCriteria);
        if(filterCriteria.equals("Subject")){
            return filterBySubject(draft, objects);
        }
        else if(filterCriteria.equals("Sender")){
            return filterBySender(draft, objects);
        }
        else if(filterCriteria.equals("Receiver")){
            return filterByReceiver(draft, objects);
        }
        else if(filterCriteria.equals("Priority")){
            return filterByPriority(draft, objects);
        }
        else if(filterCriteria.equals("Attachment")){
            return filterByAttachment(draft, objects);
        }
        else if(filterCriteria.equals("Date")){
            return filterByDate(draft, objects);
        }
        else if(filterCriteria.equals("Body")){
            return filterByBody(draft, objects);
        }
        else {
            return draft;
        }
    }
    public ArrayList<Mail> filterTrash(String email, String sortCriteria, String filterCriteria, ArrayList<String> objects){
        ArrayList<Mail> trash = inboxList(email, sortCriteria);
        if(filterCriteria.equals("Subject")){
            return filterBySubject(trash, objects);
        }
        else if(filterCriteria.equals("Sender")){
            return filterBySender(trash, objects);
        }
        else if(filterCriteria.equals("Receiver")){
            return filterByReceiver(trash, objects);
        }
        else if(filterCriteria.equals("Priority")){
            return filterByPriority(trash, objects);
        }
        else if(filterCriteria.equals("Attachment")){
            return filterByAttachment(trash, objects);
        }
        else if(filterCriteria.equals("Date")){
            return filterByDate(trash, objects);
        }
        else if(filterCriteria.equals("Body")){
            return filterByBody(trash, objects);
        }
        else {
            return trash;
        }
    }
    public ArrayList<Mail> filterSent(String email, String sortCriteria, String filterCriteria, ArrayList<String> objects){
        ArrayList<Mail> sent = inboxList(email, sortCriteria);
        if(filterCriteria.equals("Subject")){
            return filterBySubject(sent, objects);
        }
        else if(filterCriteria.equals("Sender")){
            return filterBySender(sent, objects);
        }
        else if(filterCriteria.equals("Receiver")){
            return filterByReceiver(sent, objects);
        }
        else if(filterCriteria.equals("Priority")){
            return filterByPriority(sent, objects);
        }
        else if(filterCriteria.equals("Attachment")){
            return filterByAttachment(sent, objects);
        }
        else if(filterCriteria.equals("Date")){
            return filterByDate(sent, objects);
        }
        else if(filterCriteria.equals("Body")){
            return filterByBody(sent, objects);
        }
        else {
            return sent;
        }
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
    public ArrayList<Mail> sortByDate(ArrayList<Mail> Folder){
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
    public ArrayList<Mail> filterBySubject(ArrayList<Mail> Folder, ArrayList<String> subjects){
        CriteriaIF subjectCriteria = new CriteriaSubject();
        return subjectCriteria.meetCriteria(Folder, subjects);
    }
    public ArrayList<Mail> filterBySender(ArrayList<Mail> Folder, ArrayList<String> senders){
        CriteriaIF senderCriteria = new CriteriaSender();
        return senderCriteria.meetCriteria(Folder, senders);
    }
    public ArrayList<Mail> filterByReceiver(ArrayList<Mail> Folder, ArrayList<String> receivers){
        CriteriaIF receiverCriteria = new CriteriaReciever();
        return receiverCriteria.meetCriteria(Folder, receivers);
    }
    public ArrayList<Mail> filterByPriority(ArrayList<Mail> Folder, ArrayList<String> priority){
        CriteriaIF priorityCriteria = new CriteriaPriority();
        return priorityCriteria.meetCriteria(Folder, priority);
    }
    public ArrayList<Mail> filterByBody(ArrayList<Mail> Folder, ArrayList<String> body){
        CriteriaIF bodyCriteria = new CriteriaBody();
        return bodyCriteria.meetCriteria(Folder, body);
    }
    public ArrayList<Mail> filterByDate(ArrayList<Mail> Folder, ArrayList<String> date){
        CriteriaIF dateCriteria = new CriteriaDate();
        return dateCriteria.meetCriteria(Folder, date);
    }
public ArrayList<Mail> filterByAttachment(ArrayList<Mail> Folder, ArrayList<String> attachment){
    CriteriaIF attachmentCriteria = new CriteriaAttachment();
    return attachmentCriteria.meetCriteria(Folder, attachment);
}

}
