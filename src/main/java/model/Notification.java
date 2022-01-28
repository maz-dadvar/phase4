package model;

import java.io.Serializable;

public class Notification implements Serializable {
    private String text;
    private boolean hasRead;

    public Notification(String text, boolean hasRead) {
        this.text = text;
        this.hasRead = hasRead;
    }

    public String getText() {
        return text;
    }

    public boolean isHasRead() {
        return hasRead;
    }
}
