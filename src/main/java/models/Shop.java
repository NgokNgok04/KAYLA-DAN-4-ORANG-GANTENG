package models;
import utils.*;
import java.util.*;

public class Shop {
    private static List<Pair<Product,Integer>> availableItem = new ArrayList<Pair<Product,Integer>>();

    static {
        availableItem.add(new Pair<Product,Integer>(new Product("SIRIP_HIU"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("SUSU"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("DAGING_DOMBA"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("DAGING_KUDA"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("TELUR"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("DAGING_BERUANG"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("JAGUNG"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("STROBERI"),0));
    }

    public static List<Pair<Product,Integer>> getAvailableItem(){
        return availableItem;
    }

    public static Pair<Product,Integer> getPair(int idx){
        return availableItem.get(idx);
    }

    
    public static Product getItem(int idx){
        return availableItem.get(idx).getFirst();
    }

    public static Product getItem(String name){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                return getPair(i).getFirst();
            }
        }
        return null;
    }
    
    public static int getQuantity(int idx){
        return availableItem.get(idx).getSecond();
    }
    
    public static int getQuantity(String name){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                return getPair(i).getSecond();
            }
        }
        return -1;
    }

    
    public static void itemBought(String name, int quantity){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                int currentQuantity = getQuantity(i);
                getPair(i).setSecond(currentQuantity + quantity);
            }
        }
    }

    public static void itemSold(String name, int quantity){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                int currentQuantity = getQuantity(i);
                getPair(i).setSecond(currentQuantity - quantity);
            }
        }
    }

}
