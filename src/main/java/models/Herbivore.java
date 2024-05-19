package models;
import gameexception.GameException;

import java.awt.*;
import java.util.*;
import java.util.List;
import icons.Icon;

public class Herbivore extends Animal{

    private static Map<String, Herbivore> HERBIVORES = new HashMap<>();

    static {
        HERBIVORES.put("SAPI", new Herbivore("SAPI", true, Icon.COW, false, false, false, new Product("SUSU"), 10, 0, "HERBIVORE"));
        HERBIVORES.put("DOMBA", new Herbivore("DOMBA", true, Icon.SHEEP, false, false, false, new Product("DAGING_DOMBA"), 12, 0, "HERBIVORE"));
        HERBIVORES.put("KUDA", new Herbivore("KUDA", true, Icon.HORSE, false, false, false, new Product("DAGING_KUDA"), 14, 0, "HERBIVORE"));
    }

    private Herbivore(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, int weightToHarvest, int weight, String animalType) {
        super(name, active, image, instantHarvest, protection, trap, product, weightToHarvest, weight, animalType);
    }

    public Herbivore(Herbivore other) {
        super(other);
    }

    public Herbivore(String name){
        this(HERBIVORES.get(name));
    }

    public Herbivore(String name, int weight, List<Item> items) {
        this(HERBIVORES.get(name));
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
            throw new GameException("Herbivore can only eat Product");
        }
        if (product.getOrigin() != Product.PRODUCT_PLANT) {
            throw new GameException("Herbivore can only eat Animal Product");
        }
        product.setActive(false);
        setWeight(getWeight() + product.getAddedWeight());
    }
}
