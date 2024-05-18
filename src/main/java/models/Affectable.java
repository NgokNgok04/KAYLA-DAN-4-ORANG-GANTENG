package models;
import java.util.*;
interface Affectable {
    public void accelerate();
    public void delay();
    public void instantHarvest();
    public void destroy();
    public void protect();
    public void trap();
    public void addItem();
    public void addItems(List<Item> items);
    public List<Item> getItems();
    public Item getItem();
}
