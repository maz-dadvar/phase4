package controller.Tweet.Like;

import model.Tweet;
import model.User;

import java.util.EventObject;

public class LikeEvent extends EventObject {
    public boolean like;
    public User likerUser;
    public Tweet likedTweet;
    public LikeEvent(Object source, boolean like, User likerUser, Tweet likedTweet) {
        super(source);
        this.like = like;
        this.likerUser  = likerUser;
        this.likedTweet = likedTweet;
    }

    public boolean getLike() {
        return like;
    }
}
