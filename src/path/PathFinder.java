package src.path;

import src.Coordinates;
import src.Map;

import java.util.Collection;

public interface PathFinder {
    Collection<Coordinates> findPathToNearestEntity(Map map, String entityID, Coordinates startPosition);
}
