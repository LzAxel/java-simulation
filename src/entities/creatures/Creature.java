package src.entities.creatures;

import src.Coordinates;
import src.Map;
import src.entities.Entity;
import src.path.BFSPathFinder;
import src.path.PathFinder;

import java.util.Objects;

public abstract class Creature extends Entity {
    private final PathFinder pathFinder = new BFSPathFinder();
    protected int speed;
    protected int maxHealth;
    protected int health;

    public Creature(Coordinates coordinates, int speed, int maxHealth, int health) {
        super(coordinates);
        this.speed = speed;
        this.maxHealth = maxHealth;
        this.health = health;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public Entity getEntityAround(Map map, String entityID) {
        var neighbors = map.getNearbyEntities(getCoordinates());

        for (Entity entity : neighbors) {
            if (entity == null) continue;
            if (Objects.equals(entity.getID(), entityID)) {
                return entity;
            }
        }

        return null;
    }

    public <T extends Entity> void goToNearestEntity(Map map, Class<T> entityType) {
        var path = getPathFinder().findPathToNearestEntity(map, entityType, coordinates);
        if (path == null) return;

        var pathIterator = path.iterator();
        var localSpeed = this.speed;

        map.setEntity(getCoordinates(), null);
        while (pathIterator.hasNext() && localSpeed > 0) {
            coordinates = pathIterator.next();

            localSpeed--;
        }
        map.setEntity(getCoordinates(), this);
    }

    public abstract void makeMove(Map map);
}
