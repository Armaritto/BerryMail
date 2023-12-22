package com.berrymail.entities;
import java.util.ArrayList;
public class CriteriaSubject implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> subjects) {
        ArrayList<Mail> filtered = new ArrayList<>();
        for(String subject : subjects){
            for(Mail mail : mails){
                 if(mail.getSubject().toLowerCase().contains(subject.toLowerCase()))
                     filtered.add(mail);
            }
        }
        return filtered;
    }
}
