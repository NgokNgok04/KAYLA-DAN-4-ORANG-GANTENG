package models;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import icons.Icon;

public class Plant extends LivingThing {

    private final int ageToHarvest;
    private int age;

    private static Map<String, Plant> PLANTS = new HashMap<String, Plant>();

    static {
        PLANTS.put("BIJI_JAGUNG", new Plant("BIJI_JAGUNG", true, Icon.CORN_SEED, false, false, false, new Product("JAGUNG"), 3, 0));
        PLANTS.put("BIJI_LABU", new Plant("BIJI_LABU", true, Icon.PUMPKIN_SEED, false, false, false, new Product("LABU"), 5, 0));
        PLANTS.put("BIJI_STROBERI", new Plant("BIJI_STROBERI", true, Icon.STRAWBERRY_SEED, false, false, false, new Product("STROBERI"), 4, 0));
    }

    private Plant(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, int ageToHarvest, int age) {
        super("PLANT", name, active, image, instantHarvest, protection, trap, product);
        this.ageToHarvest = ageToHarvest;
        this.age = age;
    }

    public Plant(Plant other) {
        super(other);
        this.ageToHarvest = other.ageToHarvest;
        this.age = other.age;
    }

    public Plant(String name) {
        this(PLANTS.get(name));
    }

    public Plant(String name, int age, List<Item> items) {
        this(PLANTS.get(name));
        this.age = age;
        for (Item item : items) {
            if (!item.getName().equals("ACCELERATE") && !item.getName().equals("DELAY")) {
                item.useEffect(this);
            }
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void grow() {
        setAge(getAge()+1);
    }

    @Override
    public void accelerate() {
        age = age + 2;
    }

    @Override
    public void delay() {
        age = age > 2 ? age - 2 : 0;
    }

}
