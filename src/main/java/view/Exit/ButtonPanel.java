package view.Exit;

import interfaces.Dimensions;
import interfaces.Listener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ButtonPanel extends JPanel implements Dimensions {

    protected JButton button;
    LinkedList<Listener> listeners;

    public ButtonPanel() {
        this.setLayout(null);
        this.listeners = new LinkedList<>();
        this.setSize(new Dimension(480, 100));
        this.setBackground(backgroundColor);

        button = new JButton();
        button.setBackground(buttonColor);
        button.setBounds(this.getWidth() - 110, this.getHeight() / 2 - 35 , buttonWidth, buttonHeight);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        this.add(button);
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }
}
