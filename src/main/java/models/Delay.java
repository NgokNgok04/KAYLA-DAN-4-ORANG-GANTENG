package models;

import icons.Icon;

public class Delay extends Item {

    public Delay() {
        super("DELAY", Icon.DELAY);
    }

    public Delay(Delay other) {
        super(other);
    }

    @Override
    public void useEffect(Affectable affectable) {
        affectable.delay();
        affectable.addItem(this);
    }
}
