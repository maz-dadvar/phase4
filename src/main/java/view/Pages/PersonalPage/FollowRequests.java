package view.Pages.PersonalPage;

import authentication.Controller.AuthController;
import controller.FollowRequests.AcceptAndRejectController;
import interfaces.Listener;
import model.User;
import view.ButtonPanelTemplate;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

public class FollowRequests extends ButtonPanelTemplate {

    private LinkedList<FollowRequestPanel> followRequestPanels;
    private JScrollPane                    scrollPane;
    private JPanel                         panel;

    public FollowRequests(){
        super();
        this.setBackground(backgroundColor);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(LineBorder.createBlackLineBorder());
        panel.setBackground(backgroundColor);
        followRequestPanels = new LinkedList<>();
    }

    public void addComponents(LinkedList<User> followRequests){
        int height = (int) (followRequests.size() * (mainPanelHeight / 2.2 + 15));

        int y = 80;
        for (User user : followRequests) {
            FollowRequestPanel followRequestPanel = new FollowRequestPanel(user);
            followRequestPanel.setBounds(0, y, followRequestPanel.getWidth(), followRequestPanel.getHeight());
            y = y + followRequestPanel.getHeight() + 15;
            followRequestPanels.add(followRequestPanel);
            panel.add(followRequestPanel);
        }

        for (FollowRequestPanel followRequestPanel : followRequestPanels) {
            followRequestPanel.addListener(new Listener() {
                @Override
                public void Listen(String string) {
                    if (string.equals("Reject")) {
                        (new AcceptAndRejectController()).reject(AuthController.currentUser, followRequestPanel.getUser());
                    }
                    if (string.equals("Accept")) {
                        (new AcceptAndRejectController()).accept(AuthController.currentUser, followRequestPanel.getUser());
                    }
                }
            });
        }

        scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setBounds(0, 0, (int) (mainPanelWidth*0.97), mainPanelHeight*height);
        panel.setPreferredSize(new Dimension((int) (mainPanelWidth*0.97), mainPanelHeight*height));
        scrollPane.setBounds(0, 0, (int) (mainPanelWidth*0.97), (int) (mainPanelHeight*0.995));
        scrollPane.setVisible(true);
        this.add(scrollPane);
    }

    public void removeComps(){
        if (scrollPane != null && panel != null){
            remove(scrollPane);
            remove(panel);
        }
    }

}
