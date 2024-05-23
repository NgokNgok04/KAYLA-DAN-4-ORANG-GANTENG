package models;

import gamexception.GameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(0, player.getMoney());
        assertEquals(20, player.getField().size());
        assertEquals(6, player.getActiveDeck().size());
        assertEquals(40, player.getDeckSlot());

        for (LivingThing livingThing : player.getField()) {
            assertFalse(livingThing.isActive());
        }

        for (GameObject gameObject : player.getActiveDeck()) {
            assertFalse(gameObject.isActive());
        }

        player.resetPlayer();
    }

    @Test
    public void testGetMaxShuffleCount() {
        assertEquals(4, player.getMaxShuflleCount());
    }

    @Test
    public void testSetAndGetMoney() {
        player.setMoney(100);
        assertEquals(100, player.getMoney());

        player.resetPlayer();
    }

    @Test
    public void testResetPlayer() {
        player.setMoney(100);
        player.getFieldItem(0).setActive(true);
        player.getActiveDeckItem(0).setActive(true);

        player.resetPlayer();
        assertEquals(0, player.getMoney());
        for (LivingThing livingThing : player.getField()) {
            assertFalse(livingThing.isActive());
        }
        for (GameObject gameObject : player.getActiveDeck()) {
            assertFalse(gameObject.isActive());
        }

        player.resetPlayer();
    }

    @Test
    public void testIsActiveDeckFull() {
        assertFalse(player.isActiveDeckFull());

        for (GameObject gameObject : player.getActiveDeck()) {
            gameObject.setActive(true);
        }

        assertTrue(player.isActiveDeckFull());

        player.resetPlayer();
    }

    @Test
    public void testFindEmptyActiveDeckItem() throws GameException {
        assertEquals(0, player.findEmptyActiveDeckItem());

        for (GameObject gameObject : player.getActiveDeck()) {
            gameObject.setActive(true);
        }

        assertThrows(GameException.class, () -> player.findEmptyActiveDeckItem());

        player.resetPlayer();
    }

    @Test
    public void testAddAndRemoveCardInDeck() throws GameException {
        GameObject card = new GameObject();
        player.addCardInDeck(card, 0);
        player.getActiveDeckItem(0).setActive(true);

        assertSame(card, player.getActiveDeck().get(0));

        player.removeCardInDeck(0);
        assertFalse(player.getActiveDeck().get(0).isActive());

        player.resetPlayer();
    }

    @Test
    public void testHarvestField() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        player.getFieldItem(0).setActive(true);

        assertThrows(GameException.class, () -> player.harvestField(pos));

        player.resetPlayer();
    }

    // @Test
    // public void testHarvestField_EmptyCard() {
    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);

    //     // Test harvesting an empty card
    //     player.getFieldItem(0).setActive(false);  // Ensure the field is empty
    //     assertThrows(GameException.class, () -> player.harvestField(pos));
    // }

    // @Test
    // public void testHarvestField_ActiveDeckFull() throws GameException {
    //     player.setMoney(600);
    //     Shop shop = Shop.getInstance();
    //     shop.addItem("SIRIP_HIU", 10);

    //     List<Product> cart = new ArrayList<>();
    //     cart.add(new Product("SIRIP_HIU"));
    //     player.buyCartRequest(cart);

    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);

    //     // Fill the active deck
    //     for (int i = 0; i < 6; i++) {
    //         player.addCardInDeck(new Product("TestProduct" + i), i);
    //     }

    //     assertThrows(GameException.class, () -> player.harvestField(pos));

    //     player.resetPlayer();
    //     shop.setQuantity("SIRIP_HIU", 0);
    // }

    // @Test
    // public void testHarvestField_AnimalHarvestable() throws GameException {
    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);
    //     Animal animal = new Carnivore();
    //     player.addCardInField(animal, pos);

    //     // Test harvesting an animal that is harvestable
    //     animal.setActive(true);  // Make the animal active
    //     player.harvestField(pos);

    //     assertEquals(1, player.getCountActiveCard());  // Check the active deck count

    //     player.resetPlayer();
    // }

    // @Test
    // public void testHarvestField_AnimalNotHarvestable() throws GameException {
    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);
    //     Animal animal = new Carnivore();
    //     player.addCardInField(animal, pos);

    //     // Test harvesting an animal that is not harvestable
    //     animal.setActive(true);  // Make the animal active
    //     assertThrows(GameException.class, () -> player.harvestField(pos));

    //     // Check that the card is still in the field
    //     assertSame(animal, player.getFieldItem(0));

    //     player.resetPlayer();
    // }

    // @Test
    // public void testHarvestField_PlantHarvestable() throws GameException {
    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);
    //     Plant plant = new Plant("BIJI_JAGUNG");
    //     player.addCardInField(plant, pos);

    //     // Test harvesting a plant that is harvestable
    //     plant.setActive(true);  // Make the plant active
    //     player.harvestField(pos);

    //     assertEquals(1, player.getCountActiveCard());  // Check the active deck count

    //     player.resetPlayer();
    // }

    // @Test
    // public void testHarvestField_PlantNotHarvestable() throws GameException {
    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);
    //     Plant plant = new Plant("BIJI_JAGUNG");
    //     player.addCardInField(plant, pos);

    //     // Test harvesting a plant that is not harvestable
    //     plant.setActive(true);  // Make the plant active
    //     assertThrows(GameException.class, () -> player.harvestField(pos));

    //     // Check that the card is still in the field
    //     assertSame(plant, player.getFieldItem(0));

    //     player.resetPlayer();
    // }

    // @Test
    // public void testHarvestField_InstantHarvest() throws GameException {
    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);
    //     Animal animal = new Carnivore();
    //     player.addCardInField(animal, pos);

    //     // Test instant harvest
    //     animal.setActive(true);  // Make the animal active
    //     player.harvestField(pos);

    //     assertEquals(1, player.getCountActiveCard());  // Check the active deck count

    //     player.resetPlayer();
    // }

    @Test
    public void testAddAndRemoveCardInField() {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        LivingThing card = new Carnivore();
        player.addCardInField(card, pos);

        assertSame(card, player.getField().get(0));

        player.removeCardInField(pos);
        assertFalse(player.getField().get(0).isActive());

        player.resetPlayer();
    }

    @Test
    public void testSwapCardInField() {
        Pair<Integer, Integer> posStart = new Pair<>(0, 0);
        Pair<Integer, Integer> posAfter = new Pair<>(1, 1);

        LivingThing cardStart = new Carnivore();
        LivingThing cardAfter = new Carnivore();

        player.addCardInField(cardStart, posStart);
        player.addCardInField(cardAfter, posAfter);

        player.swapCardInField(posStart, posAfter);

        assertSame(cardAfter, player.getField().get(0));
        assertSame(cardStart, player.getField().get(6));

        player.resetPlayer();
    }

    @Test
    public void testBuyAndSell() throws GameException {
        player.setMoney(600);
        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 10);

        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));

        player.buyCartRequest(cart);

        assertEquals(100, player.getMoney());
        assertSame("SIRIP_HIU", player.getActiveDeck().get(0).getName());

        player.sell(0);

        assertEquals(600, player.getMoney());
        assertFalse(player.getActiveDeck().get(0).isActive());

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
    }

    @Test
    public void testConvertCartGUI() {
        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));
        cart.add(new Product("SIRIP_HIU"));

        List<Pair<String,Integer>> result = player.convertCartGUI(cart);

        assertEquals(1, result.size());
        assertEquals("SIRIP_HIU", result.get(0).getFirst());
        assertEquals(2, result.get(0).getSecond());
    }

    @Test
    public void testBuyCartRequest() throws GameException {
        player.setMoney(600);
        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 10);

        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));

        player.buyCartRequest(cart);

        assertEquals(100, player.getMoney());
        assertSame("SIRIP_HIU", player.getActiveDeck().get(0).getName());

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
    }

    // @Test
    // public void testBuy() throws GameException {
    //     player.setMoney(600);
    //     Shop shop = Shop.getInstance();
    //     shop.addItem("SIRIP_HIU", 10);

    //     player.buy("SIRIP_HIU", 1);

    //     assertEquals(100, player.getMoney());
    //     assertSame("SIRIP_HIU", player.getActiveDeck().get(0).getName());

    //     player.resetPlayer();
    //     shop.setQuantity("SIRIP_HIU", 0);
    // }

    @Test
    public void testSell() throws GameException {
        player.setMoney(600);
        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 10);

        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));

        player.buyCartRequest(cart);

        assertEquals(100, player.getMoney());
        assertSame("SIRIP_HIU", player.getActiveDeck().get(0).getName());

        player.sell(0);

        assertEquals(600, player.getMoney());
        assertFalse(player.getActiveDeck().get(0).isActive());

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
    }

    // @Test
    // public void testPlaceItem() throws GameException {
    //     Pair<Integer, Integer> pos = new Pair<>(0, 0);
    //     List<LivingThing> fieldTarget = player.getField();
    //     player.placeItem(new Protect(), pos, fieldTarget);
    //     assertFalse(player.getFieldItem(0).isActive());
    //     // assertEquals("PROTECT", player.getFieldItem(0).getName());

    //     player.resetPlayer();
    // }

    @Test
    public void testPlaceProduct() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        List<LivingThing> fieldTarget = player.getField();
        player.addCardInField(new Carnivore(), pos);
        player.placeProduct(new Product("DAGING_KUDA"), pos, fieldTarget);
        assertEquals(8, ((Animal) player.getFieldItem(0)).getWeight());
        player.resetPlayer();
    }

    @Test
    public void testPlaceLiving() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        List<LivingThing> fieldTarget = player.getField();
        player.placeLiving(new Carnivore(), pos, fieldTarget);
        assertTrue(player.getFieldItem(0).isActive());
        player.resetPlayer();
    }

    @Test
    public void testShuffleCard() throws GameException {
        player.setDeckSlot(4);  // Set deck slot to 4 to force shuffling all cards
        List<GameObject> shuffled = player.shuffleCard();
        assertEquals(4, shuffled.size());

        for (GameObject gameObject : shuffled) {
            assertTrue(gameObject.isActive());
        }

        player.resetPlayer();
    }

    @Test
    public void testGetCountActiveCard(){
        int countActiveCards = player.getCountActiveCard();
        assertEquals(0, countActiveCards);

        player.resetPlayer();
    }

    @Test
    public void testGetCountActiveCard2(){
        // assertDoesNotThrow(GameException.class, () -> player.addCardInDeck(new Protect(), 0));
        assertDoesNotThrow(() -> player.addCardInDeck(new Protect(), 0));
        int countActiveCards = player.getCountActiveCard();
        assertEquals(1, countActiveCards);

        player.resetPlayer();
    }

    @Test
    public void testGetCountActiveField(){
        int countActiveField = player.getCountActiveField();
        assertEquals(0, countActiveField);

        player.resetPlayer();
    }

    @Test
    public void testGetCountActiveField2(){
        player.addCardInField(new Carnivore(), new Pair<Integer,Integer>(0, 0));
        int countActiveField = player.getCountActiveField();
        assertEquals(1, countActiveField);

        player.resetPlayer();
    }
    // @Test
    // public void testShuffleCard2() throws GameException {
    //     player.setDeckSlot(4);  // Set deck slot to 4 to force shuffling all cards
    //     player.setActiveDeckItem(new Protect(), 0);
    //     player.setActiveDeckItem(new Protect(), 2);
    //     player.setActiveDeckItem(new Protect(), 3);
    //     List<GameObject> shuffled = player.shuffleCard();
    //     assertEquals(4, shuffled.size());

    //     for (GameObject gameObject : shuffled) {
    //         assertTrue(gameObject.isActive());
    //     }

    //     player.resetPlayer();
    // }
}
