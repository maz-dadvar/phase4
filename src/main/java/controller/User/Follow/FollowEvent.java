package controller.User.Follow;

import model.User;

import java.io.Serializable;
import java.util.EventObject;

public class FollowEvent extends EventObject implements Serializable {

    private User follower;
    private User following;
    private boolean follow;

    public FollowEvent(Object source, User follower, User following, boolean follow) {
        super(source);
        this.follower  = follower;
        this.following = following;
        this.follow    = follow;
    }

    public User getFollower() {
        return follower;
    }

    public User getFollowing() {
        return following;
    }

    public boolean isFollow() {
        return follow;
    }
}
