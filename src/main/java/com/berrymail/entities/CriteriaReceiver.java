package com.berrymail.entities;
import java.util.ArrayList;
public class CriteriaReceiver implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria) {
        ArrayList<Mail> filtered = new ArrayList<>();
        for(String reciever : criteria){
            for(Mail mail : mails){
                if(mail.getTo().toLowerCase().contains(reciever.toLowerCase()))
                    filtered.add(mail);
            }
        }
        return filtered;
    }
}
