package view.Pages.Settings.ChangePassword;

import authentication.Controller.InvalidLogIn;
import controller.Settings.ChangePassword.*;
import interfaces.Listener;
import view.ButtonPanelTemplate;
import view.MainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordPanel extends ButtonPanelTemplate implements ActionListener {

    public ChangePasswordPanel() {
        super();

        button1.setText("Submit");

        label1.setBounds(10, mainPanelHeight/3 - 15, 150, 50);
        label2.setBounds(10, mainPanelHeight/3 + 25, 150, 50);
        label3.setBounds(40, mainPanelHeight/3 + 60, 150, 50);
        button1.setBounds((frameWidth - buttonWidth) / 2, mainPanelHeight*2/3, buttonWidth, buttonHeight);
        textField1.setBounds(130, mainPanelHeight/3, textFieldWidth, textFieldHeight);
        textField2.setBounds(130, mainPanelHeight/3 + 40, textFieldWidth, textFieldHeight);

        button1.addActionListener(this);

        label1.setText("Type new password : ");
        label2.setText("Re-type new password : ");
        label3.setText("Password is not valid!");

        label3.setVisible(false);

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(textField1);
        this.add(textField2);
        this.add(button1);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1){
                listener.Listen("Submit");
            }
        }
    }

    public void showChangePasswordPanel(ChangePasswordPanel changePasswordPanel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(changePasswordPanel);
        MainPanel.mainPanel.repaint();
    }

    public boolean changePasswordAction() {
        boolean flag = false;
        try {
            if (this.textField1.getText().equals(this.textField2.getText())){
                this.label3.setVisible(false);
                ChangePasswordListener changePasswordListener = new ChangePasswordListener(new ChangePasswordEvent(this, this.textField1.getText()));
                textField1.setText("");
                textField2.setText("");
                flag = true;
            } else {
                label3.revalidate();
                this.label3.setVisible(true);
                label3.repaint();
                flag = false;
                throw new InvalidLogIn("Invalid");
            }
        } catch (InvalidLogIn notValid){
            System.out.println(notValid.getMessage());
        }
        return flag;
    }
}
