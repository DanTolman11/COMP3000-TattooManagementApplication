package com.tolman;

import com.tolman.view.panels.LoginPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Application extends JPanel {

    private static Application INSTANCE;

    private static JFrame frame;

    private Application() {
        frame = new JFrame("Application");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit the application? You may have unsaved changes", "Confirm", JOptionPane.YES_NO_OPTION);

                // return if user says no
                if(response == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        frame.setVisible(true);
    }

    public void setWindow(JPanel panel){
        frame.setContentPane(panel);
        frame.revalidate();
        frame.invalidate();
        frame.repaint();
    }

    public static JFrame getFrame() {
        return frame;
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
