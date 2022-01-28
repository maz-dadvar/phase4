package controller.Search;

import authentication.Controller.AuthController;
import controller.Controller;
import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.Search;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SearchController extends Controller {

    public void sendDataToServer(Search search){
        SendDataToServer sendDataToServer = new SendDataToServer();
        RequestToServer request = new RequestToServer(AuthController.currentUser);
        request.setRequestSearchUser(search);
        sendDataToServer.sendToServer(request);
    }

    public Search getUsersFound(){
        Search search;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(AuthController.socket.getInputStream());
            search = (Search) objectInputStream.readObject();
            if (search != null){
                return search;
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
