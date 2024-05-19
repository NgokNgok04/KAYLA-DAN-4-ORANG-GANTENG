package models;
import java.util.*;
public interface Affectable {
    public void accelerate();
    public void delay();
    public void instantHarvest();
    public void destroy();
    public void protect();
    public void trap();
    public void addItem(Item item);
    public void addItems(List<Item> items);
    public List<Item> getItems();
    public Item getItem(int index);
}
