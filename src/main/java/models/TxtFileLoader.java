package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public String getExtension(){
        return "TXT";
    }

    public void loadGameState(Scanner reader){
        GameManager game = GameManager.getInstance();
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
                int idx = Pair.convertTokenToPair(parsedLine[0]).getSecond();
                GameObject obj = GameContext.createObject(parsedLine[1]);
                player.addCardInDeck(obj, idx);
            }catch(GameException e){
                e.printMessage();
            }
        }
        
        int nField = Integer.parseInt(reader.nextLine());
        for(int i=0;i<nField;i++){
            String[] parsedLine = reader.nextLine().split(" ");
            Pair<Integer,Integer> pos = Pair.convertTokenToPair(parsedLine[0]);
            
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
                Item item = (Item) GameContext.createObject(parsedLine[j+4]);
                if(item instanceof Accelerate || item instanceof Delay){
                    livingObj.addItem(item);
                }else{
                    item.useEffect(livingObj);
                }
            }
            player.addCardInField(livingObj, pos);
        }
    }

    public void load(String dirpath) throws Exception{
        GameManager game = GameManager.getInstance();
        Shop shop = Shop.getInstance();
        shop.resetShop();
        if(!isValid(dirpath)){
            throw new Exception("State Files Invalid");
        }
        String filePathState = dirpath+File.separator+"gamestate.txt";
        String filePathP1 = dirpath+File.separator+"player1.txt";
        String filePathP2 = dirpath+File.separator+"player2.txt";

        try (Scanner reader = new Scanner(new File(filePathState))) {
            loadGameState(reader);
        }
        try (Scanner reader = new Scanner(new File(filePathP1))) {
            loadPlayer(game.getPlayer1(),reader);
        }
        try (Scanner reader = new Scanner(new File(filePathP2))) {
            loadPlayer(game.getPlayer2(),reader);
        }
    }

    public void saveGameState(String dirpath){
        GameManager game = GameManager.getInstance();
        Path filePath = Paths.get(dirpath, "gamestate.txt");
        File obj = filePath.toFile();
        Shop shop = Shop.getInstance();
        try{
            obj.createNewFile();
            try(FileWriter writer = new FileWriter(obj,false)){
                writer.write(game.getCurTurn()+"\n");

                int nItems = shop.getCountItemsReady();
                ArrayList<Pair<String,Integer>> items = shop.getItemsReady();

                writer.write(nItems+"\n");
                for(int i=0;i<nItems;i++){
                    writer.write(items.get(i).getFirst()+" "+items.get(i).getSecond()+"\n");
                }
            };
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void savePlayer(String dirpath,Player player,int idx){
        Path filePath = Paths.get(dirpath, "player"+idx+".txt");
        File obj = filePath.toFile();
        try{
            obj.createNewFile();
            try(FileWriter writer = new FileWriter(obj,false)){
                writer.write(player.getMoney()+"\n");
                writer.write(player.getDeckSlot()+"\n");

                List<GameObject> deck = player.getActiveDeck();
                int countDeckSize = deck.size();
                int countActive = player.getCountActiveCard();

                writer.write(countActive+"\n");
                for(int i=0;i<countDeckSize;i++){
                    if(!deck.get(i).isActive()){
                        continue;
                    }
                    char row = (char)('A'+i);
                    writer.write(row+"01 ");
                    writer.write(deck.get(i).getName()+"\n");
                } 

                int countActiveField = player.getCountActiveField();
                List<LivingThing> field = player.getField();
                int countFieldSize = field.size();
                
                writer.write(countActiveField+"\n");
                for(int i=0;i<countFieldSize;i++){
                    if(!player.getField().get(i).isActive()){
                        continue;
                    }
                    writer.write(Pair.convertPairToToken(Pair.convertIdxToPair(i))+" ");
                    writer.write(field.get(i).getName()+" ");
                    if(field.get(i) instanceof Animal animal){
                        writer.write(animal.getWeight()+" ");
                    }else{
                        Plant plant = (Plant)field.get(i);
                        writer.write(plant.getAge()+" ");
                    }

                    List<Item> items = field.get(i).getItems();
                    int countItems = items.size();
                    writer.write(countItems+" ");

                    for(int j=0;j<countItems;j++){
                        writer.write(items.get(j).getName()+" ");
                    }
                    writer.write("\n");
                }
            };
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void save(String dirpath){
        GameManager game = GameManager.getInstance();
        saveGameState(dirpath);
        savePlayer(dirpath, game.getPlayer1(),1);
        savePlayer(dirpath, game.getPlayer2(), 2);
    }
}