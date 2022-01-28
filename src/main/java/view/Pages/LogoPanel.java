package view.Pages;

import interfaces.Dimensions;

import javax.swing.*;
import java.awt.*;

public class LogoPanel extends TopPanel implements Dimensions {

    public LogoPanel() {
        super();
        this.setBackground(new Color(4, 50, 95));
        this.setSize(new Dimension(logoPanelWidth, logoPanelHeight));


        JLabel title = new JLabel();
        title.setForeground(buttonColor);
        title.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        title.setText("Twitter!");
        title.setBounds((int) (frameWidth / 2.7), (int) ((this.getHeight() - logoHeight) / 9), 200, 30);

        this.add(title);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawRect(147, 5, 200, 40);
    }
}
