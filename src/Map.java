package src;

import src.entities.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Map {
    private final int width;
    private final int height;
    private final HashMap<Coordinates, Entity> entities;

    public Map(int width, int height) {
        this.height = height;
        this.width = width;
        this.entities = new HashMap<>();
    }

    public Collection<Entity> getEntities() {
        return this.entities.values();
    }

    public <T> Collection<T> getEntitiesByType(Class<T> entityType) {
        var result = new LinkedList<T>();

        for (Entity entity: this.entities.values()) {
            if (entity != null && entityType.isAssignableFrom(entity.getClass())) {
                result.add(entityType.cast(entity));
            }
        }

        return result;
    }

    public boolean isEmpty(Coordinates coordinates) {
        return this.entities.get(coordinates) == null;
    }

    public void removeEntity(Coordinates coordinates) {
        this.entities.remove(coordinates);
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        this.entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return this.entities.get(coordinates);
    }

    public <T> int countEntities(Class<T> entityClass) {
        int count = 0;
        for (Entity entity : this.entities.values()) {
            if (entity != null && entity.getClass() == entityClass) {
                count++;
            }
        }

        return count;
    }

    public Collection<Entity> getNearbyEntities(Coordinates coordinates) {
        LinkedList<Entity> neighbors = new LinkedList<Entity>();

        var directions = new int[][]{{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};

        for (int[] direction : directions) {
            var entityCoordinates = new Coordinates(coordinates.x() + direction[0], coordinates.y() + direction[1]);

            if (entityCoordinates.x() < 0 || entityCoordinates.y() < 0) {
                continue;
            }
            neighbors.add(this.getEntity(entityCoordinates));
        }

        return neighbors;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
