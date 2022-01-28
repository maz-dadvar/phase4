package model;

import java.io.Serializable;

public class Privacy implements Serializable {

    private int userId;
    private boolean isPrivate;
    public Privacy(int userId, boolean isPrivate){
        this.userId = userId;
        this.isPrivate = isPrivate;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
