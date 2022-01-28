package view.Pages;

import interfaces.Listener;
import model.User;
import view.ButtonPanelTemplate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePanelLabel extends ButtonPanelTemplate implements ActionListener {

    private ImageIcon profileIcon;
    private User user;

    public ProfilePanelLabel(User user){
        super();
        this.user = user;
        this.setSize(new Dimension(mainPanelWidth, (int) (mainPanelHeight/3.5)));
        this.setBackground(toolbarColor);
        profileIcon = new ImageIcon(user.profilePicture.getImage().getScaledInstance((int) (profileImageWidth*1.5)
                , (int) (profileImageHeight*1.5), Image.SCALE_DEFAULT));

        button1.setIcon(profileIcon);
        label1.setText(user.name + " " + user.lastname);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 19));
        button1.setBounds(10, 30, profileImageWidth, profileImageHeight);
        label1.setBounds(150, 30, 200, 15);
        button1.addActionListener(this);
        this.add(button1);
        this.add(label1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners){
            if (e.getSource() ==  button1){
                listener.Listen("Profile");
            }
        }
    }

    public User getUser() {
        return user;
    }
}
