package view.Pages.PersonalPage;

import authentication.Controller.AuthController;
import controller.Information.InfoController;
import interfaces.Dimensions;
import view.ButtonPanelTemplate;
import view.MainPanel;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends ButtonPanelTemplate implements Dimensions {

    InfoController infoController;

    public InfoPanel() {
        super();
        infoController = new InfoController();

        labelSettings();

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label6);
        this.add(label7);
        this.add(label8);
        this.add(label9);
        this.add(label10);
    }

    public void showInfoPanel(InfoPanel infoPanel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(infoPanel);
        MainPanel.mainPanel.repaint();
    }

    public void labelSettings() {
        label1.setText("Name                 : " + infoController.getName());
        label2.setText("Lastname           : " + infoController.getLastname());
        label3.setText("Username          : " + infoController.getUsername());
        if (infoController.getPrivacy()){
            label4.setText("Privacy             : Private");
        } else {
            label4.setText("Privacy             : Public");
        }

        if (infoController.getDateOfBirth() == null) {
            label5.setText("Date of Birth     : -----");
        } else {
            label5.setText("Date of Birth     : " + infoController.getDateOfBirth());
        }

        if (infoController.getPhoneNumber() == null || infoController.getPhoneNumber().equals("null")) {
            label6.setText("Phone Number  : -----");
        } else {
            label6.setText("Phone Number  : " + infoController.getPhoneNumber());
        }

        if (infoController.getBio() == null || infoController.getBio().equals("null")) {
            label7.setText("Bio                    : -----");
        } else {
            label7.setText("Bio                    : " + infoController.getBio());
        }

        label8.setText("Last Seen          : " + infoController.getLastSeenMode());
        label9.setText("E-mail Address : " + infoController.getEmailAddress());
        label10.setIcon(new ImageIcon(AuthController.currentUser.profilePicture.getImage().getScaledInstance((int) (profileImageWidth*1.5)
                , (int) (profileImageHeight*1.5), Image.SCALE_DEFAULT)));

        label1.setBounds(10, 90, 400, 20);
        label2.setBounds(10, 130, 400, 20);
        label3.setBounds(10, 170, 400, 20);
        label4.setBounds(10, 210, 400, 20);
        label5.setBounds(10, 250, 400, 20);
        label6.setBounds(10, 290, 400, 20);
        label7.setBounds(10, 330, 400, 20);
        label8.setBounds(10, 370, 400, 20);
        label9.setBounds(10, 410, 400, 20);
        label10.setBounds(mainPanelWidth - profileImageWidth*2 - 10,210, (int) (profileImageWidth*1.5), (int) (profileImageHeight*1.5));

        label1.setForeground(Color.BLUE);
        label2.setForeground(Color.BLUE);
        label3.setForeground(Color.BLUE);
        label4.setForeground(Color.BLUE);
        label5.setForeground(Color.BLUE);
        label6.setForeground(Color.BLUE);
        label7.setForeground(Color.BLUE);
        label8.setForeground(Color.BLUE);
        label9.setForeground(Color.BLUE);

        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label6.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label7.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label8.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label9.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        label5.setVisible(true);
        label6.setVisible(true);
        label7.setVisible(true);
        label8.setVisible(true);
        label9.setVisible(true);
        label10.setVisible(true);
    }
}
