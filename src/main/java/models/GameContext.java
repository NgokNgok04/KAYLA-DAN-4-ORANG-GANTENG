package models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class GameContext {

    private static List<String> LIST_GAME_OBJECTS = new ArrayList<String>();
    public static Map<String, String> OBJECT_TYPE = new HashMap<>();

    static {
        OBJECT_TYPE.put("HIU_DARAT", "models.Carnivore");
        OBJECT_TYPE.put("SAPI", "models.Herbivore");
        OBJECT_TYPE.put("DOMBA", "models.Herbivore");
        OBJECT_TYPE.put("KUDA", "models.Herbivore");
        OBJECT_TYPE.put("AYAM", "models.Omnivore");
        OBJECT_TYPE.put("BERUANG", "models.Omnivore");
        OBJECT_TYPE.put("BIJI_JAGUNG", "models.Plant");
        OBJECT_TYPE.put("BIJI_LABU", "models.Plant");
        OBJECT_TYPE.put("BIJI_STROBERI", "models.Plant");
        OBJECT_TYPE.put("SIRIP_HIU", "models.Product");
        OBJECT_TYPE.put("SUSU", "models.Product");
        OBJECT_TYPE.put("DAGING_DOMBA", "models.Product");
        OBJECT_TYPE.put("DAGING_KUDA", "models.Product");
        OBJECT_TYPE.put("TELUR", "models.Product");
        OBJECT_TYPE.put("DAGING_BERUANG", "models.Product");
        OBJECT_TYPE.put("JAGUNG", "models.Product");
        OBJECT_TYPE.put("LABU", "models.Product");
        OBJECT_TYPE.put("STROBERI", "models.Product");
        OBJECT_TYPE.put("ACCELERATE", "models.Accelerate");
        OBJECT_TYPE.put("DELAY", "models.Delay");
        OBJECT_TYPE.put("INSTANT_HARVEST", "models.InstantHarvest");
        OBJECT_TYPE.put("DESTROY", "models.Destroy");
        OBJECT_TYPE.put("PROTECT", "models.Protect");
        OBJECT_TYPE.put("TRAP", "models.Trap");
    }
    static {
        LIST_GAME_OBJECTS.add("HIU_DARAT");
        LIST_GAME_OBJECTS.add("SAPI");
        LIST_GAME_OBJECTS.add("DOMBA");
        LIST_GAME_OBJECTS.add("KUDA");
        LIST_GAME_OBJECTS.add("AYAM");
        LIST_GAME_OBJECTS.add("BERUANG");
        LIST_GAME_OBJECTS.add("BIJI_JAGUNG");
        LIST_GAME_OBJECTS.add("BIJI_LABU");
        LIST_GAME_OBJECTS.add("BIJI_STROBERI");
        LIST_GAME_OBJECTS.add("SIRIP_HIU");
        LIST_GAME_OBJECTS.add("SUSU");
        LIST_GAME_OBJECTS.add("DAGING_DOMBA");
        LIST_GAME_OBJECTS.add("DAGING_KUDA");
        LIST_GAME_OBJECTS.add("TELUR");
        LIST_GAME_OBJECTS.add("DAGING_BERUANG");
        LIST_GAME_OBJECTS.add("JAGUNG");
        LIST_GAME_OBJECTS.add("LABU");
        LIST_GAME_OBJECTS.add("STROBERI");
        LIST_GAME_OBJECTS.add("ACCELERATE");
        LIST_GAME_OBJECTS.add("DELAY");
        LIST_GAME_OBJECTS.add("INSTANT_HARVEST");
        LIST_GAME_OBJECTS.add("DESTROY");
        LIST_GAME_OBJECTS.add("PROTECT");
        LIST_GAME_OBJECTS.add("TRAP");
    }

    public static GameObject createObject(String name){
        try{
            String className = OBJECT_TYPE.get(name);
            Class<?> classObj = Class.forName(className);
            if(className.equals("models.Carnivore")){
                return (GameObject)classObj.getDeclaredConstructor().newInstance(); 
            }
            if(classObj.getSuperclass().getName().equals("models.Item")){
                return (GameObject)classObj.getDeclaredConstructor().newInstance(); 
            }
            return (GameObject)classObj.getDeclaredConstructor(String.class).newInstance(name); 
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static GameObject randomGameObject(){
        Random random = new Random();
        int randomGM;
        do{
            randomGM = random.nextInt(LIST_GAME_OBJECTS.size());
        }while(LIST_GAME_OBJECTS.get(randomGM)=="BERUANG");

        return createObject(LIST_GAME_OBJECTS.get(randomGM));
    }
    public static void main(String[] args) {
        GameObject tes = createObject("ACCELERATE");
        System.out.println(tes.getTypeObject());
    }
}
