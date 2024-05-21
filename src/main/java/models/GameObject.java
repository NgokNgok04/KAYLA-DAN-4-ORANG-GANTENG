package models;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.swing.JPanel;

public class GameObject {

    private String typeObject;
    private String name;
    private boolean active;
    private Image image;
    private JPanel parent;

    public static final int CARNIVORE = 1;
    public static final int HERBIVORE = 2;
    public static final int OMNIVORE = 3;
    public static final int PLANT = 4;
    public static final int PRODUCT = 5;
    public static final int ACCELERATE = 6;
    public static final int DELAY = 7;
    public static final int INSTANT_HARVEST = 8;
    public static final int DESTROY = 9;
    public static final int PROTECT = 10;
    public static final int TRAP = 11;

    public static Map<String, Integer> OBJECT_TYPE = new HashMap<>();

    static {
        OBJECT_TYPE.put("HIU_DARAT", CARNIVORE);
        OBJECT_TYPE.put("SAPI", HERBIVORE);
        OBJECT_TYPE.put("DOMBA", HERBIVORE);
        OBJECT_TYPE.put("KUDA", HERBIVORE);
        OBJECT_TYPE.put("AYAM", OMNIVORE);
        OBJECT_TYPE.put("BERUANG", OMNIVORE);
        OBJECT_TYPE.put("BIJI_JAGUNG", PLANT);
        OBJECT_TYPE.put("BIJI_LABU", PLANT);
        OBJECT_TYPE.put("BIJI_STROBERI", PLANT);
        OBJECT_TYPE.put("SIRIP_HIU", PRODUCT);
        OBJECT_TYPE.put("SUSU", PRODUCT);
        OBJECT_TYPE.put("DAGING_DOMBA", PRODUCT);
        OBJECT_TYPE.put("DAGING_KUDA", PRODUCT);
        OBJECT_TYPE.put("TELUR", PRODUCT);
        OBJECT_TYPE.put("DAGING_BERUANG", PRODUCT);
        OBJECT_TYPE.put("JAGUNG", PRODUCT);
        OBJECT_TYPE.put("LABU", PRODUCT);
        OBJECT_TYPE.put("STROBERI", PRODUCT);
        OBJECT_TYPE.put("ACCELERATE", ACCELERATE);
        OBJECT_TYPE.put("DELAY", DELAY);
        OBJECT_TYPE.put("INSTANT_HARVEST", INSTANT_HARVEST);
        OBJECT_TYPE.put("DESTROY", DESTROY);
        OBJECT_TYPE.put("PROTECT", PROTECT);
        OBJECT_TYPE.put("TRAP", TRAP);
    }

    public GameObject() {
        typeObject = "";
        name = "";
        active = false;
        image = null;
        parent = null;
    }

    public GameObject(String typeObject, String name, boolean active, Image image) {
        this.typeObject = typeObject;
        this.name = name;
        this.active = active;
        this.image = image;
        this.parent = null;
    }

    public GameObject(GameObject other) {
        this.typeObject = other.typeObject;
        this.name = other.name;
        this.active = other.active;
        this.image = other.image;
        this.parent = null;
    }

    public String getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(String typeObject) {
        this.typeObject = typeObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    public JPanel getParent() {
        return parent;
    }
    
    public void setParent(JPanel parent) {
        this.parent = parent;
    }

    public String getNameParsed() {
        return name.replace("_", " ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameObject that)) return false;
        return Objects.equals(typeObject, that.typeObject) && Objects.equals(name, that.name);
    }
}
