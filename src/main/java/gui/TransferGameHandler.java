package gui;

import java.awt.datatransfer.*;
import java.io.IOException;
import javax.swing.*;
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
            System.out.println("Data received: "+gameObject.getName());
        } catch (UnsupportedFlavorException | IOException e) {
            return false;
        }

        CardItem target = (CardItem) supp.getComponent();

        CardItem parent = (CardItem) gameObject.getParent();

        System.out.println("Target: "+target.getObject().getName());
        System.out.println("Parent: "+parent.getObject().getName());

        if (parent == target) {
            System.out.println("Sama");
            return true;
        }

        parent.setObject(target.getObject());
        target.setObject(gameObject);

        return true;
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
