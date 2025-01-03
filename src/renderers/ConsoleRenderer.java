package src.renderers;

import src.Coordinates;
import src.Map;
import src.entities.creatures.Herbivore;
import src.entities.creatures.Predator;
import src.entities.resources.Grass;

public class ConsoleRenderer implements Renderer {
    public void renderFrame(Map map) {
        StringBuilder frame = new StringBuilder("\r".repeat(map.getHeight()));

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                var entity = map.getEntity(new Coordinates(j, i));
                if (entity != null) {
                    var id = entity.getID();
                    String icon = String.valueOf(id.charAt(0));
                    icon = switch (id) {
                        case "grass" -> "\uD83C\uDF3E";
                        case "tree" -> "\uD83C\uDF33";
                        case "herbivore" -> "\uD83D\uDC14";
                        case "rock" -> "\uD83E\uDDF1";
                        case "predator" -> "\uD83E\uDD81";
                        default -> icon;
                    };
                    frame.append(icon);
                    continue;
                }
                frame.append("\uD83D\uDFEB");
            }
            frame.append("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(frame + String.format("\nP: %d, C: %d, F: %d", map.countEntities(Predator.class), map.countEntities(Herbivore.class), map.countEntities(Grass.class)));
    }
}
