package view.Pages;

import interfaces.Dimensions;

import javax.swing.*;
import java.awt.*;

public class PageTemplate extends JPanel implements Dimensions {

    public JPanel showPanel;

    public PageTemplate() {
        showPanel = new JPanel();
        this.setLayout(null);
        this.setBackground(new Color(4, 50, 95));
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight));
        showPanel.setLayout(null);
        showPanel.setBackground(new Color(4, 50, 95));
        showPanel.setSize(new Dimension(mainPanelWidth, mainPanelHeight));
        this.add(showPanel);
    }

}
