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
        player.setMoney(0);
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
    public void testHarvestField_EmptyCard() {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);

        // Test harvesting an empty card
        player.getFieldItem(0).setActive(false);  // Ensure the field is empty
        assertThrows(GameException.class, () -> player.harvestField(pos));

        player.resetPlayer();
    }

    @Test
    public void testHarvestField_ActiveDeckFull() throws GameException {
        player.setMoney(600);
        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 10);

        // List<Product> cart = new ArrayList<>();
        // cart.add(new Product("SIRIP_HIU"));
        // player.buyCartRequest(cart);

        Pair<Integer, Integer> pos = new Pair<>(0, 0);

        // Fill the active deck
        for (int i = 0; i < 6; i++) {
            player.addCardInDeck(new Protect(), i);
        }

        assertThrows(GameException.class, () -> player.harvestField(pos));

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
    }

    @Test
    public void testHarvestField_AnimalHarvestable() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Animal animal = new Carnivore();
        player.addCardInField(animal, pos);

        // Test harvesting an animal that is harvestable
        animal.setActive(true);  // Make the animal active
        animal.setWeight(100);
        player.harvestField(pos);

        assertEquals(1, player.getCountActiveCard());  // Check the active deck count

        player.resetPlayer();
    }

    @Test
    public void testHarvestField_AnimalNotHarvestable() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Animal animal = new Carnivore();
        player.addCardInField(animal, pos);

        // Test harvesting an animal that is not harvestable
        animal.setActive(true);  // Make the animal active
        assertThrows(GameException.class, () -> player.harvestField(pos));

        // Check that the card is still in the field
        assertSame(animal, player.getFieldItem(0));

        player.resetPlayer();
    }

    @Test
    public void testHarvestField_PlantHarvestable() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Plant plant = new Plant("BIJI_JAGUNG");
        player.addCardInField(plant, pos);

        // Test harvesting a plant that is harvestable
        plant.setActive(true);  // Make the plant active
        plant.setAge(100);
        player.harvestField(pos);

        assertEquals(1, player.getCountActiveCard());  // Check the active deck count

        player.resetPlayer();
    }

    @Test
    public void testHarvestField_PlantNotHarvestable() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Plant plant = new Plant("BIJI_JAGUNG");
        player.addCardInField(plant, pos);

        // Test harvesting a plant that is not harvestable
        plant.setActive(true);  // Make the plant active
        assertThrows(GameException.class, () -> player.harvestField(pos));

        // Check that the card is still in the field
        assertSame(plant, player.getFieldItem(0));

        player.resetPlayer();
    }

    @Test
    public void testHarvestField_InstantHarvest() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Animal animal = new Carnivore();
        player.addCardInField(animal, pos);

        // Test instant harvest
        animal.setActive(true);  // Make the animal active
        assertThrows(GameException.class, () -> player.harvestField(pos));

        assertEquals(0, player.getCountActiveCard());  // Check the active deck count

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

    @Test
    public void testBuyCartRequest_InsufficientStock() {
        player.setMoney(600);
        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 1); // Only add 1 stock

        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));
        cart.add(new Product("SIRIP_HIU")); // Adding 2, but only 1 in stock

        assertThrows(GameException.class, () -> player.buyCartRequest(cart));

        assertEquals(600, player.getMoney()); // Money should not be deducted

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
    }

    @Test
    public void testBuyCartRequest_NotEnoughMoney() {
        player.setMoney(50); // Not enough money for buying SIRIP_HIU

        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 10);

        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));

        assertThrows(GameException.class, () -> player.buyCartRequest(cart));

        assertEquals(50, player.getMoney()); // Money should not be deducted

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
    }

    @Test
    public void testBuyCartRequest_MultipleProducts() throws GameException {
        player.setMoney(650);
        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 10);
        shop.addItem("DAGING_KUDA", 5);

        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));
        cart.add(new Product("DAGING_KUDA"));

        player.buyCartRequest(cart);

        assertEquals(0, player.getMoney());
        assertSame("SIRIP_HIU", player.getActiveDeck().get(0).getName());
        assertSame("DAGING_KUDA", player.getActiveDeck().get(1).getName());

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
        shop.setQuantity("DAGING_KUDA", 0);
    }

    @Test
    public void testBuyCartRequest_MultipleProducts_InsufficientStock() {
        player.setMoney(600);
        Shop shop = Shop.getInstance();
        shop.addItem("SIRIP_HIU", 10);
        shop.addItem("DAGING_KUDA", 2); // Only 2 in stock

        List<Product> cart = new ArrayList<>();
        cart.add(new Product("SIRIP_HIU"));
        cart.add(new Product("DAGING_KUDA"));
        cart.add(new Product("DAGING_KUDA")); // Adding 2, but only 2 in stock

        assertThrows(GameException.class, () -> player.buyCartRequest(cart));

        assertEquals(600, player.getMoney()); // Money should not be deducted

        player.resetPlayer();
        shop.setQuantity("SIRIP_HIU", 0);
        shop.setQuantity("DAGING_KUDA", 0);
    }

    @Test
    public void testPlaceItem() {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Item item = new Protect();
        Item item2 = new Destroy();

        // Test placing item on player's field
        assertThrows(GameException.class, () -> player.placeItem(item, pos, player.getField()));

        // Test placing item on opponent's field
        assertThrows(GameException.class, () -> player.placeItem(item, pos, new ArrayList<>()));

        // Test instant harvest
        player.addCardInField(new Carnivore(), pos);
        assertThrows(GameException.class, () -> player.placeItem(item2, pos, player.getField()));
        assertDoesNotThrow(() -> player.placeItem(item, pos, player.getField()));

        // assertEquals(0, player.getCountActiveCard());
    }

    @Test
    public void testPlaceDeckToField() throws GameException {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Pair<Integer, Integer> pos2 = new Pair<>(3, 3);
        GameObject card = new Carnivore();
        player.addCardInDeck(card, 0);

        // Test placing a LivingThing from deck to field
        player.placeDeckToField(0, pos, player.getField());
        assertEquals(card, player.getFieldItem(0));

        // Test placing a Product from deck to field
        card = new Product("SIRIP_HIU");
        player.addCardInDeck(card, 0);
        assertThrows(GameException.class, () -> player.placeDeckToField(0, pos2, player.getField()));
        assertDoesNotThrow(() -> player.placeDeckToField(0, pos, player.getField()));

        // Test placing an Item from deck to field
        card = new Protect();
        player.addCardInDeck(card, 0);
        assertThrows(GameException.class, () -> player.placeDeckToField(0, pos2, player.getField()));
        assertDoesNotThrow(() -> player.placeDeckToField(0, pos, player.getField()));
    }

    @Test
    public void testPlaceProduct() {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        Product product = new Product("DAGING_KUDA");
        Product product2 = new Product("DAGING_KUDA");

        // Test placing product on player's field
        player.addCardInField(new Carnivore(), pos);
        assertDoesNotThrow(() -> player.placeProduct(product, pos, player.getField()));

        // Test placing product on opponent's field
        assertThrows(GameException.class, () -> player.placeProduct(product2, pos, new ArrayList<>()));

        player.resetPlayer();
    }

    @Test
    public void testPlaceLiving() {
        Pair<Integer, Integer> pos = new Pair<>(0, 0);
        LivingThing living = new Carnivore();
        LivingThing living2 = new Carnivore();

        // Test placing living thing on player's field
        assertDoesNotThrow(() -> player.placeLiving(living, pos, player.getField()));

        // Test placing living thing on opponent's field
        assertThrows(GameException.class, () -> player.placeLiving(living2, pos, new ArrayList<>()));

        player.resetPlayer();
    }

    // @Test
    // public void testUpdateSelected() throws GameException {
    //     List<GameObject> selected = new ArrayList<>();
    //     selected.add(new GameObject());
    //     selected.add(new GameObject());

    //     player.updateSelected(selected);

    //     assertEquals(4, player.getDeckSlot());
    //     assertEquals(2, player.getCountActiveCard());
    // }

    @Test
    public void testGetCountActiveField() {
        assertEquals(0, player.getCountActiveField());

        player.addCardInField(new Carnivore(), new Pair<>(0, 0));
        assertEquals(1, player.getCountActiveField());

        player.addCardInField(new Carnivore(), new Pair<>(1, 1));
        assertEquals(2, player.getCountActiveField());

        player.removeCardInField(new Pair<>(0, 0));
        assertEquals(1, player.getCountActiveField());
    }

    @Test
    public void testShuffleCard() {
        // Test shuffling when deck slot is sufficient
        List<GameObject> shuffled = player.shuffleCard();
        assertEquals(4, shuffled.size());

        // Test shuffling when deck slot is less than 4
        player.setDeckSlot(3);
        shuffled = player.shuffleCard();
        assertEquals(3, shuffled.size());
    }
}
