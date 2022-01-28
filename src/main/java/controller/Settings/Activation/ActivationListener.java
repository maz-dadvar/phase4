package controller.Settings.Activation;

import controller.Settings.Privacy.SettingsController;

public class ActivationListener {

    private SettingsController settingsController = new SettingsController();

    public ActivationListener(ActivationEvent activationEvent) {
        settingsController.deactivate(activationEvent.getUserId());
    }


}
