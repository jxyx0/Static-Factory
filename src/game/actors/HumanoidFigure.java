package game.actors;

import game.enums.Identity;

/**
 * Class to represent the Humanoid Figure which the player can sell items to
 */
public class HumanoidFigure extends IdleActor {

    /**
     * Constructor
     */
    public HumanoidFigure() {
        super("Humanoid Figure", 'H', 100);
        super.addCapability(Identity.TRADER);
    }
}
