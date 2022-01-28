package view.Pages.PersonalPage;

import interfaces.Dimensions;
import interfaces.Listener;
import view.ButtonPanelTemplate;
import view.MainPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class NewTweetPanel extends ButtonPanelTemplate implements Dimensions, ActionListener {

    public JTextArea textArea;
    public ImageIcon imageIcon;
    public File imageFile;

    public NewTweetPanel() {
        super();

        button1.setText("Submit Tweet");
        button2.setText("Insert Image");
        label1.setText("Write your tweet : ");
        textArea = new JTextArea();

        button1.setBounds(300 - buttonWidth/2, 350, buttonWidth*2, buttonHeight);
        button2.setBounds(100, 350, buttonWidth, buttonHeight);
        label1.setBounds(10, 100, 150, 50);
        textArea.setBounds(200, 100, 250, 200);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);

        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label1.setVisible(true);

        button1.addActionListener(this);
        button2.addActionListener(this);

        this.add(button1);
        this.add(button2);
        this.add(label1);
        this.add(textArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == button1) {
                listener.Listen("Submit Tweet");
            }
            if (e.getSource() == button2) {
                listener.Listen("Insert Image");
            }
        }
    }

    public void showNewTweetPanel(NewTweetPanel newTweetPanel) {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(newTweetPanel);
        MainPanel.mainPanel.repaint();
    }

    public void getImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            imageFile = file;
            try {
                ImageIcon image = new ImageIcon(ImageIO.read(file).getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                label2.setIcon(image);
                this.imageIcon = image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
