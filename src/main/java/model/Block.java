package model;

import java.io.Serializable;

public class Block implements Serializable {
    private boolean block;
    private int blockerId;
    private int blockedId;

    public Block(boolean block, int blockerId, int blockedId) {
        this.block = block;
        this.blockerId = blockerId;
        this.blockedId = blockedId;
    }

    public boolean isBlock() {
        return block;
    }

    public int getBlockerId() {
        return blockerId;
    }

    public int getBlockedId() {
        return blockedId;
    }
}
