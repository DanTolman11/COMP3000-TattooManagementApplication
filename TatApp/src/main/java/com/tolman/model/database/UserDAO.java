package com.tolman.model.database;

import com.tolman.model.User;
import com.tolman.utills.ConfigurationManager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    private static UserDAO INSTANCE;
    private DBConnection connection = DBConnection.getInstance();
    private UserDAO(){

    }
    public Optional<User> getUserByUsername(String username) {
        try {
            String sql = String.format("select * from user where username='%s'", username);
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return Optional.of(user);
            }
            return Optional.empty();
        }catch (SQLException e){
            e.printStackTrace();
            return Optional.empty();
        }


    }
    public List<User> getAllUsers() throws SQLException {
        List<User> results = new ArrayList<>();
        String sql = String.format("select * from user");
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            results.add(user);
        }
        return results;

    }
    public static UserDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDAO();
        }

        return INSTANCE;
    }
}
