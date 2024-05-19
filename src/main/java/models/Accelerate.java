package models;

import icons.*;

public class Accelerate extends Item{

    public Accelerate() {
        super("ACCELERATE", Icon.ACCELERATE);
    }

    public Accelerate(Accelerate other) {
        super(other);
    }

    @Override
    public void useEffect(Affectable affectable) {
        affectable.accelerate();
        affectable.addItem(this);
    }

}
