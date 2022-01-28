package controller.Tweet.Mute;

import model.User;
import java.util.EventObject;

public class MuteEvent extends EventObject {

    public boolean mute;
    public User muterUser;
    public User mutedUser;

    public MuteEvent(Object source, boolean mute, User muterUser, User mutedUser) {
        super(source);
        this.mute = mute;
        this.muterUser = muterUser;
        this.mutedUser = mutedUser;
    }
}
