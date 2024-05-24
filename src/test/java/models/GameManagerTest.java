package models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import models.GameManager;
import models.Plant;

public class GameManagerTest {
    private GameManager game;

    @BeforeEach
    public void setUp(){
        game = GameManager.getInstance();
        game.reset();
    }

    @Test
    public void TestGetInstance(){
        assertEquals(game.getCurTurn(), 1);
        assertNotNull(game.getPlayer1());
        assertNotNull(game.getPlayer2());
        assertNotNull(game.getFileLoader("TXT"));
    }

    @Test
    public void testSingletonInstance() {
        GameManager anotherInstance = GameManager.getInstance();
        assertSame(game, anotherInstance);
    }

    @Test
    public void TestGetFileLoader(){
        assertNotNull(game.getFileLoader("TXT").getClass());
    }

    @Test
    public void TestReset(){
        game.setCurTurn(4);
        assertEquals(game.getCurTurn(), 4);
        game.reset();
        assertEquals(game.getCurTurn(), 1);
        assertEquals(game.getPlayer1().getMoney(), 0);
        assertEquals(game.getPlayer2().getMoney(), 0);
    }

    @Test
    public void TestGetExtensions(){
        String[] extensions = game.getExtensions();
        assertEquals(1, extensions.length);
        assertEquals("TXT", extensions[0]);
    }

    @Test
    public void TestGetPlayer1(){
        game.setCurTurn(1);
        assertEquals(game.getPlayer1(), game.getCurPlayer());
    }

    @Test
    public void TestGetPlayer2(){
        game.setCurTurn(2);
        assertEquals(game.getPlayer2(), game.getCurPlayer());
    }

    @Test
    public void TestGetCurPlayer(){
        game.setCurTurn(1);
        assertEquals(game.getPlayer1(), game.getCurPlayer());
    }

    @Test
    public void TestGetEnemyPlayer(){
        game.setCurTurn(1);
        assertEquals(game.getPlayer2(), game.getEnemyPlayer());
    }

    @Test
    public void TestGetSetCurTurn(){
        game.setCurTurn(1);
        assertEquals(game.getCurTurn(), 1);
        game.setCurTurn(5);
        assertNotEquals(game.getCurTurn(), 1);
    }

    @Test
    public void TestAgePlants(){
        Player player1 = game.getPlayer1();
        player1.setFieldElement(new Plant("BIJI_STROBERI"), 0);
        game.agePlants(player1.getField());
        assertTrue(((Plant) player1.getFieldItem(0)).getAge() > 0);
    }

    @Test
    public void testNext() {
        game.setCurTurn(1);
        Player initialPlayer = game.getCurPlayer();
        Player nextPlayer = game.next();
        assertNotSame(initialPlayer, nextPlayer);
        assertEquals(2, game.getCurTurn());

        game.setCurTurn(20);
        assertNull(game.next());
    }

    @Test
    public void testAddPlugin() {
        try {
            String jarPath = "dist/YAMLFileLoader.jar";
            File testJar = new File(jarPath);
            if (testJar.exists()) {
                game.addPlugin(jarPath);
                // assertNotNull(game.getFileLoader("JSON"));
            } 
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception should not be thrown while adding a plugin: " + e.getMessage());
        }
    }
}