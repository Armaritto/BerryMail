package com.berrymail.entities;
public class MailDirector {
    private final MailBuilderIF mailbuilder;
    public MailDirector(MailBuilderIF mailbuilder){
        this.mailbuilder = mailbuilder;
    }
    public Mail getMail(){
        return this.mailbuilder.getMail();
    }
    public void constructMail(String id, String from, String to, String subject, String body, String date, String time, String priority, String folder, String attachment){
        mailbuilder.generateId(id);
        mailbuilder.generateFrom(from);
        mailbuilder.generateTo(to);
        mailbuilder.generateSubject(subject);
        mailbuilder.generateBody(body);
        mailbuilder.generateDate(date);
        mailbuilder.generateTime(time);
        mailbuilder.generatePriority(priority);
        mailbuilder.generateFolder(folder);
        mailbuilder.generateAttachment(attachment);
    }
}
