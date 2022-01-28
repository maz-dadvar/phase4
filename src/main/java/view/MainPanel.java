package view;

import authentication.Controller.AuthController;
import controller.FollowRequests.FollowRequestsController;
import controller.Lists.ListsController;
import controller.Messaging.MessagingController;
import controller.Timeline.ShowTweetsController;
import interfaces.Dimensions;
import interfaces.Listener;
import view.Pages.Explore.ExplorePanel;
import view.Pages.Explore.Search.SearchResult;
import view.Pages.LogoPanel;
import view.Pages.Messaging.ContactsPanel;
import view.Pages.PersonalPage.FollowRequests;
import view.Pages.PersonalPage.InfoPanel;
import view.Pages.PersonalPage.Notifications.NotificationsListener;
import view.Pages.PersonalPage.PersonalPagePanel;
import view.Pages.Settings.SettingsPanel;
import view.Pages.Timeline.ShowTweetsPanel;
import view.Pages.Timeline.TimelinePanel;
import view.Pages.ToolBar;
import view.Pages.TopPanel;
import javax.swing.*;

public class MainPanel extends JPanel implements Dimensions {

    public CenterPanel       centerPanel;
    public TimelinePanel     timelinePanel;
    public SettingsPanel     settingsPanel;
    public ExplorePanel      explorePanel;
    public ContactsPanel     contactsPanel;
    public PersonalPagePanel personalPagePanel;
    public ToolBar           toolBar;
    public TopPanel          topPanel;
    public static MainPanel  mainPanel;
    public SearchResult      listsPanel;

    public MainPanel() {
        this.setLayout(null);
        this.setBackground(backgroundColor);
        mainPanel = this;

        centerPanel       = new CenterPanel();
        settingsPanel     = new SettingsPanel();
        explorePanel      = new ExplorePanel();
        contactsPanel     = new ContactsPanel();
        personalPagePanel = new PersonalPagePanel();
        timelinePanel     = new TimelinePanel();
        toolBar           = new ToolBar();
        topPanel          = new LogoPanel();
        listsPanel        = new SearchResult();

        centerPanel.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());
        topPanel.setBounds(0, 0, topPanel.getWidth(), topPanel.getHeight());
        timelinePanel.setBounds(0, topPanel.getHeight(), timelinePanel.getWidth(), timelinePanel.getHeight());
        settingsPanel.setBounds(0, topPanel.getHeight(), settingsPanel.getWidth(), settingsPanel.getHeight());
        explorePanel.setBounds(0, topPanel.getHeight(), explorePanel.getWidth(), explorePanel.getHeight());
        contactsPanel.setBounds(0, topPanel.getHeight(), contactsPanel.getWidth(), contactsPanel.getHeight());
        personalPagePanel.setBounds(0, topPanel.getHeight(), personalPagePanel.getWidth(), personalPagePanel.getHeight());
        toolBar.setBounds(0, topPanel.getHeight() + mainPanelHeight, toolBar.getWidth(), toolBar.getHeight());

        this.add(centerPanel);

        centerPanel.logIn.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Log In")) {
                    centerPanel.logInAction();
                }
            }
        });

        centerPanel.signUp.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Sign Up")) {
                    centerPanel.signUpAction();
                }
            }
        });

        centerPanel.returnPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Return")) {
                    centerPanel.returnAction();
                }
            }
        });

        contactsPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Start New Conversation")){
                    contactsPanel.removeComps();
                    listsPanel = new SearchResult();
                    listsPanel.addComponents((new ListsController()).getFollowings(AuthController.currentUser));
                    showPanel(listsPanel);
                }
            }
        });

        toolBar.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Explore")) {
                    showPanel(explorePanel);
                } else if (string.equals("Timeline")) {
                    timelinePanel = new TimelinePanel();
                    timelinePanel.setBounds(0, topPanel.getHeight(), timelinePanel.getWidth(), timelinePanel.getHeight());
                    timelinePanel.addComponents((new ShowTweetsController()).getTimelineTweets(AuthController.currentUser));
                    showPanel(timelinePanel);
                } else if (string.equals("Settings")) {
                    showPanel(settingsPanel);
                } else if (string.equals("Messaging")) {
                    contactsPanel.removeComps();
                    contactsPanel.setContacts((new MessagingController()).getContacts(AuthController.currentUser));
                    contactsPanel.addComponents();
                    showPanel(contactsPanel);
                } else if (string.equals("Personal Page")) {
                    showPanel(personalPagePanel);
                }
            }
        });

        explorePanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Search User")) {
                    explorePanel.showSearchModePanel();
                } else if (string.equals("Popular Tweets")) {
                    explorePanel.showPopularPanel();
                }
            }
        });

        personalPagePanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("New Tweet")) {
                    personalPagePanel.newTweetPanel.showNewTweetPanel(personalPagePanel.newTweetPanel);
                }
                if (string.equals("Show Tweets")) {
                    ShowTweetsPanel showTweetsPanel = new ShowTweetsPanel();
                    showPanel(showTweetsPanel);
                    showTweetsPanel.addComponents((new ShowTweetsController()).getUserTweets(AuthController.currentUser.getId()));
                }
                if (string.equals("Edit Information")) {
                    personalPagePanel.editInfoPanel.showEditInfoPanel(personalPagePanel.editInfoPanel);
                }
                if (string.equals("Followings")) {
                    listsPanel = new SearchResult();
                    listsPanel.addComponents((new ListsController()).getFollowings(AuthController.currentUser));
                    showPanel(listsPanel);
                }
                if (string.equals("Followers")) {
                    listsPanel = new SearchResult();
                    listsPanel.addComponents((new ListsController()).getFollowers(AuthController.currentUser));
                    showPanel(listsPanel);
                }
                if (string.equals("Info")) {
                    personalPagePanel.infoPanel = new InfoPanel();
                    personalPagePanel.infoPanel.showInfoPanel(personalPagePanel.infoPanel);
                }
                if (string.equals("Follow Requests")){
                    personalPagePanel.followRequestsPanel = new FollowRequests();
                    personalPagePanel.followRequestsPanel.addComponents((new FollowRequestsController()).getFollowRequests());
                    showPanel(personalPagePanel.followRequestsPanel);
                }
                if (string.equals("Notifications")) {
                    NotificationsListener notificationsListener = new NotificationsListener();
                    notificationsListener.sendNotificationsRequest();
                    personalPagePanel.showNotificationsPanel(notificationsListener.getNotifications());
                }
                if (string.equals("Sent Follow Requests")) {
                    listsPanel = new SearchResult();
                    listsPanel.addComponents((new FollowRequestsController()).sentFollowRequests());
                    showPanel(listsPanel);
                }
                if (string.equals("Saved Messages")) {

                }
                if (string.equals("Logout")) {
                    personalPagePanel.showLogoutPanel();
                }
            }
        });

        settingsPanel.addListener(new Listener() {
            @Override
            public void Listen(String string) {
                if (string.equals("Privacy")) {
                    settingsPanel.privacyPanel.showPrivacyPanel(settingsPanel.privacyPanel);
                }
                if (string.equals("Last Seen Mode")) {
                    settingsPanel.lastSeenModePanel.showLastSeenModePanel(settingsPanel.lastSeenModePanel);
                }
                if (string.equals("Deactivate")) {
                    settingsPanel.deactivatePanel.showDeactivatePanel(settingsPanel.deactivatePanel);
                }
                if (string.equals("Change Password")) {
                    settingsPanel.changePasswordPanel.showChangePasswordPanel(settingsPanel.changePasswordPanel);
                }
                if (string.equals("Delete Account")) {
                    settingsPanel.deleteAccountPanel.showDeleteAccountPanel(settingsPanel.deleteAccountPanel);
                }
            }
        });

    }

    public void showPanel(JPanel pageTemplate) {
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.add(topPanel);
        mainPanel.add(toolBar);
        mainPanel.add(pageTemplate);
        mainPanel.repaint();
    }

}
