package com.berrymail.entities;
import java.util.ArrayList;
public class CriteriaAttachment implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria) {
        ArrayList<Mail> filtered = new ArrayList<>();
        for(String attachment : criteria){
            for(Mail mail : mails){
                if(mail.getAttachment().equalsIgnoreCase(attachment))
                    filtered.add(mail);
            }
        }
        return filtered;
    }
}
