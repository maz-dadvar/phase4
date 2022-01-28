package controller.Settings.DeleteAccount;


public class DeleteAccountListener {
    public DeleteController deleteController = new DeleteController();
    public DeleteAccountListener(DeleteAccountEvent deleteAccountEvent) {
        deleteController.deleteAccount();
    }
}
