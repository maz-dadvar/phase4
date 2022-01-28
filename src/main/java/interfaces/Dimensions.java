package interfaces;

import java.awt.*;

public interface Dimensions {
    public static final int frameWidth         = 500;
    public static final int frameHeight        = 640;
    public static final int buttonWidth        = 80;
    public static final int buttonHeight       = 30;
    public static final int logoWidth          = 300;
    public static final int logoHeight         = 300;
    public static final int selectPanelHeight  = 540;
    public static final int buttonPanelHeight  = frameHeight - selectPanelHeight;
    public static final int textFieldWidth     = 200;
    public static final int textFieldHeight    = 25;
    public static final int logoPanelWidth     = frameWidth;
    public static final int logoPanelHeight    = 60;
    public static final int toolbarWidth       = frameWidth;
    public static final int toolbarHeight      = 80;
    public static final int mainPanelWidth     = frameWidth;
    public static final int mainPanelHeight    = frameHeight - logoPanelHeight - toolbarHeight;
    public static final int profileImageWidth  = 100;
    public static final int profileImageHeight = 100;
    public static final int tweetOptionWidth   = 52;
    public static final int tweetOptionHeight  = 52;
    public static final int port               = 149;
    public static final String host            = "localhost";

    //public static final Color buttonColor     = new Color(255, 189, 2);
    //public static final Color backgroundColor = new Color(4, 50, 95);

    //public static final Color buttonColor     = new Color(54, 167, 245);
    public static final Color buttonColor     = new Color(54, 167, 245);
    public static final Color backgroundColor = new Color(4, 30, 57);
    public static final Color toolbarColor    = new Color(5, 38, 73);

}
