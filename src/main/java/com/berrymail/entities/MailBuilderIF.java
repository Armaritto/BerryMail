package com.berrymail.entities;
import java.util.ArrayList;
import java.util.Date;
public interface MailBuilderIF {
    public void generateId(String id);
    public void generateFrom(String from);
    public void generateTo(String to);
    public void generateSubject(String subject);
    public void generateBody(String body);
    public void generateDateNTime(Date dateNtime);
    public void generatePriority(String priority);
    public void generateAttachment( ArrayList<Attachment> attachments);
    public Mail getMail();
}
