package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PM implements Serializable {

    private int id;
    public User writer, contact;
    public String text;
    public boolean hasRead;
    public LocalDateTime date;
    private boolean hasHyperLink;
    private User hyperLinkUser;

    public PM(User writer, User contact, String text, LocalDateTime date){
        this.writer  = writer;
        this.contact = contact;
        this.text    = text;
        this.hasRead = false;
        this.date    = date;
        this.hasHyperLink = text.contains("@");
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public User getWriter() {
        return writer;
    }

    public String getText() {
        return text;
    }

    public boolean hasRead() {
        return hasRead;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHyperLinkUser(User user){
        this.hyperLinkUser = user;
    }

    public User getHyperLinkUser() {
        return hyperLinkUser;
    }

    public boolean hasHyperLink() {
        return hasHyperLink;
    }

    public void setHasHyperLink(boolean hasHyperLink) {
        this.hasHyperLink = hasHyperLink;
    }
}
