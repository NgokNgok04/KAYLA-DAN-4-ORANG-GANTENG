package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivingThingTest {

    private LivingThing livingThing;

    @BeforeEach
    void setUp() {
        Image image = new ImageIcon("image.jpg").getImage();
        List<Item> items = new ArrayList<>();
        items.add(new DummyItem("Item 1", image));
        Product product = new Product("SIRIP_HIU");;
        livingThing = new LivingThingImpl("Tiger", true, image, true, false, false, product, items);
    }

    @Test
    void testConstructorWithItems() {
        assertEquals("Tiger", livingThing.getName());
        assertTrue(livingThing.isInstantHarvest());
        assertFalse(livingThing.isProtection());
        assertFalse(livingThing.isTrap());
        assertEquals(1, livingThing.getItems().size());
        assertEquals("Item 1", livingThing.getItem(0).getName());
    }

    @Test
    void testConstructorWithoutItems() {
        Image image = new ImageIcon("image.jpg").getImage();
        Product product = new Product("SIRIP_HIU");
        LivingThing livingThing = new LivingThingImpl("Tiger", true, image, true, false, false, product);
        assertEquals("Tiger", livingThing.getName());
        assertTrue(livingThing.isInstantHarvest());
        assertFalse(livingThing.isProtection());
        assertFalse(livingThing.isTrap());
        assertEquals(0, livingThing.getItems().size());
    }

    @Test
    void testCopyConstructor() {
        LivingThing copiedLivingThing = new LivingThingImpl((LivingThingImpl) livingThing);
        assertEquals(livingThing.getName(), copiedLivingThing.getName());
        assertEquals(livingThing.isInstantHarvest(), copiedLivingThing.isInstantHarvest());
        assertEquals(livingThing.isProtection(), copiedLivingThing.isProtection());
        assertEquals(livingThing.isTrap(), copiedLivingThing.isTrap());
        assertEquals(livingThing.getProduct(), copiedLivingThing.getProduct());
        assertEquals(livingThing.getItems().size(), copiedLivingThing.getItems().size());
    }

    @Test
    void testHarvest() {
        Product product = livingThing.harvest();
        assertEquals("SIRIP_HIU", product.getName());
    }

    @Test
    void testInstantHarvest() {
        // assertFalse(livingThing.isInstantHarvest());
        livingThing.instantHarvest();
        assertTrue(livingThing.isInstantHarvest());
    }

    @Test
    void testDestroy() {
        assertTrue(livingThing.isActive());
        livingThing.destroy();
        assertFalse(livingThing.isActive());
    }

    @Test
    void testProtect() {
        assertFalse(livingThing.isProtection());
        livingThing.protect();
        assertTrue(livingThing.isProtection());
    }

    @Test
    void testTrap() {
        assertFalse(livingThing.isTrap());
        livingThing.trap();
        assertTrue(livingThing.isTrap());
    }

    @Test
    void testAddItem() {
        assertEquals(1, livingThing.getItems().size());
        livingThing.addItem(new DummyItem("Item 2", null));
        assertEquals(2, livingThing.getItems().size());
    }

    @Test
    void testAddItems() {
        assertEquals(1, livingThing.getItems().size());
        List<Item> items = new ArrayList<>();
        items.add(new DummyItem("Item 2", null));
        items.add(new DummyItem("Item 3", null));
        livingThing.addItems(items);
        assertEquals(3, livingThing.getItems().size());
    }
}

class LivingThingImpl extends LivingThing {

    public LivingThingImpl(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product, List<Item> items) {
        super("ANIMAL", name, active, image, instantHarvest, protection, trap, product, items);
    }

    public LivingThingImpl(String name, boolean active, Image image, boolean instantHarvest, boolean protection, boolean trap, Product product) {
        super("ANIMAL", name, active, image, instantHarvest, protection, trap, product);
    }

    public LivingThingImpl(LivingThingImpl other) {
        super(other);
    }

    @Override
    public void accelerate() {
        // Not implemented in this test
    }

    @Override
    public void delay() {
        // Not implemented in this test
    }

    // @Override
    // public void useEffect(Affectable affectable) {
    //     // Not implemented in this test
    // }
}

class DummyItem extends Item {

    public DummyItem(String name, Image image) {
        super(name, image);
    }

    @Override
    public void useEffect(Affectable affectable) {
        // Dummy implementation for testing
    }
}
