package controller;

import model.User;

public class Context {
    public DBSet<User> users = new UserDB();
}
