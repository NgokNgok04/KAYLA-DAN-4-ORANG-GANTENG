package models;
import gameexception.GameException;

import java.awt.*;
import java.util.*;
import java.util.List;
import icons.Icon;

public class Omnivore extends Animal{

    private static Map<String, Omnivore> OMNIVORES = new HashMap<>();

    static {
        OMNIVORES.put("AYAM", new Omnivore("AYAM", true, Icon.CHICKEN, false, false, false, new Product("TELUR"), 5, 0));
        OMNIVORES.put("BERUANG", new Omnivore("BERUANG", true, Icon.BEAR, false, false, false, new Product("DAGING_BERUANG"), 25, 0));
    }

    private Omnivore(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, int weightToHarvest, int weight) {
        super(name, active, image, instantHarvest, protection, trap, product, weightToHarvest, weight, "OMNIVORES");
    }

    public Omnivore(Animal other) {
        super(other);
    }

    public Omnivore(String name) {
        this(OMNIVORES.get(name));
    }

    public Omnivore(String name, int weight, List<Item> items) {
        this(OMNIVORES.get(name));
        setWeight(weight);
        for (Item item : items) {
            if (!item.getName().equals("ACCELERATE") && !item.getName().equals("DELAY")) {
                item.useEffect(this);
            }
        }
    }

    @Override
    public void eat(GameObject eatable) throws GameException {
        if (!(eatable instanceof Product product)) {
            throw new GameException("Omnivore can only eat Product");
        }
        product.setActive(false);
        setWeight(getWeight() + product.getAddedWeight());
    }
}
