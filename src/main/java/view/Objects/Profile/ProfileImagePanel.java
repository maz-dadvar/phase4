package view.Objects.Profile;
import interfaces.Dimensions;
import model.User;

import javax.swing.*;
import java.awt.*;

public class ProfileImagePanel extends JPanel implements Dimensions {

    private JLabel imageLabel;

    public ProfileImagePanel(User user) {
        this.setSize(new Dimension(300, 300));
        this.setLayout(null);
        imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon(user.profilePicture.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        imageLabel.setBounds(0, 0, 300, 300);
        this.add(imageLabel);
    }
}
