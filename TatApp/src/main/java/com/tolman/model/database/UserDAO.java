package com.tolman.model.database;

import com.tolman.model.User;

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

    public List<User> getAllUsers() throws SQLException {
        List<User> results = new ArrayList<>();
        String sql = String.format("select * from user");
        Statement statement = connection.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt("id"));
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

    public Optional<User> getUserByUsername(String username) {
        try {
            String sql = String.format("select * from user where username='%s'", username);
            Statement statement = connection.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
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

    public boolean createUser(User user) throws SQLException {
        String sql = String.format("INSERT INTO user(username, password) values('%s','%s')", user.getUsername(), user.getPassword());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()){
            user.setId(rs.getInt(1));
        }
        return true;
    }

    public boolean updateUser(User user) throws SQLException {
        String sql = String.format("UPDATE user set username='%s', password='%s' WHERE id='%d'", user.getUsername(), user.getPassword(), user.getId());
        Statement statement = connection.getConnection().createStatement();
        statement.executeUpdate(sql);
        return true;
    }

    public void deleteUser(User user) throws SQLException {
        String sql = String.format("DELETE FROM user WHERE id='%d'", user.getId());
        Statement statement = connection.getConnection().createStatement();
        statement.execute(sql);
    }
}
