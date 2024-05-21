/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.util.ArrayList;
import models.Player;
import models.Product;
import java.util.List;
import javax.swing.JOptionPane;
import utils.Pair;


/**
 *
 * @author Suthasoma
 */
public class GameShop extends javax.swing.JFrame {
    
    private ArrayList<Product> cart = new ArrayList<>();
    private Player owner;
    private MainFrame parent;

    /**
     * Creates new form GameShop
     */
    public GameShop(Player owner, List<Pair<Product, Integer>> shops, MainFrame parent) {
        this.owner = owner;
        this.parent = parent;
        initComponents();
        this.setLocationRelativeTo(parent);
        for (Pair<Product, Integer> item : shops) {
            if (item.getSecond() > 0) {
                shopList.add(new ShopItem(this, item.getFirst(), item.getSecond()));
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        shopList = new javax.swing.JPanel();
        buyButton = new gui.ButtonRounded();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        roundedPane1.setBackground(new java.awt.Color(51, 51, 255));
        roundedPane1.setRoundBottomLeft(30);
        roundedPane1.setRoundBottomRight(30);
        roundedPane1.setRoundTopLeft(30);
        roundedPane1.setRoundTopRight(30);

        jLabel1.setFont(new java.awt.Font("Serif", 3, 36)); // NOI18N
        jLabel1.setText("GAME SHOP");

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        shopList.setMaximumSize(new java.awt.Dimension(596, 32767));
        shopList.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        jScrollPane1.setViewportView(shopList);

        buyButton.setText("BELI");
        buyButton.setRadius(40);
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundedPane1Layout = new javax.swing.GroupLayout(roundedPane1);
        roundedPane1.setLayout(roundedPane1Layout);
        roundedPane1Layout.setHorizontalGroup(
            roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPane1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPane1Layout.createSequentialGroup()
                        .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(249, 249, 249))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPane1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))))
        );
        roundedPane1Layout.setVerticalGroup(
            roundedPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(roundedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        // TODO add your handling code here:
        // do buying item
        try {
            owner.buyCartRequest(cart);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Komposisi barang yang dibeli atau jumlah Uang tidak cukup.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buyButtonActionPerformed

    public void addProductCart(Product product) {
        cart.add(product);
    }
    
    public void removeProduct(Product product) {
        cart.remove(product);
    }
    
    public ArrayList<Product> getCart() {
        return cart;
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.ButtonRounded buyButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private gui.RoundedPane roundedPane1;
    private javax.swing.JPanel shopList;
    // End of variables declaration//GEN-END:variables
}
