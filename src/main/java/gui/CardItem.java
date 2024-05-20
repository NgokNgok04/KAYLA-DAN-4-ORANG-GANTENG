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
        System.out.println(field);
        setObject(object);
        refreshData();
        addDragListener();
        setTransferHandler(new TransferGameHandler());
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
        this.setSize(new Dimension(252, 368));
        this.setMinimumSize(new Dimension(100, 150));
        imageLabel.setSize(new Dimension(99, 130));
    }

    public void refreshData() {
        if (!object.isActive()) {
            imageLabel.setIcon(new ImageIcon(icons.Icon.NOTHING.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
            nameLabel.setText(" ");
            return;
        }
        if (getField() == FIELD_CARD) {
            if (object instanceof Animal animal && animal.getWeight() >= animal.getWeightToHarvest()) {
                imageLabel.setIcon(new ImageIcon(animal.getProduct().getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
                nameLabel.setText(animal.getProduct().getName());
                return;
            }
            if (object instanceof Plant plant && plant.getAge() >= plant.getAgeToHarvest()) {
                imageLabel.setIcon(new ImageIcon(plant.getProduct().getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
                nameLabel.setText(plant.getProduct().getName());
                return;
            }
        }
        imageLabel.setIcon(new ImageIcon(object.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH)));
        nameLabel.setText(object.getName());
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
                    System.out.println("Card clicked: " + object.getName());
                    // Add any additional actions to be performed on click
                    onClickActionPerformed(e);
                    
                }
            });
        }
    }
    
    public void onClickActionPerformed(MouseEvent e) {
        if (parent != null && object.isActive()) {
            parent.setEnabled(false);
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
        jPanel1 = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();

        nameLabel.setText("jLabel2");
        jPanel1.add(nameLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean swap;
    private Pair<Integer, Integer> position;
    private Player owner;
    private Component parent;
    private boolean field;
    private GameObject object;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
