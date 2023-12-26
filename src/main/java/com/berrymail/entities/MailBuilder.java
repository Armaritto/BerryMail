package com.berrymail.entities;
import java.util.ArrayList;
import java.util.Date;
public class MailBuilder implements MailBuilderIF{
    private String id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private Date dateNtime;
    private String priority;
    private ArrayList<Attachment> attachments;
    public void generateId(String id){
        this.id = id;
    }
    public void generateFrom(String from){
        this.from = from;
    }
    public void generateTo(String to){
        this.to = to;
    }
    public void generateSubject(String subject){
        this.subject = subject;
    }
    public void generateBody(String body){
        this.body = body;
    }
    public void generateDateNTime(Date dateNtime) {
        this.dateNtime = dateNtime;
    }
    public void generatePriority(String priority){
        this.priority = priority;
    }
    public void generateAttachment( ArrayList<Attachment> attachments){
        this.attachments = attachments;
    }
    public Mail getMail(){
        return new Mail(id, from, to, subject, body, dateNtime, priority, attachments);
    }

}
