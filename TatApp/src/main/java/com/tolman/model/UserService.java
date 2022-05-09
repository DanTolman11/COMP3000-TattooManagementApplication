package com.tolman.model;

import com.tolman.model.database.UserDAO;
import com.tolman.utils.SecurityUtils;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static UserService INSTANCE;

    private UserDAO userDAO = UserDAO.getInstance();

    private UserService(){

    }

    public static UserService getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public List<User> getAllUsers(){
        try{
            return userDAO.getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean createUser(User user) {
        if(!validateUser(user)){
            return false;
        }
        if(user.getId() != 0){
            return false;
        }
        try {
            return userDAO.createUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        if(user.getId() == 0){
            return false;
        }
        try {
            return userDAO.updateUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(User user) {
        try{
            userDAO.deleteUser(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean validateUser(User user) {
        return !user.getUsername().equals("Fuck");
    }

    public boolean validateLoginCredentials(String username, String password){
        Optional<User> user = UserDAO.getInstance().getUserByUsername(username);
        if(!user.isPresent()){
            return false;
        }
        try {
            return user.get().getPassword().equals(SecurityUtils.hexDigest(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Passed failed");
        }
        return false;
    }



}
