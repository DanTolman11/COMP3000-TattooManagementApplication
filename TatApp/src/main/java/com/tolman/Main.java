package com.tolman;

import com.tolman.model.User;
import com.tolman.model.database.UserDAO;

import java.sql.SQLException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDAO userdao = UserDAO.getInstance();
        Optional<User> applicationUser = userdao.getUserByUsername("dan");
        if(!applicationUser.isPresent()){
            System.out.println("user does not exist");
            return;
        }
        System.out.println(applicationUser.get().getPassword());

    }
}
