package view.Pages.Explore.Search;

import authentication.Controller.AuthController;
import interfaces.Listener;
import model.User;
import view.ButtonPanelTemplate;
import view.MainPanel;
import view.Objects.Profile.ProfilePanel;
import view.Objects.Profile.ProfilePanelListener;
import view.Pages.ProfilePanelLabel;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedList;

public class SearchResult extends ButtonPanelTemplate {

    private LinkedList<User>              usersFound;
    private LinkedList<ProfilePanelLabel> profiles;
    private User                          viewer;
    private JScrollPane                   scrollPane;
    private JPanel                        panel;

    public SearchResult(){
        super();
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(LineBorder.createBlackLineBorder());
        panel.setBackground(backgroundColor);
        profiles = new LinkedList<>();
    }

    public void addComponents(LinkedList<User> users) {
        viewer = AuthController.currentUser;
        this.usersFound = users;
        int height = (int) (usersFound.size() * (mainPanelHeight / 2.2 + 15));

        int y = 50;
        for (User user : usersFound) {
            ProfilePanelLabel profilePanelLabel = new ProfilePanelLabel(user);
            profilePanelLabel.setBounds(0, y, profilePanelLabel.getWidth(), profilePanelLabel.getHeight());
            y = y + profilePanelLabel.getHeight() + 15;
            profiles.add(profilePanelLabel);
            panel.add(profilePanelLabel);
        }


        for (ProfilePanelLabel profilePanelLabel : profiles) {
            profilePanelLabel.addListener(new Listener() {
                @Override
                public void Listen(String string) {
                    if (string.equals("Profile")) {
                        showProfilePanel(profilePanelLabel.getUser());
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

    public void showProfilePanel(User user) {
        ProfilePanel profilePanel = new ProfilePanel(viewer, user);
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(profilePanel);
        MainPanel.mainPanel.repaint();
        profilePanel.addListener(new ProfilePanelListener(profilePanel));
    }

    public void showLabel(String text){
        label1.setText(text);
        label1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        label1.setBackground(backgroundColor);
        label1.setForeground(Color.WHITE);
        label1.setBounds(300, 20, 100, 50);
        label1.setVisible(true);
        this.add(label1);
    }

}
