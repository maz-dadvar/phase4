package controller.Tweet.NewTweet;

import authentication.Controller.AuthController;
import controller.TweetDB;
import model.RequestToServer;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SendDataToServer extends controller.Controller{

    private final Socket socket = AuthController.socket;
    private OutputStream outputStream;

    public void sendToServer(RequestToServer request){
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(request);
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Request hasn't sent to server!");
        }
    }
}
