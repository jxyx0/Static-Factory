package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.items.Purchasable;
import game.actions.PurchaseItemAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ComputerTerminal extends Ground {

    private ArrayList<Purchasable> purchasables;
    private ArrayList<GameMap> gameMaps = new ArrayList<GameMap>();
    private Map<GameMap, Location> fixedLocations = new HashMap<>();
    private Map<GameMap, String> mapNames = new HashMap<>();

    public ComputerTerminal(ArrayList<Purchasable> purchasables) {
        super('=');
        this.purchasables = purchasables;
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        for (Purchasable purchasable : purchasables) {
            actions.add(new PurchaseItemAction(purchasable));
        }

        for (GameMap map : gameMaps) {
            if (!map.equals(location.map())) {
                Location fixedLocation = fixedLocations.get(map);
                if (fixedLocation != null) {
                    String mapName = mapNames.get(map);
                    actions.add(new TeleportAction(map, fixedLocation.x(), fixedLocation.y(), mapName));
                }
            }
        }
        return actions;
    }

    /**
     * Adds a game map to the terminal along with its fixed location and name
     *
     * @param gameMap the GameMap to be added
     * @param fixedLocation the fixed Location on the GameMap, can be null
     * @param mapName the name of the GameMap
     * @throws NullPointerException if the gameMap is null
     */
    public void addGameMap(GameMap gameMap, Location fixedLocation, String mapName) {
        Objects.requireNonNull(gameMap);
        gameMaps.add(gameMap);
        mapNames.put(gameMap, mapName);
        if (fixedLocation != null) {
            fixedLocations.put(gameMap, fixedLocation);
        }
    }

}
