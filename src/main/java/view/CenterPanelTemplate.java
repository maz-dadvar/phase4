package view;

import interfaces.Dimensions;

import javax.swing.*;

public class CenterPanelTemplate extends JPanel implements Dimensions {

    public CenterPanelTemplate() {
        this.setLayout(null);
        this.setBackground(backgroundColor);
        this.setBounds(0, 0, 480, 640);
    }
}
