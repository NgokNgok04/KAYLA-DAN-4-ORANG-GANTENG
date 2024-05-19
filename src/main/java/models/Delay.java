package models;

public class Delay extends Item {

    @Override
    public void useEffect(Affectable affectable) {
        affectable.delay();
        affectable.addItem(this);
    }

    @Override
    public String getItemType() {
        return "DELAY";
    }
}
