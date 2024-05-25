package models;

import icons.Icon;
import org.junit.jupiter.api.Test;
import gamexception.GameException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OmnivoreTest {

    @Test
    void testConstructorWithItems() {
        List<Item> items = new ArrayList<>();
        items.add(new InstantHarvest());
        items.add(new Delay());
        items.add(new Accelerate());

        Omnivore omnivore = new Omnivore("AYAM", 10, items);

        assertEquals("ANIMAL", omnivore.getTypeObject());
        assertEquals("AYAM", omnivore.getName());
        assertTrue(omnivore.isActive());
        assertEquals(Icon.CHICKEN, omnivore.getImage());
        // assertFalse(omnivore.isInstantHarvest());
        assertFalse(omnivore.isProtection());
        assertFalse(omnivore.isTrap());
        assertEquals(new Product("TELUR"), omnivore.getProduct());
        assertEquals(5, omnivore.getWeightToHarvest());
        assertEquals(10, omnivore.getWeight());
        assertEquals("OMNIVORE", omnivore.getAnimalType());

        List<Item> omnivoreItems = omnivore.getItems();
        assertEquals(2, omnivoreItems.size());
        assertTrue(omnivoreItems.stream().anyMatch(item -> item instanceof Delay));
        assertTrue(omnivoreItems.stream().anyMatch(item -> item instanceof Accelerate));
    }

    @Test
    void testDefaultOmnivore() {
        Omnivore omnivore = new Omnivore("AYAM");

        assertEquals("ANIMAL", omnivore.getTypeObject());
        assertEquals("AYAM", omnivore.getName());
        assertTrue(omnivore.isActive());
        assertEquals(Icon.CHICKEN, omnivore.getImage());
        // assertFalse(omnivore.isInstantHarvest());
        assertFalse(omnivore.isProtection());
        assertFalse(omnivore.isTrap());
        assertEquals(new Product("TELUR"), omnivore.getProduct());
        assertEquals(5, omnivore.getWeightToHarvest());
        assertEquals(0, omnivore.getWeight());
        assertEquals("OMNIVORE", omnivore.getAnimalType());
        assertNotNull(omnivore.getItems());
        assertTrue(omnivore.getItems().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        Omnivore original = new Omnivore("AYAM");
        Omnivore copied = new Omnivore(original);

        assertEquals(original.getTypeObject(), copied.getTypeObject());
        assertEquals(original.getName(), copied.getName());
        assertEquals(original.isActive(), copied.isActive());
        assertEquals(original.getImage(), copied.getImage());
        // assertEquals(original.isInstantHarvest(), copied.isInstantHarvest());
        assertEquals(original.isProtection(), copied.isProtection());
        assertEquals(original.isTrap(), copied.isTrap());
        assertEquals(original.getProduct(), copied.getProduct());
        assertEquals(original.getWeightToHarvest(), copied.getWeightToHarvest());
        assertEquals(original.getWeight(), copied.getWeight());
        assertEquals(original.getAnimalType(), copied.getAnimalType());
        assertEquals(original.getItems(), copied.getItems());
    }

    @Test
    void testEatWithPlantProduct() {
        Omnivore omnivore = new Omnivore("AYAM");
        Product corn = new Product("JAGUNG");
        int initialWeight = omnivore.getWeight();

        assertDoesNotThrow(() -> omnivore.eat(corn));
        assertEquals(initialWeight + corn.getAddedWeight(), omnivore.getWeight());
    }

    @Test
    void testEatWithAnimalProduct() {
        Omnivore omnivore = new Omnivore("AYAM");
        Product meat = new Product("DAGING_DOMBA");
        int initialWeight = omnivore.getWeight();

        assertDoesNotThrow(() -> omnivore.eat(meat));
        assertEquals(initialWeight + meat.getAddedWeight(), omnivore.getWeight());
    }

    @Test
    void testEatWithNonProductObject() {
        Omnivore omnivore = new Omnivore("AYAM");
        GameObject rock = new GameObject("OBJECT", "Rock", true, null);

        Exception exception = assertThrows(GameException.class, () -> omnivore.eat(rock));
        assertEquals("Omnivore can only eat Product", exception.getMessage());
    }
}