package com.berrymail.entities;
import java.util.Comparator;
public class PriorityComparator implements Comparator<Mail> {
    private int priorityConverter(Mail mail){
        int priority;
        if(mail.getPriority().equals("Critical")){
            priority = 3;
        }
        else if(mail.getPriority().equals("Urgent")){
            priority = 2;
        }
        else if(mail.getPriority().equals("Moderate")){
            priority = 1;
        }
        else if(mail.getPriority().equals("Default")){
            priority = 0;
        }
        else{
            System.out.println("Debug and see why i am here");
            priority = 0;
        }
        return priority;
    }
    @Override
    public int compare(Mail o1, Mail o2) {
        int priorityMail1 = priorityConverter(o1);
        int priorityMail2 = priorityConverter(o2);
        if(priorityMail1 > priorityMail2){
            return -1;
        }
        else if(priorityMail1 < priorityMail2){
            return 1;
        }
        else{
            return 0;
        }
    }
}
