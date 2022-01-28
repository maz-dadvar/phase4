package controller.Information;

import authentication.Controller.AuthController;
import controller.Tweet.NewTweet.SendDataToServer;
import model.RequestToServer;
import model.User;

import java.time.LocalDateTime;

public class EditInfoController extends controller.Controller{

    public void submit() {
        AuthController.userDB.update(AuthController.currentUser);
    }

    public void editUsername(String username) {
        AuthController.currentUser.username = username;
    }

    public void changePassword(String password) {
        AuthController.currentUser.password = password;
    }

    public void editBio(String bio) {
        AuthController.currentUser.bio = bio;
    }

    public void editEmailAddress(String emailAddress) {
        AuthController.currentUser.emailAddress = emailAddress;
    }

    public void editName(String name) {
        AuthController.currentUser.name = name;
    }

    public void editLastname(String lastname) {
        AuthController.currentUser.lastname = lastname;
    }

    public void editPhoneNumber(String phoneNumber) {
        AuthController.currentUser.phoneNumber = phoneNumber;
    }

    public void editDateOfBirth(String dateOfBirth) {
        AuthController.currentUser.dateOfBirth = dateOfBirth;
    }

    public void submitProfileImage(User user){
        RequestToServer request = new RequestToServer(user);
        request.setProfileImage(user.profilePicture);
        SendDataToServer sendDataToServer = new SendDataToServer();
        sendDataToServer.sendToServer(request);
    }

}
