package controller.Settings.LastSeenMode;

import controller.Settings.Privacy.SettingsController;
import model.LastSeenMode;

public class LastSeenModeListener {
    SettingsController settingsController = new SettingsController();

    public LastSeenModeListener(LastSeenModeEvent lastSeenModeEvent) {
        LastSeenMode lastSeenMode = new LastSeenMode(lastSeenModeEvent.getUserId(), lastSeenModeEvent.getLastSeenMode());
        settingsController.changeLastSeenMode(lastSeenMode);
    }

}
