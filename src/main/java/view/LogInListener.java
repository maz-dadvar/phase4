package view;

import authentication.View.*;
import interfaces.Listener;

public class LogInListener implements Listener {

    CenterPanel centerPanel;
    SignUp signUp;
    LogIn  logIn;

    public LogInListener(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
        this.signUp      = new SignUp();
        this.logIn       = new LogIn();
    }

    @Override
    public void Listen(String string) {
        if (string.equals("Sign In")){
            centerPanel.removeAll();
            centerPanel.revalidate();
            logIn.setLogInFormListener(new LogInFormListener());
            centerPanel.add(centerPanel.logIn);
            centerPanel.add(centerPanel.returnPanel);
            centerPanel.repaint();
        }
        if (string.equals("Sign Up")){
            centerPanel.removeAll();
            centerPanel.revalidate();
            signUp.setFormListener(new RegistrationFormListener());
            centerPanel.add(centerPanel.signUp);
            centerPanel.add(centerPanel.returnPanel);
            centerPanel.repaint();
        }
    }
}
