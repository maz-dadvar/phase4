package controller.Tweet.Retweet;

import model.Tweet;
import model.User;

import java.util.EventObject;

public class RetweetEvent extends EventObject {

    public User user;
    public Tweet tweet;

    public RetweetEvent(Object source, User user, Tweet tweet) {
        super(source);
        this.user  = user;
        this.tweet = tweet;
    }
}
