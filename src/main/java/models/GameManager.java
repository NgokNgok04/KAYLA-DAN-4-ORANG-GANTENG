package models;

import java.util.ArrayList;

public class GameManager {
    private Integer currentTurn;
    private Player player1;
    private Player player2;
    private Shop shop;
    // private List<FileLoader> listFileLoader;
    // private BearAttack BearAttack;

    GameManager(Player p1, Player p2, Shop shop){
        currentTurn = 1;
        player1 = p1;
        player2 = p2;
        this.shop = shop;
        // listFileLoader = new ArrayList<>();
        // bearAttack = null;
    }



}
