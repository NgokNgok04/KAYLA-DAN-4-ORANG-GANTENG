/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;


import models.*;
import utils.Pair;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
/**
 *
 * @author Suthasoma
 */
public class CardItem extends JPanel {

    public static boolean FIELD_CARD = true;
    public static boolean DECK_CARD = false;
    /**
     * Creates new form FieldItem
     */
    public CardItem() {
        initComponents();
        initPreferredSize();
        this.owner = null;
        this.field = FIELD_CARD;
        this.position = new Pair<>(0,0);
        this.swap = false;
        this.parent = null;
        addActionReader();
        setObject(new Herbivore("SAPI"));
        object.setActive(false);
        refreshData();
        addDragListener();
        setTransferHandler(new TransferGameHandler());
    }

    public CardItem(GameObject object, Player owner, boolean field, Pair<Integer, Integer> position, MainFrame parent, boolean swap) {
        initComponents();
        initPreferredSize();
        this.owner = owner;
        this.field = field;
        this.position = position;
        this.parent = parent;
        this.swap = swap;
        addActionReader();
        setObject(object);
        addDragListener();
        setTransferHandler(new TransferGameHandler());
    }

    public void setCardItem(GameObject object, Player owner, boolean field, Pair<Integer, Integer> position, MainFrame parent, boolean swap) {
        initComponents();
        initPreferredSize();
        this.owner = owner;
        this.field = field;
        this.position = position;
        this.parent = parent;
        this.swap = swap;
        setObject(object);
    }

    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject object) {
        this.object = object;
        this.object.setParent(this);
        refreshData();
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean getField() {
        return field;
    }

    public void setField(boolean field) {
        this.field = field;
    }
    
    public MainFrame getsParent() {
        return parent;
    }

    public void setParent(MainFrame parent) {
        this.parent = parent;
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
    }

    public boolean isSwap() {
        return swap;
    }

    public void setSwap(boolean swap) {
        this.swap = swap;
    }

    public void removeObject() {
        setObject(new Herbivore("SAPI"));
        this.object.setActive(false);
        refreshData();
    }

    public void initPreferredSize() {
        this.setSize(new Dimension(110, 140));
        this.setPreferredSize(new Dimension(110,140));
        this.setMinimumSize(new Dimension(110, 140));
        this.setMaximumSize(new Dimension(110, 140));
        imageLabel.setSize(new Dimension(113, 95));
        imageLabel.setPreferredSize(new Dimension(113,95));
        imageLabel.setMaximumSize(new Dimension(113, 95));
    }

    public void updateNameLabel(){
        nameLabel.setText(object.getNameCard(true));
    }
    public void refreshData() {
//        System.out.println("Masukk");
        System.out.println(object.getName() + " " + getField());
        if (!object.isActive()) {
//            System.out.println("Kosong");
            imageLabel.setIcon(new ImageIcon(icons.Icon.NOTHING.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
            nameLabel.setText(" ");
            return;
        }
        if (getField() == FIELD_CARD) {

            if (object instanceof Plant plant && (plant.getAge() >= plant.getAgeToHarvest() || plant.isInstantHarvest())) {
                imageLabel.setIcon(new ImageIcon(plant.getProduct().getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
                nameLabel.setText(plant.getProduct().getNameParsed() + " (READY)");
                return;
            }

            nameLabel.setText(object.getNameCard(true));

        } else {
            nameLabel.setText(object.getNameCard(false));
        }
//        System.out.println("Active Baby");
        imageLabel.setIcon(new ImageIcon(object.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));


//        System.out.println("Settled");
    }

    public void addDragListener() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                CardItem parent = (CardItem) e.getSource();
                parent.putClientProperty("transfer.data", parent.getObject());
                TransferHandler handler = parent.getTransferHandler();
                handler.exportAsDrag(parent, e, TransferHandler.MOVE);
            }
        });
    }

    public void addActionReader() {
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    // Add any additional actions to be performed on click
                    onClickActionPerformed(e);
                }
            });
    }
    
    public void onClickActionPerformed(MouseEvent e) {
        System.out.println("Object cliked "+object.isActive() + " name " + object.getName());
        if (object.isActive()) {
            if (getField() == FIELD_CARD){
//                System.out.println("Card clicked: " + object.getName());
                parent.setEnabled(false);
                this.nameLabel.setText(object.getNameParsed());
                new PopUpDetail(this).setVisible(true);
            } else {
                if (object instanceof Product product) {
                    if (JOptionPane.showConfirmDialog(parent, "Yakin jual barang?") == JOptionPane.YES_OPTION){
                        try {
                            owner.sell(position.getSecond());
                            parent.refreshActiveDeck();
                            parent.refreshCoin();
                        } catch (Exception exp) {
                            JOptionPane.showMessageDialog(parent, "Maaf, terjadi error");
                        }
                    }
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedPane1 = new gui.RoundedPane();
        jPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        roundedPane1.setBackground(new Color(0.392f, 0.2f, 0.0431f, 0.97f));
        roundedPane1.setRoundBottomLeft(25);
        roundedPane1.setRoundBottomRight(25);
        roundedPane1.setRoundTopLeft(25);
        roundedPane1.setRoundTopRight(25);

        jPanel1.setOpaque(false);

        nameLabel.setFont(new java.awt.Font("Segoe Print", 0, 10)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("jLabel2");
        jPanel1.add(nameLabel);

        imageLabel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                imageLabelAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        javax.swing.GroupLayout roundedPane1Layout = new javax.swing.GroupLayout(roundedPane1);
        roundedPane1.setLayout(roundedPane1Layout);
        roundedPane1Layout.setHorizontalGroup(
            roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        roundedPane1Layout.setVerticalGroup(
            roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPane1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(roundedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void imageLabelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_imageLabelAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_imageLabelAncestorAdded

    private boolean swap;
    private Pair<Integer, Integer> position;
    private Player owner;
    private MainFrame parent;
    private boolean field;
    private GameObject object;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel;
    private gui.RoundedPane roundedPane1;
    // End of variables declaration//GEN-END:variables
}
