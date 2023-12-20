package com.berrymail.services;
import com.berrymail.entities.User;
import com.berrymail.entities.UserBuilder;
import com.berrymail.entities.UserBuilderIF;
import com.berrymail.entities.UserDirector;
import java.util.ArrayList;
import java.util.Objects;
public class UserService {
    UserBuilderIF userBuilder = new UserBuilder();
    UserDirector userDir = new UserDirector(userBuilder);
    public String createAccount(String fname, String lname, String username, String email, String password, ArrayList<String> inbox ,ArrayList<String> sent, ArrayList<String> trash ,ArrayList<String> draft){
        if(UserDirector.users.get(email) == null){
            userDir.makeUser(fname, lname, username, email, password, inbox, sent, trash, draft);
            return "Account created";
        }
        return "An account already exists";
    }
    public User accessAccount(String email, String password) throws Exception {
        if(UserDirector.users.get(email) == null){
            throw new Exception("Account doesn't exist");
        }
        else{
           if(Objects.equals(UserDirector.users.get(email).getPassword(), password)) {
               return UserDirector.users.get(email);
           }
           else if(!Objects.equals(UserDirector.users.get(email).getPassword(), password)){
               throw new Exception("Wrong password!");
           }
        }
        return null;
    }
    public String addMailToInbox(String email, String mailID){
        UserDirector.users.get(email).getInbox().add(mailID);
        return "Mail added to inbox";
    }
    public String addMailToSent(String email, String mailID){
        UserDirector.users.get(email).getSent().add(mailID);
        return "Mail sent Successfully";
    }

}
