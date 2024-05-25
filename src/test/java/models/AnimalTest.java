package models;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testConstructorWithItems() {
        Product product = new Product("SUSU");
        List<Item> items = new ArrayList<>();
        items.add(new InstantHarvest());
        AnimalMock animal = new AnimalMock("TestAnimal", true, null, true, true, true, product, items, 10, 5, "Herbivore");

        assertEquals("ANIMAL", animal.getTypeObject());
        assertEquals("TestAnimal", animal.getName());
        assertTrue(animal.isActive());
        assertNull(animal.getImage());
        assertTrue(animal.isInstantHarvest());
        assertTrue(animal.isProtection());
        assertTrue(animal.isTrap());
        assertEquals(product, animal.getProduct());
        assertEquals(items, animal.getItems());
        assertEquals(10, animal.getWeightToHarvest());
        assertEquals(5, animal.getWeight());
        assertEquals("Herbivore", animal.getAnimalType());
    }

    @Test
    void testConstructorWithoutItems() {
        Product product = new Product("SUSU");
        AnimalMock animal = new AnimalMock("TestAnimal", true, null, true, true, true, product, 10, 5, "Herbivore");

        assertEquals("ANIMAL", animal.getTypeObject());
        assertEquals("TestAnimal", animal.getName());
        assertTrue(animal.isActive());
        assertNull(animal.getImage());
        assertTrue(animal.isInstantHarvest());
        assertTrue(animal.isProtection());
        assertTrue(animal.isTrap());
        assertEquals(product, animal.getProduct());
        assertNotNull(animal.getItems());
        assertTrue(animal.getItems().isEmpty());
        assertEquals(10, animal.getWeightToHarvest());
        assertEquals(5, animal.getWeight());
        assertEquals("Herbivore", animal.getAnimalType());
    }

    @Test
    void testCopyConstructor() {
        Product product = new Product("SUSU");
        List<Item> items = new ArrayList<>();
        items.add(new InstantHarvest());
        AnimalMock original = new AnimalMock("TestAnimal", true, null, true, true, true, product, items, 10, 5, "Herbivore");
        AnimalMock copied = new AnimalMock(original);

        assertEquals(original.getTypeObject(), copied.getTypeObject());
        assertEquals(original.getName(), copied.getName());
        assertEquals(original.isActive(), copied.isActive());
        assertNull(copied.getImage());
        assertEquals(original.isInstantHarvest(), copied.isInstantHarvest());
        assertEquals(original.isProtection(), copied.isProtection());
        assertEquals(original.isTrap(), copied.isTrap());
        assertEquals(original.getProduct(), copied.getProduct());
        assertEquals(original.getItems(), copied.getItems());
        assertEquals(original.getWeightToHarvest(), copied.getWeightToHarvest());
        assertEquals(original.getWeight(), copied.getWeight());
        assertEquals(original.getAnimalType(), copied.getAnimalType());
    }

    @Test
    void testAccelerate() {
        AnimalMock animal = new AnimalMock("TestAnimal", true, null, false, false, false, null, 10, 5, "Herbivore");

        assertEquals(5, animal.getWeight());
        animal.accelerate();
        assertEquals(13, animal.getWeight());
    }

    @Test
    void testDelay() {
        AnimalMock animal = new AnimalMock("TestAnimal", true, null, false, false, false, null, 10, 10, "Herbivore");

        assertEquals(10, animal.getWeight());
        animal.delay();
        assertEquals(5, animal.getWeight());

        animal.setWeight(4);
        animal.delay();
        assertEquals(0, animal.getWeight());

        animal.setProtection(true);
        animal.setWeight(10);
        animal.delay();
        assertEquals(10, animal.getWeight());
    }

    @Test
    void testSetAndGetAnimalType() {
        AnimalMock animal = new AnimalMock("TestAnimal", true, null, false, false, false, null, 10, 5, "Herbivore");

        assertEquals("Herbivore", animal.getAnimalType());
        animal.setAnimalType("Carnivore");
        assertEquals("Carnivore", animal.getAnimalType());
    }

    @Test
    void testSetAndGetWeight() {
        AnimalMock animal = new AnimalMock("TestAnimal", true, null, false, false, false, null, 10, 5, "Herbivore");

        assertEquals(5, animal.getWeight());
        animal.setWeight(8);
        assertEquals(8, animal.getWeight());
    }

    @Test
    void testEat() {
        AnimalMock animal = new AnimalMock("TestAnimal", true, null, false, false, false, null, 10, 5, "Herbivore");
        GameObject eatable = new GameObject("FOOD", "Grass", true, null);

        assertDoesNotThrow(() -> animal.eat(eatable));
    }

    private static class AnimalMock extends Animal {
        public AnimalMock(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, List<Item> items, int weightToHarvest, int weight, String animalType) {
            super(name, active, image, instantHarvest, protection, trap, product, items, weightToHarvest, weight, animalType);
        }

        public AnimalMock(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, int weightToHarvest, int weight, String animalType) {
            super(name, active, image, instantHarvest, protection, trap, product, weightToHarvest, weight, animalType);
        }

        public AnimalMock(Animal other) {
            super(other);
        }

        @Override
        public void eat(GameObject eatable) {
            // Mock implementation for testing
        }
    }
}