package src;

import src.actions.*;
import src.renderers.ConsoleRenderer;
import src.renderers.Renderer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Simulation {
    private static final Renderer renderer = new ConsoleRenderer();
    private static final Map map = new Map(25, 25);
    private static final Collection<Action> initActions = new ArrayList<Action>(List.of(
            new SpawnGrassAction(30d),
            new SpawnRockAction(15d),
            new SpawnTreeAction(10d),
            new SpawnHerbivoresAction(1.5d),
            new SpawnPredatorsAction(0.2d)
    ));
    private static final Collection<Action> turnActions = new ArrayList<Action>(List.of(
            new EntitiesMakeTurnAction()
    ));

    private static final int targetTickFps = 5;
    private static final int targetTickTime = 1000000000 / targetTickFps;

    private static final int targetRenderFps = 60;
    private static final int targetRenderTime = 1000000000 / targetRenderFps;

    private static boolean isPaused = false;


    public static void init() {
        for (Action action : initActions) {
            action.execute(map);
        }
    }

    public static void nextTurn() {
        for (Action action : turnActions) {
            action.execute(map);
        }
    }

    public static void pauseSimulation() {
        isPaused = true;
    }

    public static void startSimulation() {
        long timeAccumulator = 0;

        while (!isPaused) {
            long startTime = System.nanoTime();

            if (timeAccumulator >= targetTickTime) {
                nextTurn();
                timeAccumulator = 0;
            }

            renderer.renderFrame(map);
            long difference = System.nanoTime() - startTime;

            if (difference < targetRenderTime) {
                try {
                    Thread.sleep((targetRenderTime - difference) / 1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            timeAccumulator += System.nanoTime() - startTime;
        }
    }
}
