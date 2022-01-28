package controller.Settings.ChangePassword;

import controller.Information.EditInfoController;

public class ChangePasswordListener {

    public EditInfoController changePasswordController = new EditInfoController();

    public ChangePasswordListener(ChangePasswordEvent changePasswordEvent) {
        changePasswordController.changePassword(changePasswordEvent.newPassword);
        changePasswordController.submit();
    }
}
