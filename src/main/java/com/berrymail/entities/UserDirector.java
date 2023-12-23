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
import java.util.HashMap;
public class UserDirector {
    public static HashMap <String, User> users = new HashMap<>();
    private UserBuilderIF userbuilder ;
    public UserDirector(UserBuilderIF userbuilder){
        this.userbuilder= userbuilder;
    }
    public User getUser(){
        return this.userbuilder.getUser();
    }
    public void makeUser(String fname, String lname, String username, String email, String password, ArrayList<String> inbox ,ArrayList<String> sent, ArrayList<String> trash ,ArrayList<String> draft) throws IOException {
        if(!users.containsKey(email)){
            this.userbuilder.generateFirstName(fname);
            this.userbuilder.generateLastName(lname);
            this.userbuilder.generateUsername(username);
            this.userbuilder.generateEmail(email);
            this.userbuilder.generatePassword(password);
            this.userbuilder.generateInbox(inbox);
            this.userbuilder.generateTrash(trash);
            this.userbuilder.generateDraft(draft);
            this.userbuilder.generateSent(sent);
            users.put(email, this.getUser());
            saveUser(this.getUser());
        }
    }
    public HashMap<String, User> loadUserFromFile() throws IOException {
        HashMap<String,User> map;
        Path p = Path.of("./src/main/java/com/berrymail/assets/accounts.json");
        String json;
        try{
            json = Files.readString(p);
        }
        catch(Exception e){
            File file = new File("./src/main/java/com/berrymail/assets/accounts.json");
            file.createNewFile();
            json = Files.readString(p);
        }
        Type listType = new TypeToken<HashMap<String,User>>() {}.getType();
        map = new Gson().fromJson(json, listType);
        if(map == null)
            map = new HashMap<>();
        users = map;
        return map;
    }
    public void saveUser(User user) throws IOException {
        HashMap<String,User> map = loadUserFromFile();
        Gson gson = new Gson();
        map.put(user.getEmail(),user);
        String json = gson.toJson(map);
        FileWriter write = new FileWriter("./src/main/java/com/berrymail/assets/accounts.json");
        write.write(json);
        write.close();
        users = map;
    }
}
