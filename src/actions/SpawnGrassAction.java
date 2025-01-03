package src.actions;

import src.Coordinates;
import src.entities.resources.Grass;

public class SpawnGrassAction extends SpawnAction<Grass> {
    public SpawnGrassAction(double ratePercents) {
        super(ratePercents);
    }

    @Override
    protected Grass spawnEntity(Coordinates coordinates) {
        return new Grass(coordinates);
    }
}
