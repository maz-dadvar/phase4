package model;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class User implements Serializable {

    public int           id;
    public String        name;
    public String        lastname;
    public String        emailAddress;
    public String        username;
    public String        password;
    public String        phoneNumber;
    public String        bio;
    public LocalDateTime lastSeen;
    public int           lastSeenMode;
    public SavedMessages savedMessages;
    public boolean       isPrivate;
    public boolean       isActive;
    public ImageIcon     profilePicture;
    public String dateOfBirth;
    private File imageFile;

    public LinkedList<Chat>    chats;
    public LinkedList<Tweet>   tweets;
    public LinkedList<Integer> followings;
    public LinkedList<Integer> followers;
    public LinkedList<Integer> blockedUsers;
    public LinkedList<Integer> requestedUsers;
    public LinkedList<Integer> requested;
    public LinkedList<Integer> mutedUsers;
    public LinkedList<String>  notifications;
    public LinkedList<List>    lists;


    public User(String name, String lastname, String emailAddress, String username,
                String password, LocalDateTime dateOfBirth, String phoneNumber, String bio) {
        this.name = name;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth.getYear() + "-" + dateOfBirth.getMonthValue() + "-" + dateOfBirth.getDayOfMonth();
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }

    public User(String name, String lastname, String emailAddress, String username,
                String password, String dateOfBirth, String phoneNumber, String bio) {
        this.name = name;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String name, String lastName, String emailAddress, String userName, String password, boolean isActive) {
        this.name           = name;
        this.lastname       = lastName;
        this.emailAddress   = emailAddress;
        this.username       = userName;
        this.password       = password;
        this.isPrivate      = false;
        this.isActive       = true;
        this.tweets         = new LinkedList();
        this.followers      = new LinkedList();
        this.followings     = new LinkedList();
        this.blockedUsers   = new LinkedList();
        this.requested      = new LinkedList();
        this.chats          = new LinkedList();
        this.requestedUsers = new LinkedList();
        this.mutedUsers     = new LinkedList();
        this.notifications  = new LinkedList();
        this.lists          = new LinkedList();
        this.lastSeenMode   = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
}
