package src.actions;

import src.Coordinates;
import src.entities.resources.Tree;

public class SpawnTreeAction extends SpawnAction<Tree> {
    public SpawnTreeAction(double ratePercents) {
        super(ratePercents);
    }

    @Override
    protected Tree spawnEntity(Coordinates coordinates) {
        return new Tree(coordinates);
    }
}
