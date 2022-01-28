package view.Pages.Settings.Deactivate;

import interfaces.Listener;
import view.ButtonPanelTemplate;
import view.MainPanel;
import view.Pages.Settings.LastSeenMode.LastSeenModePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeactivatePanel extends ButtonPanelTemplate implements ActionListener {


    public DeactivatePanel() {
        super();

        button1.setText("Yes");
        button2.setText("No!");
        label1.setText("Deactivate Account");
        label2.setText("Are you sure?");

        button1.addActionListener(this);
        button2.addActionListener(this);

        label1.setFont(new Font("Times New Roman", Font.PLAIN, 24));

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);

        label1.setBounds((int) ((mainPanelWidth - label1.getWidth()) / 3.5), mainPanelHeight/4, 200, 50);
        label2.setBounds(215, (int) (mainPanelHeight/1.7), 200, 50);
        button1.setBounds(mainPanelWidth / 2  - buttonWidth - 30, mainPanelHeight*2/3, buttonWidth, buttonHeight);
        button2.setBounds(mainPanelWidth/2 + 30, mainPanelHeight*2/3, buttonWidth, buttonHeight);

        this.add(button1);
        this.add(button2);
        this.add(label1);
        this.add(label2);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Yes");
            }
            if (e.getSource() == button2) {
                listener.Listen("No");
            }
        }
    }

    public void showDeactivatePanel(DeactivatePanel deactivatePanel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(deactivatePanel);
        MainPanel.mainPanel.repaint();
    }

}
