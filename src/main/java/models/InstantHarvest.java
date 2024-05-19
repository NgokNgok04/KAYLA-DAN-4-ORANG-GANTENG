package models;

public class InstantHarvest extends Item{

    @Override
    public void useEffect(Affectable affectable) {
        affectable.instantHarvest();
        affectable.addItem(this);
    }

    @Override
    public String getItemType() {
        return "INSTANT_HARVEST";
    }
}
