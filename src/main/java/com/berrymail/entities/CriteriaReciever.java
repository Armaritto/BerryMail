package com.berrymail.entities;
import java.util.ArrayList;
public class CriteriaReciever implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria) {
        ArrayList<Mail> filtered = new ArrayList<>();
        for(String reciever : criteria){
            for(Mail mail : mails){
                if(mail.getFrom().equalsIgnoreCase(reciever))
                    filtered.add(mail);
            }
        }
        return filtered;
    }
}
