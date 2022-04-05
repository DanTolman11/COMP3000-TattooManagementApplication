package com.tolman.controller;

import com.tolman.model.User;
import com.tolman.model.database.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class ApplicationPanel extends JPanel {

    private static ApplicationPanel INSTANCE;


    private ApplicationPanel() {
        this.add(new JLabel("Application"));
    }

    public static ApplicationPanel getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ApplicationPanel();
        }
        return INSTANCE;
    }
}
