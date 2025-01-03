package src.entities.resources;

import src.Coordinates;
import src.entities.Entity;

public class Grass extends Entity {
    public Grass(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public String getID() {
        return "grass";
    }
}
