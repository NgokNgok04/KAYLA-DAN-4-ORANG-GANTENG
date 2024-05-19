package models;

import java.util.HashMap;
import java.util.Map;

public class GameContext {

    private static final Map<String, GameObject> GAME_OBJECTS = new HashMap<String, GameObject>();

    static {
        GAME_OBJECTS.put("HIU_DARAT", new Carnivore());
        GAME_OBJECTS.put("SAPI", new Herbivore("SAPI"));
        GAME_OBJECTS.put("DOMBA", new Herbivore("DOMBA"));
        GAME_OBJECTS.put("KUDA", new Herbivore("KUDA"));
        GAME_OBJECTS.put("AYAM", new Omnivore("AYAM"));
        GAME_OBJECTS.put("BERUANG", new Omnivore("BERUANG"));
        GAME_OBJECTS.put("BIJI_JAGUNG", new Plant("BIJI_JAGUNG"));
        GAME_OBJECTS.put("BIJI_LABU", new Plant("BIJI_LABU"));
        GAME_OBJECTS.put("BIJI_STROBERI", new Plant("BIJI_STROBERI"));
        GAME_OBJECTS.put("SIRIP_HIU", new Product("SIRIP_HIU"));
        GAME_OBJECTS.put("SUSU", new Product("SUSU"));
        GAME_OBJECTS.put("DAGING_DOMBA", new Product("DAGING_DOMBA"));
        GAME_OBJECTS.put("DAGING_KUDA", new Product("DAGING_KUDA"));
        GAME_OBJECTS.put("TELUR", new Product("TELUR"));
        GAME_OBJECTS.put("DAGING_BERUANG", new Product("DAGING_BERUANG"));
        GAME_OBJECTS.put("JAGUNG", new Product("JAGUNG"));
        GAME_OBJECTS.put("LABU", new Product("LABU"));
        GAME_OBJECTS.put("STROBERI", new Product("STROBERI"));
        GAME_OBJECTS.put("ACCELERATE", new Accelerate());
        GAME_OBJECTS.put("DELAY", new Delay());
        GAME_OBJECTS.put("INSTANT_HARVEST", new InstantHarvest());
        GAME_OBJECTS.put("DESTROY", new Destroy());
        GAME_OBJECTS.put("PROTECT", new Protect());
        GAME_OBJECTS.put("TRAP", new Trap());
    }

    public static GameObject getGameObject(String name) {
        return GAME_OBJECTS.get(name);
    }
}
