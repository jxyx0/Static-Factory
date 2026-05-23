package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.grounds.*;
import game.grounds.action.DropFruitAction;
import game.grounds.action.GrowAction;
import game.items.*;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Crater());

        List<String> map = Arrays.asList(
                        "...~~~~.........~~~...........",
                        "...~~~~.......................",
                        "...~~~........................",
                        "......u.......................",
                        ".............#####............",
                        ".............#___#...........~",
                        ".............#___#..........~~",
                        ".............##.##.........~~~",
                        ".................~~........~~~",
                        ".................~~~.......~~~",
                        ".............~~~~~~~........~~",
                        "............~~~~~~~~.........~",
                        ".....~~~...~~~~~~~~~..........",
                        ".....~~~~~~~~~~~~~~~~........~",
                        ".....~~~~~~~~~~~~~~~~~~~....~~");

        List<String> spaceshipParkingLot = Arrays.asList(
                ".......",
                ".#####.",
                ".#___#.",
                ".#___#.",
                ".##_##.",
                ".......",
                ".......",
                ".......",
                ".......",
                ".......");

        List<String> newMoon = Arrays.asList(
                "..........................~~~~",
                "..........................~~~~",
                "..........................~~~~",
                "~..........................~..",
                "~~...........#####............",
                "~~~..........#___#............",
                "~~~..........#___#............",
                "~~~..........##_##............",
                "~~~..................~~.......",
                "~~~~................~~~~......",
                "~~~~...............~~~~~......",
                "..~................~~~~.......",
                "....................~~........",
                ".............~~...............",
                "............~~~~..............");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);
        GameMap gameMap2 = new GameMap(groundFactory, spaceshipParkingLot);
        world.addGameMap(gameMap2);
        GameMap gameMap3 = new GameMap(groundFactory, newMoon);
        world.addGameMap(gameMap3);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        gameMap.at(5, 10).addActor(new HuntsmanSpider());

        Player player = new Player("Intern", '@', 4);
        player.addBalance(1000);

        player.addBalance(100000);
        world.addPlayer(player, gameMap.at(15, 6));
        gameMap.at(0, 10).addItem(new MetalSheet());
        gameMap.at(10, 5).addItem(new MetalSheet());
        gameMap.at(3, 14).addItem(new MetalSheet());

        gameMap.at(8,9).addItem(new Bolt());
        gameMap.at(18,3).addItem(new Bolt());

        gameMap.at(5,11).addItem(new MetalPipe());
        gameMap.at(13,14).setGround(new Crater(new HuntsmanSpider()));
        gameMap.at(9,9).setGround(new Crater(new AlienBug()));
        gameMap.at(2,0).setGround(new Crater(new SuspiciousAstronaut()));

        Plant inheritree = new Plant(',');
        List<Stage> lifecycles = new ArrayList<>();
        Stage newStage = new Stage(3, ',');
        newStage.addTreeAction(new GrowAction(3));
        lifecycles.add(newStage);
        newStage = new Stage(6, 't');
        newStage.addTreeAction(new DropFruitAction(new SmallFruit()));
        newStage.addTreeAction(new GrowAction(6));
        lifecycles.add(newStage);
        newStage = new Stage(5, 'y');
        newStage.addTreeAction(new GrowAction(5));
        lifecycles.add(newStage);
        newStage = new Stage('T');
        newStage.addTreeAction(new DropFruitAction(new LargeFruit()));
        lifecycles.add(newStage);
        inheritree.setStages(lifecycles);
        gameMap.at(15, 11).setGround(inheritree);


        gameMap.at(3,5).addItem(new JarOfPickles());
        gameMap.at(22,10).addItem(new JarOfPickles());

        gameMap.at(4,7).addItem(new PotOfGold());
        gameMap.at(3,9).addItem(new PotOfGold());

        ArrayList<Purchasable> purchasables = new ArrayList<>();
        purchasables.add(new DragonSlayerReplica());
        purchasables.add(new EnergyDrink());
        purchasables.add(new ToiletPaperRoll());
        purchasables.add(new Theseus());

        ComputerTerminal computer = new ComputerTerminal(purchasables);
//        gameMap.at(4,8).setGround(new ComputerTerminal(purchasables));

        gameMap.at(15, 5).setGround(computer);
        gameMap2.at(3, 2).setGround(computer);
        gameMap3.at(15, 5).setGround(computer);

        computer.addGameMap(gameMap, gameMap.at(15, 6), "Polymorphia"); // Fixed location in gameMap
        computer.addGameMap(gameMap2, gameMap2.at(3, 3), "Static Factory"); // Fixed location in spaceshipParkingLot
        computer.addGameMap(gameMap3, gameMap3.at(15, 6), "Refactorio"); // Fixed location in newMoon

        gameMap2.at(1, 6).addActor(new HumanoidFigure());

        world.run();
        System.out.println(FancyMessage.YOU_ARE_FIRED);
    }
}
