package com.berrymail.entities;
import java.util.ArrayList;
public class CriteriaPriority implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria) {
        ArrayList<Mail> filtered = new ArrayList<>();
        for(String priority : criteria){
            for(Mail mail : mails){
                if(mail.getPriority().equalsIgnoreCase(priority))
                    filtered.add(mail);
            }
        }
        return filtered;
    }
}
