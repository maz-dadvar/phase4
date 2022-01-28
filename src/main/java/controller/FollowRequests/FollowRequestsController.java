package controller.FollowRequests;

import authentication.Controller.AuthController;
import controller.Controller;
import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class FollowRequestsController extends Controller {

    public LinkedList<User> getFollowRequests(){
        RequestToServer request = new RequestToServer(AuthController.currentUser);
        request.setRequestFollowRequests(true);
        (new SendDataToServer()).sendToServer(request);
        return followRequests();
    }

    public LinkedList<User> sentFollowRequests(){
        RequestToServer request = new RequestToServer(AuthController.currentUser);
        request.setRequestSentFollowRequests(true);
        (new SendDataToServer()).sendToServer(request);
        return followRequests();
    }

    public LinkedList<User> followRequests(){
        LinkedList<User> users;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(AuthController.socket.getInputStream());
            users = (LinkedList<User>) objectInputStream.readObject();
            if (users != null){
                return users;
            } else {
                System.out.println("Null search object!");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Didn't received user!");
            return null;
        }
    }
}
