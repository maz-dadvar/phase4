package authentication.View;

import interfaces.Dimensions;
import interfaces.Listener;
import view.SelectTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends SelectTemplate implements ActionListener, Dimensions {

    private JButton    button;
    private JLabel     nameLabel;
    private JLabel     lastnameLabel;
    private JLabel     emailLabel;
    private JLabel     usernameLabel;
    private JLabel     passwordLabel;
    private JLabel     passwordNotice;
    private JLabel     dateOfBirthLabel;
    private JLabel     phoneNumberLabel;
    private JLabel     bioLabel;
    private JLabel     required1;
    private JLabel     required2;
    private JLabel     required3;
    private JLabel     required4;
    private JLabel     required5;
    private JLabel     optional1;
    private JLabel     optional2;
    private JLabel     optional3;
    private JLabel     warning;
    public  JTextField name;
    public  JTextField lastname;
    public  JTextField emailAddress;
    public  JTextField username;
    public  JTextField password;
    public  JTextField year;
    public  JTextField month;
    public  JTextField day;
    public  JTextField phoneNumber;
    public  JTextField bio;
    private RegistrationFormListener formListener;
    public  RegistrationFormEvent signUpFormEvent;

    public SignUp() {
        super();
        this.setBounds(0, 0, frameWidth, selectPanelHeight);

        formListener     = new RegistrationFormListener();
        button           = new JButton("Sign Up");
        nameLabel        = new JLabel();
        lastnameLabel    = new JLabel();
        emailLabel       = new JLabel();
        usernameLabel    = new JLabel();
        passwordLabel    = new JLabel();
        passwordNotice   = new JLabel();
        dateOfBirthLabel = new JLabel();
        phoneNumberLabel = new JLabel();
        bioLabel         = new JLabel();
        required1        = new JLabel();
        required2        = new JLabel();
        required3        = new JLabel();
        required4        = new JLabel();
        required5        = new JLabel();
        optional1        = new JLabel();
        optional2        = new JLabel();
        optional3        = new JLabel();
        warning          = new JLabel();
        name             = new JTextField();
        lastname         = new JTextField();
        emailAddress     = new JTextField();
        username         = new JTextField();
        password         = new JTextField();
        year             = new JTextField();
        month            = new JTextField();
        day              = new JTextField();
        phoneNumber      = new JTextField();
        bio              = new JTextField();

        button.setBorder(BorderFactory.createRaisedBevelBorder());
        name.setBorder(BorderFactory.createRaisedBevelBorder());
        lastname.setBorder(BorderFactory.createRaisedBevelBorder());
        emailAddress.setBorder(BorderFactory.createRaisedBevelBorder());
        username.setBorder(BorderFactory.createRaisedBevelBorder());
        password.setBorder(BorderFactory.createRaisedBevelBorder());
        year.setBorder(BorderFactory.createRaisedBevelBorder());
        month.setBorder(BorderFactory.createRaisedBevelBorder());
        day.setBorder(BorderFactory.createRaisedBevelBorder());
        phoneNumber.setBorder(BorderFactory.createRaisedBevelBorder());
        bio.setBorder(BorderFactory.createRaisedBevelBorder());

        nameLabel.setBounds(20, 230, 100, 50);
        lastnameLabel.setBounds(20, 260, 100, 50);
        emailLabel.setBounds(20, 290, 100, 50);
        usernameLabel.setBounds(20, 320, 100, 50);
        passwordLabel.setBounds(20, 350, 100, 50);
        passwordNotice.setBounds(5, 375, 495, 50);
        dateOfBirthLabel.setBounds(20, 400, 100, 50);
        phoneNumberLabel.setBounds(20, 430, 100, 50);
        bioLabel.setBounds(20, 460, 100, 50);
        name.setBounds(150, 245, textFieldWidth, textFieldHeight);
        lastname.setBounds(150, 275, textFieldWidth, textFieldHeight);
        emailAddress.setBounds(150, 305, textFieldWidth, textFieldHeight);
        username.setBounds(150, 335, textFieldWidth, textFieldHeight);
        password.setBounds(150, 365, textFieldWidth, textFieldHeight);
        year.setBounds(150, 415, 60, textFieldHeight);
        month.setBounds(220, 415, 40, textFieldHeight);
        day.setBounds(270, 415, 40, textFieldHeight);
        phoneNumber.setBounds(150, 445, textFieldWidth, textFieldHeight);
        bio.setBounds(150, 475, textFieldWidth, textFieldHeight);
        button.setBounds(frameWidth/2 - buttonWidth/2, 510, buttonWidth, buttonHeight);
        optional1.setBounds(355, 400, 100, 50);
        optional2.setBounds(355, 430, 100, 50);
        optional3.setBounds(355, 460, 100, 50);
        required1.setBounds(355, 230, 100, 50);
        required2.setBounds(355, 260, 100, 50);
        required3.setBounds(355, 290, 100, 50);
        required4.setBounds(355, 320, 100, 50);
        required5.setBounds(355, 350, 100, 50);
        warning.setBounds(152, 60, 250, 50);

        button.setBackground(buttonColor);
        nameLabel.setForeground(Color.WHITE);
        lastnameLabel.setForeground(Color.WHITE);
        emailLabel.setForeground(Color.WHITE);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);
        passwordNotice.setForeground(Color.RED);
        dateOfBirthLabel.setForeground(Color.WHITE);
        phoneNumberLabel.setForeground(Color.WHITE);
        bioLabel.setForeground(Color.WHITE);
        optional1.setForeground(Color.GREEN);
        optional2.setForeground(Color.GREEN);
        optional3.setForeground(Color.GREEN);
        required1.setForeground(Color.RED);
        required2.setForeground(Color.RED);
        required3.setForeground(Color.RED);
        required4.setForeground(Color.RED);
        required5.setForeground(Color.RED);
        warning.setForeground(Color.RED);

        warning.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        nameLabel.setText("name                    : ");
        lastnameLabel.setText("last name            :");
        emailLabel.setText("E-mail address   : ");
        usernameLabel.setText("username            :");
        passwordLabel.setText("password            :");
        passwordNotice.setText("Notice : Your password must contain at least 8 characters using alphabet and digits. ");
        dateOfBirthLabel.setText("date of birth        : ");
        phoneNumberLabel.setText("phone number   :");
        bioLabel.setText("Bio                        :");
        optional1.setText("(Optional)");
        optional2.setText("(Optional)");
        optional3.setText("(Optional)");
        required1.setText("(Required)");
        required2.setText("(Required)");
        required3.setText("(Required)");
        required4.setText("(Required)");
        required5.setText("(Required)");
        warning.setText("Registration failed!");

        button.addActionListener(this);

        this.add(nameLabel);
        this.add(name);
        this.add(lastnameLabel);
        this.add(lastname);
        this.add(emailLabel);
        this.add(emailAddress);
        this.add(usernameLabel);
        this.add(username);
        this.add(passwordLabel);
        this.add(password);
        this.add(passwordNotice);
        this.add(dateOfBirthLabel);
        this.add(year);
        this.add(month);
        this.add(day);
        this.add(phoneNumberLabel);
        this.add(phoneNumber);
        this.add(bioLabel);
        this.add(bio);
        this.add(button);
        this.add(optional1);
        this.add(optional2);
        this.add(optional3);
        this.add(required1);
        this.add(required2);
        this.add(required3);
        this.add(required4);
        this.add(required5);
        this.add(warning);

        warning.setVisible(false);
    }

    @Override
    public String getName() {
        return name.getText();
    }

    public String getLastname() {
        return lastname.getText();
    }

    public String getEmailAddress() {
        return emailAddress.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public String  getPassword() {
        return password.getText();
    }

    public String getYear() {
        return year.getText();
    }

    public String getMonth() {
        return month.getText();
    }

    public String getDay() {
        return day.getText();
    }

    public String getPhoneNumber() {
        return phoneNumber.getText();
    }

    public String getBio() {
        return bio.getText();
    }

    public void setFormListener(RegistrationFormListener formListener) {
        this.formListener = formListener;
    }

    public void setErrorVisible(boolean isVisible){
        this.warning.setVisible(isVisible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners){
            if (e.getSource() == button){
                String dateOfBirth = getYear() + " " + getMonth() + " " + getDay();
                signUpFormEvent = new RegistrationFormEvent(this,
                        getName(),
                        getLastname(),
                        getEmailAddress(),
                        getUsername(),
                        getPassword(),
                        dateOfBirth,
                        getPhoneNumber(),
                        getBio());
                formListener.eventOccurred(signUpFormEvent);
                listener.Listen("Sign Up");
            }
        }
    }
}
