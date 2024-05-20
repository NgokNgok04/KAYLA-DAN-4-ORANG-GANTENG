package models;
import java.util.*;

import gamexception.GameException;
import utils.*;
public class Player {
    private int money;
    private List<LivingThing> field = new ArrayList<>(20);
    private List<GameObject> activeDeck = new ArrayList<>(6);
    private int deckSlot;

    public Player(){
        this.money = 0;
        this.field = new ArrayList<>(20);
        this.activeDeck = new ArrayList<>(6);
        this.deckSlot = 40;
    }

    public Player(int money, List<LivingThing> field, List<GameObject> activeDeck, int deckSlot){
        this.money = money;
        this.field = new ArrayList<>(field);
        this.activeDeck = new ArrayList<>(activeDeck);
        this.deckSlot = deckSlot;
    }

    public Player(Player player){
        this.money = player.money;
        this.field = new ArrayList<>(player.field);
        this.deckSlot = player.deckSlot;
    }

    public int getMoney(){
        return this.money;
    }

    public LivingThing getFieldItem(int idx){
        return this.getField().get(idx);
    }

    public List<LivingThing> getField(){
        return this.field;
    }

    public GameObject getActiveDeckItem(int idx){
        return this.getActiveDeck().get(idx);
    }

    public List<GameObject> getActiveDeck(){
        return this.activeDeck;
    }

    public int getDeckSlot(){
        return this.deckSlot;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void setFieldElement(LivingThing object,int idx){
        this.field.set(idx, object);
    }

    public void setField(List<LivingThing> field){
        this.field = new ArrayList<>(field);
    }

    public void setActiveDeckItem(GameObject card, int idx){
        this.activeDeck.set(idx,card);
    }

    public void setActiveDeck(List<GameObject> activeDeck){
        this.activeDeck = new ArrayList<>(activeDeck);
    }

    public boolean isActiveDeckFull(){
        for(int i = 0; i < 6; i++){
            if(this.activeDeck.get(i) == null){
                return false;
            }
        }
        return true;
    }

    public int findEmptyActiveDeckItem() throws GameException{
        for(int i = 0; i < 6; i++){
            if(this.activeDeck.get(i) == null){
                return i;
            }
        }
        throw new GameException("Deck is full");
    }

    public List<GameObject> shuffleCard(){
        List<GameObject> resultShuffle = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            resultShuffle.add(GameContext.randomGameObject());
        }

        return resultShuffle;
    }

    public void addCardInField(LivingThing card, Pair<Integer,Integer> pos){
        int idx = pos.convertPairToIdx();
        this.setFieldElement(card, idx);
    }

    public void removeCardInField(Pair<Integer,Integer> pos){
        int idx = pos.convertPairToIdx();
        this.setFieldElement(null, idx);
    }

    public void moveCardInField(Pair<Integer,Integer> posStart, Pair<Integer,Integer> posAfter){
        int idxStart = posStart.convertPairToIdx();
        int idxAfter = posAfter.convertPairToIdx();

        LivingThing cardStart = this.getFieldItem(idxStart);
        LivingThing cardAfter = this.getFieldItem(idxAfter);

        this.setFieldElement(cardAfter, idxStart);
        this.setFieldElement(cardStart, idxAfter);
    }

    public void addCardInDeck(GameObject card, int idx) throws GameException{
        if (this.getActiveDeck().get(idx) == null){
            throw new GameException("Index you want to add Card not empty");
        } else {
            this.setActiveDeckItem(card, idx);
        }
    }

    public void removeCardInDeck(int idx) throws GameException{
        if (this.getActiveDeck().get(idx) == null){
            throw new GameException("Index you want to remove Card is empty");
        } else {
            this.setActiveDeckItem(null, idx);
        }
    }

    public void harvestField(Pair<Integer,Integer> pos) throws GameException{
        int idx = pos.convertPairToIdx();
        if (this.getFieldItem(idx) == null){
            throw new GameException("Cant harvest a Empty Card");
        } else if (this.isActiveDeckFull()){
            throw new GameException("Player Active Deck is full, Cant harvest");
        } else {
            if (this.getFieldItem(idx).isInstantHarvest()){
                int idxResultHarvest = findEmptyActiveDeckItem();
                setActiveDeckItem(this.getFieldItem(idx).harvest(), idxResultHarvest);
                this.removeCardInField(pos);

            } else if (this.getFieldItem(idx).getTypeObject().equals("ANIMAL")){
                Animal animal = (Animal) this.getFieldItem(idx);

                if (animal.getWeight() == animal.getWeightToHarvest()){
                    int idxResultHarvest = findEmptyActiveDeckItem();
                    setActiveDeckItem(this.getFieldItem(idx).harvest(), idxResultHarvest);
                    this.removeCardInField(pos);
                }
            } else if (this.getFieldItem(idx).getTypeObject().equals("PLANT")){
                Plant plant = (Plant) this.getFieldItem(idx);

                if (plant.getAge() == plant.getAgeToHarvest()){
                    int idxResultHarvest = findEmptyActiveDeckItem();
                    setActiveDeckItem(this.getFieldItem(idx).harvest(), idxResultHarvest);
                    this.removeCardInField(pos);
                }
            }   
        }

        throw new GameException("Card is not harvestable");
    }

    public void buy(String name,int quantity) throws GameException{
        Shop shop = Shop.getInstance();
        if ((shop.getItem(name).getPrice() * quantity) <= this.money){
            if (this.isActiveDeckFull()){
                throw new GameException("Player Active Deck is full, Cant harvest");
            } else {
                int idxResultBuy = findEmptyActiveDeckItem();
                setActiveDeckItem(new Product(name), idxResultBuy);
                shop.itemSold(name, quantity);
            }
        } else {
            throw new GameException("Player cant afford to buy it");
        }
    }

    public void sold(int idx) throws GameException{
        Shop shop = Shop.getInstance();
        if (this.getActiveDeckItem(idx) == null){
            throw new GameException("Cant Sell empty card");
        } else if (!this.getActiveDeckItem(idx).getTypeObject().equals("PRODUCT")){
            throw new GameException("Player only can sell Product Card");
        } else {
            Product product = (Product) this.getActiveDeckItem(idx);
            shop.itemBought(product.getName(), 1);
            this.money += product.getPrice();
            setActiveDeckItem(null, idx);
        }
    }
}
