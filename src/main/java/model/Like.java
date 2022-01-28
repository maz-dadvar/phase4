package model;

import java.io.Serializable;

public class Like implements Serializable {
    private boolean like;
    private int likerId;
    private int tweetId;

    public Like(boolean like, int likerId, int id){
        this.like = like;
        this.likerId = likerId;
        this.tweetId = id;
    }

    public boolean isLike() {
        return like;
    }

    public int getTweetId() {
        return tweetId;
    }

    public int getLikerId() {
        return likerId;
    }
}
