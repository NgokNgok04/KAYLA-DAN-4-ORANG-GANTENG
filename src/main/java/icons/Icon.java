package icons;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Icon {

    public static Image ACCELERATE = getImage("accelerate.png");
    public static Image BEAR = getImage("bear.png");
    public static Image BEAR_MEAT = getImage("bear_meat.png");
    public static Image CHICKEN = getImage("chicken.png");
    public static Image CORN = getImage("corn.png");
    public static Image CORN_SEED = getImage("corn_seed.png");
    public static Image COW = getImage("cow.png");
    public static Image DELAY = getImage("delay.png");
    public static Image DESTROY = getImage("destroy.png");
    public static Image EGG = getImage("egg.png");
    public static Image HORSE = getImage("horse.png");
    public static Image HORSE_MEAT = getImage("horse_meat.png");
    public static Image INSTANT_HARVEST = getImage("instant_harvest.png");
    public static Image MILK = getImage("milk.png");
    public static Image PROTECT = getImage("protect.png");
    public static Image PUMPKIN = getImage("pumpkin.png");
    public static Image PUMPKIN_SEED = getImage("pumpkin_seed.png");
    public static Image SHARK = getImage("shark.png");
    public static Image SHARK_FIN = getImage("shark_fin.png");
    public static Image SHEEP = getImage("sheep.png");
    public static Image SHEEP_MEAT = getImage("sheep_meat.png");
    public static Image STRAWBERRY = getImage("strawberry.png");
    public static Image STRAWBERRY_SEED = getImage("strawberry_seed.png");
    public static Image TRAP = getImage("trap.png");
    public static Image PLAYER1 = getImage("player1.jpg");
    public static Image PLAYER2 = getImage("player2.jpg");
    public static Image NOTHING = getImage("nothing.png");

    private static Image getImage(String path) {
        try {
            return ImageIO.read(Objects.requireNonNull(Icon.class.getClassLoader().getResourceAsStream("icons/card_" + path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    };
}
