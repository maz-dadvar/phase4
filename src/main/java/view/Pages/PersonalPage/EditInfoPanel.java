package view.Pages.PersonalPage;

import authentication.Controller.AuthController;
import controller.Information.EditInfoController;
import controller.Information.EditInfoListener;
import interfaces.Dimensions;
import interfaces.Listener;
import view.ButtonPanelTemplate;
import view.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class EditInfoPanel extends ButtonPanelTemplate implements Dimensions, ActionListener {

    public File imageFile;
    public ImageIcon imageIcon;

    public EditInfoPanel() {
        super();

        button1.setText("Edit Name");
        button2.setText("Edit Lastname");
        button3.setText("Edit Username");
        button4.setText("Edit E-mail");
        //button5.setText("Edit Date of Birth");
        button6.setText("Edit Phone Number");
        button7.setText("Edit Bio");
        button8.setText("Insert Image:");
        label1.setText("Edit Name                 : ");
        label2.setText("Edit Lastname           : ");
        label3.setText("Edit Username          : ");
        label4.setText("Edit E-mail Address : ");
        label6.setText("Edit Phone Number  : ");
        label7.setText("Edit Bio                    : ");
        label8.setText("Insert Profile Image     :  ");

        label1.setForeground(Color.BLUE);
        label2.setForeground(Color.BLUE);
        label3.setForeground(Color.BLUE);
        label4.setForeground(Color.BLUE);
        label6.setForeground(Color.BLUE);
        label7.setForeground(Color.BLUE);
        label8.setForeground(Color.BLUE);

        label1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        //label5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        label6.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        label7.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        label8.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        button1.setBounds(340, 90, (int) (buttonWidth*1.6), buttonHeight);
        button2.setBounds(340, 110 +   buttonHeight, (int) (buttonWidth*1.6), buttonHeight);
        button3.setBounds(340, 130 + 2*buttonHeight, (int) (buttonWidth*1.6), buttonHeight);
        button4.setBounds(340, 150 + 3*buttonHeight, (int) (buttonWidth*1.6), buttonHeight);
        button6.setBounds(340, 170 + 4*buttonHeight, (int) (buttonWidth*1.6), buttonHeight);
        button7.setBounds(340, 190 + 5*buttonHeight, (int) (buttonWidth*1.6), buttonHeight);
        button8.setBounds(340, 210 + 6*buttonHeight, (int) (buttonWidth*1.6), buttonHeight);
        label1.setBounds(10,  90, 400, 20);
        label2.setBounds(10, 141, 400, 20);
        label3.setBounds(10, 192, 400, 20);
        label4.setBounds(10, 243, 400, 20);
        label6.setBounds(10, 294, 400, 20);
        label7.setBounds(10, 345, 400, 20);
        label8.setBounds(10, 396, 400, 20);
        textField1.setBounds(165, 90, 150, buttonHeight);
        textField2.setBounds(165, button2.getY(), 150, buttonHeight);
        textField3.setBounds(165, button3.getY(), 150, buttonHeight);
        textField4.setBounds(165, button4.getY(), 150, buttonHeight);
        textField6.setBounds(165, button6.getY(), 150, buttonHeight);
        textField7.setBounds(165, button7.getY(), 150, buttonHeight);


        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        //label5.setVisible(true);
        label6.setVisible(true);
        label7.setVisible(true);
        label8.setVisible(true);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        //button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        //this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        //this.add(label5);
        this.add(label6);
        this.add(label7);
        this.add(label8);
        this.add(textField1);
        this.add(textField2);
        this.add(textField3);
        this.add(textField4);
        //this.add(textField5);
        this.add(textField6);
        this.add(textField7);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Edit Name");
            }
            if (e.getSource() == button2) {
                listener.Listen("Edit Lastname");
            }
            if (e.getSource() == button3) {
                listener.Listen("Edit Username");
            }
            if (e.getSource() == button4) {
                listener.Listen("Edit E-mail");
            }
            if (e.getSource() == button5) {
                listener.Listen("Edit Date of Birth");
            }
            if (e.getSource() == button6) {
                listener.Listen("Edit Phone Number");
            }
            if (e.getSource() == button7) {
                listener.Listen("Edit Bio");
            }
            if (e.getSource() == button8) {
                listener.Listen("Insert Image");
            }
        }
    }

    public void showEditInfoPanel(EditInfoPanel editInfoPanel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(editInfoPanel);
        MainPanel.mainPanel.repaint();
    }

    public void getImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            imageFile = file;
            try {
                ImageIcon image = new ImageIcon(ImageIO.read(file).getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                this.imageIcon = image;
                AuthController.currentUser.profilePicture = image;
                AuthController.currentUser.setImageFile(imageFile);
                EditInfoController editInfoController = new EditInfoController();
                editInfoController.submitProfileImage(AuthController.currentUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
