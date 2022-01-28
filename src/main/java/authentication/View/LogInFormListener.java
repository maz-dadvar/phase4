package authentication.View;

import authentication.Controller.AuthController;

public class LogInFormListener implements SignInFormListener {

    private AuthController authController = new AuthController();

    @Override
    public void eventOccurred(LogInFormEvent logInFormEvent) {
        authController.logIn(logInFormEvent);
    }
}
