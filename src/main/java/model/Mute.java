package model;

import java.io.Serializable;

public class Mute implements Serializable {
    private boolean mute;
    private int muterId;
    private int mutedId;

    public Mute(boolean mute, int muterId, int mutedId) {
        this.mute = mute;
        this.muterId = muterId;
        this.mutedId = mutedId;
    }

    public boolean isMute() {
        return mute;
    }

    public int getMuterId() {
        return muterId;
    }

    public int getMutedId() {
        return mutedId;
    }
}
