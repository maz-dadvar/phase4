package view;

import interfaces.Dimensions;
import interfaces.Listener;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ButtonPanelTemplate extends JPanel implements Dimensions {

    protected JButton              button1;
    protected JButton              button2;
    protected JButton              button3;
    protected JButton              button4;
    protected JButton              button5;
    protected JButton              button6;
    protected JButton              button7;
    protected JButton              button8;
    protected JButton              button9;
    protected JLabel               label1;
    protected JLabel               label2;
    protected JLabel               label3;
    protected JLabel               label4;
    protected JLabel               label5;
    protected JLabel               label6;
    protected JLabel               label7;
    protected JLabel               label8;
    protected JLabel               label9;
    public    JLabel               label10;
    public    JTextField           textField1;
    public    JTextField           textField2;
    public    JTextField           textField3;
    public    JTextField           textField4;
    public    JTextField           textField5;
    public    JTextField           textField6;
    public    JTextField           textField7;
    public    LinkedList<Listener> listeners;

    public ButtonPanelTemplate() {
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight));

        listeners  = new LinkedList<>();
        button1    = new JButton();
        button2    = new JButton();
        button3    = new JButton();
        button4    = new JButton();
        button5    = new JButton();
        button6    = new JButton();
        button7    = new JButton();
        button8    = new JButton();
        button9    = new JButton();
        label1     = new JLabel();
        label2     = new JLabel();
        label3     = new JLabel();
        label4     = new JLabel();
        label5     = new JLabel();
        label6     = new JLabel();
        label7     = new JLabel();
        label8     = new JLabel();
        label9     = new JLabel();
        label10     = new JLabel();
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField7 = new JTextField();

        button1.setBackground(buttonColor);
        button2.setBackground(buttonColor);
        button3.setBackground(buttonColor);
        button4.setBackground(buttonColor);
        button5.setBackground(buttonColor);
        button6.setBackground(buttonColor);
        button7.setBackground(buttonColor);
        button8.setBackground(buttonColor);
        button9.setBackground(buttonColor);
        textField1.setBackground(Color.WHITE);
        textField2.setBackground(Color.WHITE);
        textField3.setBackground(Color.WHITE);
        textField4.setBackground(Color.WHITE);
        textField5.setBackground(Color.WHITE);
        textField6.setBackground(Color.WHITE);
        textField7.setBackground(Color.WHITE);

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        label4.setForeground(Color.WHITE);
        label5.setForeground(Color.WHITE);
        label6.setForeground(Color.WHITE);
        label7.setForeground(Color.WHITE);
        label8.setForeground(Color.WHITE);
        label9.setForeground(Color.WHITE);
        label10.setForeground(Color.WHITE);
        textField1.setForeground(Color.BLACK);
        textField2.setForeground(Color.BLACK);
        textField3.setForeground(Color.BLACK);
        textField4.setForeground(Color.BLACK);
        textField5.setForeground(Color.BLACK);
        textField6.setForeground(Color.BLACK);
        textField7.setForeground(Color.BLACK);

        label1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label6.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label8.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        label10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        textField1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textField7.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        button1.setBorder(BorderFactory.createRaisedBevelBorder());
        button2.setBorder(BorderFactory.createRaisedBevelBorder());
        button3.setBorder(BorderFactory.createRaisedBevelBorder());
        button4.setBorder(BorderFactory.createRaisedBevelBorder());
        button5.setBorder(BorderFactory.createRaisedBevelBorder());
        button6.setBorder(BorderFactory.createRaisedBevelBorder());
        button7.setBorder(BorderFactory.createRaisedBevelBorder());
        button8.setBorder(BorderFactory.createRaisedBevelBorder());
        button9.setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void addListener(Listener listeners) {
        this.listeners.add(listeners);
    }
}
