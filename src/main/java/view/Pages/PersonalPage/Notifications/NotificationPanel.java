package view.Pages.PersonalPage.Notifications;

import interfaces.Dimensions;
import model.Notification;

import javax.swing.*;
import java.awt.*;

public class NotificationPanel extends JPanel implements Dimensions {

    public NotificationPanel(Notification notification){
        this.setLayout(null);
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight/4));
        this.setBackground(backgroundColor);

        JLabel label = new JLabel();
        label.setText(notification.getText());
        label.setFont(new Font("Times New Roman", Font.BOLD, 24));
        label.setBackground(backgroundColor);
        label.setForeground(Color.WHITE);
        label.setBounds(10, 40, getWidth(), getHeight());

        this.add(label);
    }
}
