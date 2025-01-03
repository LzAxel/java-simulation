package src.actions;

import src.Coordinates;
import src.Map;
import src.entities.Entity;

import java.util.Random;

public abstract class SpawnAction<T extends Entity> extends Action {
    private final double ratePercents;

    public SpawnAction(double ratePercents) {
        this.ratePercents = ratePercents;
    }

    public void execute(Map map) {
        var entitiesToSpawn = (double) (map.getWidth() * map.getHeight()) / 100 * ratePercents;
        var rate = 0;

        while (rate < entitiesToSpawn) {
            Coordinates coordinates = getEmptyRandomCoordinates(map);
            map.setEntity(coordinates, spawnEntity(coordinates));

            rate++;
        }
    }

    private Coordinates getEmptyRandomCoordinates(Map map) {
        var random = new Random();
        while (true) {
            var randonCoordinates = new Coordinates(random.nextInt(0, map.getWidth()), random.nextInt(0, map.getHeight()));

            if (map.isEmpty(randonCoordinates)) {
                return randonCoordinates;
            }
        }
    }

    protected abstract T spawnEntity(Coordinates coordinates);
}
