package controller.Tweet.Mute;

import controller.Tweet.NewTweet.SendDataToServer;
import model.Mute;
import model.RequestToServer;

public class MuteListener {

    public MuteController muteController = new MuteController();

    public MuteListener(MuteEvent muteEvent) {
        RequestToServer request = new RequestToServer(muteEvent.muterUser);
        Mute mute = new Mute(muteEvent.mute, muteEvent.muterUser.id, muteEvent.mutedUser.id);
        request.setMute(mute);
        muteController.sendMuteDataToServer(request);
    }
}
