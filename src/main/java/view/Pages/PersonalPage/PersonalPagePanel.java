package view.Pages.PersonalPage;

import authentication.Controller.AuthController;
import controller.Information.EditInfoEvent;
import controller.Information.EditInfoListener;
import controller.Tweet.NewTweet.NewTweetEvent;
import controller.Tweet.NewTweet.NewTweetListener;
import interfaces.Listener;
import model.Notification;
import model.RequestToServer;
import model.Tweet;
import view.MainFrame;
import view.MainPanel;
import view.Pages.PageTemplate;
import view.Pages.PersonalPage.Notifications.NotificationsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.LinkedList;


public class PersonalPagePanel extends PageTemplate implements ActionListener {

    private JButton newTweet;
    private JButton showTweets;
    private JButton editInformation;
    private JButton followings;
    private JButton followers;
    private JButton info;
    private JButton followRequests;
    private JButton notifications;
    private JButton sentFollowRequests;
    private JButton savedMessages;
    private JButton logout;
    private LogoutPanel logoutPanel;
    public  LinkedList<Listener> listeners;
    public  InfoPanel infoPanel;
    public  EditInfoPanel editInfoPanel;
    public  NewTweetPanel newTweetPanel;
    private NotificationsPanel notificationsPanel;
    public  FollowRequests     followRequestsPanel;


    public PersonalPagePanel() {
        super();
        showPanel.setBackground(Color.BLACK);

        listeners          = new LinkedList<>();
        newTweet           = new JButton("New Tweet");
        showTweets         = new JButton("Show Tweets");
        editInformation    = new JButton("Edit Information");
        followings         = new JButton("Followings");
        followers          = new JButton("Followers");
        info               = new JButton("Info");
        followRequests     = new JButton("Follow Requests");
        notifications      = new JButton("Notifications");
        sentFollowRequests = new JButton("Sent Follow Requests");
        savedMessages      = new JButton("Saved Messages");
        logout             = new JButton("Logout");
        logoutPanel        = new LogoutPanel();
        editInfoPanel      = new EditInfoPanel();
        newTweetPanel      = new NewTweetPanel();

        newTweet.setBackground(buttonColor);
        showTweets.setBackground(buttonColor);
        editInformation.setBackground(buttonColor);
        followings.setBackground(buttonColor);
        followers.setBackground(buttonColor);
        info.setBackground(buttonColor);
        followRequests.setBackground(buttonColor);
        notifications.setBackground(buttonColor);
        sentFollowRequests.setBackground(buttonColor);
        savedMessages.setBackground(buttonColor);
        logout.setBackground(buttonColor);

        newTweet.setBorder(BorderFactory.createRaisedBevelBorder());
        showTweets.setBorder(BorderFactory.createRaisedBevelBorder());
        editInformation.setBorder(BorderFactory.createRaisedBevelBorder());
        followings.setBorder(BorderFactory.createRaisedBevelBorder());
        followers.setBorder(BorderFactory.createRaisedBevelBorder());
        info.setBorder(BorderFactory.createRaisedBevelBorder());
        followRequests.setBorder(BorderFactory.createRaisedBevelBorder());
        notifications.setBorder(BorderFactory.createRaisedBevelBorder());
        sentFollowRequests.setBorder(BorderFactory.createRaisedBevelBorder());
        savedMessages.setBorder(BorderFactory.createRaisedBevelBorder());
        logout.setBorder(BorderFactory.createRaisedBevelBorder());

        int distance = (mainPanelHeight - 11*buttonHeight) / 12;
        newTweet.setBounds(frameWidth/2 - buttonWidth, distance, buttonWidth*2, buttonHeight);
        showTweets.setBounds(frameWidth/2 - buttonWidth,2*distance + buttonHeight, buttonWidth*2, buttonHeight);
        editInformation.setBounds(frameWidth/2 - buttonWidth, 3*distance + 2*buttonHeight, buttonWidth*2, buttonHeight);
        followings.setBounds(frameWidth/2 - buttonWidth, 4*distance + 3*buttonHeight, buttonWidth*2, buttonHeight);
        followers.setBounds(frameWidth/2 - buttonWidth, 5*distance + 4*buttonHeight, buttonWidth*2, buttonHeight);
        info.setBounds(frameWidth/2 - buttonWidth, 6*distance + 5*buttonHeight, buttonWidth*2, buttonHeight);
        followRequests.setBounds(frameWidth/2 - buttonWidth, 7*distance + 6*buttonHeight, buttonWidth*2, buttonHeight);
        notifications.setBounds(frameWidth/2 - buttonWidth,8*distance + 7*buttonHeight, buttonWidth*2, buttonHeight);
        sentFollowRequests.setBounds(frameWidth/2 - buttonWidth, 9*distance + 8*buttonHeight, buttonWidth*2, buttonHeight);
        savedMessages.setBounds(frameWidth/2 - buttonWidth,10*distance + 9*buttonHeight, buttonWidth*2, buttonHeight);
        logout.setBounds(frameWidth/2 - buttonWidth, 11*distance + 10*buttonHeight, buttonWidth*2, buttonHeight);


        newTweet.addActionListener(this);
        showTweets.addActionListener(this);
        editInformation.addActionListener(this);
        followings.addActionListener(this);
        followers.addActionListener(this);
        followRequests.addActionListener(this);
        info.addActionListener(this);
        notifications.addActionListener(this);
        sentFollowRequests.addActionListener(this);
        savedMessages.addActionListener(this);
        logout.addActionListener(this);

        showPanel.add(newTweet);
        showPanel.add(showTweets);
        showPanel.add(editInformation);
        showPanel.add(followings);
        showPanel.add(followers);
        showPanel.add(info);
        showPanel.add(followRequests);
        showPanel.add(notifications);
        showPanel.add(sentFollowRequests);
        showPanel.add(savedMessages);
        showPanel.add(logout);

        editInfoPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Edit Name")) {
                    AuthController.currentUser.name = editInfoPanel.textField1.getText();
                    EditInfoListener editInfoListener = new EditInfoListener(new EditInfoEvent(this, AuthController.currentUser));
                }
                if (string.equals("Edit Lastname")) {
                    AuthController.currentUser.lastname = editInfoPanel.textField2.getText();
                    EditInfoListener editInfoListener = new EditInfoListener(new EditInfoEvent(this, AuthController.currentUser));
                }
                if (string.equals("Edit Username")) {
                    AuthController.currentUser.username = editInfoPanel.textField3.getText();
                    EditInfoListener editInfoListener = new EditInfoListener(new EditInfoEvent(this, AuthController.currentUser));
                }
                if (string.equals("Edit E-main")) {
                    AuthController.currentUser.emailAddress = editInfoPanel.textField4.getText();
                    EditInfoListener editInfoListener = new EditInfoListener(new EditInfoEvent(this, AuthController.currentUser));
                }
                if (string.equals("Edit Date of Birth")) {
                    //AuthController.currentUser.dateOfBirth = editInfoPanel.textField5.;
                    EditInfoListener editInfoListener = new EditInfoListener(new EditInfoEvent(this, AuthController.currentUser));
                }
                if (string.equals("Edit Phone Number")) {
                    AuthController.currentUser.phoneNumber = editInfoPanel.textField6.getText();
                    EditInfoListener editInfoListener = new EditInfoListener(new EditInfoEvent(this, AuthController.currentUser));
                }
                if (string.equals("Edit Bio")) {
                    AuthController.currentUser.bio = editInfoPanel.textField7.getText();
                    EditInfoListener editInfoListener = new EditInfoListener(new EditInfoEvent(this, AuthController.currentUser));
                }
                if (string.equals("Insert Image")){
                    editInfoPanel.getImage();
                }
            }
        });

        newTweetPanel.addListener(new Listener() {
            Tweet tweet;
            @Override
            public void Listen(String string) {
                if (string.equals("Submit Tweet")) {
                    if (!newTweetPanel.textArea.getText().equals("") && newTweetPanel.textArea.getText() != null) {
                        if (newTweetPanel.imageIcon != null) {
                            tweet = new Tweet(AuthController.currentUser, newTweetPanel.textArea.getText(), true);
                            tweet.imageIcon = newTweetPanel.imageIcon;
                            NewTweetListener newTweetListener = new NewTweetListener(new NewTweetEvent(this, tweet));
                        } else {
                            tweet = new Tweet(AuthController.currentUser, newTweetPanel.textArea.getText(), true);
                            NewTweetListener newTweetListener = new NewTweetListener(new NewTweetEvent(this, tweet));
                        }
                    } else if (newTweetPanel.imageIcon != null) {
                        Tweet tweet = new Tweet(AuthController.currentUser, "  ", true);
                        tweet.imageIcon = newTweetPanel.imageIcon;
                        NewTweetListener newTweetListener = new NewTweetListener(new NewTweetEvent(this, tweet));
                    }
                }
                if (string.equals("Insert Image")) {
                    newTweetPanel.getImage();
                    if (tweet != null) {
                        tweet.imageFile = newTweetPanel.imageFile;
                    }
                }
            }
        });

        logoutPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Logout")) {
                    RequestToServer request = new RequestToServer(AuthController.currentUser);
                    request.setLastSeen(LocalDateTime.now());
                    AuthController.submitLastSeen(request);
                    MainPanel.mainPanel.removeAll();
                    MainPanel.mainPanel.revalidate();
                    MainPanel.mainPanel.add(MainPanel.mainPanel.centerPanel);
                    MainPanel.mainPanel.centerPanel.logIn.usernameField.setText("");
                    MainPanel.mainPanel.centerPanel.logIn.passwordField.setText("");
                    MainPanel.mainPanel.repaint();
                }
                if (string.equals("Exit")) {
                    RequestToServer request = new RequestToServer(AuthController.currentUser);
                    request.setLastSeen(LocalDateTime.now());
                    AuthController.submitLastSeen(request);
                    MainFrame.mainFrame.dispose();
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
            if (e.getSource() == newTweet) {
                listener.Listen("New Tweet");
            }
            if (e.getSource() == showTweets) {
                listener.Listen("Show Tweets");
            }
            if (e.getSource() == editInformation) {
                listener.Listen("Edit Information");
            }
            if (e.getSource() == followings) {
                listener.Listen("Followings");
            }
            if (e.getSource() == followers) {
                listener.Listen("Followers");
            }
            if (e.getSource() == info) {
                listener.Listen("Info");
            }
            if (e.getSource() == followRequests) {
                listener.Listen("Follow Requests");
            }
            if (e.getSource() == notifications) {
                listener.Listen("Notifications");
            }
            if (e.getSource() == sentFollowRequests) {
                listener.Listen("Sent Follow Requests");
            }
            if (e.getSource() == savedMessages) {
                listener.Listen("Saved Messages");
            }
            if (e.getSource() == logout) {
                listener.Listen("Logout");
            }
        }
    }

    public void showLogoutPanel() {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(logoutPanel);
        MainPanel.mainPanel.repaint();
    }

    public void showNotificationsPanel(LinkedList<Notification> notifications){
        notificationsPanel = new NotificationsPanel(notifications);
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(notificationsPanel);
        MainPanel.mainPanel.repaint();
    }
}
