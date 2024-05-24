package models;

import icons.Icon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testConstructorWithName() {
        Product product = new Product("SIRIP_HIU");
        assertNotNull(product);
        assertEquals("SIRIP_HIU", product.getName());
        assertTrue(product.isActive());
        assertEquals(Icon.SHARK_FIN, product.getImage());
        assertTrue(product.getOrigin());
        assertEquals(12, product.getAddedWeight());
        assertEquals(500, product.getPrice());
    }

    @Test
    void testCopyConstructor() {
        Product original = new Product("SIRIP_HIU");
        Product copy = new Product(original);

        assertNotNull(copy);
        assertEquals(original.getName(), copy.getName());
        assertEquals(original.isActive(), copy.isActive());
        assertEquals(original.getImage(), copy.getImage());
        assertEquals(original.getOrigin(), copy.getOrigin());
        assertEquals(original.getAddedWeight(), copy.getAddedWeight());
        assertEquals(original.getPrice(), copy.getPrice());
    }

    @Test
    void testGetOrigin() {
        Product product = new Product("SIRIP_HIU");
        assertTrue(product.getOrigin());
    }

    @Test
    void testGetAddedWeight() {
        Product product = new Product("SIRIP_HIU");
        assertEquals(12, product.getAddedWeight());
    }

    @Test
    void testGetPrice() {
        Product product = new Product("SIRIP_HIU");
        assertEquals(500, product.getPrice());
    }

    @Test
    void testPlantProduct() {
        Product product = new Product("JAGUNG");
        assertNotNull(product);
        assertEquals("JAGUNG", product.getName());
        assertTrue(product.isActive());
        assertEquals(Icon.CORN, product.getImage());
        assertFalse(product.getOrigin());
        assertEquals(3, product.getAddedWeight());
        assertEquals(150, product.getPrice());
    }
}