package controller.FollowRequests;

import controller.Controller;
import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.User;

public class AcceptAndRejectController extends Controller {

    public void accept(User accepter, User accepted){
        RequestToServer request = new RequestToServer(accepter);
        request.setAccept(true);
        request.setTargetId(accepted.getId());
        (new SendDataToServer()).sendToServer(request);
    }

    public void reject(User rejecter, User rejected){
        RequestToServer request = new RequestToServer(rejecter);
        request.setReject(true);
        request.setTargetId(rejected.getId());
        (new SendDataToServer()).sendToServer(request);
    }

}
