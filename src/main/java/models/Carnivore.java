package models;
import java.util.*;
public class Carnivore extends Animal{
    private Carnivore(String name, String image, int weightToHarvest, int weight){
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
