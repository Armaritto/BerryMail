package com.berrymail.services;
import com.berrymail.entities.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
public class MailService {
    MailBuilderIF mailBuilder = new MailBuilder();
    MailDirector mailDir = new MailDirector(mailBuilder);
    public Mail createMail(String id, String from, String to, String subject, String body, Date dateNtime, String priority,  ArrayList<Attachment> attachments) throws IOException {
        mailDir.constructMail(id, from, to,subject, body, dateNtime, priority, attachments);
        return mailDir.getMail();
    }
}
