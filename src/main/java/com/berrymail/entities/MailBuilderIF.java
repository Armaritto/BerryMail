package com.berrymail.entities;
public interface MailBuilderIF {
    public void generateId(String id);
    public void generateFrom(String from);
    public void generateTo(String to);
    public void generateSubject(String subject);
    public void generateBody(String body);
    public void generateDate(String date);
    public void generateTime(String time);
    public void generatePriority(String priority);
    public void generateAttachment(String attachment);
    public Mail getMail();
}
