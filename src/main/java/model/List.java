package model;

import java.io.Serializable;
import java.util.ArrayList;

public class List implements Serializable {
    public String name;
    public int id;
    public int ownerId;
    public ArrayList<Integer> users = new ArrayList<>();

    // Constructor
    public List(int ownerId, String name){
        this.ownerId = ownerId;
        this.name    = name;
    }

}
