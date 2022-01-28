package controller.Settings.Privacy;

import model.Privacy;

public class PrivacyListener {

    SettingsController settingsController = new SettingsController();

    public PrivacyListener(Privacy privacy) {
        settingsController.changePrivacy(privacy);
    }
}
