package authentication.View;

import java.util.EventObject;

public class LogInFormEvent extends EventObject {

    private String username;
    private String password;
    public boolean isValid;
    private boolean serverFound = false;

    public LogInFormEvent(Object source, String username, String password) {
        super(source);
        this.username = username;
        this.password = password;
        this.isValid = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setServerFound(boolean hasServerBeenFound){
        serverFound = hasServerBeenFound;
    }
}
