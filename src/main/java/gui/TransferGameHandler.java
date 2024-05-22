package gui;

import java.awt.datatransfer.*;
import java.io.IOException;
import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;

import gamexception.GameException;
import models.*;

public class TransferGameHandler extends TransferHandler {

    public static final DataFlavor GAMEOBJECT_FLAVOR = new DataFlavor(GameObject.class, "GameObject");

    public int getSourceActions(JComponent c) {
        return MOVE;
    }

    protected Transferable createTransferable(JComponent c) {
        GameObject data = (GameObject) c.getClientProperty("transfer.data");
        if (data != null) {
            return new ObjectTransferable(data);
        }
        return null;
    }

    public boolean canImport(TransferSupport supp) {
        return supp.isDataFlavorSupported(GAMEOBJECT_FLAVOR);
    }

    public boolean importData(TransferSupport supp) {
        if (!supp.isDrop()) {
            return false;
        }

        Transferable transferable = supp.getTransferable();

        GameObject gameObject;

        try {
            gameObject = (GameObject) transferable.getTransferData(GAMEOBJECT_FLAVOR);
        } catch (UnsupportedFlavorException | IOException e) {
            return false;
        }

        CardItem target = (CardItem) supp.getComponent();

        CardItem source = (CardItem) gameObject.getParent();

        if (source.equals(target)) {
            return false;
        }

        if (target.getField() == CardItem.DECK_CARD) {
            return false;
        }
        if (source.getField() == CardItem.FIELD_CARD && target.isSwap()) {
            // TODO : add swap field from player
            // Swap posisi pada FIELD
            System.out.println("Swaping");
            source.getOwner().swapCardInField(source.getPosition(), target.getPosition());
            source.setObject(target.getObject());
            target.setObject(gameObject);
            return true;
        } else if (source.getField() == CardItem.DECK_CARD){
            // Melakukan penanaman
            if (gameObject instanceof LivingThing thing) {
                if (source.getOwner() != target.getOwner()) {
                    JOptionPane.showMessageDialog(source.getsParent(), "Tidak dapat dilakukan pada ladang lawan.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if (target.getObject().isActive()) {
                    JOptionPane.showMessageDialog(source.getsParent(), "Tidak bisa, sudah terisi soalnya.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                target.getOwner().addCardInField((LivingThing) GameContext.createObject(gameObject.getName()), target.getPosition());
                target.setObject(target.getOwner().getFieldItem(target.getPosition().convertPairToIdx()));
                try {
                    source.getOwner().removeCardInDeck(source.getPosition().convertPairToIdx());
                    source.getsParent().refreshActiveDeck();
                } catch (Exception ee) {
                    ee.printStackTrace();
                    JOptionPane.showMessageDialog(source.getsParent(), "Errorrr..", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
            }
            // Memberi makan
            if (gameObject instanceof Product food) {
                if (target.getOwner() != source.getOwner()) {
                    JOptionPane.showMessageDialog(source.getsParent(), "Tidak dapat dilakukan pada ladang lawan.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if (target.getObject() instanceof Animal animal) {
                    try {
                        animal.eat(food);
                        source.getOwner().removeCardInDeck(source.getPosition().getSecond());
                        source.getsParent().refreshActiveDeck();
                        return true;
                    } catch (GameException e) {
                        JOptionPane.showMessageDialog(source.getsParent(), "Jenis makanan tidak sesuai.", "Warning", JOptionPane.WARNING_MESSAGE);
                        e.printStackTrace();
                        return false;
                    }
                }
                JOptionPane.showMessageDialog(source.getsParent(), "Heyy ini tanaman!.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            // Memakai item
            if (gameObject instanceof Item item) {
                if (item.getName().equals("DELAY") || item.getName().equals("DESTROY")) {
                    if (source.getOwner() == target.getOwner()) {
                        JOptionPane.showMessageDialog(source.getsParent(), "Pakai ke ladang lawan yaa...", "Warning", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }
                    item.useEffect((LivingThing) target.getObject());
                    source.getsParent().changeFieldToEnemy();
                    try {
                        source.getOwner().removeCardInDeck(source.getPosition().convertPairToIdx());
                    } catch (Exception ee) {
                        ee.printStackTrace();
                        return false;
                    }
                    source.getsParent().refreshActiveDeck();
                    return true;
                }
                if (source.getOwner() != target.getOwner()) {
                    JOptionPane.showMessageDialog(source.getsParent(), "Rugi dong kalau dipakai ke ladang lawan...", "Warning", JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if (item.getName().equals("INSTANT_HARVEST")) {
                    try {
                        source.getOwner().removeCardInDeck(source.getPosition().convertPairToIdx());
                        item.useEffect((LivingThing) target.getObject());
                        target.getOwner().harvestField(target.getPosition());
                        target.getsParent().changeFieldToPlayer();
                        source.getsParent().refreshActiveDeck();
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(source.getsParent(), "Errorrr..", "Warning", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }

                }

                item.useEffect((LivingThing) target.getObject());

                try {
                    source.getOwner().removeCardInDeck(source.getPosition().convertPairToIdx());
                    source.getsParent().refreshActiveDeck();
                } catch (Exception ee) {
                    ee.printStackTrace();
                    return false;
                }
                return true;
            }
        }

        return false;
    }

    private static class ObjectTransferable implements Transferable {
        private final GameObject object;

        public ObjectTransferable(GameObject object) {
            this.object = object;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{GAMEOBJECT_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return GAMEOBJECT_FLAVOR.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return object;
        }
    }
}
