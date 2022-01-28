package controller.Settings.Privacy;

import authentication.Controller.AuthController;
import controller.Tweet.NewTweet.SendDataToServer;
import model.LastSeenMode;
import model.Privacy;
import model.RequestToServer;

public class SettingsController extends controller.Controller{

    public void changePrivacy(Privacy privacy) {
        SendDataToServer sendDataToServer = new SendDataToServer();
        RequestToServer request = new RequestToServer(AuthController.currentUser);
        request.setRequestChangePrivacy(privacy);
        sendDataToServer.sendToServer(request);
        AuthController.currentUser.isPrivate = privacy.isPrivate();
    }

    public void changeLastSeenMode(LastSeenMode lastSeenMode){
        SendDataToServer sendDataToServer = new SendDataToServer();
        RequestToServer request = new RequestToServer(AuthController.currentUser);
        request.setLastSeenMode(lastSeenMode);
        sendDataToServer.sendToServer(request);
        AuthController.currentUser.lastSeenMode = lastSeenMode.getLastSeenMode();
    }

    public void deactivate(int userId){
        SendDataToServer sendDataToServer = new SendDataToServer();
        RequestToServer request = new RequestToServer(AuthController.currentUser);
        request.setRequestDeactivateId(userId);
        sendDataToServer.sendToServer(request);
    }
}
