package controller.Tweet.Report;

import model.Report;
import model.Tweet;
import java.util.EventObject;

public class ReportEvent extends EventObject {

    public Tweet tweet;
    public Report report;

    public ReportEvent(Object source, Tweet tweet, Report report) {
        super(source);
        this.report = report;
        this.tweet = tweet;
    }
}
