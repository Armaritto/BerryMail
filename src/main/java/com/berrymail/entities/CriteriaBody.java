package com.berrymail.entities;
import java.util.ArrayList;
public class CriteriaBody implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria) {
        ArrayList<Mail> filtered = new ArrayList<>();
        for(String body : criteria){
            for(Mail mail : mails){
                if(mail.getBody().toLowerCase().contains(body.toLowerCase()))
                    filtered.add(mail);
            }
        }
        return filtered;
    }
}
