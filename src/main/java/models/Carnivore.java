package models;
import java.util.*;
public class Carnivore extends Animal{

    private Carnivore(String name, String image, int weightToHarvest, int weight, Product product){
        this.setTypeObject("Animal");
        this.setName(name);
        this.setActive(true);
        this.setImage(image);

        this.setInstantHarvest(false);
        this.setProtection(false);
        this.setTrap(false);
        this.setProduct(product);
        // this.setItems(items);

        this.setWeightToHarvest(weightToHarvest);
        this.setWeight(weight);
        this.setJenisHewan("Carnivore");
    }

    public Carnivore(){
        new Carnivore("HIU DARAT","card_shark.png",20,0,new Product("SIRIP_HIU"));
    }

}
