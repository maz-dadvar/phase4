package view.Pages.Settings.LastSeenMode;

import interfaces.Listener;
import view.ButtonPanelTemplate;
import view.MainPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LastSeenModePanel extends ButtonPanelTemplate implements ActionListener {

    public LastSeenModePanel() {
        super();

        button1.setText("Everybody");
        button2.setText("Nobody");
        button3.setText("Contacts");

        int distance = (this.getHeight() - 3 * buttonHeight) / 4;
        button1.setBounds(frameWidth/2 - buttonWidth, distance, buttonWidth*2, buttonHeight);
        button2.setBounds(frameWidth/2 - buttonWidth, buttonHeight + 2*distance, buttonWidth*2, buttonHeight);
        button3.setBounds(frameWidth/2 - buttonWidth, 2*buttonHeight + 3*distance, buttonWidth*2, buttonHeight);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        this.add(button1);
        this.add(button2);
        this.add(button3);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Everybody");
            }
            if (e.getSource() == button2) {
                listener.Listen("Nobody");
            }
            if (e.getSource() == button3) {
                listener.Listen("Contacts");
            }
        }
    }

    public void showLastSeenModePanel(LastSeenModePanel lastSeenModePanel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(lastSeenModePanel);
        MainPanel.mainPanel.repaint();
    }

}
