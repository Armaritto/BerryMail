package com.berrymail.entities;
public class MailBuilder implements MailBuilderIF{
    private String id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private String date;
    private String time;
    private String priority;
    private String attachment;
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
    public void generateDate(String date){
        this.date = date;
    }
    public void generateTime(String time){
        this.time = time;
    }
    public void generatePriority(String priority){
        this.priority = priority;
    }
    public void generateAttachment(String attachment){
        this.attachment = attachment;
    }
    public Mail getMail(){
        return new Mail(id, from, to, subject, body, date, time, priority, attachment);
    }

}
