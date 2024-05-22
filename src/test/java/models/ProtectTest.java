package models;

import icons.Icon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProtectTest {

    @Test
    void testConstructor() {
        Protect protect = new Protect();

        assertEquals("ITEM", protect.getTypeObject());
        assertEquals("PROTECT", protect.getName());
        assertTrue(protect.isActive());
        assertNotNull(protect.getImage());
        assertEquals(Icon.PROTECT, protect.getImage());
    }

    @Test
    void testCopyConstructor() {
        Protect original = new Protect();
        Protect copied = new Protect(original);

        assertEquals(original.getTypeObject(), copied.getTypeObject());
        assertEquals(original.getName(), copied.getName());
        assertEquals(original.isActive(), copied.isActive());
        assertEquals(original.getImage(), copied.getImage());
    }

    @Test
    void testUseEffect() {
        Protect protect = new Protect();
        AffectableMock affectable = new AffectableMock();

        protect.useEffect(affectable);

        assertTrue(affectable.isProtected());
        assertTrue(affectable.getItems().contains(protect));
    }

    private static class AffectableMock implements Affectable {

        private boolean protectedStatus;
        private List<Item> items;

        public AffectableMock() {
            this.protectedStatus = false;
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
            this.protectedStatus = true;
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

        public boolean isProtected() {
            return protectedStatus;
        }
    }
}
