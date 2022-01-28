package model;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Tweet implements Serializable {
    public int id, numberOfRetweets;
    public User user;
    public String text;
    public ImageIcon imageIcon = null;
    public File imageFile;
    public boolean isTweet;
    public int fatherId;
    public LocalDateTime date;
    public Report report;
    public ArrayList<Integer> comments;
    public ArrayList<Integer> likesId;
    public ArrayList<Integer> retweetedUsers;
    public ArrayList<Integer> reportedUsers;

    // Constructor
    public Tweet(User user, String text, boolean isTweet) {
        this.user     = user;
        this.text     = text;
        this.numberOfRetweets = 0;
        this.isTweet = isTweet;
        report = new Report();
        this.date = LocalDateTime.now();
        comments = new ArrayList<>();
        likesId  = new ArrayList<>();
        retweetedUsers = new ArrayList<>();
        reportedUsers  = new ArrayList<>();
    }


}
