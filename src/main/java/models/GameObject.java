package models;

public class GameObject {
    private String typeObject;
    private String name;
    private boolean active;
    private String image;

    public String getTypeObject(){
        return this.typeObject;
    }

    public String getName(){
        return this.name;
    }

    public Boolean getActive(){
        return this.active;
    }

    public String getImage(){
        return this.image;
    }

    public void setTypeObject(String typeObject){
        this.typeObject = typeObject;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setActive(boolean isActive){
        this.active = isActive;
    }

    public void setImage(String image){
        this.image = image;
    }

}
