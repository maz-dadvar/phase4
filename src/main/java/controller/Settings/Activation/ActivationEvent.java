package controller.Settings.Activation;

import java.util.EventObject;

public class ActivationEvent extends EventObject {

    private int userId;
    public ActivationEvent(Object source, int userId) {
        super(source);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
