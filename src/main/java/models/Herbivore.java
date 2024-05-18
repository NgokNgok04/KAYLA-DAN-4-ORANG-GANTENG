package models;
import java.util.*;
public class Herbivore extends Animal{
    private static final Herbivore SAPI = new Herbivore("SAPI","card_cow.png",10,0, new Product("SUSU"));
    private static final Herbivore DOMBA = new Herbivore("DOMBA","card_sheep.png",12,0,new Product("DAGING_DOMBA"));
    private static final Herbivore KUDA = new Herbivore("KUDA","card_horse.png",14,0,new Product("DAGING_KUDA"));

    private Herbivore(String name, String image, int weightToHarvest, int weight, Product product){
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
        this.setJenisHewan("Herbivore");
    }

    private Herbivore(Herbivore Herbivore){
        this.setTypeObject("Animal");
        this.setName(Herbivore.getName());
        this.setActive(true);
        this.setImage(Herbivore.getImage());

        this.setInstantHarvest(false);
        this.setProtection(false);
        this.setTrap(false);
        this.setProduct(Herbivore.getProduct());
        // this.setItems(items);

        this.setWeightToHarvest(Herbivore.getWeightToHarvest());
        this.setWeight(Herbivore.getWeight());
        this.setJenisHewan("Herbivore");
    }

    public Herbivore(String name){
        if (name.equals("SAPI")){
            new Herbivore(Herbivore.SAPI);
        } else if(name.equals("DOMBA")){
            new Herbivore(Herbivore.DOMBA);
        } else if (name.equals("KUDA")){
            new Herbivore(Herbivore.KUDA);
        }
    }

}
