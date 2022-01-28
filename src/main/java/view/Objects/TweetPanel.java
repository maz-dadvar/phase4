package view.Objects;

import interfaces.Dimensions;
import interfaces.Listener;
import model.Tweet;
import model.User;
import view.ButtonPanelTemplate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TweetPanel extends ButtonPanelTemplate implements Dimensions, ActionListener {

    public  Tweet tweet;
    private ImageIcon likeIcon;
    private ImageIcon dislikeIcon;
    private ImageIcon muteIcon;
    private ImageIcon unmuteIcon;

    public TweetPanel(Tweet tweet, User viewer) {
        super();
        if (tweet.imageIcon == null) {
            this.setSize(new Dimension(mainPanelWidth, (int) (mainPanelHeight/3)));
        } else {
            this.setSize(new Dimension(mainPanelWidth, (int) (mainPanelHeight/2)));
        }
        this.setSize(new Dimension(mainPanelWidth, (int) (mainPanelHeight/1.85)));
        this.tweet = tweet;
        this.setBackground(toolbarColor);

        Image likeImage    = new ImageIcon("./resources/like.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);
        Image dislikeImage = new ImageIcon("./resources/dislike.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);
        Image retweetImage = new ImageIcon("./resources/retweet.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);
        Image commentImage = new ImageIcon("./resources/comment.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);
        Image reportImage  = new ImageIcon("./resources/report.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);
        Image muteImage    = new ImageIcon("./resources/mute.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);
        Image unmuteImage  = new ImageIcon("./resources/unmute.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);
        Image forwardImage = new ImageIcon("./resources/forward.png").getImage().getScaledInstance(tweetOptionWidth, tweetOptionHeight, Image.SCALE_DEFAULT);

        ImageIcon profileIcon = new ImageIcon(tweet.user.profilePicture.getImage().getScaledInstance((int) (profileImageWidth*1.5)
                , (int) (profileImageHeight*1.5), Image.SCALE_DEFAULT));
        ImageIcon retweetIcon = new ImageIcon(retweetImage);
        ImageIcon commentIcon = new ImageIcon(commentImage);
        ImageIcon reportIcon  = new ImageIcon(reportImage);
        ImageIcon forwardIcon = new ImageIcon(forwardImage);
        likeIcon    = new ImageIcon(likeImage);
        dislikeIcon = new ImageIcon(dislikeImage);
        muteIcon    = new ImageIcon(muteImage);
        unmuteIcon  = new ImageIcon(unmuteImage);

        button1.setIcon(profileIcon);
        button2.setIcon(likeIcon);
        button3.setIcon(retweetIcon);
        button4.setIcon(commentIcon);
        button5.setIcon(reportIcon);
        button6.setIcon(muteIcon);
        button7.setIcon(forwardIcon);

        if (tweet.likesId.contains(viewer.id)) {
            button2.setIcon(dislikeIcon);
        } else {
            button2.setIcon(likeIcon);
        }

        if (viewer.mutedUsers.contains(tweet.user.id)) {
            button6.setIcon(unmuteIcon);
        } else {
            button6.setIcon(muteIcon);
        }

        label1.setText(tweet.user.name + " " + tweet.user.lastname);
        label2.setText("<html>" + tweet.text + "</html>");
        String date = tweet.date.getYear() + "-" + tweet.date.getMonthValue() + "-" + tweet.date.getDayOfMonth()
                + " " + tweet.date.getHour() + ":" + tweet.date.getMinute() + ":" + tweet.date.getSecond();
        label3.setText(date);
        label4.setText(tweet.likesId.size() + " likes");
        label5.setText(tweet.retweetedUsers.size() + " retweets");

        label1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        label5.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        button1.setBounds(10, 30, profileImageWidth, profileImageHeight);
        button2.setBounds(10, 70 + profileImageHeight, tweetOptionWidth, tweetOptionHeight);
        button3.setBounds(30 + tweetOptionWidth, 70 + profileImageHeight, tweetOptionWidth, tweetOptionHeight);
        button4.setBounds(50 + 2*tweetOptionWidth, 70 + profileImageHeight, tweetOptionWidth, tweetOptionHeight);
        button5.setBounds(70 + 3*tweetOptionWidth, 70 + profileImageHeight, tweetOptionWidth, tweetOptionHeight);
        button6.setBounds(90 + 4*tweetOptionWidth, 70 + profileImageHeight, tweetOptionWidth, tweetOptionHeight);
        button7.setBounds(110 + 5*tweetOptionWidth, 70 + profileImageHeight, tweetOptionWidth, tweetOptionHeight);
        label1.setBounds(150, 30, 200, 15);
        label2.setBounds(150, 30, 320, 130);
        label3.setBounds( 10, 40 + profileImageHeight, 200, 35);
        label4.setBounds(200, 40 + profileImageHeight, 100, 35);
        label5.setBounds(300, 40 + profileImageHeight, 100, 35);

        label2.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Profile");
            }
            if (e.getSource() == button2) {
                listener.Listen("Like");
            }
            if (e.getSource() == button3) {
                listener.Listen("Retweet");
            }
            if (e.getSource() == button4) {
                listener.Listen("Comment");
            }
            if (e.getSource() == button5) {
                listener.Listen("Report");
            }
            if (e.getSource() == button6) {
                listener.Listen("Mute");
            }
            if (e.getSource() == button7) {
                listener.Listen("Forward");
            }
        }
    }

    public void changeLikeIcon(boolean like) {
        if (like) {
            this.button2.setIcon(likeIcon);
        } else if (!(like)) {
            this.button2.setIcon(dislikeIcon);
        }
    }

    public void changeMuteIcon(boolean mute) {
        if (mute) {
            this.button6.setIcon(muteIcon);
        } else if (!(mute)) {
            this.button6.setIcon(unmuteIcon);
        }
    }

}
