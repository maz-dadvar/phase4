package model;

import interfaces.SearchMode;

import java.io.Serializable;
import java.util.LinkedList;

public class Search implements Serializable {

    private SearchMode mode;
    private String search;
    private LinkedList<User> usersFound;

    public Search(SearchMode mode, String search){
        this.mode = mode;
        this.search = search;
        this.usersFound = new LinkedList<>();
    }

    public SearchMode getMode() {
        return mode;
    }

    public String getSearch() {
        return search;
    }

    public LinkedList<User> getUsersFound() {
        return usersFound;
    }

    public void setUsersFound(LinkedList<User> usersFound) {
        this.usersFound = usersFound;
    }
}
