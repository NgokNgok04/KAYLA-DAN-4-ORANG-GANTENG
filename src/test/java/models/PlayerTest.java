package models;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamexception.GameException;
import utils.Pair;

public class PlayerTest{
    private Player player;

    @BeforeEach
    public void setUp() throws Exception {
        ArrayList<LivingThing> field = new ArrayList<>();
        for (int i = 0 ; i < 20; i++)  {
            field.add(new Omnivore("AYAM"));
            field.get(i).setActive(false);
        }
        ArrayList<GameObject> activeDeck = new ArrayList<>();
        for (int i = 0; i < 6; i++)  {
            activeDeck.add(new Accelerate());
            activeDeck.get(i).setActive(false);
        }
        player = new Player(100, field, activeDeck, 6);
    }

    @Test
    public void TestConstructor(){
        player = new Player();
        assertEquals(0, player.getMoney());
        assertEquals(player.getField().get(0).getName(), "HIU_DARAT");
        assertFalse(player.getField().get(0).isActive());
        assertEquals(player.getActiveDeck().get(0).getName(), "");
        assertFalse(player.getActiveDeck().get(0).isActive());
        assertEquals(player.getDeckSlot(), 40);
    }

    @Test
    public void TestConstructor2(){
        assertEquals(100, player.getMoney());
        assertEquals(player.getField().get(0).getName(), "AYAM");
        assertFalse(player.getField().get(0).isActive());
        assertEquals(player.getActiveDeck().get(0).getName(), "ACCELERATE");
        assertFalse(player.getActiveDeck().get(0).isActive());
        assertEquals(player.getDeckSlot(), 6);
    }

    @Test
    public void TestCopyConstructor(){
        Player copiedPlayer = new Player(player);
        assertEquals(copiedPlayer.getMoney(), player.getMoney());
        assertEquals(copiedPlayer.getField(), player.getField());
        assertEquals(copiedPlayer.getDeckSlot(), player.getDeckSlot());
    }

    @Test
    public void TestGetMaxShuffleCount(){
        assertEquals(player.getMaxShuflleCount(), 4); //default = 4
        for (int i = 0; i < 6; i++)  {
            player.getActiveDeck().get(i).setActive(true);
        }
        assertEquals(player.getMaxShuflleCount(), 0); // all cards on deck is active, cant receive cards from shuffle
    }

    @Test
    public void TestResetPlayer(){
        assertFalse(player.getActiveDeck().isEmpty());
        assertFalse(player.getField().isEmpty());
        player.resetPlayer();
        for(int i = 0; i < player.getActiveDeck().size(); i++){
            assertFalse(player.getActiveDeckItem(i).isActive());
        }
        for(int i = 0; i < player.getField().size(); i++){
            assertFalse(player.getFieldItem(i).isActive());
        }
    }

    @Test
    public void TestGetAndSetMoney(){
        assertEquals(100, player.getMoney());
        player.setMoney(0);
        assertEquals(0, player.getMoney());
    }

    @Test
    public void TestGetAndSetField(){
        assertEquals(player.getField().get(0).getName(), "AYAM");
        assertFalse(player.getField().get(0).isActive());
        List<LivingThing> newfield = new ArrayList<>(20);
        newfield.add(new Carnivore());
        player.setField(newfield);
        assertEquals(player.getFieldItem(0).getName(), "HIU_DARAT");}

    @Test
    public void TestGetFieldItem(){
        assertEquals(player.getFieldItem(0).getName(), "AYAM");
        assertFalse(player.getFieldItem(0).isActive());
    }

    @Test
    public void TestSetFieldElement(){
        assertEquals(player.getFieldItem(0).getName(), "AYAM");
        assertFalse(player.getFieldItem(0).isActive());
        player.setFieldElement(new Carnivore(), 0);
        assertEquals(player.getFieldItem(0).getName(), "HIU_DARAT");
        assertTrue(player.getFieldItem(0).isActive());
    }

    @Test
    public void TestSetField(){
        assertEquals(player.getFieldItem(0).getName(), "AYAM");
        List<LivingThing> newfield = new ArrayList<>(20);
        newfield.add(new Carnivore());
        player.setField(newfield);
        assertEquals(player.getField().size(), 1);
    }

    @Test
    public void TestGetAndSetActiveDeck(){
        assertEquals(player.getActiveDeck().get(0).getName(), "ACCELERATE");
        player.setActiveDeck(new ArrayList<>(3));
        assertEquals(player.getActiveDeck().size(), 0);
    }

    @Test
    public void TestGetAndSetActiveDeckItem(){
        assertEquals(player.getActiveDeckItem(0).getName(), "ACCELERATE");
        List<GameObject> newDeck= new ArrayList<>(6);
        newDeck.add(new GameObject());
        player.setActiveDeckItem(new GameObject(), 0);
        assertEquals(player.getActiveDeckItem(0).getName(), "");
    }

    @Test
    public void TestGetAndSetDeckSlot(){
        assertEquals(player.getDeckSlot(), 6);
        player.setDeckSlot(0);
        assertEquals(player.getDeckSlot(), 0);
        
    }

    @Test
    public void TestIsActiveDeckFull(){
        for(int i = 0; i < 6; i++){
            player.getActiveDeckItem(i).setActive(true);
        }
        assertTrue(player.isActiveDeckFull());
    }

    @Test
    public void TestFindEmptyActiveDeckItem(){
        int idx = 3;
        for(int i = 0; i < idx; i++){
            player.getActiveDeckItem(i).setActive(true);
        }
        try {
            assertEquals(player.findEmptyActiveDeckItem(), idx);
        } catch (GameException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void TestShuffleCard(){
        player.setDeckSlot(0);
        assertEquals(player.shuffleCard().size(), 0);
        player.setDeckSlot(6);
        assertNotEquals(player.shuffleCard().size(), 0);
    }

    @Test
    public void TestUpdateSelectedDeck(){
        int idx;
        try {
            List<GameObject> selected = new ArrayList<>();
            selected.add(new Plant("BIJI_JAGUNG"));
            idx = player.findEmptyActiveDeckItem();
            player.updateSelectedDeck(selected);
            assertEquals(player.getActiveDeckItem(idx).getName(), "BIJI_JAGUNG");
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestAddCardInField(){
        LivingThing card = new Plant("BIJI_STROBERI");
        player.addCardInField(card, new Pair<Integer,Integer>(0, 1));
        assertEquals(player.getFieldItem(1).getName(), "BIJI_STROBERI");
    }

    @Test
    public void TestRemoveCardInField(){
        player.removeCardInField( new Pair<Integer,Integer>(0, 1));
        assertFalse(player.getFieldItem(1).isActive());
    }

    @Test
    public void TestSwapCardInField(){
        LivingThing cardStart = player.getFieldItem(0);
        LivingThing cardAfter = player.getFieldItem(1);
        assertEquals(player.getFieldItem(0).getName(), cardStart.getName());
        assertEquals(player.getFieldItem(1).getName(), cardAfter.getName());
        player.swapCardInField(new Pair<Integer,Integer>(0, 0), new Pair<Integer,Integer>(0, 1));
        assertEquals(player.getFieldItem(0).getName(), cardAfter.getName());
        assertEquals(player.getFieldItem(1).getName(), cardStart.getName());
    }

    @Test
    public void TestAddCardInDeck(){
        LivingThing card = new Plant("BIJI_JAGUNG");
        try {
            player.addCardInDeck(card, 2);
        } catch (GameException e) {
            e.printStackTrace();
        }
        assertEquals(player.getActiveDeckItem(2).getName(), "BIJI_JAGUNG");
        player.setActiveDeckItem(new GameObject(), 0);
    }

    @Test
    public void TestRemoveCardInDeck(){
        player.getActiveDeckItem(1).setActive(true);

        assertTrue(player.getActiveDeckItem(1).isActive());
        try {
            player.removeCardInDeck(1);
        } catch (GameException e) {
            e.printStackTrace();
        }
        assertFalse(player.getActiveDeckItem(1).isActive());
    }

    @Test
    public void TestHarvestField(){
        try {
            player.harvestField(new Pair<Integer,Integer>(0, 0));
            assertTrue(player.getFieldItem(1).isActive()); //tetap aktif karena belum cukup weight nya

            player.getFieldItem(1).setInstantHarvest(true); // instan harvest diaktifkan, jadi inaktif
            player.getFieldItem(1).setProtection(false);
            player.harvestField(new Pair<Integer,Integer>(0, 1));
            assertFalse(player.getFieldItem(1).isActive());
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestConvertCartGUI(){
        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SUSU"));
        cart.add(new Product("SIRIP_HIU"));
        cart.add(new Product("SUSU"));
        List<Pair<String,Integer>> convertedCart = player.convertCartGUI(cart);
        assertEquals("SUSU", convertedCart.get(0).getFirst());
        assertEquals(2, convertedCart.get(0).getSecond());
        assertEquals("SIRIP_HIU", convertedCart.get(1).getFirst());
        assertEquals(1, convertedCart.get(1).getSecond());
    }

    @Test
    public void TestBuyCartRequest(){
        try {
            Player player1 = new Player();
            player1.setActiveDeckItem(new Product("SUSU"), 0);
            player1.setActiveDeckItem(new Product("TELUR"), 1);
            player1.sell(0);
            player1.sell(1);

            List<Product> cartStack = new ArrayList<>();
            cartStack.add(new Product("SUSU"));
            cartStack.add(new Product("TELUR"));

            assertEquals(player.getMoney(), 100);
            player.buyCartRequest(cartStack);
            assertNotEquals(player.getMoney(), 100);
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestBuy(){
        try {
            Player player1 = new Player();
            player1.setActiveDeckItem(new Product("SUSU"), 0);
            player1.sell(0);

            assertEquals(player.getMoney(), 100);
            player.buy("SUSU", 1);
            assertFalse(player.getFieldItem(0).isActive());
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestSell(){
        try {
            assertEquals(player.getMoney(), 100);
            player.setActiveDeckItem(new Product("TELUR"), 0);
            player.sell(0);
            assertNotEquals(player.getMoney(), 100);
            assertFalse(player.getFieldItem(0).isActive());
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestPlaceItem(){
        Item destroyItem = new Destroy();
        Pair<Integer, Integer> position = new Pair<>(0, 0);

        GameException exception1 = assertThrows(GameException.class, () -> {
        player.placeItem(destroyItem, position, player.getField());
        });
        assertEquals("Can't place Destroy and Delay to your own field", exception1.getMessage());
    }

    @Test
    public void TestPlaceProduct(){
        try {
            Pair<Integer, Integer> position = new Pair<>(0, 0);
            Animal targ = (Animal) player.getField().get(position.convertPairToIdx());
            int prevweight = targ.getWeight();
            player.placeProduct(new Product("JAGUNG"), position, player.getField());
            assertNotEquals(targ.getWeight(), prevweight);
        } catch (GameException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestPlaceLiving(){
        try {
            Pair<Integer, Integer> position = new Pair<>(0, 0);
            player.placeLiving(new Herbivore("SAPI"), position, player.getField());
            assertEquals(player.getFieldItem(position.convertPairToIdx()).getName(), "SAPI");
        } catch (GameException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestPlaceDeckToField(){
        try {
            Pair<Integer, Integer> position = new Pair<>(0, 0);
            player.placeDeckToField(0, position, player.getField());
        } catch (GameException e) {
            e.printStackTrace();
        }


    }

}