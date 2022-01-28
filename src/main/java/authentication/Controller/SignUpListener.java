package authentication.Controller;

import authentication.View.RegistrationFormEvent;
import authentication.View.SignUpFormListener;

public class SignUpListener implements SignUpFormListener {

    private AuthController authController = new AuthController();

    @Override
    public void eventOccurred(RegistrationFormEvent registrationFormEvent) {
        authController.register(registrationFormEvent);
    }
}
