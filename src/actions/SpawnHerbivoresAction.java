package src.actions;

import src.Coordinates;
import src.entities.creatures.Herbivore;

public class SpawnHerbivoresAction extends SpawnAction<Herbivore> {
    public SpawnHerbivoresAction(double ratePercents) {
        super(ratePercents);
    }

    @Override
    protected Herbivore spawnEntity(Coordinates coordinates) {
        return new Herbivore(coordinates, 2, 10);
    }
}
