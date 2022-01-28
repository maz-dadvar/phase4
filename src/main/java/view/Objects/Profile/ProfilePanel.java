package view.Objects.Profile;

import controller.User.Block.Block;
import interfaces.Dimensions;
import interfaces.Listener;
import model.User;
import view.ButtonPanelTemplate;
import view.MainPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePanel extends ButtonPanelTemplate implements Dimensions, ActionListener {

    public User user;
    public User viewer;

    public ProfilePanel(User viewer, User user) {
        super();
        this.user   = user;
        this.viewer = viewer;
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight + toolbarHeight + 40));
        this.setLayout(null);
        this.setBackground(backgroundColor);

        button1.setText("Follow");
        button2.setText("Unfollow");
        button3.setText("Mute");
        button4.setText("UnMute");
        button5.setText("Block");
        button6.setText("Unblock");
        button7.setText("Tweets");
        button8.setText("Return");
        button9.setText("Message");
        label1.setIcon(new ImageIcon(user.profilePicture.getImage().getScaledInstance(mainPanelWidth, mainPanelHeight, Image.SCALE_DEFAULT)));
        String date = user.lastSeen.getYear() + "-" + user.lastSeen.getMonthValue() + "-" + user.lastSeen.getDayOfMonth()
                + " " + user.lastSeen.getHour() + ":" + user.lastSeen.getMinute() + ":" + user.lastSeen.getSecond();
        label2.setText("Last Seen : " + date);
        label3.setText("HyperLink : @" + user.getUsername());
        label2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        label3.setFont(new Font("Times New Roman", Font.BOLD, 16));

        int distance = (mainPanelWidth - 4*buttonWidth) / 5;

        label1.setBounds(0, 0,                       mainPanelWidth, mainPanelHeight);
        label2.setBounds(2*distance +   buttonWidth, 560, 300, 50);
        label3.setBounds(2*distance +   buttonWidth, 530, 300, 50);
        button1.setBounds(     distance,                 510, buttonWidth, buttonHeight);
        button2.setBounds(     distance,                 510, buttonWidth, buttonHeight);
        button3.setBounds(2*distance +   buttonWidth, 510, buttonWidth, buttonHeight);
        button4.setBounds(2*distance +   buttonWidth, 510, buttonWidth, buttonHeight);
        button5.setBounds(3*distance + 2*buttonWidth, 510, buttonWidth, buttonHeight);
        button6.setBounds(3*distance + 2*buttonWidth, 510, buttonWidth, buttonHeight);
        button7.setBounds(4*distance + 3*buttonWidth, 510, buttonWidth, buttonHeight);
        button8.setBounds(4*distance + 3*buttonWidth, 560, buttonWidth, buttonHeight);
        button9.setBounds(     distance,                 560, buttonWidth, buttonHeight);

        label2.setVisible((user.lastSeenMode != 2 || user.followers.contains(viewer.id)) && user.lastSeenMode != 3);
        label3.setVisible(true);

        if (viewer.followings.contains(user.id)) {
            button1.setVisible(false);
            button2.setVisible(true);
        }
        if (viewer.mutedUsers.contains(user.id)) {
            button3.setVisible(false);
            button4.setVisible(true);
        }
        if (viewer.blockedUsers.contains(user.id)) {
            button5.setVisible(false);
            button6.setVisible(true);
        }

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(button9);
        this.add(label1);
        this.add(label2);
        this.add(label3);
    }

    public void showProfile(User user) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(this);
        MainPanel.mainPanel.repaint();
    }

    public void returnToTimeline() {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(MainPanel.mainPanel.timelinePanel);
        MainPanel.mainPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Follow");
            }
            if (e.getSource() == button2) {
                listener.Listen("Unfollow");
            }
            if (e.getSource() == button3) {
                listener.Listen("Mute");
            }
            if (e.getSource() == button4) {
                listener.Listen("UnMute");
            }
            if (e.getSource() == button5) {
                listener.Listen("Block");
            }
            if (e.getSource() == button6) {
                listener.Listen("Unblock");
            }
            if (e.getSource() == button7) {
                listener.Listen("Tweets");
            }
            if (e.getSource() == button8) {
                listener.Listen("Return");
            }
            if (e.getSource() == button9) {
                listener.Listen("Message");
            }
        }
    }

    public void changeMuteIcon(boolean mute) {
        if (mute) {
            this.button3.setVisible(true);
            this.button4.setVisible(false);
        } else if (!(mute)) {
            this.button3.setVisible(false);
            this.button4.setVisible(true);
        }
    }

    public void changeFollowIcon(boolean follow) {
        if (follow) {
            this.button1.setVisible(true);
            this.button2.setVisible(false);
        } else {
            this.button1.setVisible(false);
            this.button2.setVisible(true);
        }
    }

    public void changeBlockIcon(Block block) {
        switch (block) {
            case BLOCK -> {
                this.button5.setVisible(true);
                this.button6.setVisible(false);
            }
            case UNBLOCK -> {
                this.button5.setVisible(false);
                this.button6.setVisible(true);
            }
        }
    }

}
