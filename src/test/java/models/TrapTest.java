package models;

import icons.Icon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    @Test
    void testConstructor() {
        Trap trap = new Trap();

        assertEquals("ITEM", trap.getTypeObject());
        assertEquals("TRAP", trap.getName());
        assertTrue(trap.isActive());
        assertNotNull(trap.getImage());
        assertEquals(Icon.TRAP, trap.getImage());
    }

    @Test
    void testCopyConstructor() {
        Trap original = new Trap();
        Trap copied = new Trap(original);

        assertEquals(original.getTypeObject(), copied.getTypeObject());
        assertEquals(original.getName(), copied.getName());
        assertEquals(original.isActive(), copied.isActive());
        assertEquals(original.getImage(), copied.getImage());
    }

    @Test
    void testUseEffect() {
        Trap trap = new Trap();
        AffectableMock affectable = new AffectableMock();

        trap.useEffect(affectable);

        assertTrue(affectable.isTrapped());
        assertTrue(affectable.getItems().contains(trap));
    }

    private static class AffectableMock implements Affectable {

        private boolean trappedStatus;
        private List<Item> items;

        public AffectableMock() {
            this.trappedStatus = false;
            this.items = new ArrayList<>();
        }

        @Override
        public void accelerate() {
            // Do nothing for testing purposes
        }

        @Override
        public void delay() {
            // Do nothing for testing purposes
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
            this.trappedStatus = true;
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

        public boolean isTrapped() {
            return trappedStatus;
        }
    }
}