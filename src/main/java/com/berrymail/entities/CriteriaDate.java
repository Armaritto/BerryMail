package com.berrymail.entities;
import java.util.ArrayList;
import java.util.Date;
public class CriteriaDate implements CriteriaIF{
    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria) {
        /* criteria is a list of 2 objects [date, status] where status is {before, after, on} */
        ArrayList<Mail> filtered = new ArrayList<>();
        switch(criteria.get(1)){
            case "before":
                for(Mail mail : mails){
                    if(mail.getDateNtime().before(new Date(criteria.get(0))))
                        filtered.add(mail);
                }
                break;
            case "after":
                for(Mail mail : mails){
                    if(mail.getDateNtime().after(new Date(criteria.get(0))))
                        filtered.add(mail);
                }
                break;
            case "on":
                for(Mail mail : mails){
                    if(mail.getDateNtime().equals(new Date(criteria.get(0))))
                        filtered.add(mail);
                }
                break;
        }
        return filtered;
    }
}
