package view.Pages;

import interfaces.Dimensions;
import interfaces.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ToolBar extends JPanel implements ActionListener, Dimensions {

    private JButton exploreButton;
    private JButton timelineButton;
    private JButton settingsButton;
    private JButton messagingButton;
    private JButton personalPageButton;
    public LinkedList<Listener> listeners;

    public ToolBar() {
        this.setLayout(null);
        this.setBackground(toolbarColor);
        this.setSize(new Dimension(toolbarWidth, toolbarHeight));
        listeners = new LinkedList<>();

        exploreButton      = new JButton("Explore");
        timelineButton     = new JButton("Timeline");
        settingsButton     = new JButton("Settings");
        messagingButton    = new JButton("Messaging");
        personalPageButton = new JButton("Personal Page");

        exploreButton.setBackground(buttonColor);
        timelineButton.setBackground(buttonColor);
        settingsButton.setBackground(buttonColor);
        messagingButton.setBackground(buttonColor);
        personalPageButton.setBackground(buttonColor);

        exploreButton.setBorder(BorderFactory.createRaisedBevelBorder());
        timelineButton.setBorder(BorderFactory.createRaisedBevelBorder());
        settingsButton.setBorder(BorderFactory.createRaisedBevelBorder());
        messagingButton.setBorder(BorderFactory.createRaisedBevelBorder());
        personalPageButton.setBorder(BorderFactory.createRaisedBevelBorder());

        int distance = (frameWidth - 5 * buttonWidth) / 6 - 2;
        settingsButton.setBounds(distance, this.getHeight() / 2 - 34 , buttonWidth, buttonHeight);
        exploreButton.setBounds(2*distance + buttonWidth, this.getHeight() / 2 - 34 , buttonWidth, buttonHeight);
        timelineButton.setBounds(3*distance + 2*buttonWidth, this.getHeight() / 2 - 34 , buttonWidth, buttonHeight);
        messagingButton.setBounds(4*distance + 3*buttonWidth, this.getHeight() / 2 - 35 , buttonWidth, buttonHeight);
        personalPageButton.setBounds(5*distance + 4*buttonWidth, this.getHeight() / 2 - 35 , (int) (buttonWidth * 1.1), buttonHeight);

        exploreButton.addActionListener(this);
        timelineButton.addActionListener(this);
        settingsButton.addActionListener(this);
        messagingButton.addActionListener(this);
        personalPageButton.addActionListener(this);

        this.add(exploreButton);
        this.add(timelineButton);
        this.add(settingsButton);
        this.add(messagingButton);
        this.add(personalPageButton);
    }

    public void addListener(Listener listeners) {
        this.listeners.add(listeners);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == exploreButton) {
                listener.Listen("Explore");
            }
            if (e.getSource() == timelineButton) {
                listener.Listen("Timeline");
            }
            if (e.getSource() == settingsButton) {
                listener.Listen("Settings");
            }
            if (e.getSource() == messagingButton) {
                listener.Listen("Messaging");
            }
            if (e.getSource() == personalPageButton) {
                listener.Listen("Personal Page");
            }
        }
    }
}
