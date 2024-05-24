package models;

import icons.Icon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DelayTest {

    @Test
    void testConstructor() {
        Delay delay = new Delay();

        assertEquals("ITEM", delay.getTypeObject());
        assertEquals("DELAY", delay.getName());
        assertTrue(delay.isActive());
        assertNotNull(delay.getImage());
        assertEquals(Icon.DELAY, delay.getImage());
    }

    @Test
    void testCopyConstructor() {
        Delay original = new Delay();
        Delay copied = new Delay(original);

        assertEquals(original.getTypeObject(), copied.getTypeObject());
        assertEquals(original.getName(), copied.getName());
        assertEquals(original.isActive(), copied.isActive());
        assertEquals(original.getImage(), copied.getImage());
    }

    @Test
    void testUseEffect() {
        Delay delay = new Delay();
        AffectableMock affectable = new AffectableMock();

        delay.useEffect(affectable);

        assertTrue(affectable.isDelayed());
        assertTrue(affectable.getItems().contains(delay));
    }

    private static class AffectableMock implements Affectable {

        private boolean delayed;
        private List<Item> items;

        public AffectableMock() {
            this.delayed = false;
            this.items = new ArrayList<>();
        }

        @Override
        public void accelerate() {
            // Do nothing for testing purposes
        }

        @Override
        public void delay() {
            this.delayed = true;
        }

        @Override
        public void instantHarvest() {
            // Do nothing for testing purposes
        }

        @Override
        public void destroy() {
            // Do nothing for testing purposes
        }

        @Override
        public void protect() {
            // Do nothing for testing purposes
        }

        @Override
        public void trap() {
            // Do nothing for testing purposes
        }

        @Override
        public void addItem(Item item) {
            items.add(item);
        }

        @Override
        public void addItems(List<Item> items) {
            this.items.addAll(items);
        }

        @Override
        public List<Item> getItems() {
            return items;
        }

        @Override
        public Item getItem(int index) {
            return items.get(index);
        }

        public boolean isDelayed() {
            return delayed;
        }
    }
}