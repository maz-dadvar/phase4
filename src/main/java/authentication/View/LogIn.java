package authentication.View;

import interfaces.Dimensions;
import interfaces.Listener;
import view.SelectTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends SelectTemplate implements Dimensions, ActionListener {

    private JButton           button;
    private JLabel            usernameLabel;
    private JLabel            passwordLabel;
    public  JLabel            warning;
    public  JTextField        usernameField;
    public  JTextField        passwordField;
    private JPanel            logo;
    private LogInFormListener logInFormListener;
    public  LogInFormEvent    logInFormEvent;

    public LogIn() {
        super();
        logInFormListener = new LogInFormListener();
        this.setBounds(0, 0, frameWidth, selectPanelHeight);
        int logoX = (int) ((frameWidth - logoWidth) / 2.15);
        int logoY = (int) ((frameWidth - logoHeight) / 2.15);

        button        = new JButton("Log In");
        logo          = new JPanel();
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        warning       = new JLabel();
        usernameField = new JTextField();
        passwordField = new JTextField();
        JLabel label = new JLabel();

        ImageIcon icon = new ImageIcon("./resources/logo_resized.jpg");
        label.setIcon(icon);

        button.setBorder(BorderFactory.createRaisedBevelBorder());
        usernameField.setBorder(BorderFactory.createRaisedBevelBorder());
        passwordField.setBorder(BorderFactory.createRaisedBevelBorder());

        logo.setBackground(Color.WHITE);
        button.setBackground(buttonColor);
        usernameField.setBackground(Color.WHITE);
        passwordField.setBackground(Color.WHITE);

        logo.setBounds(logoX, logoY , logoWidth, logoHeight);
        button.setBounds(frameWidth/2 - buttonWidth/2, 500, buttonWidth, buttonHeight);
        usernameLabel.setBounds(50, 400, 100, 50);
        usernameField.setBounds(150, 415, 200, 25);
        passwordLabel.setBounds(50, 430, 100, 50);
        passwordField.setBounds(150, 445, 200, 25);
        warning.setBounds(152, 460, 250, 50);

        usernameLabel.setText("Username      : ");
        passwordLabel.setText("Password      : ");
        warning.setText("username or password is wrong!");

        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        passwordField.setForeground(Color.BLACK);
        warning.setForeground(Color.RED);

        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        warning.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        logo.add(label);

        this.add(logo);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(warning);
        this.add(button);

        warning.setVisible(false);
        button.addActionListener(this);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }

    public void setLogInFormListener(LogInFormListener logInFormListener) {
        this.logInFormListener = logInFormListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners){
            if (e.getSource() == button){
                logInFormEvent = new LogInFormEvent(this, getUsername(), getPassword());
                logInFormListener.eventOccurred(logInFormEvent);
                listener.Listen("Log In");
            }
        }
    }
}
