package view.Pages.Explore.Search;

import interfaces.Dimensions;
import interfaces.Listener;
import interfaces.SearchMode;
import model.User;
import view.MainPanel;
import view.Objects.RetweetMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class SearchPanel extends JPanel implements Dimensions, ActionListener {

    private JButton              searchButton;
    public  SearchMode           searchMode;
    public  LinkedList<Listener> listeners;
    private JLabel               label;
    private JTextField           textField;
    private SearchResult         searchResultPanel;

    public SearchPanel() {
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight));

        searchButton = new JButton("Search");
        label     = new JLabel();
        textField = new JTextField();
        listeners = new LinkedList<>();
        searchResultPanel = new SearchResult();

        searchButton.setBackground(buttonColor);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        label.setBackground(this.getBackground());
        label.setForeground(Color.WHITE);

        searchButton.setBorder(BorderFactory.createRaisedBevelBorder());

        searchButton.setBounds(frameWidth/2 - buttonWidth, 300, buttonWidth*2, buttonHeight);
        label.setBounds(20, 100, 200, 50);
        textField.setBounds(120, 113, textFieldWidth, textFieldHeight);

        searchButton.addActionListener(this);
        this.add(searchButton);
        this.add(label);
        this.add(textField);

        searchResultPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Profile")){
                    //
                }
            }
        });
    }

    public void addListener(Listener listeners) {
        this.listeners.add(listeners);
    }

    public void setText() {
        label.setText("Enter " + searchMode.getText());
    }

    public String getText(){
        return textField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == searchButton) {
                listener.Listen("Search");
            }
        }
    }

    public void showSearchResult(LinkedList<User> users){
        if (!users.isEmpty()){
            searchResultPanel.addComponents(users);
            MainPanel.mainPanel.removeAll();
            MainPanel.mainPanel.revalidate();
            MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
            MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
            MainPanel.mainPanel.add(searchResultPanel);
            MainPanel.mainPanel.repaint();
        } else {
           showNoResultError();
        }
    }

    private void showNoResultError(){
        JFrame searchResultMessage = new JFrame();
        searchResultMessage.setLayout(new BorderLayout());
        searchResultMessage.setSize(new Dimension(300,90));
        searchResultMessage.setTitle("Result Message");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.GREEN);
        panel.setSize(new Dimension(300, 50));
        label = new JLabel();
        label.setBounds(0, 0, 300, 50);
        label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        label.setBackground(backgroundColor);
        label.setForeground(Color.BLACK);
        label.setText("           No User found!");
        panel.add(label);
        panel.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        searchResultMessage.add(panel);
        searchResultMessage.setLocationRelativeTo(null);
        searchResultMessage.setResizable(false);
        searchResultMessage.setVisible(true);
    }
}
