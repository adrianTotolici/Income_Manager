package eu.IncomeManager.GUI.AbstractComponents;


import eu.IncomeManager.dataBase.Luxuries;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adrian on 13.03.2014.
 */
public class TabelModelLuxuries extends AbstractTableModel {
    List<String> tableHeaders;

    int curretnRow;
    private LinkedList<Luxuries> linkedList=new LinkedList<Luxuries>();

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

    @Override
    public String getColumnName(int column) {
        return tableHeaders.get(column);
    }

    public Luxuries getValueAtRow(int row){
        Luxuries luxuries = linkedList.get(row);
        return luxuries;
    }

    public void initTable(List<String> tableHeaders){
        this.tableHeaders=tableHeaders;
    }

    public void addLuxuries(Luxuries luxuries){
        linkedList.add(luxuries);
        fireTableDataChanged();
    }

    public void removeLuxuries(){
        linkedList.removeLast();
        fireTableDataChanged();
    }

    public void removeAllLuxuries(){
        linkedList=new LinkedList<Luxuries>();
        fireTableDataChanged();
    }
}

