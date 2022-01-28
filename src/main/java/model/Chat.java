package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Chat implements Serializable {

    public User self, contact;
    public ArrayList<PM> pms = new ArrayList<>();

    // Constructor
    public Chat(User self, User contact) {
        this.self = self;
        this.contact = contact;
    }


}
