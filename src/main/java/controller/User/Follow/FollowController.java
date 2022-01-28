package controller.User.Follow;

import controller.Tweet.NewTweet.SendDataToServer;
import controller.UserDB;
import model.RequestToServer;
import model.User;
import java.util.LinkedList;

public class FollowController extends controller.Controller{

    public void action(FollowEvent followEvent){
        SendDataToServer sendDataToServer = new SendDataToServer();
        RequestToServer request = new RequestToServer(followEvent.getFollower());
        request.setFollow(followEvent);
        sendDataToServer.sendToServer(request);
    }
}
