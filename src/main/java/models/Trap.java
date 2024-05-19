package models;

public class Trap extends Item {

    @Override
    public void useEffect(Affectable affectable) {
        affectable.trap();
        affectable.addItem(this);
    }

    @Override
    public String getItemType() {
        return "TRAP";
    }
}
