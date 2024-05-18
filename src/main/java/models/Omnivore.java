package models;
import java.util.*;
public class Omnivore extends Animal{
    private static final Omnivore AYAM = new Omnivore("AYAM","card_chicken.png",5,0,new Product("TELUR"));
    private static final Omnivore BERUANG = new Omnivore("BERUANG","card_bear.png",25,0, new Product("DAGING_BERUANG"));

    private Omnivore(String name, String image, int weightToHarvest, int weight, Product product){
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
        this.setJenisHewan("Omnivore");
    }

    private Omnivore(Omnivore omnivore){
        this.setTypeObject("Animal");
        this.setName(omnivore.getName());
        this.setActive(true);
        this.setImage(omnivore.getImage());

        this.setInstantHarvest(false);
        this.setProtection(false);
        this.setTrap(false);
        this.setProduct(omnivore.getProduct());
        // this.setItems(items);

        this.setWeightToHarvest(omnivore.getWeightToHarvest());
        this.setWeight(omnivore.getWeight());
        this.setJenisHewan("Omnivore");
    }

    public Omnivore(String name){
        if (name.equals("AYAM")){
            new Omnivore(Omnivore.AYAM);
        } else if(name.equals("BERUANG")){
            new Omnivore(Omnivore.BERUANG);
        }
    }

}
