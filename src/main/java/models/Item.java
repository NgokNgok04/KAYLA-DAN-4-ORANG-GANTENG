package models;

import java.awt.*;

public abstract class Item extends GameObject {

    public Item(String name, Image image) {
        super("ITEM", name, true, image);
    }

    public Item(Item other) {
        super(other);
    }

    public abstract void useEffect(Affectable affectable);

}
