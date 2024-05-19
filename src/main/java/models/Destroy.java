package models;

public class Destroy extends Item{


    @Override
    public void useEffect(Affectable affectable) {
        affectable.destroy();
    }

    @Override
    public String getItemType() {
        return "DESTROY";
    }
}
