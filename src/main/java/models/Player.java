package models;
import java.util.*;

import gamexception.GameException;
import utils.*;
public class Player {
    private int money;
    private List<LivingThing> field;
    private List<GameObject> activeDeck;
    private int deckSlot;

    public Player(){
        this.money = 0;

        this.field = new ArrayList<>(20);
        for(int i=0;i<20;i++){
            Carnivore dummy = new Carnivore();
            dummy.setActive(false);;
            field.add(dummy);
        }

        this.activeDeck = new ArrayList<>(6);
        for(int i=0;i<6;i++){
            GameObject dummy = new GameObject();
            dummy.setActive(false);
            activeDeck.add(dummy);
        }
        this.deckSlot = 40;
    }

    public int getMaxShuflleCount() {
        int count = 0;
        for (int i = 0 ; i < 6; i++)  {
            if (!activeDeck.get(i).isActive()) count++;
        }
        if (count > 4) {
            count = 4;
        }
        return count;
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

    public void resetPlayer(){
        for(GameObject obj:activeDeck){
            obj.setActive(false);
        }
        for(LivingThing liv:field){
            liv.setActive(false);
        }
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

    public int getCountActiveCard(){
        int count = 0;
        for(GameObject obj: activeDeck){
            if(obj.isActive()){
                count++;
            }
        }
        return count;
    }

    public int getCountActiveField(){
        int count = 0;
        for(LivingThing liv: field){
            if(liv.isActive()){
                count++;
            }
        }
        return count;
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

    public void setDeckSlot(int slot){
        deckSlot = slot;
    }

    public boolean isActiveDeckFull(){
        for(int i = 0; i < 6; i++){
            if(!this.activeDeck.get(i).isActive()){
                return false;
            }
        }
        return true;
    }

    public int findEmptyActiveDeckItem() throws GameException{
        for(int i = 0; i < 6; i++){
            if(!this.activeDeck.get(i).isActive()){
                return i;
            }
        }
        throw new GameException("Deck is full");
    }

    public List<GameObject> shuffleCard(){
        int nCard = 4;
        if(getDeckSlot()<4){
            nCard = getDeckSlot();
        }
        List<GameObject> resultShuffle = new ArrayList<>();
        for(int i = 0; i < nCard; i++){
            resultShuffle.add(GameContext.randomGameObject());
        }
        return resultShuffle;
    }

    public void updateSelectedDeck(List<GameObject> selected){
        for(GameObject obj:selected){
            try {
                int idx = findEmptyActiveDeckItem();
                addCardInDeck(obj, idx);
            }catch (GameException e){
                e.printStackTrace();
            }
        }
    }

    public void addCardInField(LivingThing card, Pair<Integer,Integer> pos){
        int idx = pos.convertPairToIdx();
        this.setFieldElement(card, idx);
    }

    public void removeCardInField(Pair<Integer,Integer> pos){
        int idx = pos.convertPairToIdx();
        field.get(idx).setActive(false);
    }

    public void swapCardInField(Pair<Integer,Integer> posStart, Pair<Integer,Integer> posAfter){
        int idxStart = posStart.convertPairToIdx();
        int idxAfter = posAfter.convertPairToIdx();

        LivingThing cardStart = this.getFieldItem(idxStart);
        LivingThing cardAfter = this.getFieldItem(idxAfter);

        this.setFieldElement(cardAfter, idxStart);
        this.setFieldElement(cardStart, idxAfter);
    }

    public void addCardInDeck(GameObject card, int idx) throws GameException{
        if (this.getActiveDeck().get(idx).isActive()){
            throw new GameException("Index you want to add Card not empty");
        } else {
            this.setActiveDeckItem(card, idx);
        }
    }

    public void removeCardInDeck(int idx) throws GameException{
        GameObject obj = getActiveDeckItem(idx);
        if (!obj.isActive()){
            throw new GameException("Index you want to remove Card is empty");
        }
        getActiveDeck().get(idx).deactivate();
    }

    public void harvestField(Pair<Integer,Integer> pos) throws GameException{
        int idx = pos.convertPairToIdx();
        if (!this.getFieldItem(idx).isActive()){
            throw new GameException("Cant harvest a Empty Card");
        } else if (this.isActiveDeckFull()){
            throw new GameException("Player Active Deck is full, Cant harvest");
        } else {
            int idxResultHarvest = findEmptyActiveDeckItem();
            if (!this.getFieldItem(idx).isInstantHarvest()){
                if (this.getFieldItem(idx).getTypeObject().equals("ANIMAL")){
                    Animal animal = (Animal) this.getFieldItem(idx);
                    if (animal.getWeight() < animal.getWeightToHarvest() && !animal.isInstantHarvest()){
                        throw new GameException("Card is not harvestable");
                    }
                } else{
                    Plant plant = (Plant) this.getFieldItem(idx);
                    if (plant.getAge() < plant.getAgeToHarvest() && !plant.isInstantHarvest()){
                        throw new GameException("Card is not harvestable");
                    }
                }
            }
            setActiveDeckItem(new Product(this.getFieldItem(idx).harvest().getName()), idxResultHarvest);
            removeCardInField(pos);
        }
    }

    public List<Pair<String,Integer>> convertCartGUI(List<Product> cart){
        List<String> validCartName = new ArrayList<>();
        List<Integer> validQuantity = new ArrayList<>();

        for(GameObject item:cart){
            int idx = validCartName.indexOf(item.getName());
            if(idx==-1){
                validCartName.add(item.getName());
                validQuantity.add(1);
            }else{
                int quantity = validQuantity.get(idx)+1;
                validQuantity.set(idx, quantity);
            }
        }
        List<Pair<String,Integer>> validCart = new ArrayList<>();
        int size = validCartName.size();
        for(int i=0;i<size;i++){
            validCart.add(new Pair<String,Integer>(validCartName.get(i), validQuantity.get(i)));
        }
        return validCart;
    }

    public void buyCartRequest(List<Product> cartStack)throws GameException{
        List<Pair<String,Integer>> cart = convertCartGUI(cartStack);
        Shop shop = Shop.getInstance();
        int total = 0;
        for(Pair<String,Integer> item: cart){
            if(!shop.checkStock(item.getFirst(), item.getSecond())){
                throw new GameException("Requested item's stock insufficient");
            }
            total += shop.getItem(item.getFirst()).getPrice()*item.getSecond();
        }
        if(money<total){
            throw new GameException("Player's money not enough");
        }
        for(Pair<String,Integer> item:cart){
            buy(item.getFirst(), item.getSecond());
        }
        money -= total;
    }

    public void buy(String name,int quantity) throws GameException{
        Shop shop = Shop.getInstance();        
        int idxResultBuy = findEmptyActiveDeckItem();
        addCardInDeck(new Product(name), idxResultBuy);
        shop.removeItem(name, quantity);
    }

    public void sell(int idx) throws GameException{
        Shop shop = Shop.getInstance();

        Product product = (Product) this.getActiveDeckItem(idx);
        shop.addItem(product.getName(), 1);
        this.money += product.getPrice();

        removeCardInDeck(idx);
    }

    public void placeItem(Item item,Pair<Integer,Integer> pos, List<LivingThing> fieldTarget) throws GameException{
        if(fieldTarget!=field && (item.getName().equals("DESTROY") || item.getName().equals("DELAY"))){
            throw new GameException("Can't place items other than Destroy and Delay to enemy's field");
        }
        if(fieldTarget==field && (item.getName().equals("DESTROY") || item.getName().equals("DELAY"))){
            throw new GameException("Can't place Destroy and Delay to your own field");
        }

        if(item.getName().equals("INSTANT_HARVEST")){
            harvestField(pos);
        }

        LivingThing target = fieldTarget.get(pos.convertPairToIdx());
        target.addItem(item);
    }

    public void placeProduct(Product product,Pair<Integer,Integer> pos,List<LivingThing> fieldTarget) throws GameException{
        if(fieldTarget!=field){
            throw new GameException("Can't place product to enemy's field");
        }
        Animal target = (Animal)fieldTarget.get(pos.convertPairToIdx());
        target.eat(product);
    }

    public void placeLiving(LivingThing living,Pair<Integer,Integer> pos,List<LivingThing> fieldTarget) throws GameException{
        if(fieldTarget!=this.field){
            throw new GameException("Can't place Living Things to Enemey's field");
        }
        addCardInField((LivingThing)living, pos);
    }

    public void placeDeckToField(int idx,Pair<Integer,Integer> pos,List<LivingThing> fieldTarget) throws GameException{
        GameObject itemDeck = activeDeck.get(idx);
        if(itemDeck instanceof LivingThing living){
            placeLiving(living, pos, fieldTarget);
        }else if(itemDeck instanceof Product product){
            placeProduct(product, pos, fieldTarget);
        }else{
            placeItem((Item)itemDeck, pos, fieldTarget);
        }
        removeCardInDeck(idx);
    }


}
