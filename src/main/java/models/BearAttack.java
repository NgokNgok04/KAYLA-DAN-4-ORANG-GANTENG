package models;

import utils.Pair;
import java.util.Random;

public class BearAttack {
    private Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> area;
    private Integer attackDuration;

    public BearAttack(int height,int width){
        Random rand = new Random();
        attackDuration = rand.nextInt(31)+30;

        Integer x1,x2,y1,y2;
        do{
            x1 = rand.nextInt(width);
            x2 = rand.nextInt(width);
            y1 = rand.nextInt(width);
            y2 = rand.nextInt(width);
        }while(x1 != x2 && y1 != y2);

        Pair<Integer,Integer> coor_1 =  new Pair<Integer,Integer>(x1, y1);
        Pair<Integer,Integer> coor_2 =  new Pair<Integer,Integer>(x2, y2);
        area = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(coor_1, coor_2);
    }
}