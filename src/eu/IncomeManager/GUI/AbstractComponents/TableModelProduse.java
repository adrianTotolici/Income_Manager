package eu.IncomeManager.GUI.AbstractComponents;



import eu.IncomeManager.dataBase.Produse;
import javafx.scene.control.TableCell;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adrian on 20.02.2014.
 */
public class TableModelProduse extends AbstractTableModel{
    List<String> tableHeaders;

    int curretnRow;
    private LinkedList<Produse> linkedList=new LinkedList<Produse>();

    @Override
    public int getRowCount() {
        return linkedList.size();
    }

    @Override
    public int getColumnCount() {
        return tableHeaders.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        curretnRow=rowIndex;
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return linkedList.get(rowIndex).getNume();
            case 2:
                return linkedList.get(rowIndex).getPret();
            case 3:
                return linkedList.get(rowIndex).getCantitate();
            case 4:
                return linkedList.get(rowIndex).getReducere();
        }
        return null;
    }

    public Produse getRowValue(int rowIndex){
        Produse produse = linkedList.get(rowIndex);
        return produse;
    }

    @Override
    public String getColumnName(int column) {
        return tableHeaders.get(column);
    }


    public void initTable(List<String> tableHeaders){
        this.tableHeaders=tableHeaders;
    }

    public void addProduct(Produse produse){
        linkedList.add(produse);
        fireTableDataChanged();
    }

    public void removeLastProduct(){
        linkedList.removeLast();
        fireTableDataChanged();
    }

    public void removeAllProducts(){
        linkedList=new LinkedList<Produse>();
        fireTableDataChanged();
    }

}
