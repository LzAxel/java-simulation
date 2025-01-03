package src.entities.resources;

import src.Coordinates;
import src.entities.Entity;

public class Rock extends Entity {
    public Rock(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getID() {
        return "rock";
    }
}
