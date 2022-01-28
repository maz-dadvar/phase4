package controller.Information;

import authentication.Controller.AuthController;
import model.User;

import java.time.LocalDateTime;

public class InfoController extends controller.Controller{

    public User user = AuthController.currentUser;

    public String getName() {
        return user.name;
    }

    public String getLastname() {
        return user.lastname;
    }

    public String getUsername() {
        return user.username;
    }

    public String getEmailAddress() {
        return user.emailAddress;
    }

    public String getBio() {
        return user.bio;
    }

    public String getPhoneNumber() {
        return user.phoneNumber;
    }

    public boolean getPrivacy() {
        return user.isPrivate;
    }

    public String getDateOfBirth() {
        return user.dateOfBirth;
    }

    public int getLastSeenMode() {
        return user.lastSeenMode;
    }


}
