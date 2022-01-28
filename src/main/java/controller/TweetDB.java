package controller;

import model.PM;
import model.Report;
import model.Tweet;
import model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class TweetDB implements DBSet<Tweet>{

    @Override
    public Tweet get(int id) {
        Tweet tweet = null;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM tweets WHERE id = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                int     userId     = results.getInt("user_id");
                boolean isTweet    = results.getBoolean("is_tweet");
                int fatherId       = results.getInt("father_id");
                String text        = results.getString("text");
                LocalDateTime date = results.getTimestamp("date").toLocalDateTime();
                UserDB userDB      = new UserDB();
                User user          = userDB.get(userId);
                tweet              = new Tweet(user, text, true);
                tweet.id           = id;
                tweet.date         = date;
                if (!isTweet) {
                    tweet.fatherId = fatherId;
                }
            }
            String sql1 = "SELECT * FROM likes WHERE id = " + id;
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery(sql1);
            while (resultSet1.next()) {
                tweet.likesId.add(resultSet1.getInt("user_id"));
            }

            String sql2 = "SELECT * FROM retweets WHERE id = " + id;
            Statement statement2 = connection.createStatement();
            ResultSet resultSet2 = statement2.executeQuery(sql2);
            while (resultSet2.next()) {
                tweet.retweetedUsers.add(resultSet2.getInt("user_id"));
            }

            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return tweet;
    }

    public LinkedList<Tweet> getByUserId(int id) {
        LinkedList<Tweet> tweets = new LinkedList<>();
        LinkedList<Integer> tweetIds = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM tweets WHERE user_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                tweetIds.add(results.getInt("id"));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        for (Integer tweetId : tweetIds) {
            tweets.add(get(tweetId));
        }
        return tweets;
    }

    @Override
    public LinkedList<Tweet> all() {
        LinkedList<Tweet> tweets = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM tweets";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                tweets.add(get(results.getInt("id")));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return tweets;
    }

    @Override
    public void add(Tweet tweet) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql;
            boolean isTweet;
            int fatherId;
            if (tweet.isTweet) {
                isTweet = true;
                fatherId = 0;
            } else {
                isTweet = false;
                fatherId = tweet.fatherId;
            }
            sql = "INSERT INTO tweets (user_id, is_tweet, father_id, text, date, likes, retweets)"
                    + " VALUES('" + tweet.user.id + "', '" + isTweet + "', '" + fatherId + "', '" + tweet.text
                    + "', '" + LocalDateTime.now() + "', '0', '0')";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0) {
                String sql1 = "SELECT * FROM tweets WHERE text = '" + tweet.text + "'";
                Statement statement1 = connection.createStatement();
                ResultSet resultSet  = statement1.executeQuery(sql1);
                while (resultSet.next()) {
                    tweet.id = resultSet.getInt("id");
                }
            }

            if (tweet.imageFile != null) {
                try {
                    File file = tweet.imageFile;
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO tweet_images VALUES (?, ?, ?)");
                    ps.setInt(1, tweet.id);
                    ps.setString(2, file.getName());
                    ps.setBinaryStream(3, fis, (int)file.length());
                    ps.executeUpdate();
                    ps.close();
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            String sql2 = "INSERT INTO reports (id, spam, scam, bullying, child_abuse, dangerous_organization) VALUES ('" + tweet.id
                    +"', '0', '0', '0', '0', '0')";
            Statement statement2 = connection.createStatement();
            int rows2 = statement2.executeUpdate(sql2);
            if (rows2 == 0) {
                System.out.println("Creation report table failed");
            }

            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Tweet tweet) {

    }

    public void like(int userId, int tweetId) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "INSERT INTO likes (user_id, id) VALUES ('" + userId + "', '" + tweetId + "')";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            String sql2 = "UPDATE tweets SET likes = ((SELECT likes FROM tweets WHERE id = " + tweetId + ")+1) WHERE id = " + tweetId;
            Statement statement2 = connection.createStatement();
            int rows2 = statement2.executeUpdate(sql2);
            String sql3 = "UPDATE tweets SET (user_id, id) VALUES ('" + userId + "', '" + tweetId + "')";
            Statement statement3 = connection.createStatement();
            int rows3 = statement3.executeUpdate(sql3);
            if (rows == 0 || rows2 == 0) {
                //log
                System.out.println("Like failed");
            }
            User liker  = (new UserDB()).get(userId);
            Tweet liked = get(tweetId);
            String sql4 = "INSERT INTO notifications (user_id, notification, has_read) VALUES ('" + userId
                    + "', '" + liker.getName() + " " + liker.getLastname() + " liked your tweet : " + liked.text
                    + "', 'false')";
            Statement statement4 = connection.createStatement();
            int rows4 = statement4.executeUpdate(sql4);
            if (rows4 == 0) {
                //log
                System.out.println("Sending like notification failed");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public void dislike(int userId, int tweetId) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "DELETE FROM likes WHERE user_id = " + userId + " AND id = " + tweetId;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            String sql2 = "UPDATE tweets SET likes = ((SELECT likes FROM tweets WHERE id = " + tweetId + ")-1) WHERE id = " + tweetId;
            Statement statement2 = connection.createStatement();
            int rows2 = statement2.executeUpdate(sql2);
            if (rows == 0 || rows2 == 0) {
                //log
                System.out.println("Dislike failed");
            }
            User disliker  = (new UserDB()).get(userId);
            Tweet liked = get(tweetId);
            String sql4 = "INSERT INTO notifications (user_id, notification, has_read) VALUES ('" + userId
                    + "', '" + disliker.getName() + " " + disliker.getLastname() + " disliked your tweet : " + liked.text
                    + "', 'false')";
            Statement statement4 = connection.createStatement();
            int rows4 = statement4.executeUpdate(sql4);
            if (rows4 == 0) {
                //log
                System.out.println("Sending dislike notification failed");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public void report(Tweet tweet, Report report) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //MyLogger.getLogger().log("\t\tInfo\t\tSubmit\t\tConnected to database ");

            String getNumberOfReport = "SELECT * FROM reports";
            Statement statement      = connection.createStatement();
            ResultSet resultSet      = statement.executeQuery(getNumberOfReport);

            int numberOfReports = 0;
            while (resultSet.next()){
                numberOfReports = resultSet.getInt(report.toString());
                numberOfReports++;
            }
            String sql = "UPDATE reports SET (id, spam, scam, bullying, child_abuse, dangerous_organization) = ('" + tweet.id;
            if (report.spam == 1) {
                sql = sql + "', " + numberOfReports + ", 0, 0, 0, 0) WHERE id = " + tweet.id;
            } else if (report.scam == 1) {
                sql = sql + "', 0, " + numberOfReports + ", 0, 0, 0) WHERE id = " + tweet.id;
            } else if (report.bullying == 1) {
                sql = sql + "', 0, 0, " + numberOfReports + ", 0, 0) WHERE id = " + tweet.id;
            } else if (report.childAbuse == 1) {
                sql = sql + "', 0, 0, 0, " + numberOfReports + ", 0) WHERE id = " + tweet.id;
            } else if (report.dangerousOrganization == 1) {
                sql = sql + "', 0, 0, 0, 0, " + numberOfReports + ") WHERE id = " + tweet.id;
            }
            Statement statement1 = connection.createStatement();
            int rows = statement1.executeUpdate(sql);
            if (rows == 0) {
                System.out.println("Report failed");
            }

            connection.close();
        } catch (SQLException e){
            //MyLogger.getLogger().log("\t\tError\t\tSubmit\t\tError in connecting to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tweet tweet) {

    }

    public void retweet(User user, Tweet tweet){
        Tweet newTweet = new Tweet(user, tweet.text, true);
        add(newTweet);

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //MyLogger.getLogger().log("\t\tIndo\t\tDeleteData\t\tConnected to database");
            String sql4 = "INSERT INTO notifications (user_id, notification, has_read) VALUES ('" + tweet.user.getId()
                    + "', '" + user.getName() + " " + user.getLastname() + " retweeted your tweet : " + tweet.text
                    + "', 'false')";
            Statement statement4 = connection.createStatement();
            int rows4 = statement4.executeUpdate(sql4);
            if (rows4 == 0) {
                //log
                System.out.println("Sending retweet notification failed");
            }

            String sql5 = "INSERT INTO retweets (user_id, id) VALUES ('" + user.getId() + "', '" + tweet.id + "')";
            Statement statement5 = connection.createStatement();
            int rows5 = statement5.executeUpdate(sql5);
            if (rows5 == 0) {
                //log
                System.out.println("Submitting retweet failed");
            }

            connection.close();
        } catch (SQLException e){
            //MyLogger.getLogger().log("\t\tError\t\tDeleteData\t\tError in connecting to database");
            e.printStackTrace();
        }

    }

    public void submitPM(PM pm){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //MyLogger.getLogger().log("\t\tIndo\t\tDeleteData\t\tConnected to database");
            String sql4 = "INSERT INTO pms (writer_id, contact_id, text, date, has_read) VALUES ('" + pm.getWriter().getId()
                    + "', '" + pm.contact.getId() + "', '" + pm.getText() + "', '" + pm.getDate()
                    + "', 'false')";
            Statement statement4 = connection.createStatement();
            int rows4 = statement4.executeUpdate(sql4);
            if (rows4 == 0) {
                //log
                System.out.println("Submitting PM failed");
            }
            connection.close();
        } catch (SQLException e){
            //MyLogger.getLogger().log("\t\tError\t\tDeleteData\t\tError in connecting to database");
            e.printStackTrace();
        }
    }
}
