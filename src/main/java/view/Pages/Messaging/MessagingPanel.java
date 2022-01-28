package view.Pages.Messaging;

import authentication.Controller.AuthController;
import interfaces.Listener;
import model.PM;
import view.ButtonPanelTemplate;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MessagingPanel extends ButtonPanelTemplate implements ActionListener {

    private JScrollPane                   scrollPane;
    private JPanel                        panel;
    private LinkedList<PMPanel>           allPMPanels;
    private LinkedList<PM>                allPMs;
    private JTextField                    sendMessageBox;
    private ImageIcon                     sendMessageIcon;

    public MessagingPanel(){
        super();
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight + 70));
        this.setBackground(backgroundColor);
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(LineBorder.createBlackLineBorder());
        panel.setBackground(backgroundColor);
        sendMessageBox = new JTextField();
        sendMessageBox.setBackground(Color.WHITE);
        sendMessageBox.setForeground(Color.BLACK);
        sendMessageBox.setBounds(5, mainPanelHeight, mainPanelWidth - 90, 55);
        sendMessageBox.setVisible(true);
        this.add(sendMessageBox);
        Image send = new ImageIcon("./resources/sendMessageLogo.png").getImage().getScaledInstance(60,
                60, Image.SCALE_DEFAULT);
        sendMessageIcon = new ImageIcon(send);
        button1.setIcon(sendMessageIcon);
        button1.setBounds(mainPanelWidth - 83, mainPanelHeight, sendMessageIcon.getIconWidth(),
                sendMessageIcon.getIconHeight());
        button1.addActionListener(this);
        this.add(button1);
    }

    public void addComponents(LinkedList<PM> pms) {
        allPMs = pms;
        LinkedList<PMPanel> pmPanels = new LinkedList<>();
        for (PM pm : pms){
            if (pm.getWriter().getId() == AuthController.currentUser.getId()){
                pmPanels.add(new PMPanel(pm, true));
            } else {
                pmPanels.add(new PMPanel(pm, false));
            }
        }
        int height = (int) (pms.size() * (mainPanelHeight / 2.2 + 15));

        int y = 50;
        allPMPanels = new LinkedList<>();
        for (PMPanel pmPanel : pmPanels) {
            pmPanel.setBounds(0, y, pmPanel.getWidth(), pmPanel.getHeight());
            y = y + pmPanel.getHeight() + 15;
            allPMPanels.add(pmPanel);
            panel.add(pmPanel);
        }
        scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setBounds(0, 0, (int) (mainPanelWidth*0.97), mainPanelHeight*height);
        panel.setPreferredSize(new Dimension((int) (mainPanelWidth*0.97), mainPanelHeight*height));
        scrollPane.setBounds(0, 0, (int) (mainPanelWidth*0.97), (int) (mainPanelHeight*0.995));
        scrollPane.setVisible(true);
        this.add(scrollPane);
    }


    public String getText(){
        return sendMessageBox.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Listener listener : listeners){
            if (e.getSource() == button1){
                listener.Listen("Send");
            }
        }
    }
}
