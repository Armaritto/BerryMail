package com.berrymail.services;

import com.berrymail.entities.Mail;
import com.berrymail.entities.MailBuilder;

import com.berrymail.entities.MailBuilderIF;
import com.berrymail.entities.MailDirector;

public class MailService {
    MailBuilderIF mailBuilder = new MailBuilder();
    MailDirector mailDir = new MailDirector(mailBuilder);
    public Mail createMail(String id, String from, String to, String subject, String body, String date, String time, String priority, String attachment){
        mailDir.constructMail(id, from, to,subject, body, date, time, priority, attachment);
        return mailDir.getMail();
    }

}
