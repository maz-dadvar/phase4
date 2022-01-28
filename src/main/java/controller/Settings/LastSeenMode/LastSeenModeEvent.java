package controller.Settings.LastSeenMode;

import java.util.EventObject;

public class LastSeenModeEvent extends EventObject {

    private int userId;
    private int lastSeenMode;
    public LastSeenModeEvent(Object source, int userId, int lastSeenMode) {
        super(source);
        this.userId       = userId;
        this.lastSeenMode = lastSeenMode;
    }

    public int getLastSeenMode() {
        return lastSeenMode;
    }

    public int getUserId() {
        return userId;
    }
}
