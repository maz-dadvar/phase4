package view.Objects;

import interfaces.Dimensions;

import javax.swing.*;
import java.awt.*;

public class RetweetMessage extends JPanel implements Dimensions {
    private JLabel label;

    public RetweetMessage() {
        this.setLayout(null);
        this.setBackground(Color.GREEN);
        this.setSize(new Dimension(300, 50));
        label = new JLabel();
        label.setBounds(0, 0, 300, 50);
        label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        label.setBackground(backgroundColor);
        label.setForeground(Color.BLACK);
        label.setText("           Tweet retweeted!");
        this.add(label);
    }
}
