package gamexception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class GameExceptionTest {

    @Test
    void testConstructor() {
        String errorMessage = "ERROR MESSAGE";
        GameException exception = new GameException(errorMessage);
        
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testPrintMessage() {
        String errorMessage = "ERROR MESSAGE";
        GameException exception = new GameException(errorMessage);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        exception.printMessage();

        System.setOut(System.out);

        assertEquals(errorMessage + System.lineSeparator(), outContent.toString());
    }
}
