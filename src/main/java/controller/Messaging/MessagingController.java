package controller.Messaging;

import authentication.Controller.AuthController;
import controller.Tweet.NewTweet.SendDataToServer;
import model.PM;
import model.RequestToServer;
import model.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class MessagingController extends controller.Controller{

    public LinkedList<User> getContacts(User owner){
        LinkedList<User> contacts;
        sendRequestToServer(owner);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(AuthController.socket.getInputStream());
            contacts = (LinkedList<User>) objectInputStream.readObject();
            if (contacts != null){
                return contacts;
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

    public void sendRequestToServer(User owner){
        RequestToServer request = new RequestToServer(owner);
        request.setRequestForContacts(true);
        (new SendDataToServer()).sendToServer(request);
    }

    public LinkedList<PM> getPMs(User user, User contact){
        LinkedList<PM> pms = new LinkedList<>();
        RequestToServer request = new RequestToServer(user);
        request.setRequestForPMs(true);
        request.setTargetId(contact.getId());
        (new SendDataToServer()).sendToServer(request);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(AuthController.socket.getInputStream());
            pms = (LinkedList<PM>) objectInputStream.readObject();
            if (pms != null){
                return pms;
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
