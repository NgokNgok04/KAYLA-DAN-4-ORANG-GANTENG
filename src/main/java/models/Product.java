package models;

public class Product extends GameObject {
    private boolean origin;
    private int addedWeight;
    private int price;

    public boolean getOrigin(){
        return this.origin;
    }

    public int getAddedWeight(){
        return this.addedWeight;
    }

    public int getPrice(){
        return this.price;
    }

    public void setOrigin(boolean origin){
        this.origin = origin;
    }

    public void setAddedWeight(int addedWeight){
        this.addedWeight = addedWeight;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
