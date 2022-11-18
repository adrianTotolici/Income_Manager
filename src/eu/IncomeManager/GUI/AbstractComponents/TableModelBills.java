package eu.IncomeManager.GUI.AbstractComponents;


import eu.IncomeManager.dataBase.Bills;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adrian on 20.02.2014.
 */
public class TableModelBills extends AbstractTableModel {
    List<String> tableHeaders;

    int curretnRow;
    private LinkedList<Bills> linkedList=new LinkedList<Bills>();

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
                return linkedList.get(rowIndex).getBill_name();
            case 2:
                return linkedList.get(rowIndex).getBill_value();
            default:
                return null;
        }
    }

    public Bills getValueAtRow(int rowIndex){
        Bills bills = linkedList.get(rowIndex);
        return bills;
    }

    @Override
    public String getColumnName(int column) {
        return tableHeaders.get(column);
    }

    public void initTable(List<String> tableHeaders){
        this.tableHeaders=tableHeaders;
    }

    public void addBill(Bills bill){
        linkedList.add(bill);
        fireTableDataChanged();
    }

    public void removeLastBill(){
        linkedList.removeLast();
        fireTableDataChanged();
    }

    public void removeAllBill(){
            linkedList=new LinkedList<Bills>();
        fireTableDataChanged();
    }
}
