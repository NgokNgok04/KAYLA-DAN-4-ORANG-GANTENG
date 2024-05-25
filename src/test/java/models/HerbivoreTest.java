package models;

import icons.Icon;
import org.junit.jupiter.api.Test;
import gamexception.GameException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HerbivoreTest {

    @Test
    void testConstructorWithItems() {
        List<Item> items = new ArrayList<>();
        items.add(new InstantHarvest());
        items.add(new Delay());
        items.add(new Accelerate());

        Herbivore herbivore = new Herbivore("SAPI", 10, items);

        assertEquals("ANIMAL", herbivore.getTypeObject());
        assertEquals("SAPI", herbivore.getName());
        assertTrue(herbivore.isActive());
        assertEquals(Icon.COW, herbivore.getImage());
        // assertFalse(herbivore.isInstantHarvest());
        assertFalse(herbivore.isProtection());
        assertFalse(herbivore.isTrap());
        assertEquals(new Product("SUSU"), herbivore.getProduct());
        assertEquals(10, herbivore.getWeightToHarvest());
        assertEquals(10, herbivore.getWeight());
        assertEquals("HERBIVORE", herbivore.getAnimalType());

        List<Item> herbivoreItems = herbivore.getItems();
        assertEquals(2, herbivoreItems.size());
        assertTrue(herbivoreItems.stream().anyMatch(item -> item instanceof Delay));
        assertTrue(herbivoreItems.stream().anyMatch(item -> item instanceof Accelerate));
    }

    @Test
    void testDefaultHerbivore() {
        Herbivore herbivore = new Herbivore("SAPI");

        assertEquals("ANIMAL", herbivore.getTypeObject());
        assertEquals("SAPI", herbivore.getName());
        assertTrue(herbivore.isActive());
        assertEquals(Icon.COW, herbivore.getImage());
        // assertFalse(herbivore.isInstantHarvest());
        assertFalse(herbivore.isProtection());
        assertFalse(herbivore.isTrap());
        assertEquals(new Product("SUSU"), herbivore.getProduct());
        assertEquals(10, herbivore.getWeightToHarvest());
        assertEquals(0, herbivore.getWeight());
        assertEquals("HERBIVORE", herbivore.getAnimalType());
        assertNotNull(herbivore.getItems());
        assertTrue(herbivore.getItems().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        Herbivore original = new Herbivore("SAPI");
        Herbivore copied = new Herbivore(original);

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
        Herbivore herbivore = new Herbivore("SAPI");
        Product corn = new Product("JAGUNG");
        int initialWeight = herbivore.getWeight();

        assertDoesNotThrow(() -> herbivore.eat(corn));
        assertEquals(initialWeight + corn.getAddedWeight(), herbivore.getWeight());
    }

    @Test
    void testEatWithAnimalProduct() {
        Herbivore herbivore = new Herbivore("SAPI");
        Product meat = new Product("DAGING_DOMBA");

        assertThrows(GameException.class, () -> herbivore.eat(meat));
    }

    @Test
    void testEatWithNonProductObject() {
        Herbivore herbivore = new Herbivore("SAPI");
        GameObject rock = new GameObject("OBJECT", "Rock", true, null);

        assertThrows(GameException.class, () -> herbivore.eat(rock));
    }
}