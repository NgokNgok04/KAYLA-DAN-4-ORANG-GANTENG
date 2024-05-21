package models;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import gamexception.GameException;
import utils.Pair;

public class TxtFileLoader implements FileLoader{
    public Boolean isValid(String dirpath){
        Path PathObjDir = Paths.get(dirpath);
        try{
            long fileCount = Files.list(PathObjDir).filter(Files::isRegularFile).count();
            if(fileCount<3){
                return false;
            }

            List<String> files = Files.list(PathObjDir).filter(Files::isRegularFile)
                                .map(path -> path.getFileName().toString())
                                .collect(Collectors.toList());

            Boolean existPlayer1 = files.contains("player1.txt");
            Boolean existPlayer2 = files.contains("player2.txt");
            Boolean existGamestate = files.contains("gamestate.txt");
            return existGamestate && existPlayer1 && existPlayer2;
        }catch(Exception e){
            return false;
        }
    }

    public void loadGameState(GameManager game, Scanner reader){
        game.setCurTurn(Integer.parseInt(reader.nextLine()));
        Shop shop = Shop.getInstance();

        int countItems = Integer.parseInt(reader.nextLine());
        for(int i=0;i<countItems;i++){
            String item = reader.nextLine();
            String[] parsedItem = item.split(" ");
            shop.setQuantity(parsedItem[0], Integer.parseInt(parsedItem[1]));
        }
    }

    public void loadPlayer(Player player,Scanner reader){
        player.resetPlayer();
        player.setMoney(Integer.parseInt(reader.nextLine()));   
        player.setDeckSlot(Integer.parseInt(reader.nextLine()));
        
        int nActive = Integer.parseInt(reader.nextLine());
        for(int i=0;i<nActive;i++){
            String[] parsedLine = reader.nextLine().split(" ");
            try{
                GameObject obj = GameContext.createObject(parsedLine[1]);
                player.addCardInDeck(obj, i);
            }catch(GameException e){
                e.printMessage();
            }
        }
        
        int nField = Integer.parseInt(reader.nextLine());
        for(int i=0;i<nField;i++){
            String[] parsedLine = reader.nextLine().split(" ");
            int idx = Pair.convertTokenToPair(parsedLine[0]).convertPairToIdx();
            
            GameObject obj = GameContext.createObject(parsedLine[1]);
            LivingThing livingObj = (LivingThing)obj;

            if(livingObj instanceof Animal animal){
                animal.setWeight(Integer.parseInt(parsedLine[2]));
            }else{
                Plant plant = (Plant)obj;
                plant.setAge(Integer.parseInt(parsedLine[2]));
            }

            int nItems = Integer.parseInt(parsedLine[3]);
            for(int j=0;j<nItems;j++){
                Item item = (Item) GameContext.createObject(parsedLine[j+3]);
                livingObj.addItem(item);
            }
        }

    }

    public void load(String dirpath,GameManager game) throws Exception{
        if(!isValid(dirpath)){
            throw new Exception("State Files Invalid");
        }
        File fileObj = new File(dirpath);
        try (Scanner reader = new Scanner(fileObj,"gamestate.txt")) {
            loadGameState(game,reader);
        }
        try (Scanner reader = new Scanner(fileObj,"player1.txt")) {
            loadPlayer(game.getPlayer1(),reader);
        }
        try (Scanner reader = new Scanner(fileObj,"gamestate.txt")) {
            loadPlayer(game.getPlayer2(),reader);
        }
    }

    public void save(String dirpath,GameManager game){
        
    }
}