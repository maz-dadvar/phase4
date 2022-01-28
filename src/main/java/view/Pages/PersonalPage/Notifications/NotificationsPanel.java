package view.Pages.PersonalPage.Notifications;

import model.Notification;
import view.ButtonPanelTemplate;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

public class NotificationsPanel extends ButtonPanelTemplate {

    private LinkedList<NotificationPanel> notificationPanels;
    private JScrollPane                   scrollPane;
    private JPanel                        panel;

    public NotificationsPanel(LinkedList<Notification> notifications){
        super();
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(LineBorder.createBlackLineBorder());
        panel.setBackground(backgroundColor);
        notificationPanels = new LinkedList<>();

        int height = (int) (notifications.size() * (mainPanelHeight / 3 + 15));

        int y = 0;
        for (Notification notification : notifications) {
            NotificationPanel notificationPanel = new NotificationPanel(notification);
            notificationPanel.setBounds(0, y, notificationPanel.getWidth(), notificationPanel.getHeight());
            y = y + notificationPanel.getHeight() + 15;
            notificationPanels.add(notificationPanel);
            panel.add(notificationPanel);
        }

        scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setBounds(0, 0, (int) (mainPanelWidth*0.97), mainPanelHeight*height);
        panel.setPreferredSize(new Dimension((int) (mainPanelWidth*0.97), mainPanelHeight*height));
        scrollPane.setBounds(0, 0, (int) (mainPanelWidth*0.97), (int) (mainPanelHeight*0.995));
        scrollPane.setVisible(true);
        this.add(scrollPane);
    }

}
