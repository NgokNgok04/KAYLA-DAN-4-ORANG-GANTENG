package models;

import icons.Icon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DestroyTest {

    @Test
    void testConstructor() {
        Destroy destroy = new Destroy();

        assertEquals("ITEM", destroy.getTypeObject());
        assertEquals("DESTROY", destroy.getName());
        assertTrue(destroy.isActive());
        assertNotNull(destroy.getImage());
        assertEquals(Icon.DESTROY, destroy.getImage());
    }

    @Test
    void testCopyConstructor() {
        Destroy original = new Destroy();
        Destroy copied = new Destroy(original);

        assertEquals(original.getTypeObject(), copied.getTypeObject());
        assertEquals(original.getName(), copied.getName());
        assertEquals(original.isActive(), copied.isActive());
        assertEquals(original.getImage(), copied.getImage());
    }

    @Test
    void testUseEffect() {
        Destroy destroy = new Destroy();
        AffectableMock affectable = new AffectableMock();

        destroy.useEffect(affectable);

        assertFalse(affectable.isActive());
    }

    private static class AffectableMock implements Affectable {

        private boolean active;

        public AffectableMock() {
            this.active = true;
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
            this.active = false;
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
            // Do nothing for testing purposes
        }

        @Override
        public void addItems(List<Item> items) {
            // Do nothing for testing purposes
        }

        @Override
        public List<Item> getItems() {
            return new ArrayList<>();
        }

        @Override
        public Item getItem(int index) {
            return null;
        }

        public boolean isActive() {
            return active;
        }
    }
}
