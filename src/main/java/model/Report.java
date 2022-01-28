package model;

import java.io.Serializable;

public class Report implements Serializable {
    public int spam = 0;
    public int childAbuse = 0;
    public int dangerousOrganization = 0;
    public int scam = 0;
    public int bullying = 0;

    public String toString() {
        if (this.spam == 1) {
            return "spam";
        } else if (this.childAbuse == 1) {
            return "child_abuse";
        } else if (this.dangerousOrganization == 1) {
            return "dangerous_organization";
        } else if (this.scam == 1) {
            return "scam";
        } else if (this.bullying == 1){
            return "bullying";
        } else {
            return "";
        }
    }

}
