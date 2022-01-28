package controller.Lists;

import authentication.Controller.AuthController;
import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class ListsController extends controller.Controller {

    public LinkedList<User> getFollowers(User user){
        RequestToServer request = new RequestToServer(user);
        request.setRequestFollowers(true);
        (new SendDataToServer()).sendToServer(request);
        return getList();
    }

    public LinkedList<User> getFollowings(User user){
        RequestToServer request = new RequestToServer(user);
        request.setRequestFollowings(true);
        (new SendDataToServer()).sendToServer(request);
        return getList();
    }

    public LinkedList<User> getList(){
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
