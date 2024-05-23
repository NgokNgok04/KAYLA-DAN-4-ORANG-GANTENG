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
//        listFileLoader.add(new JSONFileLoader());
//        listFileLoader.add(new YAMLFileLoader());
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
    
    public void reset() {
        curTurn = 1;
        player1 = new Player();
        player2 = new Player();
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
        if(curTurn==20){
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
            File jarFile = new File(jarDir);
            URL jarUrl = jarFile.toURI().toURL();
            try (URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl})) {
                Class<?> loadedClass = loader.loadClass("models."+className);
                Class<?> interfaceClass = Class.forName("models."+interfaceName);
                if(!interfaceClass.isAssignableFrom(loadedClass)){
                    System.out.println("KONTOLLLLLL");
                    throw new Exception("Plugin Loader tidak mengimplementasi Interface FileLoader!");
                } else {
                    System.out.println("KONTOLLLLLLasdasdasdasd");
                }
                listFileLoader.add((FileLoader)loadedClass.getDeclaredConstructor().newInstance());
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
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
            e.printStackTrace();
            throw new Exception("An unexpected error occurred.");
        }
    }
}