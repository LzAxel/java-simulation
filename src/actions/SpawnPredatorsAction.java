package src.actions;

import src.Coordinates;
import src.entities.creatures.Predator;

public class SpawnPredatorsAction extends SpawnAction<Predator> {
    public SpawnPredatorsAction(double ratePercents) {
        super(ratePercents);
    }

    @Override
    protected Predator spawnEntity(Coordinates coordinates) {
        return new Predator(coordinates, 2, 10, 3);
    }
}
