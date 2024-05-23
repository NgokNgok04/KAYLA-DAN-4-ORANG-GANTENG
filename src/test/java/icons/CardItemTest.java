package icons;

import gui.CardItem;
import gui.MainFrame;
import models.Herbivore;
import models.Plant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.InputEvent;

import javax.swing.JFrame;

import utils.Pair;

import static org.junit.jupiter.api.Assertions.*;

public class CardItemTest {
    private Robot robot;
    private JFrame frame;
    private CardItem carditem;

    @BeforeEach
    public void setUp() throws Exception {
        frame = new JFrame();
        frame.setSize(new Dimension(500, 500));
        carditem = new CardItem();
        frame.add(carditem);
        frame.pack();
        frame.setVisible(true);
        robot = new Robot();
//        robot.setAutoDelay(50);
    }

    @AfterEach
    public void tearDown() {
        frame.dispose();
    }

    @Test
    public void TestConstructor(){
        assertNull(carditem.getOwner());
        assertTrue(carditem.getField());
        assertEquals(carditem.getPosition().getFirst(), 0);
        assertEquals(carditem.getPosition().getSecond(), 0);
        assertFalse(carditem.isSwap());
        assertNull(carditem.getsParent());
        assertFalse(carditem.getObject().isActive());
    }

    @Test
    public void TestConstructor2(){
        // CardItem carditem = new CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), mf, true);
        assertNull(carditem.getOwner());
        assertEquals(carditem.getField(), CardItem.FIELD_CARD);
        assertEquals(carditem.getPosition().getFirst(), 0);
        assertEquals(carditem.getPosition().getSecond(), 0);
        assertFalse(carditem.isSwap());
    }

    @Test
    public void TestSetCardItemAndObject(){
        // CardItem cardItem = new CardItem();
        Herbivore herbivore = new Herbivore("SAPI");
        carditem.setObject(herbivore);
        assertEquals("ANIMAL", carditem.getObject().getTypeObject());
    }

    @Test
    public void TestRemoveObject(){
        // CardItem cardItem = new CardItem();
        Herbivore herbivore = new Herbivore("SAPI");
        carditem.setObject(herbivore);
        assertEquals(herbivore, carditem.getObject());
    }

    @Test
    public void TestInitPreferredSize(){
        assertEquals(carditem.getPreferredSize(), new Dimension(110,140));
        assertEquals(carditem.getMinimumSize(), new Dimension(110,140));
        assertEquals(carditem.getMaximumSize(), new Dimension(110,140));
    }

    @Test
    public void TestRefreshData(){
        carditem.setCardItem(new Plant("BIJI_JAGUNG"), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), null, true);
        carditem.getObject().setActive(false);
        assertEquals(carditem.getNameLabel().getText(), "BIJI JAGUNG");
        carditem.refreshData();
        assertEquals(carditem.getNameLabel().getText(), " ");
    }

    @Test
    public void TestDragListener() throws InterruptedException {
        Point startPoint = carditem.getLocationOnScreen();
        robot.mouseMove(startPoint.x + 10, startPoint.y + 10);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(startPoint.x + 50, startPoint.y + 50);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(5000);
        Object transferData = carditem.getClientProperty("transfer.data");
        assertNotNull(transferData);
        assertEquals(carditem.getObject(), transferData);
    }

    @Test
    public void TestClickEvent() throws InterruptedException {
        MainFrame mockParent = new MainFrame();
        mockParent.setEnabled(true);
        carditem.setCardItem(new Herbivore("SAPI"), null, CardItem.FIELD_CARD, new Pair<>(0,0), mockParent, true);
        assertTrue(mockParent.isEnabled());
        Point startPoint = carditem.getLocationOnScreen();
        robot.mouseMove(startPoint.x + 10, startPoint.y + 10);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        Thread.sleep(5000);

        assertFalse(mockParent.isEnabled());
    }


}
