package authentication.Controller;

import java.util.EventObject;

public class SignUpFormEvent extends EventObject {

    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private String dateOfBirth;
    private String phoneNumber;
    private String bio;
    private boolean isValid;
    private boolean serverFound = false;

    public SignUpFormEvent(Object source, String name, String lastname, String email, String username, String password,
                           String dateOfBirth, String phoneNumber, String bio) {
        super(source);
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isServerFound() {
        return serverFound;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setServerFound(boolean serverFound) {
        this.serverFound = serverFound;
    }
}
