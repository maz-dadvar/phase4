package view;

import interfaces.Dimensions;
import interfaces.Listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectPanel extends SelectTemplate implements Dimensions, ActionListener {

    int logoX;
    int logoY;
    private JButton btn1;
    private JButton btn2;


    public SelectPanel() {
        super();
        this.setSize(new Dimension(480, 540));

        JLabel title = new JLabel();
        title.setForeground(buttonColor);
        title.setFont(new Font("Times New Roman", Font.PLAIN, 36));
        title.setText("Twitter!");
        title.setBounds((int) (frameWidth / 2.7), (int) ((this.getHeight() - logoHeight) / 9), 200, 30);

        JPanel logo = new JPanel();

        logo.setBackground(Color.WHITE);
        logoX = (int) ((frameWidth - logoWidth) / 2.15);
        logoY = (int) ((frameWidth - logoHeight) / 2.15);
        logo.setBounds(logoX, logoY , logoWidth, logoHeight);

        JLabel label   = new JLabel();
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        label.setText("Welcome to Twitter");
        label.setBounds((int) (frameWidth / 3), (int) ((logoY + logoHeight) * 1.05), 250, 30);

        btn1 = new JButton("Sign Up");
        btn2 = new JButton("Sign In");

        btn1.setBackground(buttonColor);
        btn2.setBackground(buttonColor);

        btn1.setBounds(this.getWidth() / 2 - 100, (int) ((logoY + logoHeight) * 1.15), buttonWidth, buttonHeight);
        btn2.setBounds(this.getWidth() / 2 + 20, (int) ((logoY + logoHeight) * 1.15), buttonWidth, buttonHeight);

        btn1.setBorder(BorderFactory.createRaisedBevelBorder());
        btn2.setBorder(BorderFactory.createRaisedBevelBorder());

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        this.add(btn1);
        this.add(btn2);
        this.add(title);
        this.add(label);
        this.add(logo);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawRect(logoX / 2,logoY + logoHeight / 2,logoWidth + logoX, this.getHeight() - logoY - logoHeight / 2 - 25);
        g.setColor(Color.WHITE);
        g.drawRect(logoX / 2,logoY + logoHeight / 2,logoWidth + logoX, this.getHeight() - logoY - logoHeight / 2 - 25);
        g.setColor(Color.WHITE);
        g.drawRect((int) (frameWidth / 3.5), (this.getHeight() - logoHeight) / 14, 200, 45);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners) {
            if (e.getSource() == btn1){
                listener.Listen("Sign Up");
            }
            if (e.getSource() == btn2) {
                listener.Listen("Sign In");
            }
        }
    }


}
