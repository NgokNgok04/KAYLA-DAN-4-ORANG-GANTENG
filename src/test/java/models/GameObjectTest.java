package models;

import org.junit.jupiter.api.Test;
import java.awt.Image;
import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void getTypeObject() {
        GameObject gameObject = new GameObject();
        gameObject.setTypeObject("CARNIVORE");
        assertEquals("CARNIVORE", gameObject.getTypeObject());
    }

    @Test
    void setTypeObject() {
        GameObject gameObject = new GameObject();
        gameObject.setTypeObject("HERBIVORE");
        assertEquals("HERBIVORE", gameObject.getTypeObject());
    }

    @Test
    void getName() {
        GameObject gameObject = new GameObject();
        gameObject.setName("HIU_DARAT");
        assertEquals("HIU_DARAT", gameObject.getName());
    }

    @Test
    void setName() {
        GameObject gameObject = new GameObject();
        gameObject.setName("SAPI");
        assertEquals("SAPI", gameObject.getName());
    }

    @Test
    void isActive() {
        GameObject gameObject = new GameObject();
        gameObject.setActive(true);
        assertTrue(gameObject.isActive());
    }

    @Test
    void setActive() {
        GameObject gameObject = new GameObject();
        gameObject.setActive(false);
        assertFalse(gameObject.isActive());
    }

    @Test
    void getImage() {
        GameObject gameObject = new GameObject();
        Image image = null; // Null dulu
        gameObject.setImage(image);
        assertEquals(image, gameObject.getImage());
    }

    @Test
    void setImage() {
        GameObject gameObject = new GameObject();
        Image image = null; // Null dulu
        gameObject.setImage(image);
        assertEquals(image, gameObject.getImage());
    }

    @Test
    void testEquals() {
        GameObject gameObject1 = new GameObject("CARNIVORE", "HIU_DARAT", true, null);
        GameObject gameObject2 = new GameObject("CARNIVORE", "HIU_DARAT", true, null);
        assertEquals(gameObject1, gameObject2);

        assertNotEquals(gameObject1, "Matthew");

        GameObject gameObject3 = new GameObject("HERBIVORE", "SAPI", false, null);
        assertNotEquals(gameObject1, gameObject3);

        GameObject gameObject4 = new GameObject("HERBIVORE", "HIU_DARAT", false, null);
        assertNotEquals(gameObject1, gameObject4);

        GameObject gameObject5 = new GameObject("CARNIVORE", "SAPI", false, null);
        assertNotEquals(gameObject1, gameObject5);


    }
}
