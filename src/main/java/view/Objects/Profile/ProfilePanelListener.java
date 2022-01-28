package view.Objects.Profile;

import authentication.Controller.AuthController;
import controller.Messaging.MessagingController;
import controller.Messaging.SendPMController;
import controller.Timeline.ShowTweetsController;
import controller.Tweet.Mute.MuteEvent;
import controller.Tweet.Mute.MuteListener;
import controller.User.Block.Block;
import controller.User.Block.BlockEvent;
import controller.User.Block.BlockListener;
import controller.User.Follow.FollowEvent;
import controller.User.Follow.FollowListener;
import interfaces.Listener;
import model.PM;
import model.User;
import view.MainPanel;
import view.Pages.Messaging.MessagingPanel;
import view.Pages.Timeline.ShowTweetsPanel;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ProfilePanelListener implements Listener {

    public ProfilePanel profilePanel;
    private ShowTweetsPanel showTweetsPanel;

    public ProfilePanelListener(ProfilePanel profilePanel) {
        this.profilePanel = profilePanel;
        showTweetsPanel = new ShowTweetsPanel();
    }

    @Override
    public void Listen(String string) {
        if (string.equals("Follow")) {
            FollowListener followListener = new FollowListener(new FollowEvent(this, profilePanel.viewer, profilePanel.user, true));
            profilePanel.changeFollowIcon(false);
        }
        if (string.equals("Unfollow")) {
            FollowListener followListener = new FollowListener(new FollowEvent(this, profilePanel.viewer, profilePanel.user, false));
            profilePanel.changeFollowIcon(true);
        }
        if (string.equals("Mute")) {
            MuteListener muteListener = new MuteListener(new MuteEvent(this, true, profilePanel.viewer, profilePanel.user));
            profilePanel.changeMuteIcon(false);
        }
        if (string.equals("UnMute")) {
            MuteListener muteListener = new MuteListener(new MuteEvent(this, false, profilePanel.viewer, profilePanel.user));
            profilePanel.changeMuteIcon(true);
        }
        if (string.equals("Block")) {
            BlockListener blockListener = new BlockListener(new BlockEvent(this, profilePanel.viewer, profilePanel.user, Block.BLOCK));
            profilePanel.changeBlockIcon(Block.BLOCK);
        }
        if (string.equals("Unblock")) {
            BlockListener blockListener = new BlockListener(new BlockEvent(this, profilePanel.viewer, profilePanel.user, Block.UNBLOCK));
            profilePanel.changeBlockIcon(Block.UNBLOCK);
        }
        if (string.equals("Tweets")) {
            showTweetsPanel = new ShowTweetsPanel();
            ShowTweetsController showTweetsOfUserController = new ShowTweetsController();
            showTweetsPanel.addComponents(showTweetsOfUserController.getUserTweets(profilePanel.user.getId()));
            showTweetsPanel();
        }
        if (string.equals("Return")) {
            profilePanel.returnToTimeline();
        }
        if (string.equals("Message")) {
            showChat(this.profilePanel.user);
        }
    }

    public void showTweetsPanel(){
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(showTweetsPanel);
        MainPanel.mainPanel.repaint();
    }

    public void showChat(User user){
        LinkedList<PM> pms;
        pms = (new MessagingController()).getPMs(AuthController.currentUser, user);
        MainPanel.mainPanel.removeAll();
        MainPanel.mainPanel.revalidate();
        MessagingPanel messagingPanel = new MessagingPanel();
        messagingPanel.addComponents(pms);
        messagingPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Send")){
                    (new SendPMController()).sendPM(new PM(AuthController.currentUser, user,
                            messagingPanel.getText(), LocalDateTime.now()));
                }
            }
        });
        MainPanel.mainPanel.add(MainPanel.mainPanel.topPanel);
        MainPanel.mainPanel.add(MainPanel.mainPanel.toolBar);
        MainPanel.mainPanel.add(messagingPanel);
        MainPanel.mainPanel.repaint();
    }
}
