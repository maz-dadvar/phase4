package view.Pages.PersonalPage;

import interfaces.Listener;
import model.User;
import view.ButtonPanelTemplate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FollowRequestPanel extends ButtonPanelTemplate implements ActionListener {

    private User user;

    public FollowRequestPanel(User user){
        super();
        this.user = user;
        this.setBackground(backgroundColor);
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight/5));

        label1.setText(user.getName() + " " + user.getLastname());
        label1.setBackground(backgroundColor);
        label1.setForeground(Color.WHITE);
        button1.setText("Reject");
        button2.setText("Accept");

        label1.setFont(new Font("Times New Roman", Font.BOLD, 24));

        label1.setBounds(10, 0, 150, 70);
        button1.setBounds(250, 15, buttonWidth, buttonHeight);
        button2.setBounds(300 + buttonWidth, 15, buttonWidth, buttonHeight);

        button1.addActionListener(this);
        button2.addActionListener(this);

        this.add(button1);
        this.add(button2);
        this.add(label1);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners){
            if (e.getSource() == button1){
                listener.Listen("Reject");
            }
            if (e.getSource() == button2) {
                listener.Listen("Accept");
            }
        }
    }

    public User getUser() {
        return user;
    }
}
