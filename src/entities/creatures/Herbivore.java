package src.entities.creatures;

import src.Coordinates;
import src.Map;
import src.entities.Entity;


public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int maxHealth) {
        super(coordinates, speed, maxHealth, maxHealth);
    }

    @Override
    public String getID() {
        return "herbivore";
    }

    public void makeMove(Map map) {
        var food = getEntityAround(map, "grass");
        if (food != null) {
            eatFood(map, food);
            return;
        }

        goToNearestEntity(map, "grass");
    }

    private void eatFood(Map map, Entity foodEntity) {
        map.removeEntity(foodEntity.getCoordinates());
        if (this.health + 3 <= this.maxHealth) {
            this.health += 3;
        }
    }
}
