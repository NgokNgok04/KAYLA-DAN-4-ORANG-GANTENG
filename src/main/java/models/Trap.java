package models;

import icons.Icon;

public class Trap extends Item {

    public Trap() {
        super("TRAP", Icon.TRAP);
    }

    public Trap(Trap other) {
        super(other);
    }

    @Override
    public void useEffect(Affectable affectable) {
        affectable.trap();
        affectable.addItem(this);
    }

}
