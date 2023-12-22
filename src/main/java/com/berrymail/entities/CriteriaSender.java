package com.berrymail.entities;
import java.util.ArrayList;
public class CriteriaSender implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria) {
        ArrayList<Mail> filtered = new ArrayList<>();
        for(String sender : criteria){
            for(Mail mail : mails){
                if(mail.getFrom().equalsIgnoreCase(sender))
                    filtered.add(mail);
            }
        }
        return filtered;
    }
}
