package view.Exit;

import interfaces.Listener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitPanel extends ButtonPanel implements ActionListener {

    public ExitPanel() {
        super();
        button.setText("Exit");
        button.addActionListener(this);
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button){
                listener.Listen("Exit");
            }
        }
    }
}
