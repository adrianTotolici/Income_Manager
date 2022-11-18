package eu.IncomeManager.GUI.AbstractComponents;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by adrian on 26.03.2014.
 */
public class ChangeRowColor extends DefaultTableCellRenderer implements TableCellRenderer {
    private TableCellRenderer tableCellRenderer;

    public ChangeRowColor(TableCellRenderer tableCellRenderer){
        this.tableCellRenderer=tableCellRenderer;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        String val=(String) value;
        Component c=tableCellRenderer.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
        if (c!=null){
            if (val.equals("green")){
                c.setBackground(Color.green);
            }
            if (val.equals("red")){
                c.setBackground(Color.red);
            }
            if(val.equals("yellow")){
                c.setBackground(Color.yellow);
            }
            return c;
        }else return null;
    }
}
