package controller.Tweet.Like;

import authentication.Controller.AuthController;
import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.Like;

public class LikeController extends controller.Controller{

    public void sendLikeObject(LikeEvent like){
        RequestToServer requestToServer = new RequestToServer(AuthController.currentUser);
        requestToServer.setLike(new Like(like.like, like.likerUser.id, like.likedTweet.id));
        (new SendDataToServer()).sendToServer(requestToServer);
    }
}
