package src.actions;

import src.Coordinates;
import src.entities.resources.Rock;

public class SpawnRockAction extends SpawnAction<Rock> {
    public SpawnRockAction(double ratePercents) {
        super(ratePercents);
    }

    @Override
    protected Rock spawnEntity(Coordinates coordinates) {
        return new Rock(coordinates);
    }
}
