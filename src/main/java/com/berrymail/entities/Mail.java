package com.berrymail.entities;
import java.util.ArrayList;
import java.util.Date;
public class Mail {
    private String id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private Date dateNtime;
    private String priority;
    private ArrayList<Attachment> attachments;
    public Mail(String id, String from, String to, String subject, String body, Date dateNtime, String priority, ArrayList<Attachment> attachments) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.dateNtime = dateNtime;
        this.priority = priority;
        this.attachments = attachments;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Date getDateNtime() {
        return dateNtime;
    }
    public void setDateNtime(Date dateNtime) {
        this.dateNtime = dateNtime;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }
    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }
    @Override
    public String toString() {
        return "Mail{" +
                "id='" + id + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", dateNtime=" + dateNtime +
                ", priority='" + priority + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
