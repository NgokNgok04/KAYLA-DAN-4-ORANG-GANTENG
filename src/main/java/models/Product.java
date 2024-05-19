package models;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import icons.Icon;

public class Product extends GameObject {

    private static final Map<String, Product> PRODUCTS = new HashMap<String, Product>();

    public static final boolean PRODUCT_ANIMAL = true;
    public static final boolean PRODUCT_PLANT = false;

    static {
        PRODUCTS.put("SIRIP_HIU", new Product("SIRIP_HIU", true, Icon.SHARK_FIN, PRODUCT_ANIMAL, 12, 500));
        PRODUCTS.put("SUSU", new Product("SUSU", true, Icon.MILK, PRODUCT_ANIMAL, 4, 100));
        PRODUCTS.put("DAGING_DOMBA", new Product("DAGING_DOMBA", true, Icon.SHEEP_MEAT, PRODUCT_ANIMAL, 6, 120));
        PRODUCTS.put("DAGING_KUDA", new Product("DAGING_KUDA", true, Icon.HORSE_MEAT, PRODUCT_ANIMAL, 8, 150));
        PRODUCTS.put("TELUR", new Product("TELUR", true, Icon.EGG, PRODUCT_ANIMAL, 2, 50));
        PRODUCTS.put("DAGING_BERUANG", new Product("DAGING_BERUANG", true, Icon.BEAR_MEAT, PRODUCT_ANIMAL, 12, 500));
        PRODUCTS.put("JAGUNG", new Product("JAGUNG", true, Icon.CORN, PRODUCT_PLANT, 3, 150));
        PRODUCTS.put("LABU", new Product("LABU", true, Icon.PUMPKIN, PRODUCT_PLANT, 10, 500));
        PRODUCTS.put("STROBERI", new Product("STROBERI", true, Icon.STRAWBERRY, PRODUCT_PLANT, 5, 350));
    }

    private final boolean origin;
    private final int addedWeight;
    private final int price;

    private Product(String name, boolean active, Image image, boolean origin, int addedWeight, int price) {
        super("PRODUCT", name, active, image);
        this.origin = origin;
        this.addedWeight = addedWeight;
        this.price = price;
    }

    private Product(Product other) {
        super(other);
        this.origin = other.origin;
        this.addedWeight = other.addedWeight;
        this.price = other.price;
    }

    public Product(String name) {
        this(PRODUCTS.get(name));
    }

    public boolean getOrigin() {
        return origin;
    }

    public int getAddedWeight() {
        return addedWeight;
    }

    public int getPrice() {
        return price;
    }

}
