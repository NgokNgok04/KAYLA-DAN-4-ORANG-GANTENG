// package models;

// import gamexception.GameException;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import utils.Pair;

// import java.io.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;

// public class TxtFileLoaderTest {

//     private GameManager gameManager;
//     private TxtFileLoader txtFileLoader;

//     @BeforeEach
//     public void setUp() {
//         gameManager = GameManager.getInstance();
//         txtFileLoader = new TxtFileLoader();
//     }

//     @AfterEach
//     public void tearDown() {
//         gameManager.reset();
//     }

//     @Test
//     public void testSaveAndLoadPlayer1() throws IOException, GameException {
//         // Prepare the player data to save
//         Player player1 = gameManager.getPlayer1();
//         player1.setMoney(100);
//         player1.setDeckSlot(2);
//         player1.addCardInDeck(new Carnivore(), 0);
//         player1.addCardInField(new Carnivore(), new Pair<>(0, 0));

//         // Create a temporary directory to save to
//         Path tempDir = Files.createTempDirectory("testDir");

//         // Save the player data
//         txtFileLoader.savePlayer(tempDir.toString(), player1, 1);

//         // Verify the saved player data
//         File playerFile1 = new File(tempDir.toFile(), "player1.txt");
//         assertTrue(playerFile1.exists());

//         // Reset the game manager to simulate loading
//         gameManager.reset();

//         // Load the player data
//         try (Scanner reader = new Scanner(new File(playerFile1.getAbsolutePath()))) {
//             txtFileLoader.loadPlayer(gameManager.getPlayer1(), reader);
//         }

//         // Verify the loaded player data
//         Player loadedPlayer1 = gameManager.getPlayer1();
//         assertEquals(100, loadedPlayer1.getMoney());
//         assertEquals(2, loadedPlayer1.getDeckSlot());
//         assertEquals(1, loadedPlayer1.getCountActiveCard());
//         // assertNotNull(loadedPlayer1.getActiveDeckItem(0));
//         assertEquals("Carnivore", loadedPlayer1.getActiveDeckItem(0).getName());
//         assertTrue(loadedPlayer1.getActiveDeckItem(0) instanceof Carnivore);
//         assertEquals(1, loadedPlayer1.getCountActiveField());
//         // assertNotNull(loadedPlayer1.getFieldItem(0));
//         assertEquals("Carnivore", loadedPlayer1.getFieldItem(0).getName());
//         assertTrue(loadedPlayer1.getFieldItem(0) instanceof Carnivore);

//         // Clean up
//         Files.deleteIfExists(playerFile1.toPath());
//         Files.deleteIfExists(tempDir);
//     }

//     @Test
//     public void testSaveAndLoadPlayer2() throws IOException, GameException {
//         // Prepare the player data to save
//         Player player2 = gameManager.getPlayer2();
//         player2.setMoney(200);
//         player2.setDeckSlot(3);
//         player2.addCardInDeck(new Carnivore(), 1);
//         player2.addCardInField(new Carnivore(), new Pair<>(0, 0));

//         // Create a temporary directory to save to
//         Path tempDir = Files.createTempDirectory("testDir");

//         // Save the player data
//         txtFileLoader.savePlayer(tempDir.toString(), player2, 2);

//         // Verify the saved player data
//         File playerFile2 = new File(tempDir.toFile(), "player2.txt");
//         assertTrue(playerFile2.exists());

//         // Reset the game manager to simulate loading
//         gameManager.reset();

//         // Load the player data
//         try (Scanner reader = new Scanner(new File(playerFile2.getAbsolutePath()))) {
//             txtFileLoader.loadPlayer(gameManager.getPlayer2(), reader);
//         }

//         // Verify the loaded player data
//         Player loadedPlayer2 = gameManager.getPlayer2();
//         assertEquals(200, loadedPlayer2.getMoney());
//         assertEquals(3, loadedPlayer2.getDeckSlot());
//         assertEquals(1, loadedPlayer2.getCountActiveCard());
//         // assertNotNull(loadedPlayer2.getActiveDeckItem(1));
//         assertEquals("Herbivore", loadedPlayer2.getActiveDeckItem(1).getName());
//         assertTrue(loadedPlayer2.getActiveDeckItem(1) instanceof Herbivore);
//         assertEquals(1, loadedPlayer2.getCountActiveField());
//         // assertNotNull(loadedPlayer2.getFieldItem(0));
//         assertEquals("Herbivore", loadedPlayer2.getFieldItem(0).getName());
//         assertTrue(loadedPlayer2.getFieldItem(0) instanceof Herbivore);

//         // Clean up
//         Files.deleteIfExists(playerFile2.toPath());
//         Files.deleteIfExists(tempDir);
//     }

//     @Test
//     void testSaveAndLoadFullGame() {
//         // Initialize player 1 and player 2
//         Player player1 = gameManager.getPlayer1();
//         Player player2 = gameManager.getPlayer2();

//         // Setup some initial state for player 1
//         player1.setMoney(100);
//         player1.setDeckSlot(40);

//         // Add some cards to player 1's active deck and field
//         List<GameObject> player1ActiveDeck = new ArrayList<>();
//         player1ActiveDeck.add(new Protect());
//         player1ActiveDeck.add(new Protect());
//         player1ActiveDeck.add(new Protect());
//         player1ActiveDeck.add(new Protect());
//         player1ActiveDeck.add(new Protect());
//         player1.setActiveDeck(player1ActiveDeck);

//         List<LivingThing> player1Field = new ArrayList<>();
//         player1Field.add(new Carnivore());
//         player1Field.add(new Carnivore());
//         player1Field.add(new Plant("BIJI_JAGUNG"));
//         player1.setField(player1Field);

//         // Setup some initial state for player 2
//         player2.setMoney(200);
//         player2.setDeckSlot(40);

//         // Add some cards to player 2's active deck and field
//         List<GameObject> player2ActiveDeck = new ArrayList<>();
//         player2ActiveDeck.add(new Protect());
//         player2ActiveDeck.add(new Protect());
//         player2ActiveDeck.add(new Protect());
//         player2ActiveDeck.add(new Protect());
//         player2ActiveDeck.add(new Protect());
//         player2.setActiveDeck(player2ActiveDeck);

//         List<LivingThing> player2Field = new ArrayList<>();
//         player2Field.add(new Plant("BIJI_JAGUNG"));
//         player2Field.add(new Plant("BIJI_LABU"));
//         player2Field.add(new Carnivore());
//         player2.setField(player2Field);

//         // Save game state
//         txtFileLoader.save(tempDir.toString());

//         // Reset the game manager and players to simulate a fresh game
//         gameManager.reset();

//         // Load the saved game state
//         try {
//             txtFileLoader.load(tempDir.toString());
//         } catch (Exception e) {
//             fail("Exception thrown while loading game state: " + e.getMessage());
//         }

//         // Assert the game state after loading
//         assertEquals(100, player1.getMoney());
//         assertEquals(40, player1.getDeckSlot());
//         assertEquals(5, player1.getCountActiveCard());
//         assertEquals(3, player1.getCountActiveField());

//         assertEquals(200, player2.getMoney());
//         assertEquals(40, player2.getDeckSlot());
//         assertEquals(5, player2.getCountActiveCard());
//         assertEquals(3, player2.getCountActiveField());
//     }
// }
