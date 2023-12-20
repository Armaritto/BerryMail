package com.berrymail.entities;
import java.util.Date;
import java.util.HashMap;

public class MailDirector {
    public static HashMap<String, Mail> mails = new HashMap<>();
    private final MailBuilderIF mailbuilder;
    public MailDirector(MailBuilderIF mailbuilder){
        this.mailbuilder = mailbuilder;
    }
    public Mail getMail(){
        return this.mailbuilder.getMail();
    }
    public void constructMail(String id, String from, String to, String subject, String body, Date dateNtime, String priority, String attachment){
        mailbuilder.generateId(id);
        mailbuilder.generateFrom(from);
        mailbuilder.generateTo(to);
        mailbuilder.generateSubject(subject);
        mailbuilder.generateBody(body);
        mailbuilder.generateDateNTime(dateNtime);
        mailbuilder.generatePriority(priority);
        mailbuilder.generateAttachment(attachment);
        mails.put(id, this.mailbuilder.getMail());

    }
}
