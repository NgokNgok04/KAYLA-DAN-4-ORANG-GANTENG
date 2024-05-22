package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlantTest {

    private Plant plant;

    @BeforeEach
    void setUp() {
        // Image image = new ImageIcon("../../../../../../../assets/icons/card_pupkin.png").getImage();
        plant = new Plant("BIJI_JAGUNG", 0, new ArrayList<>());
    }

    @Test
    void testConstructor() {
        assertEquals("BIJI_JAGUNG", plant.getName());
        assertTrue(plant.isActive());
        assertEquals(0, plant.getAge());
        assertEquals(3, plant.getAgeToHarvest());
        assertEquals("JAGUNG", plant.getProduct().getName());
    }

    @Test
    void testCopyConstructor() {
        Plant copiedPlant = new Plant(plant);
        assertEquals(plant.getName(), copiedPlant.getName());
        assertEquals(plant.isActive(), copiedPlant.isActive());
        assertEquals(plant.getAge(), copiedPlant.getAge());
        assertEquals(plant.getAgeToHarvest(), copiedPlant.getAgeToHarvest());
        assertEquals(plant.getProduct().getName(), copiedPlant.getProduct().getName());
    }

    @Test
    void testConstructorWithItems() {
        List<Item> items = new ArrayList<>();
        List<Item> items2 = new ArrayList<>();
        items.add(new DummyItem("ACCELERATE", null));
        items2.add(new DummyItem("PROTECT", null));
        Plant plant = new Plant("BIJI_LABU", 2, items);
        Plant plant2 = new Plant("BIJI_LABU", 2, items2);

        assertEquals(2, plant.getAge());
        assertEquals(1, plant.getItems().size());
        assertEquals("ACCELERATE", plant.getItem(0).getName());

        assertEquals(2, plant2.getAge());
        assertEquals(1, plant2.getItems().size());
        assertEquals("PROTECT", plant2.getItem(0).getName());
    }

    @Test
    void testGrow() {
        assertEquals(0, plant.getAge());
        plant.grow();
        assertEquals(1, plant.getAge());
    }

    @Test
    void testAccelerate() {
        assertEquals(0, plant.getAge());
        plant.accelerate();
        assertEquals(2, plant.getAge());
    }

    @Test
    void testDelay() {
        assertEquals(0, plant.getAge());
        plant.delay();
        assertEquals(0, plant.getAge());
        plant.grow();
        assertEquals(1, plant.getAge());
        plant.delay();
        assertEquals(0, plant.getAge());
    }

    private static class DummyItem extends Item {
        public DummyItem(String name, Image image) {
            super(name, image);
        }

        @Override
        public void useEffect(Affectable affectable) {
            if (getName().equals("ACCELERATE")) {
                affectable.accelerate();
            } else if (getName().equals("DELAY")) {
                affectable.delay();
            }

            affectable.addItem(this);
        }
    }
}
