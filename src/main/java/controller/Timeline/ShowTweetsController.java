package controller.Timeline;

import authentication.Controller.AuthController;
import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.Tweet;
import model.User;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ShowTweetsController extends controller.Controller{

    private final Socket socket = AuthController.socket;
    private OutputStream outputStream;
    private InputStream  inputStream;

    public LinkedList<Tweet> getTimelineTweets(User viewer) {
        LinkedList<Tweet> tweets;
        RequestToServer requestToServer = new RequestToServer(viewer);
        requestToServer.setRequestTimelineTweets(true);
        sendDataToServer(requestToServer);
        tweets = getTweetsFromServer();
        return tweets;
    }

    public LinkedList<Tweet> getUserTweets(int userId) {
        LinkedList<Tweet> tweets;
        RequestToServer requestToServer = new RequestToServer(AuthController.currentUser);
        requestToServer.setRequestShowTweetsOfUser(true);
        if (userId != AuthController.currentUser.id){
            requestToServer.setTargetId(userId);
        }
        sendDataToServer(requestToServer);
        tweets = getTweetsFromServer();
        return tweets;
    }

    public LinkedList<Tweet> getPopularTweets(User viewer) {
        LinkedList<Tweet> tweets;
        RequestToServer requestToServer = new RequestToServer(viewer);
        requestToServer.setRequestPopularTweets(true);
        sendDataToServer(requestToServer);
        tweets = getTweetsFromServer();
        return tweets;
    }

    public void sendDataToServer(RequestToServer request) {
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

    private LinkedList<Tweet> getTweetsFromServer() {
        LinkedList<Tweet> tweets = new LinkedList<>();
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            tweets = (LinkedList) objectInputStream.readObject();
            if (!tweets.isEmpty()){
                System.out.println("Tweets received");
            } else {
                System.out.println("Null tweets!");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Didn't received tweets!");
        }
        return tweets;
    }
}
