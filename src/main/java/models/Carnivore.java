package models;

import java.util.List;
import icons.Icon;

import gamexception.GameException;


public class Carnivore extends Animal{

    public Carnivore(List<Item> items, int weight) {
        super("HIU_DARAT", true, Icon.SHARK, false, false, false, new Product("SIRIP_HIU"), 20, weight, "CARNIVORE");
        for (Item item : items) {
            if (!item.getName().equals("ACCELERATE") && !item.getName().equals("DELAY")) {
                item.useEffect(this);
            } else {
                this.getItems().add(item);
            }
        }
    }

    public Carnivore() {
        super("HIU_DARAT", true, Icon.SHARK, false, false, false, new Product("SIRIP_HIU"), 20, 0, "CARNIVORE");
    }

    public Carnivore(Carnivore other) {
        super(other);
    }

    @Override
    public void eat(GameObject eatable) throws GameException{
        if (!(eatable instanceof Product product)) {
            throw new GameException("Heyy, apa ituu.. Karnivor mana bisa makan lek...");
        }
        if (product.getOrigin() != Product.PRODUCT_ANIMAL) {
            throw new GameException("Karnivora hanya bisa makan produk hewan loh lek...");
        }
//        product.setActive(false);
        setWeight(getWeight() + product.getAddedWeight());
    }
}
