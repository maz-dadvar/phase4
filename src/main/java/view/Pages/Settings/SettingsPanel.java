package view.Pages.Settings;

import authentication.Controller.AuthController;
import controller.Settings.Activation.*;
import controller.Settings.DeleteAccount.*;
import controller.Settings.LastSeenMode.LastSeenModeEvent;
import controller.Settings.LastSeenMode.LastSeenModeListener;
import controller.Settings.Privacy.PrivacyListener;
import interfaces.Listener;
import model.Activation;
import model.Privacy;
import view.MainPanel;
import view.Pages.PageTemplate;
import view.Pages.Settings.ChangePassword.ChangePasswordPanel;
import view.Pages.Settings.Deactivate.DeactivatePanel;
import view.Pages.Settings.DeleteAccount.DeleteAccountPanel;
import view.Pages.Settings.LastSeenMode.LastSeenModePanel;
import view.Pages.Settings.Privacy.PrivacyPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SettingsPanel extends PageTemplate implements ActionListener {

    private JButton privacy;
    private JButton lastSeen;
    private JButton deactivate;
    private JButton changePassword;
    private JButton deleteAccount;
    public  LinkedList<Listener> listeners;
    public  PrivacyPanel privacyPanel;
    public  LastSeenModePanel lastSeenModePanel;
    public  ChangePasswordPanel changePasswordPanel;
    public  DeactivatePanel deactivatePanel;
    public  DeleteAccountPanel deleteAccountPanel;

    public SettingsPanel() {
        super();
        listeners = new LinkedList<>();
        showPanel.setBackground(backgroundColor);

        privacy           = new JButton("Privacy");
        lastSeen          = new JButton("Last Seen Mode");
        deactivate        = new JButton("Deactivate");
        changePassword    = new JButton("Change Password");
        deleteAccount     = new JButton("Delete Account");
        privacyPanel      = new PrivacyPanel();
        lastSeenModePanel = new LastSeenModePanel();
        changePasswordPanel = new ChangePasswordPanel();
        deactivatePanel     = new DeactivatePanel();
        deleteAccountPanel  = new DeleteAccountPanel();

        privacy.setBackground(buttonColor);
        lastSeen.setBackground(buttonColor);
        deactivate.setBackground(buttonColor);
        changePassword.setBackground(buttonColor);
        deleteAccount.setBackground(buttonColor);

        privacy.setBorder(BorderFactory.createRaisedBevelBorder());
        lastSeen.setBorder(BorderFactory.createRaisedBevelBorder());
        deactivate.setBorder(BorderFactory.createRaisedBevelBorder());
        changePassword.setBorder(BorderFactory.createRaisedBevelBorder());
        deleteAccount.setBorder(BorderFactory.createRaisedBevelBorder());

        int distance = (mainPanelHeight - 5 * buttonHeight) / 6;

        privacy.setBounds(frameWidth/2 - buttonWidth,distance, buttonWidth*2, buttonHeight);
        lastSeen.setBounds(frameWidth/2 - buttonWidth, 2*distance + buttonHeight, buttonWidth*2, buttonHeight);
        changePassword.setBounds(frameWidth/2 - buttonWidth, 3*distance + 2*buttonHeight, buttonWidth*2, buttonHeight);
        deactivate.setBounds(frameWidth/2 - buttonWidth, 4*distance + 3*buttonHeight, buttonWidth*2, buttonHeight);
        deleteAccount.setBounds(frameWidth/2 - buttonWidth, 5*distance + 4*buttonHeight, buttonWidth*2, buttonHeight);

        privacy.addActionListener(this);
        lastSeen.addActionListener(this);
        changePassword.addActionListener(this);
        deactivate.addActionListener(this);
        deleteAccount.addActionListener(this);

        showPanel.add(privacy);
        showPanel.add(lastSeen);
        showPanel.add(changePassword);
        showPanel.add(deactivate);
        showPanel.add(deleteAccount);

        privacyPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Public")) {
                    PrivacyListener privacyListener = new PrivacyListener(new Privacy(AuthController.currentUser.id, false));
                }
                if (string.equals("Private")) {
                    PrivacyListener privacyListener = new PrivacyListener(new Privacy(AuthController.currentUser.id, true));
                }
                returnToSettings();
            }
        });

        lastSeenModePanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Everybody")) {
                    LastSeenModeListener lastSeenModeListener = new LastSeenModeListener(new LastSeenModeEvent(this, AuthController.currentUser.id, 1));
                }
                if (string.equals("Nobody")) {
                    LastSeenModeListener lastSeenModeListener = new LastSeenModeListener(new LastSeenModeEvent(this, AuthController.currentUser.id, 3));
                }
                if (string.equals("Contacts")) {
                    LastSeenModeListener lastSeenModeListener = new LastSeenModeListener(new LastSeenModeEvent(this, AuthController.currentUser.id, 2));
                }
                returnToSettings();
            }
        });

        changePasswordPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Submit")) {
                    if (changePasswordPanel.changePasswordAction()) {
                        returnToSettings();
                    }
                }
            }
        });

        deactivatePanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Yes")) {
                    ActivationListener activationListener = new ActivationListener(new ActivationEvent(this, AuthController.currentUser.id));
                    returnToSettings();
                }
                if (string.equals("No")) {
                    returnToSettings();
                }
            }
        });

        deleteAccountPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Yes")) {
                    DeleteAccountListener deleteAccountListener = new DeleteAccountListener(new DeleteAccountEvent(this));
                    returnToSettings();
                }
                if (string.equals("No")) {
                    returnToSettings();
                }
            }
        });
    }

    public void addListener(Listener listeners) {
        this.listeners.add(listeners);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == privacy) {
                listener.Listen("Privacy");
            }
            if (e.getSource() == lastSeen) {
                listener.Listen("Last Seen Mode");
            }
            if (e.getSource() == changePassword) {
                listener.Listen("Change Password");
            }
            if (e.getSource() == deactivate) {
                listener.Listen("Deactivate");
            }
            if (e.getSource() == deleteAccount) {
                listener.Listen("Delete Account");
            }
        }
    }

    public void returnToSettings(){
        MainPanel.mainPanel.showPanel(MainPanel.mainPanel.settingsPanel);
    }
}
