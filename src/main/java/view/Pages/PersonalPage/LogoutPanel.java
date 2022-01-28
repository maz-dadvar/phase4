package view.Pages.PersonalPage;

import interfaces.Dimensions;
import interfaces.Listener;
import view.ButtonPanelTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class LogoutPanel extends ButtonPanelTemplate implements Dimensions, ActionListener {

    public LogoutPanel() {
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight));

        button1 = new JButton("Logout");
        button2   = new JButton("Exit");
        listeners    = new LinkedList<>();

        button1.setBackground(buttonColor);
        button2.setBackground(buttonColor);

        button1.setBorder(BorderFactory.createRaisedBevelBorder());
        button2.setBorder(BorderFactory.createRaisedBevelBorder());

        int distance = (this.getHeight() - 2 * buttonHeight) / 3;
        button1.setBounds(frameWidth/2 - buttonWidth, distance, buttonWidth*2, buttonHeight);
        button2.setBounds(frameWidth/2 - buttonWidth, buttonHeight + 2*distance, buttonWidth*2, buttonHeight);

        button1.addActionListener(this);
        button2.addActionListener(this);

        this.add(button1);
        this.add(button2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Logout");
            }
            if (e.getSource() == button2) {
                listener.Listen("Exit");
            }
        }
    }
}
