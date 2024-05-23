package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import gamexception.GameException;
import utils.Pair;

public class GameManager {
    private static GameManager instance;
    private int curTurn;
    private Player player1;
    private Player player2;
    private List<FileLoader> listFileLoader;
    private final String interfaceName = "FileLoader";

    private GameManager(){
        curTurn = 1;
        player1 = new Player();
        player2 = new Player();
        listFileLoader = new ArrayList<>();
        listFileLoader.add(new TxtFileLoader());
        listFileLoader.add(new JSONFileLoader());
        listFileLoader.add(new YAMLFileLoader());
    }

    public static GameManager getInstance(){
        if(instance==null){
            instance = new GameManager();
        }
        return instance;
    }

    public FileLoader getFileLoader(String name){
        for(FileLoader fileLoader: listFileLoader){
            if(fileLoader.getClass().getName().equals(name)){
                return fileLoader;
            }
        }
        return null;
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public Player getCurPlayer(){
        if(curTurn%2==1){
            return player1;
        }
        return player2;
    }

    public Player getEnemyPlayer(){
        if(curTurn%2==0){
            return player1;
        }
        return player2;
    }

    public int getCurTurn(){
        return curTurn;
    }

    public void setCurTurn(int curTurn){
        this.curTurn = curTurn;
    }

    public void agePlants(List<LivingThing> field){
        for(LivingThing thing:field){
            if(thing instanceof Plant plant){
                plant.grow();
            }
        }
    }

    public Player next(){
        if(curTurn==40){
            return null;
        }
        curTurn++;

        agePlants(player1.getField());
        agePlants(player2.getField());
        return getCurPlayer();    
    }

    public void addPlugin(String jarDir) throws Exception{
        Path path = Paths.get(jarDir);
        String fileName = path.getFileName().toString();
        String className = fileName.substring(0,fileName.lastIndexOf('.'));
        try{
            URL jarURL = new URL("jar:file:"+jarDir+"!/");
            try (URLClassLoader loader = new URLClassLoader(new URL[]{jarURL})) {
                Class<?> loadedClass = loader.loadClass(className);
                Class<?> interfaceClass = Class.forName(interfaceName);
                if(!interfaceClass.isAssignableFrom(loadedClass)){
                    throw new Exception("Plugin Loader tidak mengimplementasi Interface FileLoader!");
                }
                listFileLoader.add((FileLoader)loadedClass.getDeclaredConstructor().newInstance());
            }catch (ClassNotFoundException e) {
                throw new Exception("Class tidak ditemukan di jar file.");
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new Exception("Gagal menginstansiasi class dari JAR file.");
            } catch (Exception e) {
                throw new Exception("File yang dipilih tidak berekstensi .jar atau terjadi kesalahan lainnya.");
            }
        } catch (MalformedURLException e) {
            throw new Exception("Invalid JAR file URL.");
        } catch (FileNotFoundException e) {
            throw new Exception("JAR file not found.");
        } catch (Exception e) {
            throw new Exception("An unexpected error occurred.");
        }
    }

    public static void main(String[] args) {
        GameManager game = GameManager.getInstance();
        // Shop shop = Shop.getInstance();
        // shop.addItem("SIRIP_HIU", 10);
        // shop.addItem("SUSU", 5);

        // Player p = game.getCurPlayer();
        // Product product = (Product)GameContext.createObject("SUSU");
        // Item item = (Item)GameContext.createObject("ACCELERATE");
        // Carnivore hiu = (Carnivore)GameContext.createObject("HIU_DARAT");
        // item.useEffect(hiu);
        // try {
        //     p.addCardInDeck(product, 1);
        //     p.addCardInField(hiu, new Pair<Integer,Integer>(0, 2));
        // } catch (GameException e) {
        //     e.printStackTrace();
        // }
        // System.out.println(hiu.getItems().size());
        // FileLoader loader = game.getFileLoader("models.TxtFileLoader");
        // try {
        //     loader.save("src/main/java/models/gamestate");
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        FileLoader loader = game.getFileLoader("models.YAMLFileLoader");
        try {
            loader.load("src/main/java/models/tes/");
            loader.save("src/main/java/models/tess/");
            // loader.load("src/main/java/models/tes/");
            // loader.load("src/main/java/models/tes/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Shop shop = Shop.getInstance();
        // List<Pair<String,Integer>> items = shop.getItemsReady();
        // System.out.println(items.size());
        // for(Pair<String,Integer> item:items){
        //     System.out.println(item.getFirst());
        //     System.out.println(item.getSecond());
        // }

        // Player p = game.getPlayer1();
        // System.out.println(p.getMoney());
        // List<GameObject> objs = p.getActiveDeck();
        // for(GameObject obj:objs){
        //     if(!obj.isActive()){
        //         continue;
        //     }
        //     System.out.println(obj.getName());
        // }
        // List<LivingThing> livs = p.getField();
        // for(LivingThing liv:livs){
        //     if(!liv.isActive()){
        //         continue;
        //     }
        //     System.out.println(liv.getName());
        //     System.out.println(liv.getName());
        //     System.out.println(((Animal)liv).getWeight());
        //     System.out.println(liv.getItems().size());
        //     for(Item item:liv.getItems()){
        //         System.out.println(item.getName());
        //     }
        // }

        // Player p2 = game.getPlayer2();
        // List<LivingThing> livsS = p2.getField();
        // List<GameObject> ob = p2.getActiveDeck();
        // for(GameObject obj:ob){
        //     if(!obj.isActive()){
        //         continue;
        //     }
        //     System.out.println(obj.getName());
        // }
        
        // for(int i=0;i<livsS.size();i++){
        //     if(!livsS.get(i).isActive()){
        //         continue;
        //     }
        //     System.out.println(livsS.get(i).getName());
        //     System.out.println(Pair.convertPairToToken(Pair.convertIdxToPair(i)));
        //     System.out.println(livsS.get(i).getItems().size());
        //     for(Item item:livsS.get(i).getItems()){
        //         System.out.println(item.getName());
        //     }
        // }
    }
}