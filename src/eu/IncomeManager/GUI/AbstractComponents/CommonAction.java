package eu.IncomeManager.GUI.AbstractComponents;

import java.awt.event.ActionEvent;

/**
 * Created by adrian on 15.11.2014.
 */
class CommonAction extends javax.swing.AbstractAction {

    public CommonAction(String text, boolean selected) {
        super(text);
        super.putValue(SELECTED_KEY, selected);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
