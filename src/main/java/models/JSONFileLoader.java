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

public class JSONFileLoader implements FileLoader{
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

            Boolean existPlayer1 = files.contains("player1.json");
            Boolean existPlayer2 = files.contains("player2.json");
            Boolean existGamestate = files.contains("gamestate.json");
            return existGamestate && existPlayer1 && existPlayer2;
        }catch(Exception e){
            return false;
        }
    }
    public static void printStringDetails(String str) {
        System.out.println("String: '" + str + "' Length: " + str.length());
        for (int i = 0; i < str.length(); i++) {
            System.out.println("Character at index " + i + ": '" + str.charAt(i) + "' Unicode: " + (int) str.charAt(i));
        }
    }

    public String[] getNonBlank(String[] parsed) {
        List<String> validParsed = new ArrayList<>();
        for (String str : parsed) {
            if (str != null && !str.isEmpty()) {
                validParsed.add(str);
            }
        }
        return validParsed.toArray(new String[0]);
    }

    public void loadGameState(Scanner reader){
        GameManager game = GameManager.getInstance();
        Shop shop = Shop.getInstance();
        reader.nextLine();
        for(int i=0;i<2;i++){
            String[] parsed = getNonBlank(reader.nextLine().split("[,:\\s\"]+"));
            if(parsed[0].equals("current_turn")){;
                game.setCurTurn(Integer.parseInt(parsed[1]));
            }else if(parsed[0].equals("count_items")){
                int count_items = Integer.parseInt(parsed[1]);
                reader.nextLine();
                for(int j=0;j<count_items;j++){
                    reader.nextLine();
                    String name = "";
                    int quantity = -1;
                    for(int k=0;k<2;k++){
                        String[] item = getNonBlank(reader.nextLine().split("[,:\\s\"]+"));
                        if(item[0].equals("name")){
                            name = item[1];
                        }else if(item[0].equals("quantity")){
                            quantity = Integer.parseInt(item[1]);
                        }
                    }
                    shop.setQuantity(name, quantity);
                    reader.nextLine();
                }
                reader.nextLine();
            }
        }
        reader.nextLine();
    }

    public void loadDeck(Player player,Scanner reader,int countActiveDeck){
        reader.nextLine();
        for(int i=0;i<countActiveDeck;i++){
            reader.nextLine();
            int idx=-1;
            String name="";
            for(int j=0;j<2;j++){
                String[] item = getNonBlank(reader.nextLine().split("[\",:\\s]+"));
                // for(String ite:item){
                //     System.out.println(ite);
                // }
                if(item[0].equals("location")){
                    idx = item[1].charAt(0)-'A'; 
                }else if(item[0].equals("name")){
                    name = item[1];
                }
            }
            try {
                player.addCardInDeck(GameContext.createObject(name), idx);
            } catch (GameException e) {
                e.printStackTrace();
            }
            reader.nextLine();
        }
        reader.nextLine();
    }

    public void loadItems(LivingThing liv,int countItems, Scanner reader){
        reader.nextLine();
        for(int i=0;i<countItems;i++){
            reader.nextLine();
            String[] item = getNonBlank(reader.nextLine().split("[\",:\\s]+"));
            // for(String ite:item){
            //     System.out.println(ite);
            // }
            if(item[1].equals("ACCELERATE")||item[1].equals("DELAY")){
                liv.addItem((Item)GameContext.createObject(item[1]));
            }else{
                Item objItem = (Item)GameContext.createObject(item[1]);
                objItem.useEffect(liv);
            }
            reader.nextLine();
        }
        reader.nextLine();
    }

    public void loadField(Player player,Scanner reader,int countActiveField){
        reader.nextLine();
        for(int i=0;i<countActiveField;i++){
            reader.nextLine();
            Pair<Integer,Integer> pos = null;
            GameObject obj = null;
            for(int j=0;j<4;j++){
                String[] parsed = getNonBlank(reader.nextLine().split("[\",:\\s]+"));
                if(parsed[0].equals("location")){
                    pos = Pair.convertTokenToPair(parsed[1]);
                }else if(parsed[0].equals("name")){
                    obj = GameContext.createObject(parsed[1]);
                }else if(parsed[0].equals("count_items")){
                    LivingThing liv = (LivingThing) obj;
                    int countItems = Integer.parseInt(parsed[1]);
                    loadItems(liv, countItems, reader);
                }else if(parsed[0].equals("weight") && obj instanceof Animal animal){
                    animal.setWeight(Integer.parseInt(parsed[1]));
                }else if(parsed[0].equals("age") && obj instanceof Plant plant){
                    plant.setAge(Integer.parseInt(parsed[1]));
                }
            }
            
            reader.nextLine();
            player.addCardInField((LivingThing)obj, pos);
        }
        reader.nextLine();
    }

    public void loadPlayer(Player player,Scanner reader){
        player.resetPlayer();
        reader.nextLine();
        for(int i=0;i<4;i++){
            String[] parsed = getNonBlank(getNonBlank(reader.nextLine().split("[\",:\\s]+")));
            if(parsed[0].equals("gulden")){
                player.setMoney(Integer.parseInt(parsed[1]));
            }else if(parsed[0].equals("deck_slot")){
                player.setDeckSlot(Integer.parseInt(parsed[1]));
            }else if(parsed[0].equals("count_active_deck")){
                int countActiveDeck = Integer.parseInt(parsed[1]);
                loadDeck(player, reader, countActiveDeck);
            }else if(parsed[0].equals("count_active_field")){
                int countActiveField = Integer.parseInt(parsed[1]);
                loadField(player, reader, countActiveField);
            }
        }
        reader.nextLine();
    }

    public void load(String dirpath) throws Exception{
        GameManager game = GameManager.getInstance();
        if(!isValid(dirpath)){
            throw new Exception("State Files Invalid");
        }
        String filePathState = dirpath+File.separator+"gamestate.json";
        String filePathP1 = dirpath+File.separator+"player1.json";
        String filePathP2 = dirpath+File.separator+"player2.json";

        try (Scanner readerGS = new Scanner(new File(filePathState))) {
            loadGameState(readerGS);
        }
        try (Scanner readerP1 = new Scanner(new File(filePathP1))) {
            loadPlayer(game.getPlayer1(),readerP1);
        }
        try (Scanner readerP2 = new Scanner(new File(filePathP2))) {
            loadPlayer(game.getPlayer2(),readerP2);
        }
    }

    public void saveGameState(String dirpath){
        GameManager game = GameManager.getInstance();
        Path filePath = Paths.get(dirpath, "gamestate.json");
        File obj = filePath.toFile();
        Shop shop = Shop.getInstance();
        try{
            obj.createNewFile();
            try(FileWriter writer = new FileWriter(obj)){
                writer.write("{\n");
                writer.write("\"current_turn\": ");
                writer.write(game.getCurTurn()+",\n");

                List<Pair<String,Integer>> items = shop.getItemsReady();
                int countItems = shop.getCountItemsReady();
                writer.write("\"count_shop_items\": ");
                writer.write(countItems+",\n");

                writer.write("\"shop\": [\n");

                for(int i=0;i<countItems;i++){
                    writer.write("{\n");
                    writer.write("\"name\": \""+items.get(i).getFirst()+"\",\n");
                    writer.write("\"quantity\": "+items.get(i).getSecond()+"\n");
                    writer.write("}");
                    if(i<countItems-1){
                        writer.write(",");
                    }
                    writer.write("\n");
                }
                writer.write("]\n");
                writer.write("}");
            };
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveField(FileWriter writer,Player player){
        int countActiveField = player.getCountActiveField();
        List<LivingThing> field = player.getField();
        int countFieldSize = field.size();
        try{
            writer.write("\"count_active_field\": "+countActiveField+",\n");
            writer.write("\"field\": [\n");
            for(int i=0;i<countFieldSize;i++){
                if(!player.getField().get(i).isActive()){
                    continue;
                }
                
                writer.write("{\n");
                String token = Pair.convertPairToToken(Pair.convertIdxToPair(i));
                writer.write("\"location\": \""+token+"\",\n");
                writer.write("\"name\": \""+field.get(i).getName()+"\",\n");
                if(field.get(i) instanceof Animal animal){
                    writer.write("\"weight\": \""+animal.getWeight()+"\",\n");
                }else{
                    Plant plant = (Plant)field.get(i);
                    writer.write("\"age\": \""+plant.getAge()+"\",\n");
                }

                List<Item> items = field.get(i).getItems();
                int countItems = items.size();

                writer.write("\"count_items\": \""+countItems+"\",\n");
                writer.write("\"items\": [\n");
                for(int j=0;j<countItems;j++){
                    writer.write("{\n");
                    writer.write("\"name\": \""+items.get(j).getName()+"\"\n");
                    writer.write("}");
                    if(j<countItems-1){
                        writer.write(",");
                    }
                    writer.write("\n");
                }
                writer.write("]\n");
                writer.write("}");
                if(i<countActiveField-1){
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveDeck(FileWriter writer,Player player){
        List<GameObject> deck = player.getActiveDeck();
        int countDeckSize = deck.size();
        int countActive = player.getCountActiveCard();
        try{
            writer.write("\"count_active_deck\": "+countActive+",\n");
            writer.write("\"active_deck\": [\n");
            int counter = 0;
            for(int i=0;i<countDeckSize;i++){
                if(!deck.get(i).isActive()){
                    continue;
                }
                writer.write("{\n");
                writer.write("\"location\": \""+((char)('A'+i))+"01\",\n");
                writer.write("\"name\": \""+deck.get(i).getName()+"\"\n");
                writer.write("}");
                if(counter<countActive-1){
                    writer.write(",");
                }
                counter++;
                writer.write("\n");
            }
            writer.write("],\n");
        }catch(IOException e){
            e.printStackTrace();
        } 
    }

    public void savePlayer(String dirpath,Player player,int idx){
        Path filePath = Paths.get(dirpath, "player"+idx+".json");
        File obj = filePath.toFile();
        try{
            obj.createNewFile();
            try(FileWriter writer = new FileWriter(obj)){
                writer.write("{\n");
                writer.write("\"gulden\": ");
                writer.write(player.getMoney()+",\n");
                writer.write("\"deck_slot\": ");
                writer.write(player.getDeckSlot()+",\n");
                saveDeck(writer, player);
                saveField(writer, player);
                writer.write("}");
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