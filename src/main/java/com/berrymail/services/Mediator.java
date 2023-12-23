package com.berrymail.services;
import java.io.IOException;

import java.util.ArrayList;

import java.util.Date;
import com.berrymail.entities.Mail;
import com.berrymail.entities.MailDirector;
import com.berrymail.entities.UserDirector;
public class Mediator implements MediatorIF {
    MailService mailService =  new MailService();
    UserService userService = new UserService();
    private String ID;
    private Date dateNtime;
    public Date getDateNtime() {
        return dateNtime;
    }
    public void setDateNtime(Date dateNtime) {
        this.dateNtime = dateNtime;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void generateID(){
        int temp = (int) (Math.random() * 1000000);
        this.setID(Integer.toString(temp));
    }
    public void generateDateNTime(){
        this.dateNtime = new Date();
    }
    @Override

    public String addMails(String from, ArrayList<String> to, String subject, String body, String priority, String attachment) throws IOException {
        generateID();
        generateDateNTime();
        String bigTo = "";
        for (String s : to) {
             bigTo += s;
             if(to.indexOf(s) != to.size()-1)
                bigTo += ",";
        }
        mailService.createMail(this.getID(), from, bigTo, subject, body, this.getDateNtime(), priority, attachment);
        for (String s : to) {
            userService.addMailToInbox(s, ID);
        }
        userService.addMailToSent(from, ID);
        return "Successfully sent";
    }
    @Override
    public String moveToDraft(String from, String to, String subject, String body, String priority, String attachment) throws IOException {
        generateID();
        generateDateNTime();
        mailService.createMail(this.getID(), from, to, subject, body, this.getDateNtime(), priority, attachment);
        userService.addMailToDraft(from, ID);

        return "Mail added to draft";
    }
    @Override
    public Mail getFromDraft(String email, String id) throws IOException {
        UserDirector.users.get(email).getDraft().remove(id);
        Mail temp = MailDirector.mails.remove(id);
        userService.userDir.saveUser(UserDirector.users.get(email));
        mailService.mailDir.deleteMail(temp);
        return temp;
    }
}
