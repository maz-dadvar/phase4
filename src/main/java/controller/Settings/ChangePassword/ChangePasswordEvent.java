package controller.Settings.ChangePassword;

import java.util.EventObject;

public class ChangePasswordEvent extends EventObject {

    public String newPassword;

    public ChangePasswordEvent(Object source, String newPassword) {
        super(source);
        this.newPassword = newPassword;
    }
}
