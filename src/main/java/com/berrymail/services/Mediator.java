package com.berrymail.services;

public class Mediator implements MediatorIF {
    MailService mailService =  new MailService();
    UserService userService = new UserService();
    private String ID;
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
    @Override
    public String addMails(String from, String to, String subject, String body, String date, String time, String priority, String attachment) {
        generateID();
        mailService.createMail(this.getID(), from, to, subject, body, date, time, priority, attachment);
        userService.addMailToInbox(to, ID);
        userService.addMailToSent(from, ID);
        return "Successfully sent";
    }
}
