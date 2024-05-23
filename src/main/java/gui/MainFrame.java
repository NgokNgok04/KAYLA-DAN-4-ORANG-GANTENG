/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import gamexception.GameException;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Random;

import icons.Icon;
import java.util.Objects;
import javax.imageio.ImageIO;
import models.*;
import utils.*;

/**
 *
 * @author Suthasoma
 */
public class MainFrame extends javax.swing.JFrame {

    private ArrayList<CardItem> field = new ArrayList<>();
    private ArrayList<CardItem> deck = new ArrayList<>();

    private GameManager game;
    private Player currPlayer;
    private ClockTime clock;

    /**
     * Creates new form NewJFrame
     */
    public MainFrame() {
        backGround = Icon.BACKGROUND;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon icon = new ImageIcon(getClass().getResource("/logos/icon.png"));
        setIconImage(icon.getImage());
        initGameManager();
        initiateCards();
        refreshGame();
        clock = new ClockTime(clockLabel);
        clock.start();
    }

    public void initiateCards() {

        field.add(cardItem1);
        field.add(cardItem2);
        field.add(cardItem3);
        field.add(cardItem4);
        field.add(cardItem5);
        field.add(cardItem6);
        field.add(cardItem7);
        field.add(cardItem8);
        field.add(cardItem9);
        field.add(cardItem10);
        field.add(cardItem11);
        field.add(cardItem12);
        field.add(cardItem13);
        field.add(cardItem14);
        field.add(cardItem15);
        field.add(cardItem16);
        field.add(cardItem17);
        field.add(cardItem18);
        field.add(cardItem19);
        field.add(cardItem20);

        for (int i = 0; i < 20; i ++) {
            field.get(i).setPosition(new Pair<>(i/5, i%5));
            if (i < 10) {
                field.get(i).setObject(new Herbivore("SAPI"));
            } else if (new Random().nextInt(2) == 0) {
                field.get(i).setObject(new Carnivore());
            } else {
                field.get(i).setObject(new Plant("BIJI_STROBERI"));
            }
        }

        deck.add(cardItem21);
        deck.add(cardItem22);
        deck.add(cardItem23);
        deck.add(cardItem24);
        deck.add(cardItem25);
        deck.add(cardItem26);

        for (int i = 0; i < 6; i++) {
            deck.get(i).setPosition(new Pair<>(0, i%6));
        }
    }
    
    public void refreshCoin() {
        player1Coin.setText(Integer.toString(game.getPlayer1().getMoney()));
        player2Coin.setText(Integer.toString(game.getPlayer2().getMoney()));
    }

    public void initGameManager() {
        this.game = GameManager.getInstance();
    }

    public void refreshGame() {
        int i = 0;
        for (LivingThing thing : game.getCurPlayer().getField()) {
            field.get(i).setObject(thing);
            field.get(i).setOwner(game.getCurPlayer());
            field.get(i).setSwap(true);
            i++;
        }
        i = 0;
        for (GameObject thing : game.getCurPlayer().getActiveDeck()) {
            deck.get(i).setObject(thing);
            deck.get(i).setOwner(game.getCurPlayer());
            i++;
        }
        turnNumber.setText(Integer.toString(game.getCurTurn()));
        refreshCoin();
        refreshPlayer();
        shuffle();
    }
    
    public void refreshPlayer() {
        if (game.getCurPlayer() == game.getPlayer1()) {
            playerOne.setBackground(new java.awt.Color(0.3f, 0.89f, 0.96f, 0.7f));
            playerTwo.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        } else {
            playerTwo.setBackground(new java.awt.Color(0.3f, 0.89f, 0.96f, 0.7f));
            playerOne.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        }
    }

    public void bearAttack() {
        if (BearAttack.isAttack()) {
            new BearAttack(game.getCurPlayer(), field, this, bearAttackLabel).start();
            attackLabel.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        }
    }
    
    public void bearAttackDone() {
        attackLabel.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.0f));
    }

    public void refreshActiveDeck() {
        int i = 0;
        for (GameObject thing : game.getCurPlayer().getActiveDeck()) {
            deck.get(i).setObject(thing);
            deck.get(i).setOwner(game.getCurPlayer());
            i++;
        }
    }

    public void shuffle() {
        new ShuffleCard((ArrayList<GameObject>) game.getCurPlayer().shuffleCard(), game.getCurPlayer().getMaxShuflleCount(), this, game.getCurPlayer()).setVisible(true);
        this.setEnabled(false);
    }

    public void changeFieldToEnemy() {
        int i = 0;
        for (LivingThing thing : game.getEnemyPlayer().getField()) {
            field.get(i).setObject(thing);
            field.get(i).setOwner(game.getEnemyPlayer());
            field.get(i).setSwap(false);
            i++;
        }
    }

    public void changeFieldToPlayer() {
        int i = 0;
        for (LivingThing thing : game.getCurPlayer().getField()) {
            field.get(i).setObject(thing);
            field.get(i).setOwner(game.getCurPlayer());
            field.get(i).setSwap(true);
            i++;
        }
    }

    public void next() {
        if (game.next() == null) {
//            TODO: game ending and recreate
            this.setEnabled(false);
            new GameOver(game, this).setVisible(true);
        } else {
            refreshGame();
        }
    }

    public void setAllEnabled(boolean enabled) {
        saveButton.setEnabled(enabled);
        shopButton.setEnabled(enabled);
        pluginLoadButton.setEnabled(enabled);
        nextButton.setEnabled(enabled);
        myFieldButton.setEnabled(enabled);
        loadButton.setEnabled(enabled);
        enemyFieldButton1.setEnabled(enabled);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        softBevelBorder1 = new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED);
        jPanel1 = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backGround != null) {
                    g.drawImage(backGround, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        enemyFieldButton1 = new gui.ButtonRounded();
        loadButton = new gui.ButtonRounded();
        nextButton = new gui.ButtonRounded();
        pluginLoadButton = new gui.ButtonRounded();
        shopButton = new gui.ButtonRounded();
        saveButton = new gui.ButtonRounded();
        myFieldButton = new gui.ButtonRounded();
        fieldPanelParent = new gui.RoundedPane();
        fieldPanel = new javax.swing.JPanel();
        cardItem1 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem2 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem3 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem4 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem5 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem6 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem7 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem8 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem9 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem10 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem11 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem12 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem13 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem14 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem15 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem16 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem17 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem18 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem19 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        cardItem20 = new gui.CardItem(new Carnivore(), null, CardItem.FIELD_CARD, new Pair<Integer, Integer>(0, 0), this, true);
        deckPanelParent = new gui.RoundedPane();
        deckPanel = new javax.swing.JPanel();
        cardItem21 = new gui.CardItem(new InstantHarvest(), null, CardItem.DECK_CARD, new Pair<Integer, Integer>(0, 0), this, false);
        cardItem22 = new gui.CardItem(new Product("SUSU"), null, CardItem.DECK_CARD, new Pair<Integer, Integer>(0, 0), this, false);
        cardItem23 = new gui.CardItem(new Product("LABU"), null, CardItem.DECK_CARD, new Pair<Integer, Integer>(0, 0), this, false);
        cardItem24 = new gui.CardItem(new Destroy(), null, CardItem.DECK_CARD, new Pair<Integer, Integer>(0, 0), this, false);
        cardItem25 = new gui.CardItem(new Product("SIRIP_HIU"), null, CardItem.DECK_CARD, new Pair<Integer, Integer>(0, 0), this, false);
        cardItem26 = new gui.CardItem(new Trap(), null, CardItem.DECK_CARD, new Pair<Integer, Integer>(0, 0), this, false);
        roundedPane1 = new gui.RoundedPane();
        turnNumber = new javax.swing.JLabel();
        turnNumber1 = new javax.swing.JLabel();
        playerOne = new gui.RoundedPane();
        jLabel4 = new javax.swing.JLabel();
        playerTwo = new gui.RoundedPane();
        jLabel5 = new javax.swing.JLabel();
        roundedPane3 = new gui.RoundedPane();
        player1Coin = new javax.swing.JLabel();
        roundedPane5 = new gui.RoundedPane();
        player2Coin = new javax.swing.JLabel();
        buttonRounded1 = new gui.ButtonRounded();
        roundedPane2 = new gui.RoundedPane();
        clockLabel = new javax.swing.JLabel();
        attackLabel = new gui.RoundedPane();
        bearAttackLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setOpaque(false);

        enemyFieldButton1.setText("Ladang Lawan");
        enemyFieldButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enemyFieldButton1ActionPerformed(evt);
            }
        });

        loadButton.setText("Load State");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        nextButton.setText("NEXT");
        nextButton.setRadius(40);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        pluginLoadButton.setText("Load Plugin");
        pluginLoadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pluginLoadButtonActionPerformed(evt);
            }
        });

        shopButton.setText("Shop");
        shopButton.setRadius(40);
        shopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shopButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save State");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        myFieldButton.setText("Ladangku");
        myFieldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myFieldButtonActionPerformed(evt);
            }
        });

        fieldPanelParent.setBackground(new Color(0.0862f, 0.953f, 0.86f, 0.0f));
        fieldPanelParent.setRoundBottomLeft(25);
        fieldPanelParent.setRoundBottomRight(25);
        fieldPanelParent.setRoundTopLeft(25);
        fieldPanelParent.setRoundTopRight(25);
        fieldPanelParent.setSize(new Dimension(592, 612));

        fieldPanel.setOpaque(false);
        fieldPanel.add(cardItem1);
        fieldPanel.add(cardItem2);
        fieldPanel.add(cardItem3);
        fieldPanel.add(cardItem4);
        fieldPanel.add(cardItem5);
        fieldPanel.add(cardItem6);
        fieldPanel.add(cardItem7);
        fieldPanel.add(cardItem8);
        fieldPanel.add(cardItem9);
        fieldPanel.add(cardItem10);
        fieldPanel.add(cardItem11);
        fieldPanel.add(cardItem12);
        fieldPanel.add(cardItem13);
        fieldPanel.add(cardItem14);
        fieldPanel.add(cardItem15);
        fieldPanel.add(cardItem16);
        fieldPanel.add(cardItem17);
        fieldPanel.add(cardItem18);
        fieldPanel.add(cardItem19);
        fieldPanel.add(cardItem20);

        fieldPanelParent.setBordered(false);

        javax.swing.GroupLayout fieldPanelParentLayout = new javax.swing.GroupLayout(fieldPanelParent);
        fieldPanelParent.setLayout(fieldPanelParentLayout);
        fieldPanelParentLayout.setHorizontalGroup(
            fieldPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fieldPanelParentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        fieldPanelParentLayout.setVerticalGroup(
            fieldPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fieldPanelParentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        deckPanelParent.setBackground(new Color(0.89f, 0.086f, 0.9531f, 0.0f));
        deckPanelParent.setRoundBottomLeft(25);
        deckPanelParent.setRoundBottomRight(25);
        deckPanelParent.setRoundTopLeft(25);
        deckPanelParent.setRoundTopRight(25);
        deckPanelParent.setBordered(false);

        deckPanel.setOpaque(false);
        deckPanel.add(cardItem21);
        deckPanel.add(cardItem22);
        deckPanel.add(cardItem23);
        deckPanel.add(cardItem24);
        deckPanel.add(cardItem25);
        deckPanel.add(cardItem26);

        javax.swing.GroupLayout deckPanelParentLayout = new javax.swing.GroupLayout(deckPanelParent);
        deckPanelParent.setLayout(deckPanelParentLayout);
        deckPanelParentLayout.setHorizontalGroup(
            deckPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deckPanelParentLayout.createSequentialGroup()
                .addComponent(deckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        deckPanelParentLayout.setVerticalGroup(
            deckPanelParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deckPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        roundedPane1.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        roundedPane1.setRoundBottomLeft(80);
        roundedPane1.setRoundBottomRight(80);
        roundedPane1.setRoundTopLeft(80);
        roundedPane1.setRoundTopRight(80);
        roundedPane1.setBordered(false);

        turnNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turnNumber.setText("0");
        turnNumber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        turnNumber1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        turnNumber1.setText("TURN");
        turnNumber1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout roundedPane1Layout = new javax.swing.GroupLayout(roundedPane1);
        roundedPane1.setLayout(roundedPane1Layout);
        roundedPane1Layout.setHorizontalGroup(
            roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(turnNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(turnNumber1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addContainerGap())
        );
        roundedPane1Layout.setVerticalGroup(
            roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPane1Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(turnNumber1)
                .addGap(0, 0, 0)
                .addComponent(turnNumber)
                .addGap(27, 27, 27))
        );

        playerOne.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        playerOne.setRoundBottomLeft(30);
        playerOne.setRoundBottomRight(30);
        playerOne.setRoundTopLeft(30);
        playerOne.setRoundTopRight(30);
        playerOne.setBordered(false);

        jLabel4.setText("Player 1");

        javax.swing.GroupLayout playerOneLayout = new javax.swing.GroupLayout(playerOne);
        playerOne.setLayout(playerOneLayout);
        playerOneLayout.setHorizontalGroup(
            playerOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerOneLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        playerOneLayout.setVerticalGroup(
            playerOneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerOneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        playerTwo.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        playerTwo.setRoundBottomLeft(30);
        playerTwo.setRoundBottomRight(30);
        playerTwo.setRoundTopLeft(30);
        playerTwo.setRoundTopRight(30);
        playerTwo.setBordered(false);

        jLabel5.setText("Player 2");

        javax.swing.GroupLayout playerTwoLayout = new javax.swing.GroupLayout(playerTwo);
        playerTwo.setLayout(playerTwoLayout);
        playerTwoLayout.setHorizontalGroup(
            playerTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerTwoLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        playerTwoLayout.setVerticalGroup(
            playerTwoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(playerTwoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundedPane3.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        roundedPane3.setRoundBottomLeft(30);
        roundedPane3.setRoundBottomRight(30);
        roundedPane3.setRoundTopLeft(30);
        roundedPane3.setRoundTopRight(30);
        roundedPane3.setBordered(false);

        player1Coin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttonicon/Dollar Coin_1.png"))); // NOI18N
        player1Coin.setText("jLabel2");

        javax.swing.GroupLayout roundedPane3Layout = new javax.swing.GroupLayout(roundedPane3);
        roundedPane3.setLayout(roundedPane3Layout);
        roundedPane3Layout.setHorizontalGroup(
            roundedPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPane3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(player1Coin, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundedPane3Layout.setVerticalGroup(
            roundedPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player1Coin, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        roundedPane5.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        roundedPane5.setRoundBottomLeft(30);
        roundedPane5.setRoundBottomRight(30);
        roundedPane5.setRoundTopLeft(30);
        roundedPane5.setRoundTopRight(30);
        roundedPane5.setBordered(false);

        player2Coin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttonicon/Dollar Coin_1.png"))); // NOI18N
        player2Coin.setText("jLabel3");

        javax.swing.GroupLayout roundedPane5Layout = new javax.swing.GroupLayout(roundedPane5);
        roundedPane5.setLayout(roundedPane5Layout);
        roundedPane5Layout.setHorizontalGroup(
            roundedPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPane5Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(player2Coin, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        roundedPane5Layout.setVerticalGroup(
            roundedPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(player2Coin, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addContainerGap())
        );

        buttonRounded1.setBackground(new Color(0.2f, 0.8f, 0.9f, 0.1f));
        buttonRounded1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttonicon/exitbtn.png"))); // NOI18N
        buttonRounded1.setColor(new Color(0.7f, 0.9f, 0.9f, 0.3f));
        buttonRounded1.setColorClick(new Color(0.2f, 0.8f, 0.9f, 0.3f));
        buttonRounded1.setColorOver(new Color(0.8f, 0.9f, 0.3f, 0.3f));
        buttonRounded1.setRadius(46);
        buttonRounded1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRounded1ActionPerformed(evt);
            }
        });

        roundedPane2.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.7f));
        roundedPane2.setRoundBottomLeft(20);
        roundedPane2.setRoundBottomRight(20);
        roundedPane2.setRoundTopLeft(20);
        roundedPane2.setRoundTopRight(20);
        roundedPane2.setBordered(false);

        clockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout roundedPane2Layout = new javax.swing.GroupLayout(roundedPane2);
        roundedPane2.setLayout(roundedPane2Layout);
        roundedPane2Layout.setHorizontalGroup(
            roundedPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPane2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        roundedPane2Layout.setVerticalGroup(
            roundedPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPane2Layout.createSequentialGroup()
                .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        attackLabel.setRoundBottomLeft(20);
        attackLabel.setRoundBottomRight(20);
        attackLabel.setRoundTopLeft(20);
        attackLabel.setBackground(new java.awt.Color(1.0f, 1.0f, 1.0f, 0.0f));
        attackLabel.setRoundTopRight(20);
        attackLabel.setBordered(false);

        javax.swing.GroupLayout attackLabelLayout = new javax.swing.GroupLayout(attackLabel);
        attackLabel.setLayout(attackLabelLayout);
        attackLabelLayout.setHorizontalGroup(
            attackLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attackLabelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bearAttackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );
        attackLabelLayout.setVerticalGroup(
            attackLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bearAttackLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(myFieldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enemyFieldButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(shopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pluginLoadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(playerOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playerTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roundedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(23, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(buttonRounded1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(roundedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(141, 141, 141))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(deckPanelParent, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldPanelParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(attackLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(roundedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(shopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(enemyFieldButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(myFieldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pluginLoadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(deckPanelParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(roundedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(playerOne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(playerTwo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(roundedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(302, 302, 302)
                                .addComponent(buttonRounded1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fieldPanelParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(roundedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(attackLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void pluginLoadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pluginLoadButtonActionPerformed
        // TODO add your handling code here:    
        this.setEnabled(false);
        new PluginModal(this).setVisible(true);
    }//GEN-LAST:event_pluginLoadButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new LoadModal(this).setVisible(true);
    }//GEN-LAST:event_loadButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        new SaveModal(this).setVisible(true);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void shopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shopButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Welcome to Shop");
        this.setEnabled(false);
        if (Shop.getInstance().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Toko sedang kosong");
            this.setEnabled(true);
            return;
        }
        new GameShop(game.getCurPlayer(), this).setVisible(true);
    }//GEN-LAST:event_shopButtonActionPerformed

    private void enemyFieldButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enemyFieldButton1ActionPerformed
        // TODO add your handling code here:
        changeFieldToEnemy();
        System.out.println("Ke Ladang Lawan");
    }//GEN-LAST:event_enemyFieldButton1ActionPerformed

    private void myFieldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myFieldButtonActionPerformed
        // TODO add your handling code here:
        changeFieldToPlayer();
        System.out.println("Balik");
    }//GEN-LAST:event_myFieldButtonActionPerformed

    private void buttonRounded1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRounded1ActionPerformed
        // TODO add your handling code here:
        clock.interrupt();
        System.exit(0);
    }//GEN-LAST:event_buttonRounded1ActionPerformed

    private Image backGround;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.RoundedPane attackLabel;
    private javax.swing.JLabel bearAttackLabel;
    private gui.ButtonRounded buttonRounded1;
    private gui.CardItem cardItem1;
    private gui.CardItem cardItem10;
    private gui.CardItem cardItem11;
    private gui.CardItem cardItem12;
    private gui.CardItem cardItem13;
    private gui.CardItem cardItem14;
    private gui.CardItem cardItem15;
    private gui.CardItem cardItem16;
    private gui.CardItem cardItem17;
    private gui.CardItem cardItem18;
    private gui.CardItem cardItem19;
    private gui.CardItem cardItem2;
    private gui.CardItem cardItem20;
    private gui.CardItem cardItem21;
    private gui.CardItem cardItem22;
    private gui.CardItem cardItem23;
    private gui.CardItem cardItem24;
    private gui.CardItem cardItem25;
    private gui.CardItem cardItem26;
    private gui.CardItem cardItem3;
    private gui.CardItem cardItem4;
    private gui.CardItem cardItem5;
    private gui.CardItem cardItem6;
    private gui.CardItem cardItem7;
    private gui.CardItem cardItem8;
    private gui.CardItem cardItem9;
    private javax.swing.JLabel clockLabel;
    private javax.swing.JPanel deckPanel;
    private gui.RoundedPane deckPanelParent;
    private gui.ButtonRounded enemyFieldButton1;
    private javax.swing.JPanel fieldPanel;
    private gui.RoundedPane fieldPanelParent;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private gui.ButtonRounded loadButton;
    private gui.ButtonRounded myFieldButton;
    private gui.ButtonRounded nextButton;
    private javax.swing.JLabel player1Coin;
    private javax.swing.JLabel player2Coin;
    private gui.RoundedPane playerOne;
    private gui.RoundedPane playerTwo;
    private gui.ButtonRounded pluginLoadButton;
    private gui.RoundedPane roundedPane1;
    private gui.RoundedPane roundedPane2;
    private gui.RoundedPane roundedPane3;
    private gui.RoundedPane roundedPane5;
    private gui.ButtonRounded saveButton;
    private gui.ButtonRounded shopButton;
    private javax.swing.border.SoftBevelBorder softBevelBorder1;
    private javax.swing.JLabel turnNumber;
    private javax.swing.JLabel turnNumber1;
    // End of variables declaration//GEN-END:variables
}
