package src.entities;

import src.Coordinates;

public abstract class Entity {
    protected Coordinates coordinates;

    public Entity(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public abstract String getID();
}
