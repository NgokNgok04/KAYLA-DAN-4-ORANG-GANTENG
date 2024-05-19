package models;

import icons.Icon;

public class Protect extends Item {

    public Protect() {
        super("PROTECT", Icon.PROTECT);
    }

    public Protect(Protect other) {
        super(other);
    }

    @Override
    public void useEffect(Affectable affectable) {
        affectable.protect();
        affectable.addItem(this);
    }
}
