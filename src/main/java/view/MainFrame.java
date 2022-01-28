package view;

import javax.swing.*;
import java.awt.*;
import interfaces.Dimensions;

public class MainFrame extends JFrame implements Dimensions{

    private MainPanel mainPanel;
    public static JFrame mainFrame;

    public MainFrame() {
        super("Twitter");
        this.setBounds(3, 3, frameWidth, frameHeight);
        //this.setSize(480, 640);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.mainPanel = new MainPanel();
        this.add(mainPanel);
        mainFrame = this;
    }
}
