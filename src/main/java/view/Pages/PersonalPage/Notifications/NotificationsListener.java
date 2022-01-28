package view.Pages.PersonalPage.Notifications;

import authentication.Controller.AuthController;
import controller.Tweet.NewTweet.SendDataToServer;
import model.Notification;
import model.RequestToServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class NotificationsListener {

    public void sendNotificationsRequest(){
        RequestToServer request = new RequestToServer(AuthController.currentUser);
        request.setRequestNotifications(true);
        (new SendDataToServer()).sendToServer(request);
    }

    public LinkedList<Notification> getNotifications(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(AuthController.socket.getInputStream());
            LinkedList<Notification> notifications = (LinkedList<Notification>) objectInputStream.readObject();
            if (notifications != null){
                return notifications;
            } else {
                System.out.println("Null notifications!");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Didn't received user!");
            return null;
        }
    }
}
