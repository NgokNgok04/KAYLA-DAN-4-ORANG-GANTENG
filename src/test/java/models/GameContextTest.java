// package models;

// import org.junit.jupiter.api.Test;

// import java.util.Set;
// import java.util.stream.Collectors;

// import static org.junit.jupiter.api.Assertions.*;

// public class GameContextTest {

//     @Test
//     public void testGetGameObject() {
//         assertTrue(GameContext.getGameObject("HIU_DARAT") instanceof Carnivore, "");
//         assertTrue(GameContext.getGameObject("SAPI") instanceof Herbivore, "");
//         assertTrue(GameContext.getGameObject("DOMBA") instanceof Herbivore, "");
//         assertTrue(GameContext.getGameObject("KUDA") instanceof Herbivore, "");
//         assertTrue(GameContext.getGameObject("AYAM") instanceof Omnivore, "");
//         assertTrue(GameContext.getGameObject("BERUANG") instanceof Omnivore, "");
//         assertTrue(GameContext.getGameObject("BIJI_JAGUNG") instanceof Plant, "");
//         assertTrue(GameContext.getGameObject("BIJI_LABU") instanceof Plant, "");
//         assertTrue(GameContext.getGameObject("BIJI_STROBERI") instanceof Plant, "");
//         assertTrue(GameContext.getGameObject("SIRIP_HIU") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("SUSU") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("DAGING_DOMBA") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("DAGING_KUDA") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("TELUR") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("DAGING_BERUANG") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("JAGUNG") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("LABU") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("STROBERI") instanceof Product, "");
//         assertTrue(GameContext.getGameObject("ACCELERATE") instanceof Accelerate, "");
//         assertTrue(GameContext.getGameObject("DELAY") instanceof Delay, "");
//         assertTrue(GameContext.getGameObject("INSTANT_HARVEST") instanceof InstantHarvest, "");
//         assertTrue(GameContext.getGameObject("DESTROY") instanceof Destroy, "");
//         assertTrue(GameContext.getGameObject("PROTECT") instanceof Protect, "");
//         assertTrue(GameContext.getGameObject("TRAP") instanceof Trap, "");
//     }

//     @Test
//     public void testRandomGameObject() {
//         Set<String> gameObjectNames = GameContext.LIST_GAME_OBJECTS.stream().collect(Collectors.toSet());

//         for (int i = 0; i < 100; i++) {
//             GameObject randomObject = GameContext.randomGameObject();
//             assertNotNull(randomObject, "randomGameObject should not return null");
//             assertTrue(gameObjectNames.contains(randomObject.getName()), "randomGameObject should return a valid GameObject");
//         }
//     }
// }
