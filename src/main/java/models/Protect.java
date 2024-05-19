package models;

public class Protect extends Item {

    @Override
    public void useEffect(Affectable affectable) {
        affectable.protect();
        affectable.addItem(this);
    }

    @Override
    public String getItemType() {
        return "PROTECT";
    }
}
