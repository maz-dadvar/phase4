package Server;

import controller.TweetDB;
import controller.UserDB;
import interfaces.Dimensions;
import model.*;
import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class ServerWorker extends Thread implements Dimensions {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private final Object waiter;
    private User user;
    private RequestToServer requestFromClient;
    private TweetDB tweetDB;
    private UserDB userDB;

    public ServerWorker(Socket socket) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        this.waiter = new Object();
        this.tweetDB = new TweetDB();
        this.userDB  = new UserDB();
    }

    @Override
    public void run(){
        user = getUserDataFromUser();
        while (true){
            requestFromClient = getRequestFromClient();
            if (requestFromClient != null){
                if (requestFromClient.isRequestTimelineTweets()){                           // Timeline                 Done
                    sendTimelineTweets(requestFromClient);
                } else if (requestFromClient.isRequestPopularTweets()){                     // Popular Tweets           Done
                    sendPopularTweets(requestFromClient);
                } else if (requestFromClient.isRequestSearchUser() != null){                // Search User              Done
                    searchInDatabase(requestFromClient);
                } else if (requestFromClient.isRequestChangePrivacy() != null){             // Change Privacy           Done
                    changePrivacy(requestFromClient);
                } else if (requestFromClient.isRequestChangeLastSeenMode() != null){        // Change Last Seen Mode    Done
                    changeLastSeenMode(requestFromClient);
                } else if (!requestFromClient.getRequestChangePassword().equals("")){       // Change Password
                    System.out.println("5");
                } else if (requestFromClient.isRequestDeactivate() != 0){                   // Deactivate               Done
                    deactivate(requestFromClient);
                } else if (requestFromClient.isRequestDeleteAccount()){                     // Delete Account
                    System.out.println("7");
                } else if (requestFromClient.isNewTweet() != null){                         // New Tweet or Retweet     Done
                    if (requestFromClient.isRequestRetweet()){
                        tweetDB.retweet(requestFromClient.getOwner(), requestFromClient.isNewTweet());
                    } else {
                        tweetDB.add(requestFromClient.isNewTweet());
                    }
                } else if (requestFromClient.isRequestShowTweets()){                        // Show Tweets
                    sendTweetsOfUser(requestFromClient.getOwner().id);
                } else if (requestFromClient.isRequestShowTweetsOfUser()){                  // Show Tweets of User      Done
                    if (requestFromClient.getTargetId() != -1){
                        sendTweetsOfUser(requestFromClient.getTargetId());
                    }
                } else if (requestFromClient.isEditInformation() != null){                  // Edit Info
                    System.out.println("11");
                } else if (requestFromClient.isRequestNotifications()){                     // Notifications            Done
                    sendNotificationsToUser(requestFromClient.getOwner());
                } else if (requestFromClient.isRequestSentFollowRequests()){                // Sent Follow Requests     Done
                    sendSentFollowRequests(requestFromClient.getOwner().getId());
                } else if (requestFromClient.isRequestSavedMessages()){                     // Saved Messages
                    System.out.println("14");
                } else if (requestFromClient.isRequestInfo()){                              // Info
                    System.out.println("15");
                } else if (requestFromClient.getFollow() != null){                          // Follow                   Done
                    follow(requestFromClient);
                } else if (requestFromClient.getBlock() != null){                           // Block
                    System.out.println("17");
                } else if (requestFromClient.getReport() != null){                          // Report
                    System.out.println("18");
                } else if (requestFromClient.getLike() != null){                            // Like                     Done
                    like(requestFromClient.getLike());
                } else if (requestFromClient.getMute() != null){                            // Mute                     Done
                    mute(requestFromClient.getMute());
                } else if (requestFromClient.getLastSeen() != null){                        // Log out                  Done
                    submitLastSeen(requestFromClient.getOwner().id, requestFromClient.getLastSeen());
                } else if (requestFromClient.getProfileImage() != null){                    // Profile Image            Done
                    saveProfileImage(requestFromClient.getOwner());
                } else if (requestFromClient.isRequestForContacts()){                       // Contacts                 Done
                    sendContacts(requestFromClient.getOwner());
                } else if (requestFromClient.isRequestForPMs()){                            // Messages                 Done
                    sendPMs(requestFromClient.getOwner().getId(), requestFromClient.getTargetId());
                } else if (requestFromClient.getPm() != null) {                             // Submit PM                Done
                    submitPM(requestFromClient.getPm());
                } else if (requestFromClient.isRequestFollowers()){                         // send Followers           Done
                    sendFollowers(requestFromClient.getOwner());
                } else if (requestFromClient.isRequestFollowings()){                        // send Followings          Done
                    sendFollowings(requestFromClient.getOwner());
                } else if (requestFromClient.isRequestFollowRequests()) {                   // Follow Requests          Done
                    sendFollowRequests(requestFromClient.getOwner().getId());
                } else if (requestFromClient.isReject()) {                                  // Reject                   Done
                    reject(requestFromClient.getOwner().getId(), requestFromClient.getTargetId());
                } else if (requestFromClient.isAccept()) {                                  // Accept                   Done
                    accept(requestFromClient.getOwner().getId(), requestFromClient.getTargetId());
                }
            }
        }
    }

    private void follow(RequestToServer requestFromClient) {
        if (requestFromClient.getFollow().isFollow()){
            userDB.follow(requestFromClient.getFollow().getFollower(), requestFromClient.getFollow().getFollowing());
        } else {
            userDB.unfollow(requestFromClient.getFollow().getFollower(), requestFromClient.getFollow().getFollowing());
        }
    }

    private void deactivate(RequestToServer requestFromClient) {
        userDB.deactivate(requestFromClient.isRequestDeactivate());
    }

    private void changePrivacy(RequestToServer requestFromClient) {
        userDB.changePrivacy(requestFromClient.getOwner().id, requestFromClient.getRequestChangePrivacy().isPrivate());
    }

    private void sendPopularTweets(RequestToServer request) {
        LinkedList<Tweet> allTweets = tweetDB.all();
        LinkedList<Tweet> popularTweets = sortPopularTweets(allTweets);
        allTweets.removeIf(tweet -> request.getOwner().mutedUsers.contains(tweet.user.id));
        allTweets.removeIf(tweet -> request.getOwner().id == tweet.user.id);
        sendTweetsToClient(popularTweets);
    }

    private User getUserDataFromUser(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User userReceived = (User) objectInputStream.readObject();
            if (userReceived != null){
                return userReceived;
            } else {
                System.out.println("Null user!");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Didn't received user!");
            return null;
        }
    }

    private RequestToServer getRequestFromClient(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            RequestToServer request = (RequestToServer) objectInputStream.readObject();
            if (request != null){
                System.out.println("Request received from client is : ");
                return request;
            } else {
                System.out.println("Null request!");
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Didn't received request!");
            return null;
        }
    }

    private void sendTimelineTweets(RequestToServer request){
        LinkedList<Tweet> tweets = new LinkedList<>();
        for (Integer followingId : request.getOwner().followings) {
            if (!request.getOwner().mutedUsers.contains(followingId)){
                tweets.addAll(tweetDB.getByUserId(followingId));
            }
        }
        sendTweetsToClient(tweets);
    }

    private void sendTweetsToClient(LinkedList<Tweet> list){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(list);
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private LinkedList<Tweet> sortPopularTweets(LinkedList<Tweet> allTweets){
        int[][] tweetsAndLinks = new int[allTweets.size()][2];
        LinkedList<Tweet> popularTweets = new LinkedList<>();
        for (int i = 0; i < allTweets.size(); i++){
            tweetsAndLinks[i][0] = allTweets.get(i).id;
            tweetsAndLinks[i][1] = allTweets.get(i).likesId.size();
        }
        for (int i = 0; i < allTweets.size(); i++){
            for (int j = i + 1; j < allTweets.size(); j++){
                if (tweetsAndLinks[i][1] < tweetsAndLinks[j][1]){
                    int id    = tweetsAndLinks[i][0];
                    int likes = tweetsAndLinks[i][1];
                    tweetsAndLinks[i][0] = tweetsAndLinks[j][0];
                    tweetsAndLinks[i][1] = tweetsAndLinks[j][1];
                    tweetsAndLinks[j][0] = id;
                    tweetsAndLinks[j][1] = likes;
                }
            }
        }
        for (int i = 0; i < allTweets.size(); i++){
            for (int j = 0; j < allTweets.size(); j++){
                if (allTweets.get(i).id == tweetsAndLinks[j][0]){
                    popularTweets.add(allTweets.get(i));
                }
            }
        }
        return popularTweets;
    }

    private void sendTweetsOfUser(int targetId){
        LinkedList<Tweet> tweets = new LinkedList<>();
        tweets.addAll(tweetDB.getByUserId(targetId));
        sendTweetsToClient(tweets);
    }

    private void like(Like like){
        if (like.isLike()){
            tweetDB.like(like.getLikerId(), like.getTweetId());
        } else {
            tweetDB.dislike(like.getLikerId(), like.getTweetId());
        }
    }

    private void mute(Mute mute){
        if (mute.isMute()){
            userDB.mute(mute.getMuterId(), mute.getMutedId());
        } else {
            userDB.unMute(mute.getMuterId(), mute.getMutedId());
        }
    }

    private void changeLastSeenMode(RequestToServer request){
        userDB.changeLastSeenMode(request.isRequestChangeLastSeenMode().getUserId()
                , request.isRequestChangeLastSeenMode().getLastSeenMode());
    }

    private void searchInDatabase(RequestToServer request){
        LinkedList<User> usersFound = userDB.usersFound(request.isRequestSearchUser());
        Search search = request.isRequestSearchUser();
        search.setUsersFound(usersFound);
        sendFoundUsers(search);
    }

    private void sendFoundUsers(Search search){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(search);
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void submitLastSeen(int id, LocalDateTime lastSeen){
        userDB.submitLastSeen(id, lastSeen);
    }

    private void sendNotificationsToUser(User user){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userDB.getNotifications(user));
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void saveProfileImage(User user){
        userDB.setProfileImage(user);
    }

    private void sendContacts(User user){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userDB.getContacts(user));
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void sendPMs(int userId, int contactId){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userDB.messages(userId, contactId));
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void submitPM(PM pm){
        tweetDB.submitPM(pm);
    }

    private void sendFollowings(User user){
        LinkedList<User>    followings    = new LinkedList<>();
        LinkedList<Integer> followingsIds = userDB.getFollowingIds(user.getId());
        for (Integer integer : followingsIds){
            followings.add(userDB.get(integer));
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(followings);
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void sendFollowers(User user){
        LinkedList<User>    followers    = new LinkedList<>();
        LinkedList<Integer> followersIds = userDB.getFollowerIds(user.getId());
        for (Integer integer : followersIds){
            followers.add(userDB.get(integer));
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(followers);
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void sendFollowRequests(int userId){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userDB.followRequests(userId));
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void sendSentFollowRequests(int userId){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userDB.sentFollowRequests(userId));
            outputStream.flush();
            objectOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Object haven't sent to server!");
        }
    }

    private void accept(int accepterId, int acceptedId){
        userDB.accept(accepterId, acceptedId);
    }

    private void reject(int rejecterId, int rejectedId){
        userDB.reject(rejecterId, rejectedId);
    }
}
