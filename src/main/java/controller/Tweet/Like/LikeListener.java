package controller.Tweet.Like;

public class LikeListener {
    public LikeController likeController = new LikeController();

    public LikeListener(LikeEvent likeEvent) {
        likeController.sendLikeObject(likeEvent);
        /*
        switch (likeEvent.like) {
            case LIKE    -> likeController.like(likeEvent.likerUser, likeEvent.likedTweet);
            case DISLIKE -> likeController.dislike(likeEvent.likerUser, likeEvent.likedTweet);
        }
         */
    }
}
