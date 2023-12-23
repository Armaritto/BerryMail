package com.berrymail.services;
import com.berrymail.entities.Mail;

import java.io.IOException;
import java.util.ArrayList;

public interface MediatorIF {
    String addMails(String from, ArrayList<String> to, String subject, String body, String priority, String attachment) throws IOException;

    public String moveToDraft(String from, String to, String subject, String body, String priority, String attachment) throws IOException;
    public Mail getFromDraft(String email, String id) throws IOException;
}
