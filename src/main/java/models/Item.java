package models;

public class Item extends GameObject{
    private String typeItem;

    public String getTypeItem(){
        return this.typeItem;
    }

    public void setTypeItem(String typeItem){
        this.typeItem = typeItem;
    }

}
