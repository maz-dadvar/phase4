package model;

import java.io.Serializable;

public class Note implements Serializable {
    public int noteId;
    public int userId;
    public String text;

    // Constructor

    public Note(int userId, String text) {
        this.userId = userId;
        this.text   = text;
    }
}
