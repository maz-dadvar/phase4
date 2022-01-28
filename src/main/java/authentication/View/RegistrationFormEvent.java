package authentication.View;

import java.util.EventObject;

public class RegistrationFormEvent extends EventObject {
    private String name;
    private String lastname;
    private String emailAddress;
    private String username;
    private String password;
    private String dateOfBirth;
    private String phoneNumber;
    private String bio;
    private boolean isValid;
    private boolean serverFound;

    public RegistrationFormEvent(Object source, String name, String lastname, String emailAddress,
                                 String username, String password, String dateOfBirth, String phoneNumber, String bio) {
        super(source);
        this.name = name;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.isValid = false;
        this.serverFound = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isServerFound() {
        return serverFound;
    }

    public void setServerFound(boolean serverFound) {
        this.serverFound = serverFound;
    }

}
