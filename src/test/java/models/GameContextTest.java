package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gamexception.GameException;
import icons.Icon;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameContextTest {

    @BeforeEach
    public void setUp() {
        // Reset the OBJECT_TYPE map before each test
        GameContext.OBJECT_TYPE.clear();
        GameContext.OBJECT_TYPE.put("HIU_DARAT", "models.Carnivore");
        GameContext.OBJECT_TYPE.put("SAPI", "models.Herbivore");
        GameContext.OBJECT_TYPE.put("DOMBA", "models.Herbivore");
        GameContext.OBJECT_TYPE.put("KUDA", "models.Herbivore");
        GameContext.OBJECT_TYPE.put("AYAM", "models.Omnivore");
        GameContext.OBJECT_TYPE.put("BERUANG", "models.Omnivore");
        GameContext.OBJECT_TYPE.put("BIJI_JAGUNG", "models.Plant");
        GameContext.OBJECT_TYPE.put("BIJI_LABU", "models.Plant");
        GameContext.OBJECT_TYPE.put("BIJI_STROBERI", "models.Plant");
        GameContext.OBJECT_TYPE.put("SIRIP_HIU", "models.Product");
        GameContext.OBJECT_TYPE.put("SUSU", "models.Product");
        GameContext.OBJECT_TYPE.put("DAGING_DOMBA", "models.Product");
        GameContext.OBJECT_TYPE.put("DAGING_KUDA", "models.Product");
        GameContext.OBJECT_TYPE.put("TELUR", "models.Product");
        GameContext.OBJECT_TYPE.put("DAGING_BERUANG", "models.Product");
        GameContext.OBJECT_TYPE.put("JAGUNG", "models.Product");
        GameContext.OBJECT_TYPE.put("LABU", "models.Product");
        GameContext.OBJECT_TYPE.put("STROBERI", "models.Product");
        GameContext.OBJECT_TYPE.put("ACCELERATE", "models.Accelerate");
        GameContext.OBJECT_TYPE.put("DELAY", "models.Delay");
        GameContext.OBJECT_TYPE.put("INSTANT_HARVEST", "models.InstantHarvest");
        GameContext.OBJECT_TYPE.put("DESTROY", "models.Destroy");
        GameContext.OBJECT_TYPE.put("PROTECT", "models.Protect");
        GameContext.OBJECT_TYPE.put("TRAP", "models.Trap");
    }

    @Test
    public void testCreateObject_Carnivore() {
        // Test creating a Carnivore object
        GameObject obj = GameContext.createObject("HIU_DARAT");
        assertNotNull(obj);
        assertTrue(obj instanceof Carnivore);
    }

    @Test
    public void testCreateObject_Product() {
        // Test creating a Product object
        GameObject obj = GameContext.createObject("SIRIP_HIU");
        assertNotNull(obj);
        assertTrue(obj instanceof Product);
    }

    @Test
    public void testCreateObject_Plant() {
        // Test creating a Plant object
        GameObject obj = GameContext.createObject("BIJI_JAGUNG");
        assertNotNull(obj);
        assertTrue(obj instanceof Plant);
    }

    @Test
    public void testCreateObject_InvalidName() {
        // Test creating an object with an invalid name
        GameObject obj = GameContext.createObject("INVALID_NAME");
        assertNull(obj);
    }

    @Test
    public void testRandomGameObject() {
        // Test random object creation multiple times
        for (int i = 0; i < 10; i++) {
            GameObject obj = GameContext.randomGameObject();
            assertNotNull(obj);
            assertTrue(obj instanceof GameObject);
        }
    }

    @Test
    public void testObjectTypeMap() {
        // Test that all defined object types in OBJECT_TYPE map are valid
        for (String key : GameContext.OBJECT_TYPE.keySet()) {
            GameObject obj = GameContext.createObject(key);
            assertNotNull(obj);
            assertTrue(obj instanceof GameObject);
        }
    }
}
