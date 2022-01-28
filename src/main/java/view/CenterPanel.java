package view;

import authentication.Controller.AuthController;
import authentication.Controller.InvalidLogIn;
import authentication.View.LogIn;
import authentication.View.SignUp;
import controller.Timeline.ShowTweetsController;
import interfaces.Dimensions;
import interfaces.Listener;
import view.Exit.ButtonPanel;
import view.Exit.ExitPanel;
import view.Exit.ReturnPanel;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public class CenterPanel extends CenterPanelTemplate implements Dimensions{

    public SelectTemplate selectPanel;
    public ButtonPanel    buttonPanel;
    public LogIn          logIn;
    public SignUp         signUp;
    public ReturnPanel    returnPanel;
    LinkedList<Listener>  listeners;

    public CenterPanel() {
        super();
        this.logIn       = new LogIn();
        this.signUp      = new SignUp();
        this.selectPanel = new SelectPanel();
        this.buttonPanel = new ExitPanel();
        this.returnPanel = new ReturnPanel();
        this.listeners   = new LinkedList<>();

        buttonPanel.setBounds(0, 540, 480, 100);
        selectPanel.setBounds(0, 0, 480, 540);

        this.add(buttonPanel);
        this.add(selectPanel);

        this.addListener(new LogInListener(this));

        buttonPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Exit")){
                    ((Window) getRootPane().getParent()).dispose();
                }
            }
        });

        selectPanel.addListener(new LogInListener(this));

    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void logInAction() {
        try {
            if (logIn.logInFormEvent.getIsValid()){
                logIn.warning.setVisible(false);
                MainPanel.mainPanel.timelinePanel.addComponents((new ShowTweetsController()).getTimelineTweets(AuthController.currentUser));
                MainPanel.mainPanel.showPanel(MainPanel.mainPanel.timelinePanel);
            } else {
                throw new InvalidLogIn("Invalid");
            }
        } catch (InvalidLogIn notValid){
            logIn.warning.setVisible(true);
            System.out.println(notValid.getMessage());
        }
    }

    public void signUpAction() {
        try {
            if (signUp.signUpFormEvent.isValid() && signUp.signUpFormEvent.isServerFound()){
                signUp.setErrorVisible(false);
                MainPanel.mainPanel.timelinePanel.addComponents((new ShowTweetsController()).getTimelineTweets(AuthController.currentUser));
                MainPanel.mainPanel.showPanel(MainPanel.mainPanel.timelinePanel);
            } else {
                throw new InvalidLogIn("Invalid");
            }
        } catch (InvalidLogIn notValid){
            signUp.setErrorVisible(true);
            System.out.println(notValid.getMessage());
        }
    }

    public void returnAction() {
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        CenterPanel centerPanel = new CenterPanel();
        centerPanel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());
        MainPanel.mainPanel.add(centerPanel);
        MainPanel.mainPanel.repaint();
    }
}
