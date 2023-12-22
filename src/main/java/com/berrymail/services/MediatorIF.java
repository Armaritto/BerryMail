package com.berrymail.services;
import com.berrymail.entities.Mail;
public interface MediatorIF {
    public String addMails(String from, String to, String subject, String body, String priority, String attachment);
    public String moveToDraft(String from, String to, String subject, String body, String priority, String attachment);
    public Mail getFromDraft(String email, String id);
}
