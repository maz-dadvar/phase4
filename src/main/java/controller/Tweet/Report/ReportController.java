package controller.Tweet.Report;

import controller.TweetDB;
import model.Report;
import model.Tweet;

public class ReportController extends controller.Controller{

    TweetDB tweetDB = new TweetDB();

    public void report(Tweet tweet, Report report) {
        tweetDB.report(tweet, report);
    }

}
