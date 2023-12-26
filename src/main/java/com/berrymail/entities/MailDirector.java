package com.berrymail.entities;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MailDirector {
    public static HashMap<String, Mail> mails = new HashMap<>();
    private final MailBuilderIF mailbuilder;
    public MailDirector(MailBuilderIF mailbuilder){
        this.mailbuilder = mailbuilder;
    }
    public Mail getMail(){
        return this.mailbuilder.getMail();
    }
    public void constructMail(String id, String from, String to, String subject, String body, Date dateNtime, String priority,  ArrayList<Attachment> attachments) throws IOException {
        mailbuilder.generateId(id);
        mailbuilder.generateFrom(from);
        mailbuilder.generateTo(to);
        mailbuilder.generateSubject(subject);
        mailbuilder.generateBody(body);
        mailbuilder.generateDateNTime(dateNtime);
        mailbuilder.generatePriority(priority);
        mailbuilder.generateAttachment(attachments);
        mails.put(id, this.mailbuilder.getMail());
        saveMail(this.mailbuilder.getMail());
    }
    public HashMap<String, Mail> loadMailFromFile() throws IOException {
        HashMap<String,Mail> map;
        Path p = Path.of("./src/main/java/com/berrymail/assets/mails.json");
        String json;
        try{
            json = Files.readString(p);
        }
        catch(Exception e){
            File file = new File("./src/main/java/com/berrymail/assets/mails.json");
            file.createNewFile();
            json = Files.readString(p);
        }
        Type listType = new TypeToken<HashMap<String,Mail>>() {}.getType();
        map = new Gson().fromJson(json, listType);
        if(map == null)
            map = new HashMap<>();
        mails = map;
        return map;
    }
    public void saveMail(Mail mail) throws IOException {
        HashMap<String,Mail> map = loadMailFromFile();
        Gson gson = new Gson();
        map.put(mail.getId(),mail);
        String json = gson.toJson(map);
        FileWriter write = new FileWriter("./src/main/java/com/berrymail/assets/mails.json");
        write.write(json);
        write.close();
        mails = map;
    }
    public void deleteMail(Mail mail) throws IOException {
        HashMap<String,Mail> map = loadMailFromFile();
        Gson gson = new Gson();
        map.remove(mail.getId());
        String json = gson.toJson(map);
        FileWriter write = new FileWriter("./src/main/java/com/berrymail/assets/mails.json");
        write.write(json);
        write.close();
        mails = map;
    }
}
