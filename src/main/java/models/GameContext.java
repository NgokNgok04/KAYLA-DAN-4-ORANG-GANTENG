package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class GameContext {

    private static final Map<String, GameObject> GAME_OBJECTS = new HashMap<String, GameObject>();
    private static List<String> LIST_GAME_OBJECTS = new ArrayList<String>();
    static {
        LIST_GAME_OBJECTS.add("HIU_DARAT");
        LIST_GAME_OBJECTS.add("SAPI");
        LIST_GAME_OBJECTS.add("DOMBA");
        LIST_GAME_OBJECTS.add("KUDA");
        LIST_GAME_OBJECTS.add("AYAM");
        LIST_GAME_OBJECTS.add("BERUANG");
        LIST_GAME_OBJECTS.add("BIJI_JAGUNG");
        LIST_GAME_OBJECTS.add("BIJI_LABU");
        LIST_GAME_OBJECTS.add("BIJI_STROBERI");
        LIST_GAME_OBJECTS.add("SIRIP_HIU");
        LIST_GAME_OBJECTS.add("SUSU");
        LIST_GAME_OBJECTS.add("DAGING_DOMBA");
        LIST_GAME_OBJECTS.add("DAGING_KUDA");
        LIST_GAME_OBJECTS.add("TELUR");
        LIST_GAME_OBJECTS.add("DAGING_BERUANG");
        LIST_GAME_OBJECTS.add("JAGUNG");
        LIST_GAME_OBJECTS.add("LABU");
        LIST_GAME_OBJECTS.add("STROBERI");
        LIST_GAME_OBJECTS.add("ACCELERATE");
        LIST_GAME_OBJECTS.add("DELAY");
        LIST_GAME_OBJECTS.add("INSTANT_HARVEST");
        LIST_GAME_OBJECTS.add("DESTROY");
        LIST_GAME_OBJECTS.add("PROTECT");
        LIST_GAME_OBJECTS.add("TRAP");
    }
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

    public static GameObject randomGameObject(){
        Random random = new Random();
        int randomGM = random.nextInt(GAME_OBJECTS.size());
        return GAME_OBJECTS.get(LIST_GAME_OBJECTS.get(randomGM));
    }
}
