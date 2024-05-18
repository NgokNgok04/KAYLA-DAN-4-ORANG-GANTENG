package models;
import java.util.*;
public class Omnivore extends Animal{
    public static Omnivore ayam = new Omnivore("AYAM","IMAGE",5,0);
    public static Omnivore beruang = new Omnivore("BERUANG","IMAGE",25,0);

    private Omnivore(String name, String image, int weightToHarvest, int weight){
        this.setTypeObject("Living Thing");
        this.setName(name);
        this.setActive(true);
        this.setImage(image);

        this.setInstantHarvest(false);
        this.setProtection(false);
        this.setTrap(false);
        // this.setProduct(product);
        // this.setItems(items);

        this.setWeightToHarvest(weightToHarvest);
        this.setWeight(weight);
        this.setJenisHewan("Omnivore");
    }
}
