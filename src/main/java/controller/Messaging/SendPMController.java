package controller.Messaging;

import controller.Controller;
import controller.Tweet.NewTweet.SendDataToServer;
import model.PM;
import model.RequestToServer;

public class SendPMController extends Controller {

    public void sendPM(PM pm){
        RequestToServer request = new RequestToServer(pm.getWriter());
        request.setPm(pm);
        (new SendDataToServer()).sendToServer(request);
    }
}
