package view.Pages.Explore;

import controller.Search.SearchListener;
import interfaces.Dimensions;
import interfaces.Listener;
import interfaces.SearchMode;
import model.Search;
import model.User;
import view.MainPanel;
import view.Pages.Explore.Search.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SearchModePanel extends JPanel implements Dimensions, ActionListener {

    private JButton name;
    private JButton lastname;
    private JButton username;
    public LinkedList<Listener> listeners;
    private SearchPanel searchPanel;

    public SearchModePanel() {
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight));

        searchPanel = new SearchPanel();
        listeners   = new LinkedList<>();
        name        = new JButton("Search By Name");
        lastname    = new JButton("Search By Lastname");
        username    = new JButton("Search By Username");

        name.setBackground(buttonColor);
        lastname.setBackground(buttonColor);
        username.setBackground(buttonColor);

        name.setBorder(BorderFactory.createRaisedBevelBorder());
        lastname.setBorder(BorderFactory.createRaisedBevelBorder());
        username.setBorder(BorderFactory.createRaisedBevelBorder());

        int distance = (this.getHeight() - 3 * buttonHeight) / 4;
        name.setBounds(frameWidth/2 - buttonWidth, distance, buttonWidth*2, buttonHeight);
        lastname.setBounds(frameWidth/2 - buttonWidth, buttonHeight + 2*distance, buttonWidth*2, buttonHeight);
        username.setBounds(frameWidth/2 - buttonWidth, 2*buttonHeight + 3*distance, buttonWidth*2, buttonHeight);

        name.addActionListener(this);
        lastname.addActionListener(this);
        username.addActionListener(this);

        this.add(name);
        this.add(lastname);
        this.add(username);

        searchPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Search")) {
                    SearchListener searchListener = new SearchListener(new Search(searchPanel.searchMode, searchPanel.getText()));
                    searchPanel.showSearchResult(searchListener.getResult().getUsersFound());
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
            if (e.getSource() == name) {
                listener.Listen("Search By Name");
            }
            if (e.getSource() == lastname) {
                listener.Listen("Search By Lastname");
            }
            if (e.getSource() == username) {
                listener.Listen("Search By Username");
            }
        }
    }

    public void showSearchPanel(SearchMode searchMode) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        searchPanel.searchMode = searchMode;
        searchPanel.setText();
        MainPanel.mainPanel.add(searchPanel);
        MainPanel.mainPanel.repaint();
    }
}

