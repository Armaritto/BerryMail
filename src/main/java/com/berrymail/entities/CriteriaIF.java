package com.berrymail.entities;
import java.util.ArrayList;
public interface CriteriaIF {
    public ArrayList<Mail> meetCriteria(ArrayList<Mail> mails, ArrayList<String> criteria);
}
