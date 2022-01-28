package controller.Information;

import model.User;

public class EditInfoListener {

    public EditInfoController editInfoController = new EditInfoController();

    public EditInfoListener(EditInfoEvent editInfoEvent) {
        editInfoController.editName(editInfoEvent.user.name);
        editInfoController.editLastname(editInfoEvent.user.lastname);
        editInfoController.editUsername(editInfoEvent.user.username);
        editInfoController.editDateOfBirth(editInfoEvent.user.dateOfBirth);
        editInfoController.editEmailAddress(editInfoEvent.user.emailAddress);
        editInfoController.editPhoneNumber(editInfoEvent.user.phoneNumber);
        editInfoController.submit();
    }

    public void submitProfileImage(User user){

    }
}
