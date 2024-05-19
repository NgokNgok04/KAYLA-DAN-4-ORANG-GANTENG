package models;

import icons.Icon;

public class Destroy extends Item{

    public Destroy() {
        super("DESTROY", Icon.DESTROY);
    }

    public Destroy(Destroy other) {
        super(other);
    }

    @Override
    public void useEffect(Affectable affectable) {
        affectable.destroy();
    }
}
