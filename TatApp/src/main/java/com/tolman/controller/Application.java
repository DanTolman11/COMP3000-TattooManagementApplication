package com.tolman.controller;

import com.tolman.model.database.DBConnection;

import javax.swing.*;
import java.sql.SQLException;

public class Application {

    private static Application INSTANCE;

    private static JFrame frame;

    private Application() {
        frame = new JFrame("Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setWindow(JPanel panel){
        frame.setContentPane(panel);
        frame.revalidate();
        frame.invalidate();
        frame.repaint();
    }

    public synchronized static Application getInstance() {
        if (INSTANCE == null) {
                INSTANCE = new Application();
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        Application application = Application.getInstance();
        application.setWindow(LoginPanel.getInstance());
    }
}
