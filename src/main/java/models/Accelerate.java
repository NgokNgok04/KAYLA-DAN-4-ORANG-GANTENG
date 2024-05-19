package models;

public class Accelerate extends Item{

    @Override
    public void useEffect(Affectable affectable) {
        affectable.accelerate();
        affectable.addItem(this);
    }

    @Override
    public String getItemType() {
        return "ACCELERATE";
    }
}
