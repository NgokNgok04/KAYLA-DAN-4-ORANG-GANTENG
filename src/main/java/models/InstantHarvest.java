package models;

import icons.*;

public class InstantHarvest extends Item{

    public InstantHarvest() {
        super("INSTANT_HARVEST", Icon.INSTANT_HARVEST);
    }

    public InstantHarvest(InstantHarvest other) {
        super(other);
    }

    @Override
    public void useEffect(Affectable affectable) {
        affectable.instantHarvest();
        affectable.addItem(this);
    }

}
