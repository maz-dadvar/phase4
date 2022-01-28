package controller.Settings.Activation;

import authentication.Controller.AuthController;
import controller.UserDB;
import model.Activation;
import model.User;
import view.MainPanel;

public class ActivationController extends controller.Controller{

    public static User currentUser = AuthController.currentUser;
    public static UserDB userDB = AuthController.userDB;

    public void deactivate() {
        currentUser.isActive = false;
        userDB.activation(currentUser, Activation.DEACTIVATE);
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.centerPanel);
        MainPanel.mainPanel.centerPanel.logIn.usernameField.setText("");
        MainPanel.mainPanel.centerPanel.logIn.passwordField.setText("");
        MainPanel.mainPanel.repaint();
    }
    public void activate() {
        currentUser.isActive = true;
        userDB.activation(currentUser, Activation.ACTIVATE);
    }
}
