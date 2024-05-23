package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    private GameObject gameObject;
    private Image dummyImage;
    private JPanel dummyPanel;

    @BeforeEach
    void setUp() {
        dummyPanel = new JPanel();
        gameObject = new GameObject("TestType", "Test_Name", true, dummyImage);
        gameObject.setParent(dummyPanel);
    }

    @Test
    void testDefaultConstructor() {
        GameObject defaultGameObject = new GameObject();
        assertEquals("", defaultGameObject.getTypeObject());
        assertEquals("", defaultGameObject.getName());
        assertFalse(defaultGameObject.isActive());
        assertNull(defaultGameObject.getImage());
        assertNull(defaultGameObject.getParent());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("TestType", gameObject.getTypeObject());
        assertEquals("Test_Name", gameObject.getName());
        assertTrue(gameObject.isActive());
        assertEquals(dummyImage, gameObject.getImage());
        assertEquals(dummyPanel, gameObject.getParent());
    }

    @Test
    void testCopyConstructor() {
        GameObject copiedGameObject = new GameObject(gameObject);
        assertEquals(gameObject.getTypeObject(), copiedGameObject.getTypeObject());
        assertEquals(gameObject.getName(), copiedGameObject.getName());
        assertEquals(gameObject.isActive(), copiedGameObject.isActive());
        assertEquals(gameObject.getImage(), copiedGameObject.getImage());
        assertNull(copiedGameObject.getParent()); // Parent should be null in the copy constructor
    }

    @Test
    void testGetNameParsed() {
        assertEquals("Test Name", gameObject.getNameParsed());
    }

    @Test
    void testGetNameCard_NotFieldCard() {
        assertEquals("Test Name", gameObject.getNameCard(false));
    }

    @Test
    void testGetNameCard_FieldCard_Animal() {
        // Create a dummy animal to use with GameObject
        Carnivore animalGameObject = new Carnivore();
        // Animal dummyAnimal = new DummyAnimal("Test_Animal", true, dummyImage, 150, 100);
        animalGameObject.setParent(dummyPanel); // To simulate same context as gameObject
        String nameCard = animalGameObject.getNameCard(true);

        assertEquals("HIU DARAT 0/20", nameCard);
    }

    @Test
    void testGetNameCard_FieldCard_Plant() {
        // Create a dummy plant to use with GameObject
        Plant plantGameObject = new Plant("BIJI_JAGUNG");
        // Plant dummyPlant = new Plant("BIJI_JAGUNG");
        plantGameObject.setParent(dummyPanel); // To simulate same context as gameObject
        String nameCard = plantGameObject.getNameCard(true);

        assertEquals("BIJI JAGUNG 0/3", nameCard);
    }

    @Test
    void testDeactivate() {
        gameObject.deactivate();
        assertFalse(gameObject.isActive());
    }

    @Test
    void testEquals() {
        GameObject gameObject1 = new GameObject("Type", "Name", true, dummyImage);
        GameObject gameObject2 = new GameObject("Type", "Name", false, dummyImage);
        GameObject gameObject3 = new GameObject("Type", "DifferentName", true, dummyImage);
        GameObject gameObject4 = new GameObject("Tipe", "Name", true, dummyImage);
        Player metiu = new Player();

        assertEquals(gameObject1, gameObject2);
        assertNotEquals(gameObject1, gameObject3);
        assertNotEquals(gameObject4, gameObject1);    
        assertNotEquals(gameObject1, metiu);
    }

    // DummyAnimal class
    static class DummyAnimal extends Animal {
        public DummyAnimal(String name, boolean active, Image image, int weightToHarvest, int weight) {
            super(name, active, image, false, false, false, null, weightToHarvest, weight, "DummyAnimal");
        }

        @Override
        public void eat(GameObject eatable) {
            // Dummy implementation
        }
    }

    // DummyPlant class
    static class DummyPlant extends Plant {
        public DummyPlant(String name, boolean active, Image image, int ageToHarvest, int age) {
            super(name);
        }
    }
}
