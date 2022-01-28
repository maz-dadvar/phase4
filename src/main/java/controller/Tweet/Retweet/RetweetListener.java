package controller.Tweet.Retweet;

public class RetweetListener {

    public RetweetController retweetController = new RetweetController();

    public RetweetListener(RetweetEvent retweetEvent) {
        retweetController.retweet(retweetEvent.user, retweetEvent.tweet);
    }
}
