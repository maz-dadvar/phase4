package view.Exit;

import interfaces.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnPanel extends ButtonPanel implements ActionListener {

    public ReturnPanel() {
        super();
        this.setBounds(0, selectPanelHeight, frameWidth, buttonPanelHeight);
        button.setText("Return");
        button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button){
                listener.Listen("Return");
            }
        }
    }
}
