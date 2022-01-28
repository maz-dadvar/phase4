package model;

import java.io.Serializable;

public class LastSeenMode implements Serializable {

    private int userId;
    private int lastSeenMode;

    public LastSeenMode(int userId, int lastSeenMode){
        this.userId = userId;
        this.lastSeenMode = lastSeenMode;
    }

    public int getUserId() {
        return userId;
    }

    public int getLastSeenMode() {
        return lastSeenMode;
    }
}
