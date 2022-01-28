package controller.Settings.DeleteAccount;

import authentication.Controller.AuthController;
import view.MainPanel;

public class DeleteController {
    public void deleteAccount() {
        AuthController.userDB.remove(AuthController.currentUser);
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.centerPanel);
        MainPanel.mainPanel.centerPanel.logIn.usernameField.setText("");
        MainPanel.mainPanel.centerPanel.logIn.passwordField.setText("");
        MainPanel.mainPanel.repaint();
    }
}
