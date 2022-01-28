package view.Pages.Timeline;

import authentication.Controller.AuthController;
import controller.Tweet.Like.LikeEvent;
import controller.Tweet.Like.LikeListener;
import controller.Tweet.Mute.MuteEvent;
import controller.Tweet.Mute.MuteListener;
import controller.Tweet.Report.ReportEvent;
import controller.Tweet.Report.ReportListener;
import controller.Tweet.Retweet.RetweetEvent;
import controller.Tweet.Retweet.RetweetListener;
import interfaces.Listener;
import model.Report;
import model.Tweet;
import model.User;
import view.ButtonPanelTemplate;
import view.MainPanel;
import view.Objects.Profile.ProfilePanel;
import view.Objects.Profile.ProfilePanelListener;
import view.Objects.TweetPanel;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

public class ShowTweetsPanel extends ButtonPanelTemplate {

    public  LinkedList<Tweet>      tweets;
    private LinkedList<TweetPanel> tweetPanels;
    private User                   viewer;
    private JScrollPane            scrollPane;
    private JPanel                 panel;

    public ShowTweetsPanel() {
        super();
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(LineBorder.createBlackLineBorder());
        panel.setBackground(backgroundColor);
        tweetPanels = new LinkedList<>();
    }

    public void addComponents(LinkedList<Tweet> tweets) {
        viewer = AuthController.currentUser;
        this.tweets = tweets;
        int height = (int) (tweets.size() * (mainPanelHeight / 1.85 + 15));

        int y = 0;
        for (Tweet tweet : tweets) {
            TweetPanel tweetPanel = new TweetPanel(tweet, viewer);
            tweetPanel.setBounds(0, y, tweetPanel.getWidth(), tweetPanel.getHeight());
            y = y + tweetPanel.getHeight() + 15;
            tweetPanels.add(tweetPanel);
            panel.add(tweetPanel);
        }


        for (TweetPanel tweetPanel : tweetPanels) {
            tweetPanel.addListener(new Listener() {
                @Override
                public void Listen(String string) {
                    if (string.equals("Profile")) {
                        showProfilePanel(tweetPanel);
                    }
                    if (string.equals("Like")) {
                        like(tweetPanel);
                    }
                    if (string.equals("Retweet")) {
                        RetweetListener retweetListener = new RetweetListener(new RetweetEvent(this, viewer, tweetPanel.tweet));
                    }
                    if (string.equals("Comment")) {
                        System.out.println("Hi " + tweetPanel.tweet.user.dateOfBirth);
                    }
                    if (string.equals("Report")) {
                        showReportPanel(tweetPanel);
                    }
                    if (string.equals("Mute")) {
                        mute(tweetPanel);
                    }
                    if (string.equals("Forward")) {
                        System.out.println("Forward Message to a specified user");
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

    public void like(TweetPanel tweetPanel) {
        if (tweetPanel.tweet.likesId.contains(viewer.id)) {
            LikeListener listener = new LikeListener(new LikeEvent(this, false, viewer, tweetPanel.tweet));
            System.out.println("Tweet disliked");
            tweetPanel.changeLikeIcon(true);
        } else {
            LikeListener listener = new LikeListener(new LikeEvent(this, true, viewer, tweetPanel.tweet));
            tweetPanel.tweet.likesId.add(viewer.id);
            System.out.println("Tweet liked");
            tweetPanel.changeLikeIcon(false);
        }
    }

    public void mute(TweetPanel tweetPanel) {
        if (viewer.mutedUsers.contains(tweetPanel.tweet.user.id)) {
            for (int i = 0; i < viewer.mutedUsers.size(); i++){
                if (viewer.mutedUsers.get(i) == tweetPanel.tweet.user.id){
                    viewer.mutedUsers.remove(i);
                    break;
                }
            }
            MuteListener muteListener = new MuteListener(new MuteEvent(this, false, viewer, tweetPanel.tweet.user));
            for (TweetPanel tweetPanel1 : tweetPanels) {
                if (tweetPanel1.tweet.user.id == tweetPanel.tweet.user.id) {
                    tweetPanel1.changeMuteIcon(true);
                }
            }
        } else {
            viewer.mutedUsers.add(tweetPanel.tweet.user.id);
            MuteListener muteListener = new MuteListener(new MuteEvent(this, true, viewer, tweetPanel.tweet.user));
            for (TweetPanel tweetPanel1 : tweetPanels) {
                if (tweetPanel1.tweet.user.id == tweetPanel.tweet.user.id) {
                    tweetPanel1.changeMuteIcon(false);
                }
            }
        }
    }

    public void showReportPanel(TweetPanel tweetPanel) {
        Report report = new Report();
        JFrame reportFrame = new JFrame();
        reportFrame.setLayout(new BorderLayout());
        reportFrame.setSize(new Dimension(300,387));
        reportFrame.setTitle("Report");
        ReportPanel reportPanel = new ReportPanel();
        reportPanel.setBounds(0, 0, reportPanel.getWidth(), reportPanel.getHeight());
        reportFrame.add(reportPanel);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setResizable(false);
        reportFrame.setVisible(true);
        reportPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Spam")) {
                    report.spam = 1;
                }
                if (string.equals("Child Abuse")) {
                    report.childAbuse = 1;
                }
                if (string.equals("Scam")) {
                    report.scam = 1;
                }
                if (string.equals("Dangerous Organization")) {
                    report.dangerousOrganization = 1;
                }
                if (string.equals("Bullying")) {
                    report.bullying = 1;
                }
                ReportListener reportListener = new ReportListener(new ReportEvent(this, tweetPanel.tweet, report));
                reportFrame.dispose();
            }
        });
    }

    public void showProfilePanel(TweetPanel tweetPanel) {
        ProfilePanel profilePanel = new ProfilePanel(viewer, tweetPanel.tweet.user);
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(profilePanel);
        MainPanel.mainPanel.repaint();
        profilePanel.addListener(new ProfilePanelListener(profilePanel));
    }

}
