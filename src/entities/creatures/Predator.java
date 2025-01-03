package src.entities.creatures;

import src.Coordinates;
import src.Map;

public class Predator extends Creature {
    int attackPower;

    public Predator(Coordinates coordinates, int speed, int maxHealth, int attackPower) {
        super(coordinates, maxHealth, maxHealth, speed);
        this.attackPower = attackPower;
    }

    @Override
    public String getID() {
        return "predator";
    }

    public void makeMove(Map map) {
        var herbivore = getEntityAround(map, "herbivore");
        if (herbivore instanceof Herbivore herb) {
            attackHerbivore(map, herb);
            return;
        }

        goToNearestEntity(map, "herbivore");
    }

    public void attackHerbivore(Map map, Herbivore herbivore) {
        if (herbivore.health - this.attackPower < 1) {
            map.setEntity(herbivore.getCoordinates(), null);
        } else {
            herbivore.health -= this.attackPower;
        }
    }
}
