package com.berrymail.entities;
import java.util.ArrayList;
import java.util.HashMap;
public class CriteriaFactory {
    CriteriaIF criteriaAttachment;
    CriteriaIF criteriaPriority;
    CriteriaIF criteriaReceiver;
    CriteriaIF criteriaSender;
    CriteriaIF criteriaSubject;
    CriteriaIF criteriaBody;
    CriteriaIF criteriaDate;
    //we have criteria attachment, priority, reciever, sender, subject, body, and date we need to make and criteria combinations of them
    //type = AND, OR
    public CriteriaFactory(HashMap<String, ArrayList<String>> criteriaMap){
        for (String criterion : criteriaMap.keySet()) {
            switch (criterion.toLowerCase()) {
                case "attachment":
                    criteriaAttachment = new CriteriaAttachment();
                    break;
                case "priority":
                    criteriaPriority = new CriteriaPriority();
                    break;
                case "receiver":
                    criteriaReceiver = new CriteriaReceiver();
                    break;
                case "sender":
                    criteriaSender = new CriteriaSender();
                    break;
                case "subject":
                    criteriaSubject = new CriteriaSubject();
                    break;
                case "body":
                    criteriaBody = new CriteriaBody();
                    break;
                case "date":
                    criteriaDate = new CriteriaDate();
                    break;
            }
        }
    }
    public ArrayList<Mail> criteriaPattern(ArrayList<Mail> mails, String type,HashMap<String, ArrayList<String>> criteriaMap){
        new CriteriaFactory(criteriaMap);
        ArrayList<Mail> filtered = new ArrayList<>();
        if (type.equalsIgnoreCase("AND")) {
            // Apply "AND" criteria combination
            for (Mail mail : mails) {
                if (meetsAllCriteria(mails, mail, criteriaMap)) {
                    filtered.add(mail);
                }
            }
        }
        else if (type.equalsIgnoreCase("OR")) {
            // Apply "OR" criteria combination
            for (Mail mail : mails) {
                if(meetsAnyCriteria(mails, mail, criteriaMap)) {
                    filtered.add(mail);
                }
            }
        }
        return filtered;
    }
    private boolean meetsAllCriteria(ArrayList<Mail> mails, Mail mail, HashMap<String, ArrayList<String>> criteriaMap) {
        for (String criterion : criteriaMap.keySet()) {
            if (!meetsCriterion(mails, mail, criterion, criteriaMap.get(criterion))) {
                return false;
            }
        }
        return true;
    }
    private boolean meetsAnyCriteria(ArrayList<Mail> mails, Mail mail, HashMap<String, ArrayList<String>> criteriaMap) {
        for (String criterion : criteriaMap.keySet()) {
            if(meetsCriterion(mails, mail, criterion, criteriaMap.get(criterion))) {
                return true;
            }
        }
        return false;
    }
    private boolean meetsCriterion(ArrayList<Mail> mails, Mail mail, String criterion, ArrayList<String> values) {
        ArrayList<Mail> filter;
        switch (criterion.toLowerCase()) {
            case "attachment":
                filter = criteriaAttachment.meetCriteria(mails, values);
                if(filter.contains(mail))
                    return true;
                break;
            case "priority":
                filter = criteriaPriority.meetCriteria(mails, values);
                if(filter.contains(mail))
                    return true;
                break;
            case "receiver":
                filter =criteriaReceiver.meetCriteria(mails, values);
                if(filter.contains(mail))
                    return true;
                break;
            case "sender":
                filter = criteriaSender.meetCriteria(mails, values);
                if(filter.contains(mail))
                    return true;
                break;
            case "subject":
                filter = criteriaSubject.meetCriteria(mails, values);
                if(filter.contains(mail))
                    return true;
                break;
            case "body":
                filter = criteriaBody.meetCriteria(mails, values);
                if(filter.contains(mail))
                    return true;
                break;
            case "date":
                filter = criteriaDate.meetCriteria(mails, values);
                if(filter.contains(mail))
                    return true;
                break;
            default:
                System.out.println("debug: criteriaFactory: meetsCriterion: default case");
                return false;
        }
        return false;
    }
}
