package controller.Tweet.Mute;

import controller.Tweet.NewTweet.SendDataToServer;
import controller.UserDB;
import model.RequestToServer;
import model.User;
import java.util.LinkedList;

public class MuteController extends controller.Controller {

    public void sendMuteDataToServer(RequestToServer request){
        (new SendDataToServer()).sendToServer(request);
    }

}
