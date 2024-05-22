package models;
import utils.*;
import java.util.*;

public final class Shop {
    private static Shop instance;
    private List<Pair<Product,Integer>> availableItem;


    private Shop(){
        availableItem = new ArrayList<Pair<Product,Integer>>();
        availableItem.add(new Pair<Product,Integer>(new Product("SIRIP_HIU"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("SUSU"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("DAGING_DOMBA"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("DAGING_KUDA"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("TELUR"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("DAGING_BERUANG"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("JAGUNG"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("STROBERI"),0));
        availableItem.add(new Pair<Product,Integer>(new Product("LABU"), 0));
    }

    public static Shop getInstance(){
        if (instance == null){
            instance = new Shop();
        }
        return instance;
    }

    public List<Pair<Product,Integer>> getAvailableItem(){
        return availableItem;
    }

    public Pair<Product,Integer> getPair(int idx){
        return availableItem.get(idx);
    }
    
    public Product getItem(int idx){
        return availableItem.get(idx).getFirst();
    }

    public Product getItem(String name){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                return getPair(i).getFirst();
            }
        }
        return null;
    }
    
    public int getQuantity(int idx){
        return availableItem.get(idx).getSecond();
    }
    
    public int getQuantity(String name){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                return getPair(i).getSecond();
            }
        }
        return -1;
    }

    public void setQuantity(int idx, int quantity){
        getPair(idx).setSecond(quantity);
    }

    public boolean isEmpty(){
        for(Pair<Product,Integer>item:availableItem){
            if(item.getSecond()>0){
                return false;
            }
        }
        return true;
    }

    public int getMinPriceAvailable(){
        int min = Integer.MAX_VALUE;
        for(Pair<Product,Integer> item: availableItem){
            int quantity = item.getSecond();
            int price = item.getFirst().getPrice();
            if(quantity>0 && price<min){
                min = price;
            }
        }
        return min;
    }

    public void setQuantity(String name, int quantity){
        for(int i=0;i<availableItem.size();i++){
            if(getItem(i).getName().equals(name)){
                getPair(i).setSecond(quantity);
                return;
            }
        }
    }

    public boolean checkStock(String name,int quantity){
        return quantity<=getQuantity(name);
    }

    public int getCountItemsReady(){
        int total = 0;
        for(Pair<Product,Integer> item:availableItem){
            if(item.getSecond()!=0){
                total++;
            }
        }
        return total;
    }

    public ArrayList<Pair<String,Integer>> getItemsReady(){
        ArrayList<Pair<String,Integer>> items = new ArrayList<>();
        for(Pair<Product,Integer> item: availableItem){
            if(item.getSecond()>0){
                items.add(new Pair<String,Integer>(item.getFirst().getName(), item.getSecond()));
            }
        }
        return items;
    } 

    public void addItem(String name, int quantity){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                int currentQuantity = getQuantity(i);
                getPair(i).setSecond(currentQuantity + quantity);
                return;
            }
        }
    }

    public void removeItem(String name, int quantity){
        for(int i = 0; i < availableItem.size(); i++){
            if(getItem(i).getName().equals(name)){
                int currentQuantity = getQuantity(i);
                getPair(i).setSecond(currentQuantity - quantity);
            }
        }
    }

}
