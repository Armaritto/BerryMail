package com.berrymail.services;
public interface MediatorIF {
    public String addMails(String from, String to, String subject, String body, String date, String time, String priority, String attachment);
}
