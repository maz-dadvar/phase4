package controller.User.Block;

public class BlockListener {

    public BlockController blockController = new BlockController();

    public BlockListener(BlockEvent blockEvent) {
        switch (blockEvent.block) {
            case BLOCK   -> blockController.block(blockEvent.blocker, blockEvent.blocked);
            case UNBLOCK -> blockController.unblock(blockEvent.blocker, blockEvent.blocked);
        }
    }
}
