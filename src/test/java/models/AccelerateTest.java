package models;

import icons.Icon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccelerateTest {

    @Test
    void testConstructor() {
        Accelerate accelerate = new Accelerate();

        assertEquals("ITEM", accelerate.getTypeObject());
        assertEquals("ACCELERATE", accelerate.getName());
        assertTrue(accelerate.isActive());
        assertNotNull(accelerate.getImage());
        assertEquals(Icon.ACCELERATE, accelerate.getImage());
    }

    @Test
    void testCopyConstructor() {
        Accelerate original = new Accelerate();
        Accelerate copied = new Accelerate(original);

        assertEquals(original.getTypeObject(), copied.getTypeObject());
        assertEquals(original.getName(), copied.getName());
        assertEquals(original.isActive(), copied.isActive());
        assertEquals(original.getImage(), copied.getImage());
    }

    @Test
    void testUseEffect() {
        Accelerate accelerate = new Accelerate();
        AffectableMock affectable = new AffectableMock();

        accelerate.useEffect(affectable);

        assertTrue(affectable.isAccelerated());
        assertTrue(affectable.getItems().contains(accelerate));
    }

    private static class AffectableMock implements Affectable {

        private boolean accelerated;
        private List<Item> items;

        public AffectableMock() {
            this.accelerated = false;
            this.items = new ArrayList<>();
        }

        @Override
        public void accelerate() {
            this.accelerated = true;
        }

        @Override
        public void delay() {
            // For testing only
        }

        @Override
        public void instantHarvest() {
            // For testing only
        }

        @Override
        public void destroy() {
            // For testing only
        }

        @Override
        public void protect() {
            // For testing only
        }

        @Override
        public void trap() {
            // For testing only
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

        public boolean isAccelerated() {
            return accelerated;
        }
    }
}
