package view.Pages.Timeline;

import interfaces.Dimensions;
import interfaces.Listener;
import view.ButtonPanelTemplate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportPanel extends ButtonPanelTemplate implements Dimensions, ActionListener {

    public ReportPanel() {
        super();
        this.setSize(new Dimension(300, 350));
        this.setBackground(backgroundColor);

        button1.setText("Spam");
        button2.setText("Child Abuse");
        button3.setText("Scam");
        button4.setText("Dangerous Organization");
        button5.setText("Bullying");

        button1.setBounds(this.getWidth()/2 - buttonWidth, 30                  , 2*buttonWidth, buttonHeight);
        button2.setBounds(this.getWidth()/2 - buttonWidth, 60  +   buttonHeight, 2*buttonWidth, buttonHeight);
        button3.setBounds(this.getWidth()/2 - buttonWidth, 90  + 2*buttonHeight, 2*buttonWidth, buttonHeight);
        button4.setBounds(this.getWidth()/2 - buttonWidth, 120 + 3*buttonHeight, 2*buttonWidth, buttonHeight);
        button5.setBounds(this.getWidth()/2 - buttonWidth, 150 + 4*buttonHeight, 2*buttonWidth, buttonHeight);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);

        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        button4.setVisible(true);
        button5.setVisible(true);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Spam");
            }
            if (e.getSource() == button2) {
                listener.Listen("Child_abuse");
            }
            if (e.getSource() == button3) {
                listener.Listen("Scam");
            }
            if (e.getSource() == button4) {
                listener.Listen("Dangerous Organization");
            }
            if (e.getSource() == button5) {
                listener.Listen("Bullying");
            }
        }
    }
}
