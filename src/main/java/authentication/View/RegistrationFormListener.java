package authentication.View;

import authentication.Controller.AuthController;

public class RegistrationFormListener implements SignUpFormListener {

    private AuthController authController= new AuthController();

    @Override
    public void eventOccurred(RegistrationFormEvent registrationFormEvent) {
        authController.register(registrationFormEvent);
    }

}
