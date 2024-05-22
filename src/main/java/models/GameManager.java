package models;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private int curPlayer;
    private int curTurn;
    private Player player1;
    private Player player2;
    private List<FileLoader> listFileLoader;
    private final String interfaceName = "FileLoader";

    public GameManager(Player p1, Player p2){
        curTurn = 1;
        curPlayer = 1;
        player1 = p1;
        player2 = p2;
        listFileLoader = new ArrayList<>();
    }

    public Player getPlayer1(){
        return player1;
    }

    public Player getPlayer2(){
        return player2;
    }

    public Player getCurPlayer(){
        if(curPlayer==1){
            return player1;
        }
        return player2;
    }

    public Player getEnemyPlayer(){
        if(curPlayer==1){
            return player2;
        }
        return player1;
    }

    public void setCurTurn(int curTurn){
        this.curTurn = curTurn;
    }

    public void updateCurPlayer(){
        if(curPlayer==1){
            curPlayer++;
        }else{
            curPlayer = 1;
        }
    }

    public void agePlants(List<LivingThing> field){
        for(LivingThing thing:field){
            if(thing instanceof Plant plant){
                plant.grow();
            }
        }
    }

    public Player next(){
        if(curPlayer==2){
            curTurn++;
        }

        if(curTurn==10){
            return null;
        }

        agePlants(player1.getField());
        agePlants(player2.getField());
        updateCurPlayer();
        return getCurPlayer();    
    }

    public void addPlugin(String jarDir){
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
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
/*
 * 1. Minta field sndiri sm lawan
 * 2. minta active deck
 * 3. swap element
 * 4. harvest
 * 5. update barang shop
 * 6. beli, barang lgsg di active deck
 * 7. keranjang di shop
 * 8. Shuffle, cek dek
 */