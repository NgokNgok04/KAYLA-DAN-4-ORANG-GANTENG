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
import javax.swing.JComponent;
import javax.swing.JFrame;
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

    public CardItem(GameObject object, Player owner, boolean field, Pair<Integer, Integer> position, Component parent, boolean swap) {
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

    public void setCardItem(GameObject object, Player owner, boolean field, Pair<Integer, Integer> position, Component parent, boolean swap) {
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
    
    public Component getsParent() {
        return parent;
    }

    public void setParent(JFrame parent) {
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
        this.setSize(new Dimension(120, 168));
        this.setPreferredSize(new Dimension(120, 168));
        this.setMinimumSize(new Dimension(120, 168));
        this.setMaximumSize(new Dimension(120, 168));
        imageLabel.setSize(new Dimension(120, 168));
        imageLabel.setPreferredSize(new Dimension(120, 168));
        imageLabel.setMaximumSize(new Dimension(120, 168));
    }

    public void refreshData() {
//        System.out.println("Masukk");
        if (!object.isActive()) {
//            System.out.println("Kosong");
            imageLabel.setIcon(new ImageIcon(icons.Icon.NOTHING.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
//            nameLabel.setText(" ");
            return;
        }
        if (getField() == FIELD_CARD) {
            if (object instanceof Plant plant && (plant.getAge() >= plant.getAgeToHarvest() || plant.isInstantHarvest())) {
                imageLabel.setIcon(new ImageIcon(plant.getProduct().getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
//                nameLabel.setText(plant.getProduct().getNameParsed());
                return;
            }
        }
//        System.out.println("Active Baby");
        imageLabel.setIcon(new ImageIcon(object.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
//        nameLabel.setText(object.getNameParsed());
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
        if (getField()) {
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    // Add any additional actions to be performed on click
                    onClickActionPerformed(e);
                }
            });
        }
    }
    
    public void onClickActionPerformed(MouseEvent e) {
        if (parent != null && object.isActive()) {
            System.out.println("Card clicked: " + object.getName());
            parent.setEnabled(false);
//            this.nameLabel.setText(object.getNameParsed());
            new PopUpDetail(this).setVisible(true);
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

        imageLabel = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMaximumSize(new java.awt.Dimension(140, 168));
        setMinimumSize(new java.awt.Dimension(140, 168));
        setName(""); // NOI18N
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(140, 168));
        setLayout(new java.awt.BorderLayout());

        imageLabel.setBackground(new java.awt.Color(118, 120, 116));
        imageLabel.setMaximumSize(new java.awt.Dimension(120, 168));
        imageLabel.setMinimumSize(new java.awt.Dimension(120, 168));
        imageLabel.setPreferredSize(new java.awt.Dimension(120, 168));
        imageLabel.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                imageLabelAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        add(imageLabel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void imageLabelAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_imageLabelAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_imageLabelAncestorAdded

    private boolean swap;
    private Pair<Integer, Integer> position;
    private Player owner;
    private Component parent;
    private boolean field;
    private GameObject object;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    // End of variables declaration//GEN-END:variables
}
