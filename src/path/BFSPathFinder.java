package src.path;

import src.Coordinates;
import src.Map;
import src.entities.Entity;

import java.util.*;

public class BFSPathFinder implements PathFinder {
    public <T extends Entity> Collection<Coordinates> findPathToNearestEntity(Map map, Class<T> entityType, Coordinates startPosition) {
        Queue<Coordinates> queue = new LinkedList<>();
        queue.add(startPosition);

        Hashtable<Coordinates, Integer> distances = new Hashtable<>();
        distances.put(startPosition, 0);

        Hashtable<Coordinates, Coordinates> pathHistory = new Hashtable<>();

        var primaryMoves = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        var diagonalMoves = new int[][]{{-1, 1}, {1, 1}, {-1, -1}, {1, -1}};

        Coordinates nearestFoodCoordinates = null;

        while (!queue.isEmpty()) {
            var currentPosition = queue.remove();

            nearestFoodCoordinates = findCoordinatesToMove(map, queue, distances, pathHistory, currentPosition, primaryMoves, entityType);
            if (nearestFoodCoordinates != null) break;

            nearestFoodCoordinates = findCoordinatesToMove(map, queue, distances, pathHistory, currentPosition, diagonalMoves, entityType);
            if (nearestFoodCoordinates != null) break;
        }


        if (nearestFoodCoordinates != null) {
            Deque<Coordinates> path = new LinkedList<>();
            var current = new Coordinates(nearestFoodCoordinates.x(), nearestFoodCoordinates.y());
            path.addLast(pathHistory.get(current));

            while (pathHistory.get(pathHistory.get(current)) != null) {
                current = pathHistory.get(current);
                path.addLast(current);
            }

            return path.reversed();
        } else {
            return null;
        }
    }


    private <T extends Entity> Coordinates findCoordinatesToMove(Map map, Queue<Coordinates> queue,
                                     Hashtable<Coordinates, Integer> distances, Hashtable<Coordinates, Coordinates> pathHistory,
                                     Coordinates currentPosition, int[][] moves, Class<T> entityType) {
        for (int[] move : moves) {
            var moveCoordinates = new Coordinates(currentPosition.x() + move[0], currentPosition.y() + move[1]);

            if (moveCoordinates.x() < 0 || moveCoordinates.y() < 0 || moveCoordinates.x() > map.getWidth() - 1 || moveCoordinates.y() > map.getHeight() - 1) {
                continue;
            }

            if (distances.get(moveCoordinates) != null) {
                continue;
            }

            var currentEntity = map.getEntity(moveCoordinates);
            if (currentEntity != null) {
                if (!Objects.equals(currentEntity.getClass(), entityType)) {
                    continue;
                }
            }

            queue.add(moveCoordinates);
            distances.put(moveCoordinates, distances.get(currentPosition) + 1);
            pathHistory.put(moveCoordinates, currentPosition);

            if (currentEntity != null && Objects.equals(currentEntity.getClass(), entityType)) {
                return moveCoordinates;
            }
        }
        return null;
    }
}
