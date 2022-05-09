package com.tolman.model.database;

import com.tolman.utils.ConfigurationManager;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection INSTANCE;
    private ConfigurationManager configman = ConfigurationManager.getInstance();
    private Connection connection;

    private DBConnection() throws SQLException {
        connect();
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            URL url = DBConnection.class.getResource(configman.getStringProperty("db.url"));
            String connectionString = String.format("jdbc:sqlite:%s", new File(url.toURI()).getAbsolutePath());
            // create a connection to the database
            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public synchronized static DBConnection getInstance() {
        if (INSTANCE == null) {
            try {
                INSTANCE = new DBConnection();
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return INSTANCE;
    }
}
