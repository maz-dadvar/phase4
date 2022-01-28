package model;

import controller.User.Follow.FollowEvent;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class RequestToServer implements Serializable {

    private User          owner;
    private int           targetId;
    private int           requestDeactivateId;
    private boolean       requestTimelineTweets;
    private boolean       requestPopularTweets;
    private boolean       requestDeleteAccount;
    private boolean       requestShowTweets;
    private boolean       requestShowTweetsOfUser;
    private boolean       requestNotifications;
    private boolean       requestFollowRequests;
    private boolean       requestSentFollowRequests;
    private boolean       requestSavedMessages;
    private boolean       requestInfo;
    private boolean       requestForContacts;
    private boolean       requestForPMs;
    private boolean       requestRetweet;
    private boolean       requestFollowers;
    private boolean       requestFollowings;
    private boolean       accept;
    private boolean       reject;
    private PM            pm;
    private Mute          mute;
    private Tweet         newTweet;
    private Block         block;
    private Search        requestSearchUser;
    private String        requestChangePassword;
    private FollowEvent   follow;
    private Report        report;
    private Privacy       requestChangePrivacy;
    private EditInfo      editInformation;
    private Like          like;
    private LastSeenMode  lastSeenMode;
    private LocalDateTime lastSeen;
    private ImageIcon     profileImage;

    public RequestToServer(User owner){
        setMute(null);
        setOwnerId(owner);
        setTargetId(-1);
        setRequestTimelineTweets(false);
        setRequestPopularTweets(false);
        setRequestSearchUser(null);
        setRequestChangePrivacy(null);
        setPm(null);
        setLastSeenMode(null);
        setRequestDeactivateId(0);
        setRequestDeleteAccount(false);
        setNewTweet(null);
        setRequestRetweet(false);
        setRequestFollowers(false);
        setRequestFollowings(false);
        setRequestShowTweets(false);
        setRequestForContacts(false);
        setLike(null);
        setFollow(null);
        setBlock(null);
        setReport(null);
        setReject(false);
        setAccept(false);
        setRequestShowTweetsOfUser(false);
        setEditInformation(null);
        setRequestNotifications(false);
        setProfileImage(null);
        setRequestSentFollowRequests(false);
        setRequestSentFollowRequests(false);
        setRequestSavedMessages(false);
        setRequestInfo(false);
        setRequestChangePassword("");
        setLastSeen(null);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwnerId(User owner) {
        this.owner = owner;
    }

    public boolean isRequestTimelineTweets() {
        return requestTimelineTweets;
    }

    public void setRequestTimelineTweets(boolean requestTimelineTweets) {
        this.requestTimelineTweets = requestTimelineTweets;
    }

    public boolean isRequestPopularTweets() {
        return requestPopularTweets;
    }

    public void setRequestPopularTweets(boolean requestPopularTweets) {
        this.requestPopularTweets = requestPopularTweets;
    }

    public Search isRequestSearchUser() {
        return requestSearchUser;
    }

    public void setRequestSearchUser(Search requestSearchUser) {
        this.requestSearchUser = requestSearchUser;
    }

    public Privacy isRequestChangePrivacy() {
        return requestChangePrivacy;
    }

    public void setRequestChangePrivacy(Privacy requestChangePrivacy) {
        this.requestChangePrivacy = requestChangePrivacy;
    }

    public LastSeenMode isRequestChangeLastSeenMode() {
        return lastSeenMode;
    }

    public void setLastSeenMode(LastSeenMode lastSeenMode) {
        this.lastSeenMode = lastSeenMode;
    }

    public int isRequestDeactivate() {
        return requestDeactivateId;
    }

    public void setRequestDeactivateId(int requestDeactivateId) {
        this.requestDeactivateId = requestDeactivateId;
    }

    public boolean isRequestDeleteAccount() {
        return requestDeleteAccount;
    }

    public void setRequestDeleteAccount(boolean requestDeleteAccount) {
        this.requestDeleteAccount = requestDeleteAccount;
    }

    public Tweet isNewTweet() {
        return newTweet;
    }

    public void setNewTweet(Tweet newTweet) {
        this.newTweet = newTweet;
    }

    public boolean isRequestShowTweets() {
        return requestShowTweets;
    }

    public void setRequestShowTweets(boolean requestShowTweets) {
        this.requestShowTweets = requestShowTweets;
    }

    public boolean isRequestShowTweetsOfUser() {
        return requestShowTweetsOfUser;
    }

    public void setRequestShowTweetsOfUser(boolean requestShowTweetsOfUser) {
        this.requestShowTweetsOfUser = requestShowTweetsOfUser;
    }

    public EditInfo isEditInformation() {
        return editInformation;
    }

    public void setEditInformation(EditInfo editInformation) {
        this.editInformation = editInformation;
    }

    public boolean isRequestNotifications() {
        return requestNotifications;
    }

    public void setRequestNotifications(boolean requestNotifications) {
        this.requestNotifications = requestNotifications;
    }

    public boolean isRequestSentFollowRequests() {
        return requestSentFollowRequests;
    }

    public void setRequestSentFollowRequests(boolean requestSentFollowRequests) {
        this.requestSentFollowRequests = requestSentFollowRequests;
    }

    public boolean isRequestSavedMessages() {
        return requestSavedMessages;
    }

    public void setRequestSavedMessages(boolean requestSavedMessages) {
        this.requestSavedMessages = requestSavedMessages;
    }

    public boolean isRequestInfo(){
        return requestInfo;
    }

    public void setRequestInfo(boolean requestInfo){
        this.requestInfo = requestInfo;
    }

    public String getRequestChangePassword(){
        return requestChangePassword;
    }

    public void setRequestChangePassword(String password){
        this.requestChangePassword = password;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public FollowEvent getFollow() {
        return follow;
    }

    public void setFollow(FollowEvent follow) {
        this.follow = follow;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Mute getMute() {
        return mute;
    }

    public void setMute(Mute mute) {
        this.mute = mute;
    }

    public Privacy getRequestChangePrivacy() {
        return requestChangePrivacy;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public ImageIcon getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ImageIcon profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isRequestForContacts() {
        return requestForContacts;
    }

    public void setRequestForContacts(boolean requestForContacts) {
        this.requestForContacts = requestForContacts;
    }

    public boolean isRequestForPMs() {
        return requestForPMs;
    }

    public void setRequestForPMs(boolean requestForPMs) {
        this.requestForPMs = requestForPMs;
    }

    public PM getPm() {
        return pm;
    }

    public void setPm(PM pm) {
        this.pm = pm;
    }

    public boolean isRequestRetweet() {
        return requestRetweet;
    }

    public void setRequestRetweet(boolean requestRetweet) {
        this.requestRetweet = requestRetweet;
    }

    public boolean isRequestFollowers() {
        return requestFollowers;
    }

    public void setRequestFollowers(boolean requestFollowers) {
        this.requestFollowers = requestFollowers;
    }

    public boolean isRequestFollowings() {
        return requestFollowings;
    }

    public void setRequestFollowings(boolean requestFollowings) {
        this.requestFollowings = requestFollowings;
    }

    public boolean isRequestFollowRequests() {
        return requestFollowRequests;
    }

    public void setRequestFollowRequests(boolean requestFollowRequests) {
        this.requestFollowRequests = requestFollowRequests;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isReject() {
        return reject;
    }

    public void setReject(boolean reject) {
        this.reject = reject;
    }
}
