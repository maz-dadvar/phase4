package view.Pages.Settings.Privacy;

import interfaces.Listener;
import view.ButtonPanelTemplate;
import view.MainPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrivacyPanel extends ButtonPanelTemplate implements ActionListener {

    public PrivacyPanel() {
        super();

        button1.setText("Public");
        button2.setText("Private");

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
                listener.Listen("Public");
            }
            if (e.getSource() == button2) {
                listener.Listen("Private");
            }
        }
    }

    public void showPrivacyPanel(PrivacyPanel privacyPanel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(privacyPanel);
        MainPanel.mainPanel.repaint();
    }


}
