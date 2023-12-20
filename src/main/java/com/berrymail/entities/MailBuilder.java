package com.berrymail.entities;
public class MailBuilder implements MailBuilderIF{
    private Mail mail;
    @Override
    public void generateId(String id) {
        this.mail.setId(id);
    }
    @Override
    public void generateFrom(String from) {
        this.mail.setId(from);
    }
    @Override
    public void generateTo(String to) {
        this.mail.setId(to);
    }
    @Override
    public void generateSubject(String subject) {
        this.mail.setId(subject);
    }
    @Override
    public void generateBody(String body) {
        this.mail.setId(body);
    }
    @Override
    public void generateDate(String date) {
        this.mail.setId(date);
    }
    @Override
    public void generateTime(String time) {
        this.mail.setId(time);
    }
    @Override
    public void generatePriority(String priority) {
        this.mail.setId(priority);
    }
    @Override
    public void generateFolder(String folder) {
        this.mail.setId(folder);
    }
    @Override
    public void generateAttachment(String attachment) {
        this.mail.setId(attachment);
    }
    @Override
    public Mail getMail() {
        return this.mail;
    }
}
