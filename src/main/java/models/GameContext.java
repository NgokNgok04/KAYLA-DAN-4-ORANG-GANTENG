package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class GameContext {

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
        List<String> listObjectName = new ArrayList<>(OBJECT_TYPE.keySet());
        do{
            randomGM = random.nextInt(OBJECT_TYPE.size());
        }while(listObjectName.get(randomGM)=="BERUANG"||listObjectName.get(randomGM)=="DAGING_BERUANG");

        return createObject(listObjectName.get(randomGM));
    }
}
