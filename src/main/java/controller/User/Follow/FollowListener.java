package controller.User.Follow;

public class FollowListener {

    public FollowController followController = new FollowController();

    public FollowListener(FollowEvent followEvent) {
        followController.action(followEvent);
        if (followEvent.isFollow()){
            followEvent.getFollower().followings.add(followEvent.getFollowing().id);
            followEvent.getFollowing().followers.add(followEvent.getFollower().id);
        } else {
            for (int i = 0; i < followEvent.getFollower().followings.size(); i++){
                if (followEvent.getFollower().followings.get(i) == followEvent.getFollowing().id){
                    followEvent.getFollower().followings.remove(i);
                    break;
                }
            }
            for (int j = 0; j < followEvent.getFollowing().followers.size(); j++){
                if (followEvent.getFollowing().followers.get(j) == followEvent.getFollower().id){
                    followEvent.getFollowing().followers.remove(j);
                    break;
                }
            }
        }
    }
}
