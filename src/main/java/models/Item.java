package models;

public abstract class Item extends GameObject {

    public abstract void useEffect(Affectable affectable);
    public abstract String getItemType();

}
