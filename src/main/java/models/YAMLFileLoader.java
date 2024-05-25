package models;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import utils.Pair;

public class YAMLFileLoader implements FileLoader{
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

            Boolean existPlayer1 = files.contains("player1.yaml");
            Boolean existPlayer2 = files.contains("player2.yaml");
            Boolean existGamestate = files.contains("gamestate.yaml");
            return existGamestate && existPlayer1 && existPlayer2;
        }catch(Exception e){
            return false;
        }
    }

    public String getExtension(){
        return "YAML";
    }

    public void loadGameState(String filepath){
        GameManager game = GameManager.getInstance();
        Shop shop = Shop.getInstance();
        try(InputStream inputStream = new FileInputStream(filepath)){
            Yaml yaml = new Yaml();
            Map<String,Object> data = yaml.load(inputStream);

            game.setCurTurn((Integer)data.get("current_turn"));
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> shopItems = (List<Map<String, Object>>) data.get("shop");
            for (Map<String, Object> item : shopItems) {
                shop.setQuantity((String)item.get("name"),(Integer) item.get("quantity"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadPlayer(Player player,String filepath){
        player.resetPlayer();
        try(InputStream inputStream = new FileInputStream(filepath)){
            Yaml yaml = new Yaml();
            Map<String,Object> data = yaml.load(inputStream);
            player.setMoney((Integer)data.get("gulden"));   
            player.setDeckSlot((Integer)data.get("deck_slot"));

            List<Map<String, String>> activeDeck = (List<Map<String, String>>) data.get("active_deck");
            for (Map<String, String> obj : activeDeck) {
                int idx = Pair.convertTokenToPair(obj.get("location")).getSecond();
                System.out.println(obj.get("location"));
                GameObject deckObj = GameContext.createObject(obj.get("name"));
                player.addCardInDeck(deckObj, idx);
            }

            List<Map<String, Object>> field = (List<Map<String, Object>>) data.get("field");
            for (Map<String,Object> living:field){
                Pair<Integer,Integer> pos =Pair.convertTokenToPair((String)living.get("location"));
                GameObject obj = GameContext.createObject((String)living.get("name"));
                LivingThing livingObj = (LivingThing)obj;

                if(livingObj instanceof Animal animal){
                    animal.setWeight((Integer)(living.get("weight")));
                }else{
                    Plant plant = (Plant)obj;
                    plant.setAge((Integer)(living.get("age")));
                }
                List<Map<String, String>> items = (List<Map<String, String>>) living.get("items");
                for(Map<String,String> itemName:items){
                    Item item = (Item) GameContext.createObject(itemName.get("name"));
                    if(item instanceof Accelerate || item instanceof Delay){
                        livingObj.addItem(item);
                    }else{
                        item.useEffect(livingObj);
                    }
                }
                player.addCardInField(livingObj, pos);
            }
        }catch(Exception e){
            e.printStackTrace();
        }            
    }

    public void load(String dirpath) throws Exception{
        GameManager game = GameManager.getInstance();
        Shop shop = Shop.getInstance();
        shop.resetShop();
        if(!isValid(dirpath)){
            throw new Exception("State Files Invalid");
        }
        String filePathState = dirpath+File.separator+"gamestate.yaml";
        String filePathP1 = dirpath+File.separator+"player1.yaml";
        String filePathP2 = dirpath+File.separator+"player2.yaml";

        try (Scanner reader = new Scanner(new File(filePathState))) {
            loadGameState(filePathState);
        }
        try (Scanner reader = new Scanner(new File(filePathP1))) {
            loadPlayer(game.getPlayer1(),filePathP1);
        }
        try (Scanner reader = new Scanner(new File(filePathP2))) {
            loadPlayer(game.getPlayer2(),filePathP2);
        }
    }

    public void saveGameState(String dirpath){
        GameManager game = GameManager.getInstance();
        Path filePath = Paths.get(dirpath, "gamestate.yaml");
        File obj = filePath.toFile();
        Shop shop = Shop.getInstance();

        Map<String,Object> data = new HashMap<>();
        data.put("current_turn", game.getCurTurn());
        data.put("count_items", shop.getCountItemsReady());
        
        List<Map<String,Object>> shopItems = new ArrayList<>();
        ArrayList<Pair<String,Integer>> items = shop.getItemsReady();

        for(Pair<String,Integer> item:items){
            Map<String,Object> itemMap = new HashMap<>();
            itemMap.put("name", item.getFirst());
            itemMap.put("quantity",item.getSecond());
            shopItems.add(itemMap);
        }

        data.put("shop", shopItems);

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);

        Yaml yaml = new Yaml();
        try{
            obj.createNewFile();
            try(FileWriter writer = new FileWriter(obj,false)){
                yaml.dump(data,writer);
            };
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void savePlayer(String dirpath,Player player,int idx){
        Path filePath = Paths.get(dirpath, "player"+idx+".yaml");
        File obj = filePath.toFile();

        Map<String,Object> data = new HashMap<>();
        data.put("gulden", player.getMoney());
        data.put("deck_slot", player.getDeckSlot());
        data.put("count_active_deck", player.getCountActiveCard());
        
        List<Map<String,Object>> deckCards = new ArrayList<>();
        List<GameObject> deck = player.getActiveDeck();
        int countDeckSize = deck.size();

        for(int i=0;i<countDeckSize;i++){
            if(!deck.get(i).isActive()){
                continue;
            }
            Map<String,Object> card = new HashMap<>();
            char row = (char)('A'+i);
            card.put("location", row+"01");
            card.put("name",deck.get(i).getName());
            deckCards.add(card);
        } 
        data.put("active_deck", deckCards);

        List<Map<String,Object>> fieldCards = new ArrayList<>();
        List<LivingThing> field = player.getField();
        int countActiveField = player.getCountActiveField();
        int countFieldSize = field.size();

        data.put("count_active_field",countActiveField);
        for(int i=0;i<countFieldSize;i++){
            if(!player.getField().get(i).isActive()){
                continue;
            }
            Map<String,Object> card = new HashMap<>();
            card.put("location", Pair.convertPairToToken(Pair.convertIdxToPair(i)));
            card.put("name",field.get(i).getName());

            if(field.get(i) instanceof Animal animal){
                card.put("weight",animal.getWeight());
            }else{
                Plant plant = (Plant)field.get(i);
                card.put("age",plant.getAge());
            }

            List<Map<String,String>> livingItems = new ArrayList<>();
            List<Item> items = field.get(i).getItems();
            int countItems = items.size();

            card.put("count_items",countItems);
            for(Item item:items){
                Map<String,String> nameItem = new HashMap<>();
                nameItem.put("name",item.getName());
                livingItems.add(nameItem);
            }
            card.put("items", livingItems);
            fieldCards.add(card);
        }
        data.put("field", fieldCards);

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        Yaml yaml = new Yaml();
        try{
            obj.createNewFile();
            try(FileWriter writer = new FileWriter(obj,false)){
                yaml.dump(data,writer);
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