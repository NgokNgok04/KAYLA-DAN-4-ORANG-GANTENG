package models;

import java.awt.*;
import java.util.List;
import gamexception.GameException;

public abstract class Animal extends LivingThing{

    private int weightToHarvest;
    private int weight;
    private String animalType;

    public Animal(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, List<Item> items, int weightToHarvest, int weight, String animalType) {
        super("ANIMAL", name, active, image, instantHarvest, protection, trap, product, items);
        this.weightToHarvest = weightToHarvest;
        this.weight = weight;
        this.animalType = animalType;
    }

    public Animal(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, int weightToHarvest, int weight, String animalType) {
        super("ANIMAL", name, active, image, instantHarvest, protection, trap, product);
        this.weightToHarvest = weightToHarvest;
        this.weight = weight;
        this.animalType = animalType;
    }

    public Animal(Animal other) {
        super(other);
        this.weightToHarvest = other.weightToHarvest;
        this.weight = other.weight;
        this.animalType = other.animalType;
    }

    public int getWeightToHarvest() {
        return weightToHarvest;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public abstract void eat(GameObject eatable) throws GameException;

    @Override
    public void accelerate() {
        weight = weight + 8;
    }

    @Override
    public void delay() {
        if (!isProtection()) weight = weight > 5 ? weight - 5 : 0;
    }
}
