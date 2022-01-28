package view.Pages.Messaging;

import authentication.Controller.AuthController;
import model.PM;
import view.ButtonPanelTemplate;
import view.MainPanel;
import view.Objects.Profile.ProfilePanel;
import view.Objects.Profile.ProfilePanelListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

public class PMPanel extends ButtonPanelTemplate implements MouseListener {

    private PM pm;

    public PMPanel(PM pm, boolean isOwner){
        super();
        this.pm = pm;
        this.setSize(new Dimension(mainPanelWidth, mainPanelHeight/14));
        this.setBackground(backgroundColor);

        label1.setSize(new Dimension(mainPanelWidth, mainPanelHeight/14));
        label2.setSize(new Dimension(mainPanelWidth, mainPanelHeight/14));

        LocalDateTime time = pm.getDate();
        String date = time.getYear() + "-" + time.getMonthValue() + "-" + time.getDayOfMonth() + " " + time.getHour()
                + ":" + time.getMinute() + ":" + time.getSecond();

        label2.setText(date);

        label1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 16));

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);

        if (pm.getWriter().getId() == AuthController.currentUser.getId()){
            label1.setText("You : " + pm.getText());
        } else {
            label1.setText(pm.writer.name + " : " + pm.getText());
        }

        label1.setBounds(10,  10, 300,             30);
        label2.setBounds(330, 10,date.length()*10, 30);
        label1.setVisible(true);
        label2.setVisible(true);

        this.add(label1);
        this.add(label2);
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(buttonColor);
        g.drawRect(5, 11, mainPanelWidth - 50, mainPanelHeight/20 - 2);
    }

    public PM getPm() {
        return pm;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.pm.hasHyperLink() && this.pm.getHyperLinkUser() != null && e.getX() >= 5 && e.getX() <= 330
                && e.getY() >= 12 && e.getY() <= 30) {
            ProfilePanel profilePanel = new ProfilePanel(AuthController.currentUser, this.getPm().getHyperLinkUser());
            MainPanel.mainPanel.removeAll();
            MainPanel.mainPanel.revalidate();
            MainPanel.mainPanel.add(profilePanel);
            MainPanel.mainPanel.repaint();
            profilePanel.addListener(new ProfilePanelListener(profilePanel));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
