package controller.User.Block;

import model.User;

import java.util.EventObject;

public class BlockEvent extends EventObject {

    public User blocker;
    public User blocked;
    public Block block;

    public BlockEvent(Object source, User blocker, User blocked, Block block) {
        super(source);
        this.blocker = blocker;
        this.blocked = blocked;
        this.block   = block;
    }
}
