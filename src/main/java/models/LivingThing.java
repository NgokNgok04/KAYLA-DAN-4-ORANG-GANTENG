package models;

import java.awt.*;
import java.util.*;
import java.util.List;

public abstract class LivingThing extends GameObject implements Affectable{

    private boolean instantHarvest;
    private boolean protection;
    private boolean trap;
    private Product product;
    private List<Item> items;

    public LivingThing(String typeObject, String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, List<Item> items) {
        super(typeObject, name, active, image);
        this.instantHarvest = instantHarvest;
        this.protection = protection;
        this.trap = trap;
        this.items = items;
        this.product = product;
    }

    public LivingThing(String typeObject, String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product) {
        super(typeObject, name, active, image);
        this.instantHarvest = instantHarvest;
        this.product = product;
        this.trap = trap;
        this.protection = protection;
        this.items = new ArrayList<>();
    }

    public LivingThing(LivingThing other) {
        super(other);
        this.instantHarvest = other.instantHarvest;
        this.protection = other.protection;
        this.trap = other.trap;
        this.items = new ArrayList<>(other.items);
        this.product = other.product;
    }

    public boolean isInstantHarvest() {
        return instantHarvest;
    }

    public void setInstantHarvest(boolean instantHarvest) {
        this.instantHarvest = instantHarvest;
    }

    public boolean isProtection() {
        return protection;
    }

    public void setProtection(boolean protection) {
        this.protection = protection;
    }

    public boolean isTrap() {
        return trap;
    }

    public void setTrap(boolean trap) {
        this.trap = trap;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product harvest() {
        return getProduct();
    }

    @Override
    public void instantHarvest() {
        setInstantHarvest(true);
    }

    @Override
    public void destroy() {
        setActive(false);
    }

    @Override
    public void protect() {
        setProtection(true);
    }

    @Override
    public void trap() {
        setTrap(true);
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void addItems(List<Item> items_) {
        items.addAll(items_);
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public Item getItem(int index) {
        return items.get(index);
    }
}