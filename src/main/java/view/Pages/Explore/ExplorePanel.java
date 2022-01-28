package view.Pages.Explore;

import authentication.Controller.AuthController;
import controller.Timeline.ShowTweetsController;
import interfaces.Listener;
import interfaces.SearchMode;
import view.MainPanel;
import view.Pages.PageTemplate;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ExplorePanel extends PageTemplate implements ActionListener {

    private JButton searchUser;
    private JButton popularTweets;
    public  LinkedList<Listener> listeners;
    private SearchModePanel searchModePanel;
    private PopularTweetsPanel popularTweetsPanel;

    public ExplorePanel() {
        super();
        showPanel.setBackground(Color.RED);

        listeners          = new LinkedList<>();
        searchUser         = new JButton("Search User");
        popularTweets      = new JButton("Popular Tweets");
        searchModePanel    = new SearchModePanel();
        popularTweetsPanel = new PopularTweetsPanel();

        searchUser.setBackground(buttonColor);
        popularTweets.setBackground(buttonColor);

        searchUser.setBorder(BorderFactory.createRaisedBevelBorder());
        popularTweets.setBorder(BorderFactory.createRaisedBevelBorder());

        int distance = (mainPanelHeight - 2 * buttonHeight) / 3;

        searchUser.setBounds(frameWidth/2 - buttonWidth, distance, buttonWidth*2, buttonHeight);
        popularTweets.setBounds(frameWidth/2 - buttonWidth, 2*distance + buttonHeight, buttonWidth*2, buttonHeight);

        searchUser.addActionListener(this);
        popularTweets.addActionListener(this);

        showPanel.add(searchUser);
        showPanel.add(popularTweets);

        searchModePanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Search By Name")) {
                    searchModePanel.showSearchPanel(SearchMode.NAME);
                }
                if (string.equals("Search By Lastname")) {
                    searchModePanel.showSearchPanel(SearchMode.LASTNAME);
                }
                if (string.equals("Search By Username")) {
                    searchModePanel.showSearchPanel(SearchMode.USERNAME);
                }
            }
        });
    }

    public void addListener(Listener listeners) {
        this.listeners.add(listeners);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == searchUser) {
                listener.Listen("Search User");
            }
            if (e.getSource() == popularTweets) {
                listener.Listen("Popular Tweets");
            }
        }
    }

    public void showSearchModePanel() {
        showPanel(searchModePanel);
    }

    public void showPopularPanel() {
        popularTweetsPanel.addComponents((new ShowTweetsController()).getPopularTweets(AuthController.currentUser));
        showPanel(popularTweetsPanel);
    }

    public void showPanel(JPanel panel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(panel);
        MainPanel.mainPanel.repaint();
    }


}
