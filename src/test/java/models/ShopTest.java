package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    private Shop shop;

    @BeforeEach
    void setUp() {
        shop = Shop.getInstance();
    }

    @Test
    void testSingletonInstance() {
        Shop shop1 = Shop.getInstance();
        Shop shop2 = Shop.getInstance();
        assertSame(shop1, shop2, "Both instances should be the same");
    }

    @Test
    void testGetAvailableItem() {
        List<Pair<Product, Integer>> availableItems = shop.getAvailableItem();
        assertEquals(9, availableItems.size(), "There should be 8 items in the shop");
        assertEquals("SIRIP_HIU", availableItems.get(0).getFirst().getName());
        assertEquals(0, availableItems.get(0).getSecond());
    }

    @Test
    void testGetPairByIndex() {
        Pair<Product, Integer> pair = shop.getPair(0);
        assertNotNull(pair, "Pair should not be null");
        assertEquals("SIRIP_HIU", pair.getFirst().getName(), "First pair should be SIRIP_HIU");
    }

    @Test
    void testGetItemByIndex() {
        Product product = shop.getItem(0);
        assertNotNull(product, "Product should not be null");
        assertEquals("SIRIP_HIU", product.getName(), "First product should be SIRIP_HIU");
    }

    @Test
    void testGetItemByName() {
        Product product = shop.getItem("SUSU");
        assertNotNull(product, "Product should not be null");
        assertEquals("SUSU", product.getName(), "Product should be SUSU");
    }

    @Test
    void testGetQuantityByIndex() {
        int quantity = shop.getQuantity(0);
        assertEquals(0, quantity, "Quantity should be 0");
    }

    @Test
    void testGetQuantityByName() {
        int quantity = shop.getQuantity("SUSU");
        assertEquals(0, quantity, "Quantity should be 0");
    }

    @Test
    void testSetQuantityByIndex() {
        shop.setQuantity(0, 10);
        assertEquals(10, shop.getQuantity(0), "Quantity should be 10");
        shop.setQuantity(0, 0);
    }

    @Test
    void testSetQuantityByName() {
        shop.setQuantity("SUSU", 15);
        assertEquals(15, shop.getQuantity("SUSU"), "Quantity should be 15");
        shop.setQuantity("SUSU", 0);
    }

    @Test
    void testIsEmpty() {
        assertTrue(shop.isEmpty(), "Shop should be empty initially");
        shop.setQuantity(0, 1);
        assertFalse(shop.isEmpty(), "Shop should not be empty after setting a quantity");
        shop.setQuantity(0, 0);
    }

    @Test
    void testGetMinPriceAvailable() {
        // assertEquals(Integer.MAX_VALUE, shop.getMinPriceAvailable(), "No items available for sale initially");

        shop.setQuantity(0, 10);
        shop.setQuantity(1, 5);
        assertEquals(100, shop.getMinPriceAvailable(), "Minimum price should be 100");
        shop.setQuantity(0, 0);
        shop.setQuantity(0, 0);
    }

    @Test
    void testCheckStock() {
        shop.setQuantity("SUSU", 10);
        assertTrue(shop.checkStock("SUSU", 5), "Stock should be available");
        assertFalse(shop.checkStock("SUSU", 15), "Stock should not be available");
        shop.setQuantity("SUSU", 0);
    }

    @Test
    void testAddItem() {
        shop.addItem("SUSU", 5);
        assertEquals(5, shop.getQuantity("SUSU"), "Quantity should be 5 after adding");

        shop.addItem("SUSU", 5);
        assertEquals(10, shop.getQuantity("SUSU"), "Quantity should be 10 after adding more");

        shop.setQuantity("SUSU", 0);
    }

    @Test
    void testRemoveItem() {
        shop.setQuantity("SUSU", 10);
        shop.removeItem("SUSU", 5);
        assertEquals(5, shop.getQuantity("SUSU"), "Quantity should be 5 after removing");

        shop.removeItem("SUSU", 5);
        assertEquals(0, shop.getQuantity("SUSU"), "Quantity should be 0 after removing all");

        shop.setQuantity("SUSU", 0);
    }
}
