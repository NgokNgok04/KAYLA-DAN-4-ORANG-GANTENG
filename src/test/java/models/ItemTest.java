package models;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testConstructor() {
        Image image = createMockImage();
        Item item = new TestItem("Test Item", image);

        assertEquals("ITEM", item.getTypeObject());
        assertEquals("Test Item", item.getName());
        assertTrue(item.isActive());
        assertEquals(image, item.getImage());
    }

    @Test
    void testCopyConstructor() {
        Image image = createMockImage();
        Item originalItem = new TestItem("Test Item", image);
        Item copiedItem = new TestItem(originalItem);

        assertEquals(originalItem.getTypeObject(), copiedItem.getTypeObject());
        assertEquals(originalItem.getName(), copiedItem.getName());
        assertEquals(originalItem.isActive(), copiedItem.isActive());
        assertEquals(originalItem.getImage(), copiedItem.getImage());
    }

    private BufferedImage createMockImage() {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 100, 100);
        graphics.dispose();
        return image;
    }

    private static class TestItem extends Item {

        public TestItem(String name, Image image) {
            super(name, image);
        }

        public TestItem(Item other) {
            super(other);
        }

        @Override
        public void useEffect(Affectable affectable) {
            // Do nothing for testing purposes
        }
    }
}
