package gui;

import models.GameObject;
import models.LivingThing;
import models.Omnivore;
import models.Player;
import utils.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BearAttack extends Thread{

    public static final Random rand = new Random();

    public static boolean isAttack() {
        return rand.nextInt(100) < 10;
    }

    public static int[][] dimension = new int[][]{{1,2},{1,3},{1,4},{2,1},{2,2},{2,3},{3,1},{3,2},{4,1},{5,1}};

    public static int[] getRandomDimension() {
        int randomIndex = rand.nextInt(dimension.length);
        return dimension[randomIndex];
    }

    public static ArrayList<Pair<Integer, Integer>> getCardPosition() {
        ArrayList<Pair<Integer, Integer>> cardPosition = new ArrayList<>();
        int[] dimens = getRandomDimension();
        int row = rand.nextInt(4-dimens[1]+1);
        int col = rand.nextInt(5-dimens[0]+1);
        int rowMax = row + dimens[1];
        int colMax = col + dimens[0];
        for (int i = row; i < rowMax; i++) {
            for (int j = col; j < colMax; j++) {
                cardPosition.add(new Pair<>(i, j));
            }
        }
        return cardPosition;
    }

    public boolean checkTrap(ArrayList<Pair<Integer, Integer>> cardPosition) {
        for (Pair<Integer, Integer> pos : cardPosition) {
            int idx = pos.convertPairToIdx();
            if (owner.getFieldItem(idx).isActive() && owner.getFieldItem(idx).isTrap()) {
                return true;
            }
        }
        return false;
    }

    private MainFrame parent;
    private Player owner;
    private ArrayList<CardItem> targetCards;
    private JLabel timer;

    public BearAttack(Player player, ArrayList<CardItem> targetCards, MainFrame parent, JLabel timer) {
        this.owner = player;
        this.targetCards = targetCards;
        this.parent = parent;
        this.timer = timer;
    }

    @Override
    public void run() {
        ArrayList<Pair<Integer, Integer>> cardPosition = getCardPosition();
        parent.setAllEnabled(false);
        for (Pair<Integer, Integer> pair : cardPosition) {
            targetCards.get(pair.convertPairToIdx()).setTargetedCard();
        }
        double timeAttack = (double) rand.nextInt(30,61);
        while (!Thread.currentThread().isInterrupted() && timeAttack > 0) {
            timer.setText("Ladang Anda diserang, sisa waktu pemindahan: "+String.format("%.1f", timeAttack)+" detik");
            timer.setForeground(Color.RED);
            timeAttack-=0.1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        if (checkTrap(cardPosition)) {
            if (!owner.isActiveDeckFull()) {
                try {
                    owner.addCardInDeck(new Omnivore("BERUANG"), owner.findEmptyActiveDeckItem());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (Pair<Integer, Integer> pair : cardPosition) {
                LivingThing thing = owner.getFieldItem(pair.convertPairToIdx());
                if (thing.isActive()) {
                    thing.destroy();
                }
            }
        }
        timer.setText(" ");
        parent.changeFieldToPlayer();
        parent.refreshActiveDeck();
        parent.setAllEnabled(true);
        for (Pair<Integer, Integer> pair : cardPosition) {
            targetCards.get(pair.convertPairToIdx()).setNormalCard();
        }
    }
}
