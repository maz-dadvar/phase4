package model;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedMessages implements Serializable {

    public int ownerId;
    public ArrayList<Tweet> tweets;
    public ArrayList<Note>  notes;

    // Constructor
    public SavedMessages(int ownerId){
        this.ownerId = ownerId;
        this.tweets = new ArrayList();
        this.notes  = new ArrayList();
    }
}
