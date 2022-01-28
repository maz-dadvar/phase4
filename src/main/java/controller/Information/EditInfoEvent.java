package controller.Information;

import model.User;

import java.util.EventObject;

public class EditInfoEvent extends EventObject {
    public User user;
    public EditInfoEvent(Object source, User user) {
        super(source);
        this.user = user;
    }
}
