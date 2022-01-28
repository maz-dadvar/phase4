package controller.Tweet.NewTweet;

import model.RequestToServer;

public class NewTweetListener {

    public SendDataToServer sendDataToServer = new SendDataToServer();

    public NewTweetListener(NewTweetEvent newTweetEvent) {
        RequestToServer request = new RequestToServer(newTweetEvent.tweet.user);
        request.setNewTweet(newTweetEvent.tweet);
        sendDataToServer.sendToServer(request);
    }
}
