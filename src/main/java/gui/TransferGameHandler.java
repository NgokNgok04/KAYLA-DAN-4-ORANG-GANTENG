package gui;

import java.awt.datatransfer.*;
import java.io.IOException;
import javax.swing.*;

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
            if (source.getOwner() != null) {
                System.out.println("Swaping");
                source.getOwner().swapCardInField(source.getPosition(), target.getPosition());
            }
            source.setObject(target.getObject());
            target.setObject(gameObject);
            return true;
        } else if (source.getField() == CardItem.DECK_CARD){
            if (source.getOwner() == target.getOwner() && gameObject instanceof LivingThing thing && !target.getObject().isActive()) {
                int posInDeck = source.getPosition().getSecond();
                target.getOwner().addCardInField((LivingThing) GameContext.createObject(gameObject.getName()), target.getPosition());
                target.setObject(thing);
                try {
                    source.getOwner().removeCardInDeck(posInDeck);
                    source.getsParent().refreshActiveDeck();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (gameObject instanceof Product food && target.getObject() instanceof Animal animal && animal.isActive()) {
                try {
                    animal.eat(food);
                    source.removeObject();
                    if (source.getOwner() != null) {
                        source.getOwner().removeCardInDeck(source.getPosition().getFirst()); // getFirst is the index, getSecond is always 0
                    }
                    return true;
                } catch (GameException e) {
                    e.printMessage();
                    return false;
                }
            } else if (gameObject instanceof Item item && target.getObject().isActive()) {
                if (item.getName().equals("INSTANT_HARVEST")) {
                    item.useEffect((LivingThing) target.getObject());
                    System.out.println("Auto Harvest");
                    // TODO: auto harvest
                }
                item.useEffect((LivingThing) target.getObject());
                target.refreshData();
                source.removeObject();
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
