package controller.Tweet.Retweet;

import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.Tweet;
import model.User;
import view.Objects.RetweetMessage;

import javax.swing.*;
import java.awt.*;

public class RetweetController extends controller.Controller{

    SendDataToServer sendDataToServer = new SendDataToServer();

    public void retweet(User user, Tweet tweet) {
        RequestToServer request = new RequestToServer(user);
        request.setRequestRetweet(true);
        request.setNewTweet(tweet);
        sendDataToServer.sendToServer(request);
        showMessage();
    }

    public void showMessage() {
        JFrame retweetMessage = new JFrame();
        retweetMessage.setLayout(new BorderLayout());
        retweetMessage.setSize(new Dimension(300,90));
        retweetMessage.setTitle("Retweet Message");
        RetweetMessage retweetMessage1 = new RetweetMessage();
        retweetMessage1.setBounds(0, 0, retweetMessage1.getWidth(), retweetMessage1.getHeight());
        retweetMessage.add(retweetMessage1);
        retweetMessage.setLocationRelativeTo(null);
        retweetMessage.setResizable(false);
        retweetMessage.setVisible(true);
    }

}
