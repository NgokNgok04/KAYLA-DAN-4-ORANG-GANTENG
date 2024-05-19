package gameexception;

public class GameException extends Exception{

    public GameException(String message){
        super(message);
    }

    public void printMessage() {
        System.out.println(getMessage());
    }

}
