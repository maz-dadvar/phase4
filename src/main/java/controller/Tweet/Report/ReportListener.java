package controller.Tweet.Report;

public class ReportListener {
    public ReportController reportController = new ReportController();

    public ReportListener(ReportEvent reportEvent) {
        reportController.report(reportEvent.tweet, reportEvent.report);
    }
}
