package controller.Tweet.NewTweet;

import model.Tweet;

import java.util.EventObject;

public class NewTweetEvent extends EventObject {

    public Tweet tweet;

    public NewTweetEvent(Object source, Tweet tweet) {
        super(source);
        this.tweet = tweet;
    }
}
