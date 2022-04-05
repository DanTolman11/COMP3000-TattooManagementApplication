package com.tolman.model;

import com.tolman.controller.Application;
import com.tolman.controller.ApplicationPanel;
import com.tolman.model.database.UserDAO;

import java.util.Optional;

public class UserService {

    private static UserService INSTANCE;

    private UserDAO userDAO = UserDAO.getInstance();

    private UserService(){

    }

    public boolean validateLoginCredentials(String username, String password){
        Optional<User> user = UserDAO.getInstance().getUserByUsername(username);
        if(!user.isPresent()){
            return false;
        }
        return user.get().getPassword().equals(password);
    }

    public static UserService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }
}
