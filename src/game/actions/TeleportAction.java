package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public class TeleportAction extends Action {
    private GameMap destinationMap;
    private int destinationX;
    private int destinationY;
    private String mapName;

    /**
     * Constructs a TeleportAction that teleports an actor to a specified location on a specified map.
     *
     * @param destinationMap the map to which the actor will be teleported
     * @param x the x-coordinate of the destination location
     * @param y the y-coordinate of the destination location
     * @param mapName the name of the destination map
     */
    public TeleportAction(GameMap destinationMap, int x, int y, String mapName){
        this.destinationMap = destinationMap;
        this.destinationX = x;
        this.destinationY = y;
        this.mapName = mapName;

    }

    /**
     * Constructs a TeleportAction that teleports an actor to a random location on the current map.
     */
    public TeleportAction(){

    }

    /**
     * Executes the teleport action, moving the actor to the destination location if it is not occupied.
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a description of the result of the teleport action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location newLocation;

        if (destinationMap != null){
            newLocation = destinationMap.at(destinationX, destinationY);
        }
        else {
            Random random = new Random();
            int newRow, newCol;
            newRow = random.nextInt(map.getYRange().max());
            newCol = random.nextInt(map.getXRange().max());
            newLocation = map.at(newCol, newRow);
        }

        if (!newLocation.containsAnActor()) {
            // Remove the actor from the current location and add to the new location
            map.removeActor(actor);
            newLocation.addActor(actor);
            return actor + " has been teleported to" + newLocation.toString();
        }else{
            return actor + " failed to teleport. Locations are occupied.";
        }
    }

    /**
     * Returns a description of the teleport action for display in the menu
     *
     * @param actor the actor performing the action
     * @return a string description of the teleport action
     */
    @Override
    public String menuDescription(Actor actor) {
        if (destinationMap != null) {
            return actor + " teleports to " + mapName;
        } else {
            return actor + " uses THESEUS to teleport within the current map";
        }
    }
}
