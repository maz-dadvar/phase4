package controller;

import model.*;
import interfaces.Dimensions;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Locale;


public class UserDB implements DBSet<User>, Dimensions {

    @Override
    public User get(int id) {
        User user = null;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM users WHERE user_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                String username = results.getString("username");
                String password = results.getString("password");
                String name     = results.getString("name");
                String lastname = results.getString("lastname");
                LocalDateTime lastSeen = results.getTimestamp("last_seen").toLocalDateTime();
                int lastSeenMode = results.getInt("last_seen_mode");
                String dateOfBirth = "";
                if (results.getTimestamp("date_of_birth") != null){
                    LocalDateTime dateTime = results.getTimestamp("date_of_birth").toLocalDateTime();
                    dateOfBirth = dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth();
                }
                String emailAddress = results.getString("email_address");
                String phoneNumber  = results.getString("phone_number");
                String bio          = results.getString("bio");
                Boolean isActive    = results.getBoolean("is_active");
                Boolean isPublic    = results.getBoolean("is_public");
                user = new User(name, lastname, emailAddress, username, password, dateOfBirth, phoneNumber, bio);
                user.isPrivate = !isPublic;
                user.isActive = isActive;
                user.lastSeenMode = lastSeenMode;
                user.lastSeen     = lastSeen;
                user.id = id;
            }
            user.followings     = getFollowingIds(id);
            user.followers      = getFollowerIds(id);
            user.blockedUsers   = getBlockedUsers(id);
            user.mutedUsers     = getMutedUsers(id);
            if (getProfilePicture(id) == null) {
                ImageIcon imageIcon = new ImageIcon("./resources/unknownPerson.png");
                user.profilePicture = new ImageIcon(imageIcon.getImage().getScaledInstance(profileImageWidth
                        , profileImageHeight, Image.SCALE_DEFAULT));
            } else {
                user.profilePicture = getProfilePicture(id);
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public LinkedList<User> all() {
        LinkedList<User> users = new LinkedList<>();
        String jdbcURL = "jdbc:postgresql://localhost:5432/twitter";
        String userName = "postgres";
        String passWord = "saleh791378";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                String username = results.getString("username");
                String password = results.getString("password");
                users.add(new User(username, password));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void add(User user) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "INSERT INTO users (name, lastname, username, password, email_address, "
                    + "is_active, is_public, last_seen, last_seen_mode) VALUES ('" + user.getName() + "', '"
                    + user.getLastname() + "', '" + user.getUsername() + "', '" + user.getPassword() + "', '"
                    + user.getEmailAddress() + "', 'true', 'true','" + LocalDateTime.now() + "', '1')";
            Statement statement1 = connection.createStatement();
            int rows = statement1.executeUpdate(sql);
            if (rows == 0){
                System.out.println("Error in submitting new user!");
            } else {
                System.out.println("User submitted!");
            }

            String sql1 = "SELECT * FROM users";
            ResultSet result = statement1.executeQuery(sql1);
            while (result.next()){
                user.id = result.getInt("user_id");
            }

            if (!user.getPhoneNumber().equals("")){
                String sql2 = "UPDATE users SET phone_number = '" + user.getPhoneNumber() + "' WHERE user_id = '"
                        + user.getId() + "'";
                Statement statement2 = connection.createStatement();
                int rows2 = statement2.executeUpdate(sql2);
                if (rows2 == 0){
                    System.out.println("Error in submitting phone number!");
                } else {
                    System.out.println("Phone number submitted!");
                }
            }

            if (!user.getBio().equals("")){
                String sql2 = "UPDATE users SET bio = '" + user.getBio() + "' WHERE user_id = '" + user.getId() + "'";
                Statement statement2 = connection.createStatement();
                int rows2 = statement2.executeUpdate(sql2);
                if (rows2 == 0){
                    System.out.println("Error in submitting bio!");
                } else {
                    System.out.println("Bio submitted!");
                }
            }

            if (!user.getDateOfBirth().equals("")){
                String sql2 = "UPDATE users SET date_of_birth = '" + user.getDateOfBirth() + "' WHERE user_id = '"
                        + user.getId() + "'";
                Statement statement2 = connection.createStatement();
                int rows2 = statement2.executeUpdate(sql2);
                if (rows2 == 0){
                    System.out.println("Error in submitting date of birth!");
                } else {
                    System.out.println("Date of birth submitted!");
                }
            }

            connection.close();
        } catch (SQLException e){
            //MyLogger.getLogger().log("\t\tError\t\tSubmit\t\tError in connecting to database");
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        //System.out.println("Hiiiiiiiiiiiiiii");
    }

    @Override
    public void update(User user) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String privacy = "";
            int lastSeenMode = 0;
            if (!user.isPrivate) {
                privacy = "true";
            } else {
                privacy = "false";
            }
            lastSeenMode = user.lastSeenMode;

            String sql = "UPDATE users SET (name, lastname, username, password, email_address, phone_number," +
                    " bio, is_active, is_public, last_seen_mode) = ('" + user.name + "', '" + user.lastname + "', '" + user.username
                    + "', '" + user.password + "', '" + user.emailAddress + "', '" + user.phoneNumber + "', '" + user.bio
                    + "', '" + user.isActive + "', '" + privacy + "', '" + lastSeenMode + "') WHERE user_id = " + user.id;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows == 0) {
                System.out.println("Hi");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        //////
        //////
    }

    public int getId(String username){
        int userId = -1;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT user_id FROM users WHERE username = '" + username + "'";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                userId = results.getInt("user_id");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return userId;
    }

    static boolean isReported(int tweetId){
        int maximumReports = 0;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM reports WHERE tweet_id = " + tweetId;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                int spamReports                  = results.getInt("spam");
                int dangerousOrganizationReports = results.getInt("dangerous_organization");
                int childAbuseReports            = results.getInt("child_abuse");
                int bullyingReports              = results.getInt("bullying");
                int scamReports                  = results.getInt("scam");

                int[] reports = {spamReports, dangerousOrganizationReports, childAbuseReports,
                        bullyingReports, scamReports};

                for (int i = 0; i < 5; i++){
                    if (reports[i] > maximumReports){
                        maximumReports = reports[i];
                    }
                }
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        if (maximumReports >= 30){
            return true;
        }
        else {
            return false;
        }
    }

    public void activation(User user, Activation activation){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            System.out.println(user.id);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "";
            switch (activation) {
                case DEACTIVATE -> sql = "UPDATE users SET is_active = 'false' WHERE user_id = " + user.id;
                case ACTIVATE   -> sql = "UPDATE users SET is_active = 'true' WHERE user_id = " + user.id;
            }

            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows <= 0) {
                //System.out.println("Done");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public LinkedList<Integer> getFollowingIds(int id) {
        LinkedList<Integer> followingIds = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM follow_and_block WHERE operation = 'follow' AND user1_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                followingIds.add(results.getInt("user2_id"));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return followingIds;
    }

    public LinkedList<Integer> getFollowerIds(int id) {
        LinkedList<Integer> followerIds = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM follow_and_block WHERE operation = 'follow' AND user2_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                followerIds.add(results.getInt("user1_id"));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return followerIds;
    }

    private LinkedList<Integer> getBlockedUsers(int id) {
        LinkedList<Integer> blockedUsers = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM follow_and_block WHERE operation = 'block' AND user1_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                blockedUsers.add(results.getInt("user2_id"));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return blockedUsers;
    }

    private LinkedList<Integer> getMutedUsers(int id) {
        LinkedList<Integer> mutedUsers = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM mute WHERE user_id = " + id;
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                mutedUsers.add(results.getInt("muted_id"));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return mutedUsers;
    }

    private ImageIcon getProfilePicture(int id) {
        ImageIcon imageIcon = null;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);

            PreparedStatement ps = connection.prepareStatement("SELECT img FROM images WHERE id = " + id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                byte[] imgBytes = rs.getBytes("img");
                imageIcon = new ImageIcon(imgBytes);
            }
            rs.close();
            ps.close();

        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return imageIcon;
    }

    public void mute(int muter, int muted) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/twitter";
        String userName = "postgres";
        String passWord = "saleh791378";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "INSERT INTO mute (user_id, muted_id)"
                    + "VALUES ('" + muter + "', '" + muted + "')";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows == 0){
                //MyLogger.getLogger().log("\t\tInfo\t\tSubmit\t\tUser " + muterId + " muted user " + mutedId);
                System.out.println("Mute failed");
            }

        } catch (SQLException e){
            //MyLogger.getLogger().log("\t\tError\t\tSubmit\t\tError in connecting to database");
            e.printStackTrace();
        }
    }

    public void unMute(int unMuter, int unMuted) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/twitter";
        String userName = "postgres";
        String passWord = "saleh791378";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "DELETE FROM mute WHERE user_id = " + unMuter + " AND muted_id = " + unMuted;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0){
                //MyLogger.getLogger().log("\t\tInfo\t\tSubmit\t\tUser " + muterId + " muted user " + mutedId);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void follow(User follower, User following) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql, sql4;
            if (!following.isPrivate){
                sql = "INSERT INTO follow_and_block (user1_id, user2_id, operation)"
                        +  "VALUES ('" + follower.id + "', '" + following.id + "', 'follow')";
                Statement statement1 = connection.createStatement();
                int rows = statement1.executeUpdate(sql);
                sql4 = "INSERT INTO notifications (user_id, notification, has_read) VALUES ('" + following.getId() + "', '"
                        + follower.getName() + " " + follower.getLastname() + " started following you" + "', 'false')";
                Statement statement4 = connection.createStatement();
                int rows4 = statement4.executeUpdate(sql4);
                if (rows4 == 0) {
                    //log
                    System.out.println("Sending follow notification failed");
                }
                //MyLogger.getLogger().log("\t\tInfo\t\tSubmit\t\tUser " + followerId + " followed user " + followingId);
            } else {
                sql = "INSERT INTO follow_request (follower_id, following_id)"
                        +  "VALUES ('" + follower.id + "', '" + following.id + "')";
                Statement statement1 = connection.createStatement();
                int rows = statement1.executeUpdate(sql);
                //MyLogger.getLogger().log("\t\tInfo\t\tSubmit\t\tUser " + followerId + " sent a follow request for user "
                //        + followingId);
            }
            connection.close();
        } catch (SQLException e){
            //MyLogger.getLogger().log("\t\tError\t\tSubmit\t\tError in connecting to database");
            e.printStackTrace();
        }
    }

    public void unfollow(User follower, User following) {
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //MyLogger.getLogger().log("\t\tIndo\t\tDeleteData\t\tConnected to database");
            String sql = "DELETE FROM follow_and_block WHERE operation = 'follow' AND user1_id = "
                    + follower.id + "AND user2_id = " + following.id;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows > 0){
                //MyLogger.getLogger().log("\t\tInfo\t\tDeleteData\t\tUser " + userId + " unfollowed user " + targetId);
            }
            else {
                //MyLogger.getLogger().log("\t\tDebug\t\tDeleteData\t\tUnfollowing user " + targetId + " by user "
                //        + userId + " failed");
            }

            String sql4 = "INSERT INTO notifications (user_id, notification, has_read) VALUES ('" + following.getId()
                    + "', '" + follower.getName() + " " + follower.getLastname() + " unfollowed you" + "', 'false')";
            Statement statement4 = connection.createStatement();
            int rows4 = statement4.executeUpdate(sql4);
            if (rows4 == 0) {
                //log
                System.out.println("Sending follow notification failed");
            }

            connection.close();
        } catch (SQLException e){
            //MyLogger.getLogger().log("\t\tError\t\tDeleteData\t\tError in connecting to database");
            e.printStackTrace();
        }
    }

    public void changePrivacy(int userId, boolean isPrivate){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "UPDATE users SET is_public = '" + !isPrivate + "' WHERE user_id = " + userId;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows <= 0) {
                //System.out.println("Done");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public void changeLastSeenMode(int userId, int lastSeenMode){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "UPDATE users SET last_seen_mode = '" + lastSeenMode + "' WHERE user_id = " + userId;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows <= 0) {
                //System.out.println("Done");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public void deactivate(int userId){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "UPDATE users SET is_active = 'false' WHERE user_id = " + userId;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows <= 0) {
                //System.out.println("Done");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public boolean usernameExists(String username){
        boolean exists = false;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                if (results.getString("username").equals(username)){
                    exists = true;
                    break;
                }
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        if (exists){
            System.out.println("username exists");
        }
        return exists;
    }

    public boolean emailExists(String email){
        boolean exists = false;
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                if (results.getString("email_address").equals(email)){
                    exists = true;
                    break;
                }
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        if (exists){
            System.out.println("email exists");
        }
        return !exists;
    }

    public LinkedList<User> usersFound(Search search){
        LinkedList<User> users = new LinkedList<>();
        LinkedList<Integer> userIds = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            String column = "";
            switch (search.getMode()){
                case NAME     -> column = "name";
                case LASTNAME -> column = "lastname";
                case USERNAME -> column = "username";
            }
            while (results.next()){
                if ((results.getString(column).toLowerCase(Locale.ROOT)).contains(search.getSearch().toLowerCase(Locale.ROOT))){
                    userIds.add(results.getInt("user_id"));
                }
            }
            for (Integer userId : userIds) {
                users.add(get(userId));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return users;
    }

    public void submitLastSeen(int id, LocalDateTime lastSeen){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            String sql = "UPDATE users SET last_seen = '" + lastSeen + "' WHERE user_id = " + id;
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows == 0) {
                System.out.println("Error in submitting last seen");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public LinkedList<Notification> getNotifications(User user){
        LinkedList<Notification> notifications = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM notifications WHERE user_id = " + user.getId();
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                String text = results.getString("notification");
                boolean hasRead = results.getBoolean("has_read");
                notifications.add(new Notification(text, hasRead));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return notifications;
    }

    public void setProfileImage(User user){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            try {
                File file = user.getImageFile();
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                PreparedStatement ps = connection.prepareStatement("INSERT INTO images VALUES (?, ?, ?)");
                ps.setInt(1, user.getId());
                ps.setString(2, user.getName());
                ps.setBinaryStream(3, fis, (int) file.length());
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
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }

    }

    public LinkedList<User> getContacts(User user){
        LinkedList<User> contacts = new LinkedList<>();
        LinkedList<Integer> ids   = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM pms WHERE writer_id = '" + user.getId()
                    + "' OR contact_id = '" + user.getId() + "'";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                int writerId       = results.getInt("writer_id");
                int contactId      = results.getInt("contact_id");
                if (writerId == user.id){
                    if (!ids.contains(contactId)){
                        User contact = get(contactId);
                        contacts.add(contact);
                        ids.add(contactId);
                    }
                } else if (contactId == user.id){
                    if (!ids.contains(writerId)){
                        User contact = get(writerId);
                        contacts.add(contact);
                        ids.add(writerId);
                    }
                }
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return contacts;
    }

    public LinkedList<PM> messages(int userId, int contactId){
        LinkedList<PM> pms = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM pms WHERE (writer_id = '" + userId
                    + "' AND contact_id = '" + contactId + "') OR (contact_id = '" + userId + "' AND writer_id = '"
                    + contactId + "') ORDER BY date";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                String        text = results.getString("text");
                LocalDateTime time = results.getTimestamp("date").toLocalDateTime();
                int           id   = results.getInt("pm_id");
                int writerId       = results.getInt("writer_id");
                int contactID      = results.getInt("contact_id");
                PM pm = null;
                if (writerId == userId && contactID == contactId){
                    pm = new PM(get(userId), get(contactId), text, time);
                    pm.setId(id);
                    pms.add(pm);
                } else if (writerId == contactId && contactID == userId){
                    pm = new PM(get(contactId), get(userId), text, time);
                    pm.setId(id);
                    pms.add(pm);
                }
                if (pm != null){
                    if (pm.hasHyperLink()){
                        pm.setHyperLinkUser(getHyperLinkUser(pm));
                    }
                }
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return pms;
    }

    public LinkedList<User> followRequests(int userId){
        LinkedList<User> followRequests = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM follow_request WHERE following_id = '" + userId + "'";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                int followingId = results.getInt("follower_id");
                followRequests.add(get(followingId));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return followRequests;
    }

    public LinkedList<User> sentFollowRequests(int userId){
        LinkedList<User> sentFollowRequests = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "SELECT * FROM follow_request WHERE follower_id = '" + userId + "'";
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            while (results.next()){
                int followingId = results.getInt("following_id");
                sentFollowRequests.add(get(followingId));
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
        return sentFollowRequests;
    }

    public void accept(int accepterId, int acceptedId){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "DELETE FROM follow_request WHERE follower_id = '" + acceptedId + "' AND following_id = '"
                    + accepterId + "'";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);

            String sql2 = "INSERT INTO follow_and_block (user1_id, user2_id, operation)"
                    +  "VALUES ('" + acceptedId + "', '" + accepterId + "', 'follow')";
            Statement statement2 = connection.createStatement();
            int rows2 = statement2.executeUpdate(sql2);

            String sql3 = "INSERT INTO notifications (user_id, notification, has_read) VALUES ('" + accepterId + "', '"
                    + get(acceptedId).getName() + " " + get(acceptedId).getLastname() + " started following you"
                    + "', 'false')";
            Statement statement3 = connection.createStatement();
            int rows3 = statement3.executeUpdate(sql3);

            if (rows == 0 || rows2 == 0 || rows3 == 0){
                System.out.println("Accepting user failed!");
            }

            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public void reject(int rejecterId, int rejectedId){
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, userName, passWord);
            //System.out.println("Connected to the server");                     // Should be substituted by logging
            String sql = "DELETE FROM follow_request WHERE follower_id = '" + rejectedId + "' AND following_id = '"
                    + rejecterId + "'";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            if (rows == 0){
                System.out.println("Rejecting user failed!");
            }
            connection.close();
        } catch (SQLException e){
            //System.out.println("Error in connecting to server");               // Should be substituted by logging
            e.printStackTrace();
        }
    }

    public User getHyperLinkUser(PM pm){
        User user = null;
        String[] splitText = pm.getText().split(" ");
        String hyperLink = "";
        for (String s : splitText) {
            if (s.contains("@")) {
                hyperLink = s;
            }
        }
        String username = hyperLink.substring(1, hyperLink.length());
        if (getId(username) == -1){
            pm.setHasHyperLink(false);
        } else {
            user = get(getId(username));
        }
        return user;
    }
}
