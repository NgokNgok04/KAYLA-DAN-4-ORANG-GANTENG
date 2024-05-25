package models;

import icons.Icon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import gamexception.*;

import static org.junit.jupiter.api.Assertions.*;

class CarnivoreTest {

    @Test
    void testConstructorWithItems() {
        List<Item> items = new ArrayList<>();
        items.add(new InstantHarvest());
        items.add(new Delay());
        items.add(new Accelerate());

        Carnivore carnivore = new Carnivore(items, 10);

        assertEquals("ANIMAL", carnivore.getTypeObject());
        assertEquals("HIU_DARAT", carnivore.getName());
        assertTrue(carnivore.isActive());
        assertEquals(Icon.SHARK, carnivore.getImage());
        // assertFalse(carnivore.isInstantHarvest());
        assertFalse(carnivore.isProtection());
        assertFalse(carnivore.isTrap());
        assertEquals(new Product("SIRIP_HIU"), carnivore.getProduct());
        assertEquals(20, carnivore.getWeightToHarvest());
        assertEquals(10, carnivore.getWeight());
        assertEquals("CARNIVORE", carnivore.getAnimalType());

        List<Item> carnivoreItems = carnivore.getItems();
        assertEquals(2, carnivoreItems.size());
        assertTrue(carnivoreItems.stream().anyMatch(item -> item instanceof Delay));
        assertTrue(carnivoreItems.stream().anyMatch(item -> item instanceof Accelerate));
    }

    @Test
    void testDefaultConstructor() {
        Carnivore carnivore = new Carnivore();

        assertEquals("ANIMAL", carnivore.getTypeObject());
        assertEquals("HIU_DARAT", carnivore.getName());
        assertTrue(carnivore.isActive());
        assertEquals(Icon.SHARK, carnivore.getImage());
        // assertFalse(carnivore.isInstantHarvest());
        assertFalse(carnivore.isProtection());
        assertFalse(carnivore.isTrap());
        assertEquals(new Product("SIRIP_HIU"), carnivore.getProduct());
        assertEquals(20, carnivore.getWeightToHarvest());
        assertEquals(0, carnivore.getWeight());
        assertEquals("CARNIVORE", carnivore.getAnimalType());
        assertNotNull(carnivore.getItems());
        assertTrue(carnivore.getItems().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        Carnivore original = new Carnivore();
        Carnivore copied = new Carnivore(original);

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
    void testEatWithAnimalProduct() {
        Carnivore carnivore = new Carnivore();
        Product meat = new Product("DAGING_DOMBA");
        int initialWeight = carnivore.getWeight();

        assertDoesNotThrow(() -> carnivore.eat(meat));
        assertEquals(initialWeight + meat.getAddedWeight(), carnivore.getWeight());
    }

    @Test
    void testEatWithPlantProduct() {
        Carnivore carnivore = new Carnivore();
        Product corn = new Product("JAGUNG");

        Exception exception = assertThrows(GameException.class, () -> carnivore.eat(corn));
        assertEquals("Carnivore can only eat Animal Product", exception.getMessage());
    }

    @Test
    void testEatWithNonProductObject() {
        Carnivore carnivore = new Carnivore();
        GameObject rock = new GameObject("OBJECT", "Rock", true, null);

        Exception exception = assertThrows(GameException.class, () -> carnivore.eat(rock));
        assertEquals("Carnivore can only eat Product", exception.getMessage());
    }
}