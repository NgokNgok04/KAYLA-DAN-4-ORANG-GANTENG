package gui;

import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;
import icons.Icon;
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
            source.getOwner().swapCardInField(source.getPosition(), target.getPosition());
            source.setObject(target.getObject());
            target.setObject(gameObject);
            return true;
        } else if (source.getField() == CardItem.DECK_CARD){
            // Melakukan penanaman
            try{
                boolean status = source.getOwner().placeDeckToField(source.getPosition().convertPairToIdx(), target.getPosition(), target.getOwner().getField());
                LivingThing pohon = null;
                if(status){
                    pohon = target.getOwner().getFieldItem(target.getPosition().convertPairToIdx());
                    String name = pohon.getName();
                    if(name.equals("BIJI_JAGUNG")){
                        pohon.setImage(Icon.CORN_TREE);
                    }else if(name.equals("BIJI_LABU")){
                        pohon.setImage(Icon.PUMPKIN_TREE);
                    }else{
                        pohon.setImage(Icon.STRAWBERRY_TREE);
                    }
                }
                if(source.getOwner()!=target.getOwner()){
                    source.getsParent().changeFieldToEnemy();
                }else{
                    source.getsParent().changeFieldToPlayer();
                }
                if(pohon!=null){
                    ((CardItem)pohon.getParent()).refreshData();
                }
                source.getsParent().refreshActiveDeck();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(source.getsParent(), e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
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
