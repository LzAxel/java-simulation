package src.actions;

import src.Map;
import src.entities.creatures.Creature;

public class EntitiesMakeTurnAction extends Action {
    public void execute(Map map) {
        for (Creature entity : map.getEntitiesByType(Creature.class)) {
            entity.makeMove(map);
        }
    }
}
