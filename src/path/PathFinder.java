package src.path;

import src.Coordinates;
import src.Map;
import src.entities.Entity;

import java.util.Collection;

public interface PathFinder {
    <T extends Entity> Collection<Coordinates> findPathToNearestEntity(Map map, Class<T> entityType, Coordinates startPosition);
}
