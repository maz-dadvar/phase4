package view.Pages.Messaging;

import authentication.Controller.AuthController;
import controller.Messaging.MessagingController;
import controller.Messaging.SendPMController;
import interfaces.Listener;
import model.PM;
import model.User;
import view.ButtonPanelTemplate;
import view.MainPanel;
import view.Pages.ProfilePanelLabel;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class ContactsPanel extends ButtonPanelTemplate implements ActionListener {

    private LinkedList<User>              contacts;
    private LinkedList<ProfilePanelLabel> profiles;
    private User                          viewer;
    private JScrollPane                   scrollPane;
    private JPanel                        panel;

    public ContactsPanel() {
        super();
        this.setBackground(backgroundColor);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(LineBorder.createBlackLineBorder());
        panel.setBackground(backgroundColor);
        profiles = new LinkedList<>();

        button1.setText("Start New Conversation");
        button1.setBounds(10, 10, mainPanelWidth - 50, 30);
        button1.addActionListener(this);
        button1.setVisible(true);

        this.add(button1);
    }

    public void addComponents() {
        viewer = AuthController.currentUser;
        int height = (int) (contacts.size() * (mainPanelHeight / 2.2 + 15));

        int y = 50;
        for (User user : contacts) {
            ProfilePanelLabel profilePanelLabel = new ProfilePanelLabel(user);
            profilePanelLabel.setBounds(0, y, profilePanelLabel.getWidth(), profilePanelLabel.getHeight());
            y = y + profilePanelLabel.getHeight() + 15;
            profiles.add(profilePanelLabel);
            panel.add(profilePanelLabel);
        }

        for (ProfilePanelLabel profilePanelLabel : profiles) {
            profilePanelLabel.addListener(new Listener() {
                @Override
                public void Listen(String string) {
                    if (string.equals("Profile")) {
                        showPMs(profilePanelLabel.getUser());
                    }
                }
            });
        }

        scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setBounds(0, 0, (int) (mainPanelWidth*0.97), mainPanelHeight*height);
        panel.setPreferredSize(new Dimension((int) (mainPanelWidth*0.97), mainPanelHeight*height));
        scrollPane.setBounds(0, 0, (int) (mainPanelWidth*0.97), (int) (mainPanelHeight*0.995));
        scrollPane.setVisible(true);
        this.add(scrollPane);
    }

    public void setContacts(LinkedList<User> contacts) {
        this.contacts = contacts;
    }

    public void showPMs(User user){
        LinkedList<PM> pms;
        pms = (new MessagingController()).getPMs(AuthController.currentUser, user);
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MessagingPanel messagingPanel = new MessagingPanel();
        messagingPanel.addComponents(pms);
        messagingPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Send")){
                    (new SendPMController()).sendPM(new PM(AuthController.currentUser, user,
                            messagingPanel.getText(), LocalDateTime.now()));
                }
            }
        });
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(messagingPanel);
        MainPanel.mainPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners){
            if (e.getSource() == button1){
                listener.Listen("Start New Conversation");
            }
        }
    }

    public void removeComps(){
        if (scrollPane != null && panel != null){
            remove(scrollPane);
            remove(panel);
        }
    }

}