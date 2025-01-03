package src.entities.resources;

import src.Coordinates;
import src.entities.Entity;

public class Tree extends Entity {
    public Tree(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getID() {
        return "tree";
    }
}
