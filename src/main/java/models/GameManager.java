package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class GameManager {
    private static GameManager instance;
    private int curTurn;
    private Player player1;
    private Player player2;
    private Map<String,FileLoader> listFileLoader;
    private final String interfaceName = "FileLoader";

    private GameManager(){
        curTurn = 1;
        player1 = new Player();
        player2 = new Player();
        listFileLoader = new HashMap<>();
        listFileLoader.put("TXT", new TxtFileLoader());
    }

    public static GameManager getInstance(){
        if(instance==null){
            instance = new GameManager();
        }
        return instance;
    }

    public FileLoader getFileLoader(String name){
        return listFileLoader.get(name);
    }
    
    public void reset() {
        curTurn = 1;
        player1 = new Player();
        player2 = new Player();
    }

    public String[] getExtensions(){
        Set<String> exts = listFileLoader.keySet();
        return exts.toArray(new String[exts.size()]);
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

    public void addPlugin(String jarDir) throws Exception {
        Path path = Paths.get(jarDir);
        String fileName = path.getFileName().toString();
        String className = fileName.substring(0, fileName.lastIndexOf('.'));
        try {
            File file = new File(jarDir);
            URL jarUrl = file.toURI().toURL();
            try (URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl})) {
                JarFile jarFile = new JarFile(jarDir);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                        String classPath = entry.getName().replace("/", ".").replace(".class", "");
                        System.out.println(classPath);
                        Class<?> loadedClass = loader.loadClass(classPath);
                        Class<?> interfaceClass = Class.forName("models.FileLoader");
                        if (!interfaceClass.isAssignableFrom(loadedClass) || loadedClass.isInterface()) {
                            continue;
                        }
                        jarFile.close();
                        Method getExt = loadedClass.getDeclaredMethod("getExtension");
                        FileLoader fileLoader = (FileLoader) loadedClass.getDeclaredConstructor().newInstance();
                        listFileLoader.put((String) getExt.invoke(fileLoader), fileLoader);
                        return;
                    }
                }
                jarFile.close();
                throw new Exception("KONTOL");
            } catch (ClassNotFoundException e) {
                throw new Exception("Class not found in the jar file: " + e.getMessage(), e);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new Exception("Failed to instantiate class from JAR file: " + e.getMessage(), e);
            } catch (IOException e) {
                throw new Exception("IO error occurred while processing the JAR file: " + e.getMessage(), e);
            }
        } catch (MalformedURLException e) {
            throw new Exception("Invalid JAR file URL: " + e.getMessage(), e);
        } catch (FileNotFoundException e) {
            throw new Exception("JAR file not found: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("An unexpected error occurred: " + e.getMessage(), e);
        }
    }
}