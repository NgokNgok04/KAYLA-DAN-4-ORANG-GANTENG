package models;

import java.util.*;
public abstract class LivingThing extends GameObject {
    private boolean instantHarvest;
    private boolean protection;
    private boolean trap;
    private Product product;
    private List<Item> items;

    public boolean getInstantHarvest(){
        return this.instantHarvest;
    }

    public boolean getProtection(){
        return this.protection;
    }

    public boolean getTrap(){
        return this.trap;
    }
    
    public Product getProduct(){
        return this.product;
    }

    public List<Item> getItems(){
        return this.items;
    }

    public void setInstantHarvest(boolean isEffected){
        this.instantHarvest = isEffected;
    }
    
    public void setProtection(boolean isEffected){
        this.protection = isEffected;
    }

    public void setTrap(boolean isEffected){
        this.trap = isEffected;
    }
    
    public void setProduct(Product product){
        this.product.setOrigin(product.getOrigin());
        this.product.setAddedWeight(product.getAddedWeight());
        this.product.setPrice(product.getPrice());
    }

    public void setItems(List<Item> items){
        for(int i = 0; i < items.size(); i++){
            // this.items.get(i).setTypeItem(items.get(i).getTypeItem());
        }
    }

    public Product harvest(){
        return new Product("DUMMY");
    }

    public void eat(Product product){};

}